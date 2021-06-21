package theInvoker.cards.allies;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractFlexibleCard;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.EndOfTurnHealPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class PurifyingFlames extends AbstractFlexibleCard {
    public static final String ID = makeID(PurifyingFlames.class.getSimpleName());
    public static final String IMG = makeCardPath("Purifying_Flames.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;
    private static final int MAGIC = 12;
    private static final int HEAL = 20;

    public PurifyingFlames() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.exhaust = true;
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractCreature target = null;
        if(m != null)
            target = m;
        else if(this.target == CardTarget.SELF)
            target = p;

        this.addToBot(new DamageAction(target, new DamageInfo(p, this.magicNumber), AbstractGameAction.AttackEffect.FIRE));
        this.addToBot(new ApplyPowerAction(p, p, new EndOfTurnHealPower(target, p, HEAL)));
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
