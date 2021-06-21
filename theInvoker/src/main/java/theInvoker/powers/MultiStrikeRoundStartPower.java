package theInvoker.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MultiStrikeRoundStartPower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(MultiStrikeRoundStartPower.class);

    public MultiStrikeRoundStartPower(final AbstractCreature owner, final int amount) {
        super(STATIC);

        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 99) {
            this.amount = 99;
        }
        this.type = PowerType.BUFF;
        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner,
                new MultiStrikePower(this.owner, this.amount)));
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
