package theInvoker.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IonShellPower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(IonShellPower.class);

    public IonShellPower(AbstractCreature owner, final int amount) {
        super(STATIC);

        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        for (AbstractMonster other : AbstractDungeon.getMonsters().monsters) {
            if (!other.halfDead && !other.isDying && other != this.owner) {
                this.flash();
                this.addToTop(new DamageAction(other, new DamageInfo(this.owner, this.amount, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.LIGHTNING));
            }
        }
    }


    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
