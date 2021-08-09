//package theInvoker.cards.items;
//
//import basemod.AutoAdd;
//import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.cards.DamageInfo;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import com.megacrit.cardcrawl.powers.StrengthPower;
//import theInvoker.cards.AbstractInvokerCard;
//import theInvoker.characters.TheInvoker;
//
//import static theInvoker.InvokerMod.makeCardPath;
//import static theInvoker.InvokerMod.makeID;
//
//@AutoAdd.Ignore
//public class RodOfAtos extends AbstractInvokerCard {
//    public static final String ID = makeID(RodOfAtos.class.getSimpleName());
//    public static final String IMG = makeCardPath("Rod_of_Atos.png");
//
//    private static final CardRarity RARITY = CardRarity.SPECIAL;
//    private static final CardTarget TARGET = CardTarget.ENEMY;
//    private static final CardType TYPE = CardType.ATTACK;
//    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;
//
//    private static final int COST = Crown.COST;
//    private static final int BLOCK = Crown.BLOCK;
//    private static final int DAMAGE = Crown.DAMAGE + StaffOfWizardry.DAMAGE;
//    public static final int MAGIC = 2;
//
//    public RodOfAtos() {
//        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
//        this.baseDamage = DAMAGE;
//        this.baseBlock = BLOCK;
//    }
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
//        this.addToBot(new GainBlockAction(p, p, block));
//        this.addToBot(new ApplyPowerAction(m, p, new StrengthPower(m, -this.magicNumber), -this.magicNumber));
//    }
//
//    @Override
//    public boolean canUpgrade() {
//        return false;
//    }
//
//    @Override
//    public void upgrade() {}
//}
