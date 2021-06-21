package theInvoker.cards.spells;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.cards.items.AbstractRecipeCard;
import theInvoker.cards.items.EnergyBooster;
import theInvoker.cards.items.LotusOrb;
import theInvoker.cards.items.Platemail;
import theInvoker.characters.TheInvoker;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class NeedMoreOrbs extends AbstractSpellCard {
    public static final String ID = makeID(NeedMoreOrbs.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = -1;

    public NeedMoreOrbs() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void upgrade() {
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
    }
}
