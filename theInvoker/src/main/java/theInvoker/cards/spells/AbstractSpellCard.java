package theInvoker.cards.spells;

import basemod.ReflectionHacks;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import theInvoker.InvokerMod;
import theInvoker.cards.AbstractInvokerCard;
import theInvoker.orbs.ExortOrb;
import theInvoker.orbs.QuasOrb;
import theInvoker.orbs.WexOrb;
import theInvoker.relics.StarterRelic;
import theInvoker.vfx.InvokeHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractSpellCard extends AbstractInvokerCard {
    // TODO add spell amp magic number?

    public boolean isInInvokeInterface = false;
    private AbstractSpellCard invokedPreviewCard;
    private static final float functionPreviewCardScale = .9f;
    private static final float functionPreviewCardY = Settings.HEIGHT * 0.45F;
    private static final float functionPreviewCardX = Settings.WIDTH * 0.1F;

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

    private static final String[] orbKeys = {"Q", "W", "E"};

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
        return new String(c);
    }

    public static String sortedOrbKey(String key) {
        char[] array = key.toCharArray();
        Arrays.sort(array);
        return String.valueOf(array);
    }

    public static AbstractSpellCard invokedCardFactory(String key, int discount, boolean preview) { // TODO aghs relic check?
        String sortedKey = AbstractSpellCard.sortedOrbKey(key);
        Class<? extends AbstractSpellCard> cardClass = INVOKE_MAP.get(sortedKey);
        try {
            AbstractSpellCard retVal = cardClass.newInstance();

            int relicDiscount = 0;
            for (AbstractRelic r : AbstractDungeon.player.relics)
                if (r instanceof StarterRelic)
                    relicDiscount = ((StarterRelic) r).relicDiscount(preview);

            CardModifierManager.addModifier(retVal, new InvokedCardModifier(discount + relicDiscount));
            return retVal;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static CardGroup possibleNextInvokes(String previousInvokeKey) {
        String key = AbstractSpellCard.invokerOrbKey();
        CardGroup retVal = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        if(key == null || key.length() < 2)
            return retVal;

        String firstTwo = key.length() == 2 ? key : key.substring(0, 2);
        for (String orbKey: orbKeys) {
            String sortedKey = sortedOrbKey(orbKey + firstTwo);
            if (!sortedKey.equals(previousInvokeKey))
                retVal.addToTop(AbstractSpellCard.invokedCardFactory(sortedKey, 0, true));
            else
                retVal.addToTop(new NeedNewOrbCombo());
        }
        return retVal;
    }

    public int getSequencePosition() {
        if (InvokeHelper.possibleInvokes != null) {
            if (InvokeHelper.possibleInvokes.contains(this)) {
                return InvokeHelper.possibleInvokes.group.indexOf(this);
            }
        }
        return -1;
    }

    @Override
    public void hover() {
        if ((getSequencePosition() >= 0 || InvokeHelper.nextInvoke == this) && !isInInvokeInterface) {
            isInInvokeInterface = true;
//            InvokerMod.logger.info("hover() hit");
            ReflectionHacks.setPrivate(this, AbstractCard.class, "hovered", true);
        } else {
            super.hover();
        }
    }

    @Override
    public void unhover() {
        if ((getSequencePosition() >= 0 || InvokeHelper.nextInvoke == this) && isInInvokeInterface) {
            isInInvokeInterface = false;
//            InvokerMod.logger.info("unhover() hit");
            ReflectionHacks.setPrivate(this, AbstractCard.class, "hovered", false);
        } else {
            super.unhover();
        }
    }

    @Override
    public void render(SpriteBatch sb) { // Adapted from Evil Within
        super.render(sb);

        if (isInInvokeInterface) {
//            InvokerMod.logger.info("isHoveredInSequence");
            if (isLocked || (AbstractDungeon.player != null && (AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode))) {
                InvokerMod.logger.info("bounced");
                return;
            }

//            InvokerMod.logger.info("Is in Sequence");
            if (invokedPreviewCard == null) {
                invokedPreviewCard = (AbstractSpellCard) makeStatEquivalentCopy();
            }
//            InvokerMod.logger.info("rendering previewcard");
            invokedPreviewCard.drawScale = functionPreviewCardScale;
            invokedPreviewCard.current_x = functionPreviewCardX;
            invokedPreviewCard.current_y = functionPreviewCardY;
            // functionPreviewCard.update();
            invokedPreviewCard.render(sb);
        }

        if (!hb.hovered && invokedPreviewCard != null) {
            invokedPreviewCard = null;
        }
    }
}