package theInvoker.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class FalsePromisePower extends CustomInvokerModPower {
	public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(FalsePromisePower.class);
	public static final String POWER_ID = STATIC.ID;

	protected Color redColor2 = Color.RED.cpy();
	private int blocked = 0;
	private static long instanceCounter = 0;

	public FalsePromisePower(AbstractCreature owner, final int amount) {
		super(STATIC);

		ID = POWER_ID + "__" + (++instanceCounter);

		this.owner = owner;
		this.amount = amount;
		this.updateDescription();
		this.type = PowerType.BUFF;
		this.isTurnBased = false;
	}

//	public void playApplyPowerSfx() { // TODO sound?
//		CardCrawlGame.sound.play("POWER_INTANGIBLE", 0.05F);
//	}

	@Override
	public int onAttacked(DamageInfo info, int damageAmount) {
		if (damageAmount > 0) {
			this.blocked += damageAmount;
			this.updateDescription();
		}
		return 0;
	}

	@Override
	public int onLoseHp(int damageAmount) {
		if (damageAmount > 0) {
			this.blocked += damageAmount;
			this.updateDescription();
		}
		return 0;
	}

	@Override
	public void atEndOfRound() {
		if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			if(this.blocked > 0)
				this.addToBot(new ApplyPowerAction(this.owner, this.owner, new EndOfRoundDamagePower(this.owner,
						this.owner, this.blocked, AbstractGameAction.AttackEffect.LIGHTNING)));
			this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
		}
	}

	// Based on StSLib's TwoAmountPower
	@Override
	public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
		super.renderAmount(sb, x, y, c);
		FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.blocked),
				x, y + 15.0F * Settings.scale, this.fontScale, this.redColor2);

	}

	public void updateDescription() {
		this.description = String.format(DESCRIPTIONS[0], this.blocked);
	}
}
