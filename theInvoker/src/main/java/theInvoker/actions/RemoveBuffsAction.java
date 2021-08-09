package theInvoker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class RemoveBuffsAction extends AbstractGameAction {
    private AbstractCreature c;

    public RemoveBuffsAction(AbstractCreature c) {
        this.c = c;
        this.duration = 0.5F;
        this.actionType = ActionType.SPECIAL;
    }

    public void update() {
        for (AbstractPower p : this.c.powers) {
            if (p.type == AbstractPower.PowerType.DEBUFF) {
                this.addToTop(new RemoveSpecificPowerAction(this.c, this.c, p.ID));
            }
        }
        this.isDone = true;
    }
}