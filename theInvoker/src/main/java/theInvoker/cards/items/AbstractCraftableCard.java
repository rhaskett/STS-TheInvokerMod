package theInvoker.cards.items;

import theInvoker.cards.AbstractInvokerCard;

public abstract class AbstractCraftableCard extends AbstractInvokerCard {
    // Craftable cards must be manually added to InvokerMod.craftableCards

    public String firstComponentID;
    public String secondComponentID;

    public AbstractCraftableCard(final String id,
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
    }

}