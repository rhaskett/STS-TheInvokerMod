package theInvoker.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class AbstractFlexibleCard extends AbstractInvokerCard {
    public boolean wasJustDragged = false;
    public static AbstractCreature currentEnemyTarget;

    public AbstractFlexibleCard(final String id,
                                final String img,
                                final int cost,
                                final CardType type,
                                final CardColor color,
                                final CardRarity rarity,
                                final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);
    }

    @Override
    public void update() {
        super.update();

        if(AbstractDungeon.player != null) {
            AbstractPlayer p = AbstractDungeon.player;
            if (p.isDraggingCard && p.hoveredCard.equals(this)) {
                if(!wasJustDragged) {
                    this.targetDrawScale = 1.0f;
                    this.wasJustDragged = true;
                }

                AbstractMonster hoveredEnemy = null;
                for (AbstractMonster enemy : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (enemy.hb.hovered && !enemy.isDead && !enemy.halfDead) {
                        hoveredEnemy = enemy;
                    }
                }
                if (hoveredEnemy != null) {
                    if(this.target != CardTarget.ENEMY || AbstractFlexibleCard.currentEnemyTarget != hoveredEnemy) {
                        p.inSingleTargetMode = true;
                        this.target = CardTarget.ENEMY;
                        AbstractFlexibleCard.currentEnemyTarget = hoveredEnemy;
                        this.target_x = hoveredEnemy.hb.cX - this.hb.width * 0.6F - hoveredEnemy.hb_w * 0.6F;
                        this.target_y = hoveredEnemy.hb.cY;
                        this.applyPowers();
                    }
                    this.targetDrawScale = 0.80f;
                } else {
                    if(InputHelper.mX < Settings.WIDTH/8f){
                        if (this.target != CardTarget.NONE) {
                            p.inSingleTargetMode = false;
                            this.target = CardTarget.SELF;
                            this.applyPowers();
                        }
                        this.targetDrawScale = 0.90f;
                    }else {
                        if (this.target != CardTarget.SELF) {
                            p.inSingleTargetMode = false;
                            this.target = CardTarget.SELF;
                            this.applyPowers();
                            this.targetDrawScale = 1.00f;
                        }
                    }
                }
                wasJustDragged = true;
            }else if(wasJustDragged){
                AbstractFlexibleCard.currentEnemyTarget = null;
                this.wasJustDragged = false;
            }
        }
    }
}