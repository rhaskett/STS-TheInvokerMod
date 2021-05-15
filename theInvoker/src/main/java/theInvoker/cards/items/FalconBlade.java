package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.actions.FastAddTemporaryHPAction;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.EnergyPower;
import theInvoker.powers.VenomPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class FalconBlade extends AbstractInvokerCard {
    public static final String ID = makeID(FalconBlade.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = SagesMask.COST * 2;
    private static final int MAGIC = SagesMask.MAGIC * 2;
    private static final int TEMP_HP = FluffyHat.MAGIC * 2;

    public FalconBlade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = MAGIC;
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new EnergyPower(p, magicNumber), magicNumber));
        this.addToBot(new FastAddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, TEMP_HP));
    }
}
