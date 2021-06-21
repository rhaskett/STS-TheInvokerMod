package theInvoker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theInvoker.cards.spells.AbstractSpellCard;
import theInvoker.relics.StarterRelic;
import theInvoker.vfx.InvokeHelper;

public class InvokeAction extends AbstractGameAction {
    private int discount = 0;

    public InvokeAction() {
        super();
    }
    public InvokeAction(int discount) {
        super();
        this.discount = discount;
    }

    public void update() {
        String key = AbstractSpellCard.invokerOrbKey();
        if(key != null && key.length() == 3) {
            String sortedKey = AbstractSpellCard.sortedOrbKey(key);
            if(!sortedKey.equals(InvokeHelper.lastInvokedKey)) {
                AbstractSpellCard card = AbstractSpellCard.invokedCardFactory(sortedKey, discount, false);
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(card));
                AbstractDungeon.actionManager.addToBottom(new UpdateInvokeAction());

                InvokeHelper.lastInvokedKey = sortedKey;
            }
        }
        this.isDone = true;
    }
}