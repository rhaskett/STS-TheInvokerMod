package theInvoker.cards.items;

import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class HandOfMidas extends AbstractInvokerCard {
    public static final String ID = makeID(HandOfMidas.class.getSimpleName());
    public static final String IMG = makeCardPath("Hand_of_Midas.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 20;
    private static final int UPGRADE_PLUS_MAGIC = 8;

    public HandOfMidas() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGIC;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (m.currentHealth <= this.baseMagicNumber) {
            AbstractDungeon.player.gainGold(m.currentHealth);
            for(int i = 0; i < m.currentHealth; ++i)
                AbstractDungeon.effectList.add(new GainPennyEffect(p, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY,
                        true));

            this.addToTop(new InstantKillAction(m));

//            if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
//                AbstractDungeon.actionManager.clearPostCombatActions();
//            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}
