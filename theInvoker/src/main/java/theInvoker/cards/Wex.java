package theInvoker.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.InvokerMod;
import theInvoker.actions.UpdateInvokeAction;
import theInvoker.characters.TheInvoker;
import theInvoker.orbs.WexOrb;
import theInvoker.vfx.InvokeHelper;

public class Wex extends AbstractInvokerCard {
    public static final String ID = InvokerMod.makeID(Wex.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Wex.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 0;
    public static final int MAGIC = 1;

    public Wex() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(new WexOrb()));
        this.addToBot(new UpdateInvokeAction());
        AbstractDungeon.player.gameHandSize += this.magicNumber;

        if (upgraded)
            this.addToBot(new DrawCardAction(p, 1));
//        // TODO restore artifacts?

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