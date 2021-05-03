package theInvoker.cards.spells;

import basemod.abstracts.AbstractCardModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import theInvoker.InvokerMod;

import static theInvoker.InvokerMod.makeID;

public class InvokedCardModifier extends AbstractCardModifier {
    public static String ID = InvokerMod.makeID(InvokedCardModifier.class.getSimpleName());
    private static final String SPELL_CARD_ID = makeID(InvokedCardModifier.class.getSimpleName());
    private static final CardStrings SPELL_STRINGS = CardCrawlGame.languagePack.getCardStrings(SPELL_CARD_ID);
    private static final String TEXT = SPELL_STRINGS.DESCRIPTION;

    public InvokedCardModifier() {
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT  + rawDescription;
    }

    public boolean shouldApply(AbstractCard card) {
        return !card.exhaust && !card.isEthereal;
    }

    public void onInitialApplication(AbstractCard card) {
        card.exhaust = true;
        card.isEthereal = true;
    }

    public void onRemove(AbstractCard card) {
        card.exhaust = false;
        card.isEthereal = false;
    }

    public AbstractCardModifier makeCopy() {
        return new InvokedCardModifier();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
