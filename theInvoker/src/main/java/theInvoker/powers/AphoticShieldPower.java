package theInvoker.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theInvoker.actions.RemoveBuffsAction;

public class AphoticShieldPower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(AphoticShieldPower.class);

    public AphoticShieldPower(AbstractCreature owner, final int amount) {
        super(STATIC);

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;

        updateDescription();
    }

    @Override
    public void atEndOfRound() {
        this.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS
                && info.owner != null && info.owner != this.owner && damageAmount > 0) {

            this.flash();
            this.addToTop(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, this));
            this.addToTop(new DamageAllEnemiesAction(AbstractDungeon.player, this.amount,
                    DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.SHIELD));

        }

        return damageAmount;
    }


    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
