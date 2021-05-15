package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class MedallionOfCourageRecipe extends AbstractRecipeCard {
    public static final String ID = makeID(MedallionOfCourageRecipe.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String COMPONENT1 = Platemail.ID;
    public static final String COMPONENT2 = SagesMask.ID;

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = SagesMask.COST;

    public MedallionOfCourageRecipe() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, COMPONENT1, COMPONENT2);
    }


    @Override
    public String getRawDescription() {
        return DESCRIPTION + craftableExtendedDescription();
    }

    @Override
    public AbstractInvokerCard createCard() {
        return new MedallionOfCourage();
    }
}
