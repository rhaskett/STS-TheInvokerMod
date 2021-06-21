package theInvoker.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theInvoker.actions.DelayedDamageAction;

public class EndOfTurnHealPower extends CustomInvokerModPower {
	public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(EndOfTurnHealPower.class);
	private final AbstractCreature source;

	public EndOfTurnHealPower(AbstractCreature owner, AbstractCreature source, final int amount) {
		super(STATIC);

		this.owner = owner;
		this.source = source;
		this.amount = amount;
		this.type = PowerType.BUFF;

		updateDescription();
	}

	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (this.owner.currentHealth > 0) {
			if (isPlayer || (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
					&& !AbstractDungeon.getMonsters().areMonstersBasicallyDead())) {
				this.flashWithoutSound();
				this.addToBot(new HealAction(this.owner, this.source, this.amount));
			}
		}
	}

	@Override
	public void updateDescription() {
		description = String.format(DESCRIPTIONS[0], this.amount);
	}
}
