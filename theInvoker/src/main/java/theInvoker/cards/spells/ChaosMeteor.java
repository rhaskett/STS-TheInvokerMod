package theInvoker.cards.spells;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.InvokerMod;
import theInvoker.characters.TheInvoker;

@AutoAdd.Ignore
public class ChaosMeteor extends AbstractSpellCard {
    public static final String ID = InvokerMod.makeID(ChaosMeteor.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Chaos_Meteor.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 14;
    private static final int UPGRADE_PLUS_DMG = 6;

    public ChaosMeteor() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        // TODO Burning Effect?  Random 2
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.damage, damageTypeForTurn,
                AbstractGameAction.AttackEffect.FIRE));
    }


    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
