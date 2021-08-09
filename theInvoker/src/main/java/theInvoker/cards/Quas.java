package theInvoker.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theInvoker.InvokerMod;
import theInvoker.actions.UpdateInvokeAction;
import theInvoker.characters.TheInvoker;
import theInvoker.orbs.QuasOrb;
import theInvoker.vfx.InvokeHelper;

public class Quas extends AbstractInvokerCard {
    public static final String ID = InvokerMod.makeID(Quas.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Quas.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private static final int MAGIC = 1;

    public Quas() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(new QuasOrb()));
        this.addToBot(new UpdateInvokeAction());
        this.addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber), this.magicNumber));

        if (upgraded)  // TODO innate?
            this.addToBot(new DrawCardAction(p, 1));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = DESCRIPTION + UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}