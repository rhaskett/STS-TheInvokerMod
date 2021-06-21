package theInvoker.actions;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class ShadowPoisonLoseHPAction extends AbstractGameAction {
    private String powerID;

    public ShadowPoisonLoseHPAction(AbstractCreature target, AbstractCreature source, int amount, AttackEffect effect,
                                    String powerID) {
        this.setValues(target, source, amount);
        this.actionType = ActionType.DAMAGE;
        this.attackEffect = effect;
        this.duration = 0.33F;
        this.powerID = powerID;
    }

    public void update() {
        if (AbstractDungeon.getCurrRoom().phase != RoomPhase.COMBAT) {
            this.isDone = true;
        } else {
            if (this.duration == 0.33F && this.target.currentHealth > 0) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, this.attackEffect));
            }

            this.tickDuration();
            if (this.isDone) {
                if (this.target.currentHealth > 0) {
                    this.target.tint.color = Color.MAGENTA.cpy();
                    this.target.tint.changeColor(Color.WHITE.cpy());
                    this.target.damage(new DamageInfo(this.source, this.amount, DamageType.HP_LOSS));
                }

                AbstractPower p = this.target.getPower(this.powerID);
                if (p != null)
                    this.target.powers.remove(p);

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                    AbstractDungeon.actionManager.clearPostCombatActions();

                this.addToTop(new WaitAction(0.1F));
            }

        }
    }
}
