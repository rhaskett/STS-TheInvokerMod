package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.actions.FastAddTemporaryHPAction;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.VenomPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class OrbOfCorrosion extends AbstractInvokerCard {
    public static final String ID = makeID(OrbOfCorrosion.class.getSimpleName());
    public static final String IMG = makeCardPath("Orb_of_Corrosion.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = OrbOfVenom.COST;
    private static final int MAGIC = OrbOfVenom.MAGIC * 2;
    private static final int TEMP_HP = FluffyHat.MAGIC;

    public OrbOfCorrosion() {
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
        this.addToBot(new ApplyPowerAction(p, p, new VenomPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new FastAddTemporaryHPAction(AbstractDungeon.player, AbstractDungeon.player, TEMP_HP));
    }
}
