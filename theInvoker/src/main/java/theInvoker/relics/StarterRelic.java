package theInvoker.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theInvoker.InvokerMod;
import theInvoker.cards.spells.AbstractSpellCard;
import theInvoker.util.TextureLoader;

public class StarterRelic extends CustomRelic {
    public static final String ID = InvokerMod.makeID(StarterRelic.class.getSimpleName()); // TODO relic visuals
    private static final Texture IMG = TextureLoader.getTexture(InvokerMod.makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(InvokerMod.makeRelicOutlinePath("placeholder_relic.png"));
    public boolean activated = false;


    public StarterRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        activated = false;
        grayscale = false;
    }

    public int relicDiscount(boolean preview) {
        int retVal = 0;
        if (!activated) {
            retVal += 1;

            if (!preview) {
                activated = true;
                flash();
                grayscale = true;
            }
        }

        return retVal;
    }

    @Override
    public void onVictory() {
        activated = false;
        grayscale = false;
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
}
