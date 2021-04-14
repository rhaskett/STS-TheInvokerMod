package theInvoker.cards;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theInvoker.characters.TheInvoker;
import theInvoker.orbs.ExortOrb;
import theInvoker.orbs.QuasOrb;
import theInvoker.orbs.WexOrb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static theInvoker.TheInvoker.makeCardPath;
import static theInvoker.TheInvoker.makeID;

public class Invoke extends AbstractInvokerCard {
    public static final String ID = makeID(Invoke.class.getSimpleName());
    public static final String IMG = makeCardPath("Skill.png");

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheInvoker.Enums.COLOR_GRAY;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;
    private static final int BLOCK = 3;
    public static final Map<String, Class<? extends AbstractInvokerCard>> INVOKE_MAP = new HashMap<>();

    static {
        INVOKE_MAP.put("WWW", EMP.class);
        INVOKE_MAP.put("QQQ", ColdSnap.class);
        INVOKE_MAP.put("EEE", SunStrike.class);

    }

    public Invoke() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);

        this.baseBlock = BLOCK;
    }

    private String invokerOrbKey(ArrayList<AbstractOrb> orbs) {
        StringBuilder key = new StringBuilder();

        for (AbstractOrb orb : AbstractDungeon.player.orbs) { // TODO check order?
            if (orb instanceof QuasOrb)
                key.append("Q");
            if (orb instanceof WexOrb)
                key.append("W");
            if (orb instanceof ExortOrb)
                key.append("E");

            if (key.length() == 3) {
                break;
            }
        }

        if(key.length() == 0)
            return null;

        char[] c = key.toString().toCharArray();
        Arrays.sort(c);
        return new String(c);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        String key = invokerOrbKey(AbstractDungeon.player.orbs);

        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));

        if(key.length() == 3) {
            Class<? extends AbstractInvokerCard> card = INVOKE_MAP.get(key);
            try {
                this.addToBot(new MakeTempCardInHandAction(card.newInstance(), 1));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}