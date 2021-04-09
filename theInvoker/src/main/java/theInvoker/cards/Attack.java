//package theInvoker.cards;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theInvoker.characters.TheInvoker;
//
//import static theInvoker.TheInvoker.makeCardPath;
//
//public class Attack extends AbstractDynamicCard {
//
//    public static final String ID = theInvoker.TheInvoker.makeID(Attack.class.getSimpleName());
//    public static final String IMG = makeCardPath("Attack.png");
//
//    private static final CardRarity RARITY = CardRarity.BASIC;
//    private static final CardTarget TARGET = CardTarget.ENEMY;
//    private static final CardType TYPE = CardType.ATTACK;
//    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;
//
//    private static final int COST = 1;
//    private static final int DAMAGE = 4;
//    private static final int UPGRADE_PLUS_DMG = 3;
//
//
//    public Attack() {
//        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
//        baseDamage = DAMAGE;
//
//        this.tags.add(CardTags.STARTER_STRIKE);
//        this.tags.add(CardTags.STRIKE);
//    }
//
//
//    // Actions the card should do.
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        AbstractDungeon.actionManager.addToBottom(
//                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//    }
//
//
//    // Upgraded stats.
//    @Override
//    public void upgrade() {
//        if (!upgraded) {
//            upgradeName();
//            upgradeDamage(UPGRADE_PLUS_DMG);
//            initializeDescription();
//        }
//    }
//}