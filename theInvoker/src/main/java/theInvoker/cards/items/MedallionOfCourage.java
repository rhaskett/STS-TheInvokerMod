package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractFlexibleCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.EnergyPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class MedallionOfCourage extends AbstractFlexibleCard {
    public static final String ID = makeID(MedallionOfCourage.class.getSimpleName());
    public static final String IMG = makeCardPath("Lotus_Orb.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = 1;
    private static final int MAGIC = SagesMask.MAGIC;
    private static final int BLOCK = Platemail.BLOCK;

    public MedallionOfCourage() {
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
        if(m != null) {
            this.addToBot(new RemoveAllBlockAction(m, p));
        }else if(this.target == CardTarget.SELF){
            this.addToBot(new GainBlockAction(p, p, block));
        }

        this.addToBot(new ApplyPowerAction(p, p, new EnergyPower(p, magicNumber), magicNumber));
    }
}
