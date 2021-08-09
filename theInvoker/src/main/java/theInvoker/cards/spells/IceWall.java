package theInvoker.cards.spells;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.SlowPower;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class IceWall extends AbstractSpellCard {
    public static final String ID = makeID(IceWall.class.getSimpleName());
    public static final String IMG = makeCardPath("Ice_Wall.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0; // TODO Slow plus other block that can be amped
    private static final int BLOCK = 8;

    public IceWall() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = this.block = BLOCK;
        this.exhaust = true;  // TODO add exhaust to description when not invoked?
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // TODO Find slow keyword?  Remove Crystal Nova?
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));

        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
            this.addToBot(new ApplyPowerAction(monster, p, new SlowPower(monster, 0)));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
