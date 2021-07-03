package theInvoker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import com.megacrit.cardcrawl.vfx.combat.PotionBounceEffect;

public class PlagueWardAction extends AbstractGameAction {
    private static final float DURATION = 0.01F;

    public PlagueWardAction(final int amount) {
        this.actionType = ActionType.DEBUFF;
        this.duration = DURATION;
        this.amount = amount;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        } else {
            if (this.amount > 1 && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                for (int i=0; i < this.amount; i++) {
                    AbstractMonster randomMonster = AbstractDungeon.getMonsters().getRandomMonster(null,
                            true, AbstractDungeon.cardRandomRng);

                    this.addToTop(new ApplyPowerAction(this.target, AbstractDungeon.player,
                            new PoisonPower(this.target, AbstractDungeon.player, this.amount),
                            1, true, AttackEffect.POISON));
                }

                this.addToTop(new WaitAction(0.1F));
            }
        }
        this.isDone = true;
    }
}
