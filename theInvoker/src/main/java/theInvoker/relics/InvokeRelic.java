package theInvoker.relics;

import basemod.abstracts.CustomRelic;
import basemod.cardmods.EtherealMod;
import basemod.cardmods.ExhaustMod;
import basemod.helpers.CardModifierManager;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import theInvoker.InvokerMod;
import theInvoker.cards.spells.*;
import theInvoker.orbs.ExortOrb;
import theInvoker.orbs.QuasOrb;
import theInvoker.orbs.WexOrb;
import theInvoker.util.TextureLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InvokeRelic extends CustomRelic {
    public static final String ID = InvokerMod.makeID(InvokeRelic.class.getSimpleName()); // TODO Picture
    private static final Texture IMG = TextureLoader.getTexture(InvokerMod.makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(InvokerMod.makeRelicOutlinePath("placeholder_relic.png"));

    public InvokeRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void atTurnStart() {
        String key = AbstractSpellCard.invokerOrbKey();
        if(key != null && key.length() == 3) {
            this.addToBot(new MakeTempCardInHandAction(AbstractSpellCard.invokedCardFactory(key)));
            this.flash();
        }
    }
}
