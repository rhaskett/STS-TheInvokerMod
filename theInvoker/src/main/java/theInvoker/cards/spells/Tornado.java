package theInvoker.cards.spells;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theInvoker.InvokerMod;
import theInvoker.actions.RemoveBuffsAction;
import theInvoker.characters.TheInvoker;

public class Tornado extends AbstractSpellCard {
    public static final String ID = InvokerMod.makeID(Tornado.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Tornado.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public Tornado() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new SFXAction("ATTACK_WHIRLWIND"));
        for(AbstractPower power :  m.powers)
            if (power != null && power.type == AbstractPower.PowerType.BUFF)
                this.addToBot(new RemoveSpecificPowerAction(m, p, power));
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
