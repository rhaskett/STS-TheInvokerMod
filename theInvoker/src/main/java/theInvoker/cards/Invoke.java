package theInvoker.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theInvoker.InvokerMod;
import theInvoker.actions.InvokeAction;
import theInvoker.characters.TheInvoker;
import theInvoker.orbs.ExortOrb;
import theInvoker.orbs.QuasOrb;
import theInvoker.orbs.WexOrb;

import java.util.ArrayList;
import java.util.Arrays;

public class Invoke extends AbstractInvokerCard {
    public static final String ID = InvokerMod.makeID(Invoke.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Invoke.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 0;
    private int discount = 0;

    public Invoke() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new InvokeAction(this.discount));

        // TODO Upgrade missing
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.discount = 1;
            this.rawDescription = DESCRIPTION + UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
