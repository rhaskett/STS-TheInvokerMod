package theInvoker.cards.allies;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.ShadowPoisonPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class ShadowPoison extends AbstractInvokerCard {
    public static final String ID = makeID(ShadowPoison.class.getSimpleName());
    public static final String IMG = makeCardPath("Shadow_Poison.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADED_COST = 0;

    public ShadowPoison() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // TODO Sound
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
            if (!monster.isDead && !monster.isDying)
                this.addToBot(new ApplyPowerAction(monster, p, new ShadowPoisonPower(monster, p, this.magicNumber)));
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

