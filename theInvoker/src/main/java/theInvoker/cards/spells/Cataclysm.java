package theInvoker.cards.spells;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theInvoker.InvokerMod;
import theInvoker.characters.TheInvoker;
import theInvoker.powers.EndOfRoundDamagePower;

@AutoAdd.Ignore
public class Cataclysm extends AbstractSpellCard {
    public static final String ID = InvokerMod.makeID(Cataclysm.class.getSimpleName());
    public static final String IMG = InvokerMod.makeCardPath("Sun_Strike.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 2;
    private static final int DAMAGE = 25;
    private static final int UPGRADE_PLUS_DMG = 6;

    public Cataclysm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = damage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters)
            if (!monster.isDead && !monster.isDying)
                this.addToBot(new ApplyPowerAction(monster, p, new EndOfRoundDamagePower(monster, p, this.damage,
                        AbstractGameAction.AttackEffect.FIRE)));
    }

    @Override
    public void upgrade(){
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
