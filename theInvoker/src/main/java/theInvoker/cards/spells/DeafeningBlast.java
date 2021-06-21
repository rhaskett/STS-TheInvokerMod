package theInvoker.cards.spells;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theInvoker.InvokerMod;
import theInvoker.characters.TheInvoker;

public class DeafeningBlast extends AbstractSpellCard {
    public static final String ID = InvokerMod.makeID(DeafeningBlast.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Deafening_Blast.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int AMOUNT = 2;

    public DeafeningBlast() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
         for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
             this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber,
                     false), this.magicNumber));
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
