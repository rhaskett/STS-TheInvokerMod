package theInvoker.vfx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import theInvoker.InvokerMod;
import theInvoker.cards.Exort;
import theInvoker.cards.Quas;
import theInvoker.cards.Wex;
import theInvoker.cards.spells.AbstractSpellCard;
import theInvoker.cards.spells.NeedMoreOrbs;
import theInvoker.cards.spells.NeedNewOrbCombo;
import theInvoker.characters.TheInvoker;

// Modified from EvilWithin
public class InvokeHelper {
    public static AbstractCard nextInvoke;
    public static String lastInvokedKey;
    public static CardGroup possibleInvokes;
    public static boolean doStuff = false;

    public static final float POSSIBLE_CARD_SIZE = 0.225f;
    public static final float NEXT_CARD_SIZE = 0.45f;

//    public static final float BG_X = 150f * Settings.scale;
//    public static final float BG_Y = 700f * Settings.scale;
    public static final float HEIGHT_NEXT = 820f * Settings.yScale;
    public static final float HEIGHT_POSSIBLE = 830f * Settings.yScale;
    public static final float HEIGHT_ORB_TEXT = 900f * Settings.yScale;
//    public static final float HEIGHT_SPOT = 700f * Settings.yScale;


//    public static final Vector2 nextInvokePosition = new Vector2(480f * Settings.xScale, HEIGHT_FUNCTION);
//    public static final Vector2[] cardPositions = {
//            new Vector2(218f * Settings.xScale, HEIGHT_SEQUENCE),
//            new Vector2(293f * Settings.xScale, HEIGHT_SEQUENCE),
//            new Vector2(368f * Settings.xScale, HEIGHT_SEQUENCE)
////            new Vector2(443f * Settings.xScale, HEIGHT_SEQUENCE)
//    };
    public static final Vector2 nextInvokePosition = new Vector2(225f * Settings.xScale, HEIGHT_NEXT);
    public static final Vector2[] cardPositions = {
            new Vector2(368f * Settings.xScale, HEIGHT_POSSIBLE),
            new Vector2(443f * Settings.xScale, HEIGHT_POSSIBLE),
            new Vector2(518f * Settings.xScale, HEIGHT_POSSIBLE)
//            new Vector2(443f * Settings.xScale, HEIGHT_SEQUENCE)
    };

    public static final String[] orbNames = {
            CardCrawlGame.languagePack.getCardStrings(InvokerMod.makeID(Quas.class.getSimpleName())).NAME,
            CardCrawlGame.languagePack.getCardStrings(InvokerMod.makeID(Wex.class.getSimpleName())).NAME,
            CardCrawlGame.languagePack.getCardStrings(InvokerMod.makeID(Exort.class.getSimpleName())).NAME
    };

    public static final Vector2[] orbTextPosition = {
            new Vector2(368f * Settings.xScale, HEIGHT_ORB_TEXT),
            new Vector2(443f * Settings.xScale, HEIGHT_ORB_TEXT),
            new Vector2(518f * Settings.xScale, HEIGHT_ORB_TEXT)
    };

//    public static final Vector2[] floaterStartPositions = {
//            new Vector2(177F * Settings.xScale, HEIGHT_SPOT),
//            new Vector2(252f * Settings.xScale, HEIGHT_SPOT),
//            new Vector2(327f * Settings.xScale, HEIGHT_SPOT)
////            new Vector2(402f * Settings.xScale, HEIGHT_SPOT)
//    };

    //TODO background
//    private static final Texture bg = TextureLoader.getTexture("bronzeResources/images/ui/sequenceframe.png");
//    private static final Texture bg_4card = TextureLoader.getTexture("bronzeResources/images/ui/sequenceframe4cards.png");
//    private static final Texture sequenceSlot = TextureLoader.getTexture("bronzeResources/images/ui/sequenceSlot.png");

//    public static final String WITH_DELIMITER = "((?<=%1$s)|(?=%1$s))"; //Magic code from madness land of RegEx.

    public static void init() {
        possibleInvokes = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        nextInvoke = new NeedMoreOrbs();
        lastInvokedKey = "";
        if (AbstractDungeon.player instanceof TheInvoker)
            doStuff = true;
            InvokeHelper.updateInvokes();
    }

    public static void updateInvokes() { // TODO works with Defect orbs?
        if (!doStuff) {
            doStuff = true;
        }

        possibleInvokes = AbstractSpellCard.possibleNextInvokes(lastInvokedKey);

        int position = 0;
        for(AbstractCard card : possibleInvokes.group) {
            card.targetDrawScale = POSSIBLE_CARD_SIZE;
            card.target_x = cardPositions[position].x;
            card.target_y = cardPositions[position].y;
            card.current_x = cardPositions[position].x;
            card.current_y = cardPositions[position].y;

            position++;
        }

        String key = AbstractSpellCard.invokerOrbKey();
        if(key != null && key.length() == 3) {
            String sortedKey = AbstractSpellCard.sortedOrbKey(key);
            if (lastInvokedKey.equals(sortedKey))
                nextInvoke = new NeedNewOrbCombo();
            else
                nextInvoke = AbstractSpellCard.invokedCardFactory(key, 0, true);
        }
    }

    public static void render(SpriteBatch sb) {
        if (doStuff) {
            sb.setColor(Color.WHITE.cpy());
//            sb.draw(bg, BG_X, BG_Y, 0, 0, bg.getWidth() * Settings.scale, bg.getHeight() * Settings.scale, 1, 1, 0, 0, 0, bg.getWidth(), bg.getHeight(), false, false);
//            for (int i = 0; i < max(); i++) {
//                sb.draw(sequenceSlot, floaterStartPositions[i].x, floaterStartPositions[i].y + bobEffects[i].y, 0, 0, sequenceSlot.getWidth() * Settings.scale, sequenceSlot.getHeight() * Settings.scale, 1, 1, 0, 0, 0, sequenceSlot.getWidth(), sequenceSlot.getHeight(), false, false);

            int position = 0;
            for(AbstractCard card : possibleInvokes.group) {
                card.render(sb);
                FontHelper.renderFontCentered(sb, FontHelper.topPanelAmountFont, orbNames[position],
                        orbTextPosition[position].x, orbTextPosition[position].y, Settings.GOLD_COLOR);

                position++;
            }
            if (nextInvoke != null) {
                nextInvoke.render(sb);
            }
        }
    }

    public static void update() {
        // Ugly hack to ensure bleeding through to future runs never happens
        if (!doStuff) {
            if (AbstractDungeon.player != null || possibleInvokes.size() > 0) {
                if (AbstractDungeon.player instanceof TheInvoker) {
                    doStuff = true;
                }
            }
        } else {
            if (AbstractDungeon.player == null) {
                doStuff = false;
            } else {
                if (!(AbstractDungeon.player instanceof TheInvoker)){
                    if (possibleInvokes.size() == 0){
                        doStuff = false;
                    }
                }
            }
        }

        if (doStuff) {
            for(AbstractCard card : possibleInvokes.group) {
                card.update();
                card.updateHoverLogic();
            }
            if (nextInvoke != null) {
                nextInvoke.update();
                nextInvoke.updateHoverLogic();
                nextInvoke.target_x = nextInvokePosition.x;
                nextInvoke.current_x = nextInvokePosition.x;
                nextInvoke.target_y = nextInvokePosition.y;
                nextInvoke.current_y = nextInvokePosition.y;
                nextInvoke.targetDrawScale = NEXT_CARD_SIZE;
                nextInvoke.drawScale = NEXT_CARD_SIZE;
            }
        }
    }
}
