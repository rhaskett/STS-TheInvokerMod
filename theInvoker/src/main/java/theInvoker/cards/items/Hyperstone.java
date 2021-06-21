package theInvoker.cards.items;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.MultiStrikePower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class Hyperstone extends AbstractCombinesCard {
    public static final String ID = makeID(Hyperstone.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = 1;
    public static final int MAGIC = 2;

    public Hyperstone() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new MultiStrikePower(p, this.magicNumber)));
    }

    @Override
    public String getRawDescription() {
        return DESCRIPTION + combinesExtendedDescription();
    }


    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}

}
