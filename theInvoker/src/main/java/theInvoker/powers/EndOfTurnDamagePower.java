package theInvoker.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theInvoker.actions.DelayedDamageAction;

public class EndOfTurnDamagePower extends CustomInvokerModPower {
	public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(EndOfTurnDamagePower.class);
	public static final String POWER_ID = STATIC.ID;
	private static long instanceCounter = 0;
	private final AbstractCreature source;
	private final AbstractGameAction.AttackEffect attackEffect;

	public EndOfTurnDamagePower(AbstractCreature owner, AbstractCreature source, final int amount,
								AbstractGameAction.AttackEffect attackEffect) {
		super(STATIC);

		this.owner = owner;
		this.source = source;
		this.amount = amount;
		this.attackEffect = attackEffect;
		if (this.amount >= 999) {
			this.amount = 999;
		}
		this.type = PowerType.DEBUFF;
		ID = POWER_ID + "__" + (++instanceCounter);

		updateDescription();
	}

	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT
				&& !AbstractDungeon.getMonsters().areMonstersBasicallyDead()
				&& this.owner.currentHealth > 0) {
			this.flashWithoutSound();
			this.addToBot(new DelayedDamageAction(this.owner, this.source, this.amount, this.attackEffect, this.ID));
		}
	}

	@Override
	public void updateDescription() {
		description = String.format(DESCRIPTIONS[0], this.amount);
	}
}
