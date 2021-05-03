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
public class OrbOfCorrosionRecipe extends AbstractRecipeCard {
    public static final String ID = makeID(OrbOfCorrosionRecipe.class.getSimpleName());
    public static final String IMG = makeCardPath("Orb_of_Corrosion.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String COMPONENT1 = OrbOfVenom.ID;
    public static final String COMPONENT2 = FluffyHat.ID;

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = OrbOfCorrosion.COST;

    public OrbOfCorrosionRecipe() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, COMPONENT1, COMPONENT2);
    }

    @Override
    public String getRawDescription() {
        return DESCRIPTION + craftableExtendedDescription();
    }

    @Override
    public AbstractInvokerCard createCard() {
        return new OrbOfCorrosion();
    }
}
