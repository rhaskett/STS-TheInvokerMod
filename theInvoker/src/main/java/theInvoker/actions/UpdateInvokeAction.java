package theInvoker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theInvoker.vfx.InvokeHelper;

public class UpdateInvokeAction extends AbstractGameAction {
    public void update() {
        InvokeHelper.updateInvokes();
        this.isDone = true;
    }
}