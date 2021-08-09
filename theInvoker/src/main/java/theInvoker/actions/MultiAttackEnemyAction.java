package theInvoker.actions;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.EnemyMoveInfo;

public class MultiAttackEnemyAction extends AbstractGameAction {
    private final AbstractMonster m;

    public MultiAttackEnemyAction(AbstractMonster m, int amount) {
        this.actionType = ActionType.WAIT;
        this.m = m;
        this.amount = amount;
    }

    public void update() {
        if (this.m != null && this.m.getIntentBaseDmg() >= 0) {
            EnemyMoveInfo targetMove = ReflectionHacks.getPrivate(m, AbstractMonster.class, "move");

            int newHits = this.amount;
            if (targetMove.isMultiDamage)
                newHits += this.amount;

            EnemyMoveInfo newMove = new EnemyMoveInfo(targetMove.nextMove, targetMove.intent, targetMove.baseDamage,
                    newHits, true);

            ReflectionHacks.setPrivate(m, AbstractMonster.class, "move", newMove);
            m.createIntent();
        }

        this.isDone = true;
    }
}
