package theInvoker.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.PoisonLoseHpAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theInvoker.actions.ShadowPoisonLoseHPAction;

public class ShadowPoisonPower extends CustomInvokerModPower implements HealthBarRenderPower {
	public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ShadowPoisonPower.class);

	protected Color redColor2 = Color.MAGENTA.cpy();
	private final AbstractCreature source;
	private boolean thisTurn = false;

	public ShadowPoisonPower(AbstractCreature owner, AbstractCreature source, final int amount) {
		super(STATIC);

		this.owner = owner;
		this.source = source;
		this.amount = amount;
		this.type = PowerType.DEBUFF;
		this.updateDescription();
		this.isTurnBased = false;
	}

	public void playApplyPowerSfx() { // TODO custom sound?
		CardCrawlGame.sound.play("POWER_POISON", 0.05F);
	}

	@Override
	public void atEndOfTurn(boolean isPlayer) {
		if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
			if(!this.thisTurn)
				this.thisTurn = true;
			else {
				this.flash();
				this.addToBot(new ShadowPoisonLoseHPAction(this.owner, this.source, this.amount,
						AbstractGameAction.AttackEffect.POISON, this.ID));
			}
		}
	}

	// Based on StSLib's TwoAmountPower
	@Override
	public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
		super.renderAmount(sb, x, y, c);
		if (this.thisTurn)
			FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, "*",
				x, y + 15.0F * Settings.scale, this.fontScale, this.redColor2);

	}

	public void updateDescription() {
		if (this.thisTurn)
			this.description = String.format(DESCRIPTIONS[0], this.amount);
		else
			this.description = String.format(DESCRIPTIONS[1], this.amount);
	}

	@Override
    public void stackPower(int stackAmount) {
		this.thisTurn = false;
		this.amount *= 2;
    }

	@Override
	public int getHealthBarAmount() {
		return this.thisTurn ? this.amount : 0;
	}

	@Override
	public Color getColor() {
		return redColor2;
	}
}
