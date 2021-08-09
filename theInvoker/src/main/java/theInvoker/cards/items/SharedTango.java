package theInvoker.cards.items;

import com.evacipated.cardcrawl.mod.stslib.StSLib;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.characters.TheInvoker;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

public class SharedTango extends AbstractInvokerCard {
    public static final String ID = makeID(SharedTango.class.getSimpleName());
    public static final String IMG = makeCardPath("Tango_Shared.png");
    public static final String UPGRADED_IMG = makeCardPath("Shared_Tango.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 6;

    public SharedTango() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
        this.tags.add(CardTags.HEALING);
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber), magicNumber));

        AbstractCard masterCard = StSLib.getMasterDeckEquivalent(this);
        p.masterDeck.removeCard(masterCard);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
//            loadCardImage(UPGRADED_IMG);  TODO Beta art?
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}