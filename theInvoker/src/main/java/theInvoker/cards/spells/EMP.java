package theInvoker.cards.spells;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;
import theInvoker.InvokerMod;
import theInvoker.characters.TheInvoker;

@AutoAdd.Ignore
public class EMP extends AbstractSpellCard {
    public static final String ID = InvokerMod.makeID(EMP.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("EMP.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int AMOUNT = 2;
    private static final int UPGRADE_PLUS = 1;

    public EMP() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.magicNumber = this.baseMagicNumber = AMOUNT;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();

            for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
                if (!monster.isDead && !monster.isDying) {
                    this.addToBot(new ApplyPowerAction(monster, p, new WeakPower(monster, this.magicNumber,
                            false), this.magicNumber));
                }
            }
        }

    }

    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(UPGRADE_PLUS);
        }

    }
}
