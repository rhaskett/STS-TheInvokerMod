//package theInvoker.cards.items;
//
//import basemod.AutoAdd;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
//import com.megacrit.cardcrawl.monsters.AbstractMonster;
//import theInvoker.cards.AbstractInvokerCard;
//import theInvoker.characters.TheInvoker;
//import theInvoker.powers.MultiStrikePower;
//import theInvoker.powers.SpellWeaknessPower;
//
//import static theInvoker.InvokerMod.makeCardPath;
//import static theInvoker.InvokerMod.makeID;
//
//@AutoAdd.Ignore
//public class VeilOfDiscord extends AbstractInvokerCard {
//    public static final String ID = makeID(VeilOfDiscord.class.getSimpleName());
//    public static final String IMG = makeCardPath("Skill.png"); // TODO picture
//
//    private static final CardRarity RARITY = CardRarity.SPECIAL;
//    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
//    private static final CardType TYPE = CardType.SKILL;
//    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;
//
//    public static final int COST = SagesMask.COST;
//    private static final int DAMAGE = Crown.DAMAGE;
//    private static final int BLOCK = Crown.BLOCK;
//    private static final int MAGIC = 2;
//
//    public VeilOfDiscord() {
//        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
//        this.baseDamage = DAMAGE;
//        this.baseBlock = BLOCK;
//        this.magicNumber = MAGIC;
//    }
//
//    @Override
//    public boolean canUpgrade() {
//        return false;
//    }
//
//    @Override
//    public void upgrade() {}
//
//    @Override
//    public void use(AbstractPlayer p, AbstractMonster m) {
//        this.addToBot(new GainBlockAction(p, p, block));
//        this.addToBot(new ApplyPowerAction(p, p, new SpellWeaknessPower(p, this.magicNumber)));
//    }
//}
