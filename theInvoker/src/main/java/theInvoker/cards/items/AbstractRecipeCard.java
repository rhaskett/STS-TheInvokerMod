package theInvoker.cards.items;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theInvoker.InvokerMod.makeID;

public abstract class AbstractRecipeCard extends AbstractInvokerCard {
    // !!!!!! Craftable cards must be manually added to InvokerMod.recipeCards !!!!!!

    private static final String CRAFTABLE_ID = makeID(AbstractRecipeCard.class.getSimpleName());
    private static final CardStrings CRAFTABLE_STRINGS = CardCrawlGame.languagePack.getCardStrings(CRAFTABLE_ID);
    public static final String[] TEXT = CRAFTABLE_STRINGS.EXTENDED_DESCRIPTION;

    public String firstComponentID;
    public String secondComponentID;

    public AbstractRecipeCard(final String id,
                              final String img,
                              final int cost,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target,
                              final String firstComponentID,
                              final String secondComponentID) {

        super(id, img, cost, type, color, rarity, target);
        this.firstComponentID = firstComponentID;
        this.secondComponentID = secondComponentID;
        this.rawDescription = getRawDescription();
        this.initializeDescription();
    }

    public String craftableExtendedDescription() {
        if (firstComponentID.equals(secondComponentID)) {
            String componentName = languagePack.getCardStrings(this.firstComponentID).NAME;
            return TEXT[0] + componentName + TEXT[1];
        }

        String firstComponentName = languagePack.getCardStrings(this.firstComponentID).NAME;
        String secondComponentName = languagePack.getCardStrings(this.secondComponentID).NAME;

        return firstComponentName + TEXT[2] + secondComponentName + TEXT[1];
    }

    public abstract String getRawDescription();

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    public abstract AbstractInvokerCard createCard();

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}

}