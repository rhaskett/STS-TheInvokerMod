package theInvoker.powers;

import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theInvoker.actions.PlagueWardAction;

public class PlagueWardPower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(PlagueWardPower.class);

    public PlagueWardPower(final AbstractCreature owner, final int amount) {
        super(STATIC);

        this.type = PowerType.BUFF;
        this.owner = owner;
        this.amount = amount;

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        // Deferring the random selection to a separate action is important for preventing multiple Forge Spirits
        // from picking the same enemy and then having some of them attack a corpse.
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new PlagueWardAction(this.amount));
    }
}

