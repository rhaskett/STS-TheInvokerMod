package theInvoker.cards.allies;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.powers.StunMonsterPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;

import java.util.ArrayList;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class ShackleShot extends AbstractInvokerCard {
    public static final String ID = makeID(ShackleShot.class.getSimpleName());
    public static final String IMG = makeCardPath("Shackle_Shot.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    public ShackleShot() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractMonster second = null;
        ArrayList<AbstractMonster> tmp = new ArrayList<>();
        for (AbstractMonster other : AbstractDungeon.getMonsters().monsters) {
            if (!m.halfDead && !m.isDying && !m.isEscaping && other != m) {
                tmp.add(m);
            }
        }

        if (tmp.size() > 0)
            second = tmp.get(MathUtils.random(0, tmp.size() - 1));

        if(second != null) {
            addToBot(new ApplyPowerAction(m, p, new StunMonsterPower(m, 1), 1));
            addToBot(new ApplyPowerAction(second, p, new StunMonsterPower(second, 1), 1));
        }
        // todo else explain why it didn't work
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}

