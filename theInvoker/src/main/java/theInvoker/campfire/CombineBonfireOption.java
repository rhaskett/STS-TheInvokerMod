package theInvoker.campfire;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.campfire.AbstractCampfireOption;
import theInvoker.InvokerMod;
import theInvoker.vfx.CombineCardEffect;

public class CombineBonfireOption extends AbstractCampfireOption {
    private static final UIStrings UI_STRINGS = CardCrawlGame.languagePack.getUIString(
            InvokerMod.makeID(CombineBonfireOption.class.getSimpleName()));
    public static final String[] DESCRIPTIONS = UI_STRINGS.TEXT;
    public static final int COST = 50;


    public CombineBonfireOption(boolean active) {
        this.label = DESCRIPTIONS[0] + COST + DESCRIPTIONS[1];

        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            c.update();
        }

        this.usable = active;
        if (active) {
            this.description = DESCRIPTIONS[2];
            this.img = ImageMaster.loadImage(InvokerMod.makeUIPath("combine_campfire.png"));
        } else {
            this.description = DESCRIPTIONS[3] + COST + DESCRIPTIONS[4];
            this.img = ImageMaster.loadImage(InvokerMod.makeUIPath("combine_campfire_disabled.png"));
        }
    }

    @Override
    public void useOption() {
        if (this.usable) {
            AbstractDungeon.effectList.add(new CombineCardEffect());
        }
    }

//    public void reCheck() {
//        if (GuardianMod.getGemCards().size() == 0) {
//            this.usable = false;
//        }
//        if (this.usable) {
//            this.description = DESCRIPTIONS[1];
//            this.img = ImageMaster.loadImage(GuardianMod.getResourcePath("ui/scrapcampfire.png"));
//        } else {
//            this.description = DESCRIPTIONS[2];
//            this.img = ImageMaster.loadImage(GuardianMod.getResourcePath("ui/scrapcampfiredisabled.png"));
//        }
//
//    }

//    @Override
//    public void update() {
//        float hackScale = (float) ReflectionHacks.getPrivate(this, AbstractCampfireOption.class, "scale");
//
//        if (this.hb.hovered) {
//
//            if (!this.hb.clickStarted) {
//                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));
//                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, Settings.scale));
//
//            } else {
//                ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));
//
//            }
//        } else {
//            ReflectionHacks.setPrivate(this, AbstractCampfireOption.class, "scale", MathHelper.scaleLerpSnap(hackScale, 0.9F * Settings.scale));
//        }
//        super.update();
//    }
}