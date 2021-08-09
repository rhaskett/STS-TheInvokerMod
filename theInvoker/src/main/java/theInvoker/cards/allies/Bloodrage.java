package theInvoker.cards.allies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.actions.MultiAttackEnemyAction;
import theInvoker.cards.AbstractFlexibleCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.MultiStrikeEnemyPower;
import theInvoker.powers.MultiStrikePower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class Bloodrage extends AbstractFlexibleCard {
    public static final String ID = makeID(Bloodrage.class.getSimpleName());
    public static final String IMG = makeCardPath("Bloodrage.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;
    private static final int MAGIC = 12;
    private static final int MULTI_STRIKE = 3;

    public Bloodrage() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = null;
        if(m != null)
            target = m;
        else
            if(this.target == CardTarget.SELF)
                target = p;

        this.addToBot(new DamageAction(target, new DamageInfo(p, this.magicNumber), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

        if (this.target == CardTarget.SELF)
            this.addToBot(new ApplyPowerAction(target, p, new MultiStrikePower(target, MULTI_STRIKE)));
        else
            this.addToBot(new ApplyPowerAction(target, p, new MultiStrikeEnemyPower(target, MULTI_STRIKE)));

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
