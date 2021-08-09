package theInvoker.powers;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.List;

public class MultiStrikeEnemyPower extends CustomInvokerModPower implements CustomSetMovePower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(MultiStrikeEnemyPower.class);
    private final List<DamageInfo> alreadyDone = new ArrayList<>();
    private boolean isUsed = false;

    public MultiStrikeEnemyPower(final AbstractCreature owner, final int amount) {
        super(STATIC);

        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 99) {
            this.amount = 99;
        }
        this.type = PowerType.BUFF;
        updateDescription();
    }

    private void applyMultiStrike() {
        this.applyMultiStrike(this.amount);
    }

    private void applyMultiStrike(int applicationAmount) { // Modified From Alchyr Chen Mod
        if (owner instanceof AbstractMonster) {
            EnemyMoveInfo targetMove = ReflectionHacks.getPrivate(owner, AbstractMonster.class, "move");
            if (targetMove.intent == AbstractMonster.Intent.ATTACK ||
                    targetMove.intent == AbstractMonster.Intent.ATTACK_BUFF ||
                    targetMove.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                    targetMove.intent == AbstractMonster.Intent.ATTACK_DEFEND) {

                int newHits = applicationAmount;
                if (targetMove.isMultiDamage)
                    newHits = targetMove.multiplier += applicationAmount;

                EnemyMoveInfo newMove = new EnemyMoveInfo(targetMove.nextMove, targetMove.intent, targetMove.baseDamage,
                        newHits, true);

                ReflectionHacks.setPrivate(owner, AbstractMonster.class, "move", newMove);
                ((AbstractMonster) owner).createIntent();

                for (AbstractPower p : owner.powers)
                    p.updateDescription();

                this.flash();
            }
        }
    }

    @Override
    public void onInitialApplication() {
        this.applyMultiStrike();
    }

    public void atSetMove() {
        if (!this.isUsed)
            this.applyMultiStrike();
    }

    @Override
    public void stackPower(int stackAmount) {
        if (owner instanceof AbstractMonster) {
            AbstractMonster targetMonster = (AbstractMonster) owner;
            if (targetMonster.intent == AbstractMonster.Intent.ATTACK ||
                    targetMonster.intent == AbstractMonster.Intent.ATTACK_BUFF ||
                    targetMonster.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
                    targetMonster.intent == AbstractMonster.Intent.ATTACK_DEFEND) {


                this.applyMultiStrike(stackAmount);
            }
        }

        this.amount += stackAmount;
        this.updateDescription();
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        super.onAttack(info, damageAmount, target);

        // Modified From Alchyr Chen Mod
        if (this.amount > 0 && !alreadyDone.contains(info) && info.type == DamageInfo.DamageType.NORMAL) {
            DamageInfo copyDamageInfo = new DamageInfo(info.owner, info.output, info.type); //copy the final damage, since applyPowers won't get called

            AbstractGameAction.AttackEffect attackEffect = AbstractGameAction.AttackEffect.NONE;

            if (AbstractDungeon.actionManager.currentAction != null &&
                    AbstractDungeon.actionManager.currentAction.actionType == AbstractGameAction.ActionType.DAMAGE) {

                if (AbstractDungeon.actionManager.currentAction.attackEffect != null)
                    attackEffect = AbstractDungeon.actionManager.currentAction.attackEffect;
            }

            alreadyDone.add(copyDamageInfo);

            for (int i = 0; i < this.amount - 1; i++)
                AbstractDungeon.actionManager.addToBottom(new DamageAction(target, copyDamageInfo, attackEffect));

            this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            this.isUsed = true;
        }
    }

    @Override
    public void updateDescription() {
        description = String.format(DESCRIPTIONS[0], this.amount);
    }
}
