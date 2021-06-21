package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.EchoShellPower;
import theInvoker.powers.MultiStrikePower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class AssaultCuirass extends AbstractInvokerCard {
    public static final String ID = makeID(AssaultCuirass.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png"); // TODO picture

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = Platemail.COST;
    private static final int MAGIC = Hyperstone.MAGIC + 1;
    private static final int BLOCK = Platemail.BLOCK;

    public AssaultCuirass() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
        this.baseMagicNumber = MAGIC;
        this.exhaust = true;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
        this.addToBot(new ApplyPowerAction(p, p, new MultiStrikePower(p, this.magicNumber)));
    }
}
