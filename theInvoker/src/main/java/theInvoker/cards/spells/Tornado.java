package theInvoker.cards.spells;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.InvokerMod;
import theInvoker.actions.RemoveBuffsAction;
import theInvoker.characters.TheInvoker;

public class Tornado extends AbstractSpellCard {
    public static final String ID = InvokerMod.makeID(Tornado.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Tornado.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public Tornado() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
         for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
            this.addToBot(new RemoveBuffsAction(monster));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            updateCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
