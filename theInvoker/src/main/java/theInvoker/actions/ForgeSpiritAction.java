package theInvoker.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ForgeSpiritAction extends AbstractGameAction {
    private final AbstractCard card;

    public ForgeSpiritAction(AbstractCard card) {
        this.card = card;
    }

    @Override
    public void update() {
        this.addToBot(new DamageRandomEnemyAction(new DamageInfo(AbstractDungeon.player, card.magicNumber, card.damageTypeForTurn),
                    AttackEffect.FIRE));
//        target = CombatUtils.getRandomAliveMonster(AbstractDungeon.getMonsters(), AbstractDungeon.cardRandomRng);
//        if (this.target != null) {
//            card.calculateCardDamage((AbstractMonster) target);
//
//            // Add to top in reverse order so VFX starts/waits before the damage effect happens.
//            AbstractDungeon.actionManager.addToTop(new DamageAction(target, new DamageInfo(AbstractDungeon.player, card.damage, card.damageTypeForTurn),
//                    AttackEffect.FIRE));
////            AbstractDungeon.actionManager.addToTop(new VFXAction(new ArcaneWeaponEffect(target.hb.x, target.hb.cY + target.hb.height / 12.0F), 0.5F));
//        }

        isDone = true;
    }
}
