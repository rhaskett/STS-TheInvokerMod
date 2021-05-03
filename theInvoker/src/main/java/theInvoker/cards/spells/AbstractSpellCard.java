package theInvoker.cards.spells;

import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.orbs.ExortOrb;
import theInvoker.orbs.QuasOrb;
import theInvoker.orbs.WexOrb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSpellCard extends AbstractInvokerCard {
    // TODO add spell amp magic number?

    private static final Map<String, Class<? extends AbstractSpellCard>> INVOKE_MAP = new HashMap<>();
    static {
        INVOKE_MAP.put("EEE", SunStrike.class);
        INVOKE_MAP.put("EEQ", ForgeSpirit.class);
        INVOKE_MAP.put("EEW", ChaosMeteor.class);
        INVOKE_MAP.put("EQQ", IceWall.class);
        INVOKE_MAP.put("EQW", DeafeningBlast.class);
        INVOKE_MAP.put("EWW", Alacrity.class);
        INVOKE_MAP.put("QQQ", ColdSnap.class);
        INVOKE_MAP.put("QQW", GhostWalk.class);
        INVOKE_MAP.put("QWW", Tornado.class);
        INVOKE_MAP.put("WWW", EMP.class);
    }

    public AbstractSpellCard(final String id,
                             final String img,
                             final int cost,
                             final CardType type,
                             final CardColor color,
                             final CardRarity rarity,
                             final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);
    }

    public static String invokerOrbKey() {
        return invokerOrbKey(AbstractDungeon.player.orbs);
    }

    public static String invokerOrbKey(ArrayList<AbstractOrb> orbs) {
        StringBuilder key = new StringBuilder();

        for (AbstractOrb orb : orbs) { // TODO check order?
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

    public static AbstractSpellCard invokedCardFactory(String key) { // TODO pass class?
        Class<? extends AbstractSpellCard> cardClass = INVOKE_MAP.get(key);
        try {
            AbstractSpellCard retVal = cardClass.newInstance();
            CardModifierManager.addModifier(retVal, new InvokedCardModifier());
            return retVal;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}