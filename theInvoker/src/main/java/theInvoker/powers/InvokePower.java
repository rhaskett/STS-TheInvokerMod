package theInvoker.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import theInvoker.actions.InvokeAction;

public class InvokePower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(InvokePower.class);

    public InvokePower(final AbstractCreature owner) {
        super(STATIC);

        this.owner = owner;
        this.type = PowerType.BUFF;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.addToBot(new InvokeAction());
    }
}
