package theInvoker.cards.items;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.actions.FastAddTemporaryHPAction;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.VenomPower;

import static theInvoker.InvokerMod.makeCardPath;
import static theInvoker.InvokerMod.makeID;

@AutoAdd.Ignore
public class OrchidMalevolence extends AbstractCraftableCard {
    public static final String ID = makeID(OrchidMalevolence.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final String COMPONENT1 = StaffOfWizardry.ID;
    public static final String COMPONENT2 = StaffOfWizardry.ID;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = StaffOfWizardry.COST;
    private static final int DAMAGE = StaffOfWizardry.DAMAGE * 2;

    public OrchidMalevolence() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, COMPONENT1, COMPONENT2);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) { // TODO add silence and/or damage Amp
        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public boolean canUpgrade() {
        return false;
    }

    @Override
    public void upgrade() {}
}
