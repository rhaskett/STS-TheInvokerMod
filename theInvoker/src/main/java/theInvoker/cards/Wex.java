package theInvoker.cards;

import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.InvokerMod;
import theInvoker.characters.TheInvoker;
import theInvoker.orbs.WexOrb;

public class Wex extends AbstractInvokerCard {
    public static final String ID = InvokerMod.makeID(Wex.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String IMG = InvokerMod.makeCardPath("Quas.png");

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    public static final int MAGIC = 1;

    public Wex() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ChannelAction(new WexOrb()));
        AbstractDungeon.player.gameHandSize += this.magicNumber;

//        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
//            this.addToBot(new ApplyPowerAction(monster, p, new StrengthPower(monster, -this.magicNumber),
//                    -this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
//        }
//
//        // TODO restore artifacts?

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }
    }
}