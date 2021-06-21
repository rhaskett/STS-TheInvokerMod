package theInvoker.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import theInvoker.InvokerMod;
import theInvoker.util.TextureLoader;

public class BottleRelic extends CustomRelic implements ClickableRelic {
    public static final String ID = InvokerMod.makeID(BottleRelic.class.getSimpleName()); // TODO relic visuals
    private static final Texture IMG = TextureLoader.getTexture(InvokerMod.makeRelicPath("default_clickable_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(InvokerMod.makeRelicOutlinePath("default_clickable_relic.png"));

    private final int USES = 3;
    private boolean isPlayerTurn = false; // Only activateable during our turn, not the enemies'.

    private final int ENERGY_GAIN = 1;
    private final int HEALTH_GAIN = 5;

    public BottleRelic() {
        super(ID, IMG, OUTLINE, RelicTier.SHOP, LandingSound.MAGICAL);

        tips.clear();
        tips.add(new PowerTip(name, description));
        this.counter = USES;
    }

    public void setCounter(int setCounter) {
        if (setCounter == -2) {
            this.usedUp();
            this.counter = -2;
        }
    }


    @Override
    public void onRightClick() {
        if (!isObtained || !isPlayerTurn || this.counter <= 0) {
            return;
        }
        
        if (AbstractDungeon.getCurrRoom() != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
            flash();
            // TODO Sound?

            this.addToBot(new GainEnergyAction(ENERGY_GAIN));

            AbstractPlayer p = AbstractDungeon.player;
            this.addToBot(new HealAction(p, p, HEALTH_GAIN));
            this.counter -= 1;
        }

        if (this.counter == 0)
            stopPulse();
            this.usedUp();
            this.counter = -2;
    }
    
    @Override
    public void atPreBattle() {
        if (this.counter > 0)
            beginLongPulse();
    }

    public void atTurnStart() {
        isPlayerTurn = true; // It's our turn!
        if (this.counter > 0)
            beginLongPulse(); // Pulse while the player can click on it.
    }
    
    @Override
    public void onPlayerEndTurn() {
        isPlayerTurn = false; // Not our turn now.
        if (this.counter > 0)
            stopPulse();
    }
    

    @Override
    public void onVictory() {
        if (this.counter > 0)
            stopPulse();
    }

    // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
