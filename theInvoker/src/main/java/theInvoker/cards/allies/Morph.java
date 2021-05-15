//package theInvoker.cards.allies;
//
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theInvoker.cards.AbstractInvokerCard;
//import theInvoker.characters.TheInvoker;
//
//import static theInvoker.InvokerMod.makeCardPath;
//import static theInvoker.InvokerMod.makeID;
//
////  The Reporter - [Aya Shameimaru]
//public class Morph extends AbstractInvokerCard {
//    public static final String ID = makeID(Morph.class.getSimpleName());
//    public static final String IMG = makeCardPath("Attack.png");
//
//    private static final CardRarity RARITY = CardRarity.RARE;
//    private static final CardTarget TARGET = CardTarget.ENEMY;
//    private static final CardType TYPE = CardType.ATTACK;
//    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;
//
//    private static final int COST = 3;
//    private static final int UPGRADED_COST = 2;
//
//    public Morph() {
//        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
//        this.exhaust = true;
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        m.
//    }
//
//    @Override
//    public void upgrade() {
//        if (!upgraded) {
//            upgradeName();
//            upgradeBaseCost(UPGRADED_COST);
//            initializeDescription();
//        }
//    }
//}
//
