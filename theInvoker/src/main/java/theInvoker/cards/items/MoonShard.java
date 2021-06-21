package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.MultiStrikeRoundStartPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class MoonShard extends AbstractInvokerCard {
    public static final String ID = makeID(MoonShard.class.getSimpleName());
    public static final String IMG = makeCardPath("Power.png"); // TODO picture

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = 2;
    private static final int MAGIC = Hyperstone.MAGIC;

    public MoonShard() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = MAGIC;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new MultiStrikeRoundStartPower(p, this.magicNumber)));
    }
}
