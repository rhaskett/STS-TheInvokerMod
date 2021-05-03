package theInvoker.cards.items;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;

import java.util.Iterator;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class TownPortalScroll extends AbstractInvokerCard {
    public static final String ID = makeID(TownPortalScroll.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int MAGIC = 3;
    private static final int UPGRADED_MAGIC = 1;

    public TownPortalScroll() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new HealAction(p, p, p.maxHealth - p.currentHealth));

        AbstractDungeon.getCurrRoom().smoked = true;
        this.addToBot(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY))); // TODO VFX?

        p.hideHealthBar();
        p.isEscaping = true;
        p.flipHorizontal = !p.flipHorizontal;

        AbstractDungeon.overlayMenu.endTurnButton.disable();
        AbstractDungeon.player.escapeTimer = 2.5F;


        // TODO Skip Floors
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADED_MAGIC);
            initializeDescription();
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (! super.canUse(p, m))
            return false;

        for (AbstractMonster monster :  AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (monster.type != AbstractMonster.EnemyType.BOSS || monster.hasPower("BackAttack")) {
                return false;
            }
        }
        return true;
    }
}