package theInvoker.cards.allies;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.AphoticShieldPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class AphoticShield extends AbstractInvokerCard {
    public static final String ID = makeID(AphoticShield.class.getSimpleName());
    public static final String IMG = makeCardPath("Aphotic_Shield.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 6;
    private static final int UPGRADED_MAGIC = 3;

    public AphoticShield() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, this.magicNumber));
        this.addToBot(new ApplyPowerAction(p, p, new AphoticShieldPower(p, this.magicNumber)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_MAGIC);
            initializeDescription();
        }
    }
}

