package theInvoker.cards.items;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theInvoker.InvokerMod;
import theInvoker.cards.AbstractInvokerCard;

import java.util.TreeSet;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theInvoker.InvokerMod.makeID;

public abstract class AbstractCombinesCard extends AbstractInvokerCard {
    private static final String COMBINES_ID = makeID("Combines");
    private static final CardStrings COMBINES_STRINGS = CardCrawlGame.languagePack.getCardStrings(COMBINES_ID);
    public static final String[] TEXT = COMBINES_STRINGS.EXTENDED_DESCRIPTION;


    public AbstractCombinesCard(final String id,
                                 final String img,
                                 final int cost,
                                 final CardType type,
                                 final CardColor color,
                                 final CardRarity rarity,
                                 final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);

        this.rawDescription = GetRawDescription();
    }

    public String combinesExtendedDescription() {
        TreeSet<String> combinesWith = new TreeSet<>();
        String thisName = languagePack.getCardStrings(this.cardID).NAME;
        boolean itself = false;

        for (AbstractCraftableCard craftable: InvokerMod.getAllCraftableCards()) {
            String name = null;
            if (craftable.firstComponentID.equals(this.cardID))
                name = languagePack.getCardStrings(craftable.secondComponentID).NAME;
            else if (craftable.secondComponentID.equals(this.cardID))
                name = languagePack.getCardStrings(craftable.firstComponentID).NAME;

            if (name != null) {
                if (name.equals(thisName))
                    itself = true;
                else
                    combinesWith.add(name);
            }
        }

        if (itself) {
            if (combinesWith.size() > 0) {
                String joinedString = String.join(TEXT[0], combinesWith);
                return joinedString + TEXT[2] + TEXT[1];
            }
            return TEXT[1];
        }
        else {
            String last = combinesWith.pollLast();
            if (combinesWith.size() > 0) {
                String joinedString = String.join(TEXT[0], combinesWith);
                return joinedString + TEXT[2] + last + TEXT[3];
            }
            return last + TEXT[3];
        }
    }

    public abstract String GetRawDescription();

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}
}