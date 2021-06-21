package theInvoker.cards.allies;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.MultiStrikePower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class CullingBlade extends AbstractInvokerCard {
    public static final String ID = makeID(CullingBlade.class.getSimpleName());
    public static final String IMG = makeCardPath("Culling_Blade.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 14;
    private static final int UPGRADE_PLUS_MAGIC = 8;
    private static final int MULTI_ATTACK = 3;

    public CullingBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentHealth <= this.baseMagicNumber) {
            this.addToTop(new InstantKillAction(m));
            this.addToBot(new ApplyPowerAction(p, p, new MultiStrikePower(p, MULTI_ATTACK), MULTI_ATTACK));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}

