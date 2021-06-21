package theInvoker.cards.items;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.characters.TheInvoker;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class Platemail extends AbstractCombinesCard {
    public static final String ID = makeID(Platemail.class.getSimpleName());
    public static final String IMG = makeCardPath("Platemail.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    public static final int COST = 1;
    public static final int BLOCK = 9;

    public Platemail() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new GainBlockAction(p, p, block));
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
