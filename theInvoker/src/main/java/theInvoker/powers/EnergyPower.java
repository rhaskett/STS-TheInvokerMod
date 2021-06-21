package theInvoker.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class EnergyPower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(EnergyPower.class);

    public EnergyPower(AbstractCreature owner, final int amount) {
        super(STATIC);

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        updateDescription();
    }

    public void updateDescription() {
//        public void updateDescription() {
//            description = String.format(DESCRIPTIONS[0], calculateDamage());
//        }

        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[1] + DESCRIPTIONS[4];
        } else {
            this.description = DESCRIPTIONS[0] + DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[3] + DESCRIPTIONS[4];
        }

    }

    public void onEnergyRecharge() {
        this.flash();
        AbstractDungeon.player.gainEnergy(1);

        --this.amount;
        if (this.amount == 0)
            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        else
            this.updateDescription();
    }
}
