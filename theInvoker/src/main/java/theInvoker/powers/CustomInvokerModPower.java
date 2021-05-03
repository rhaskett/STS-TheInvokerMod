package theInvoker.powers;

import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

// Adapted from JorbsMod CustomPower
public abstract class CustomInvokerModPower extends AbstractPower {
    protected final String[] DESCRIPTIONS;

    public CustomInvokerModPower(StaticPowerInfo staticPowerInfo) {
        super();
        this.ID = staticPowerInfo.ID;
        this.name = staticPowerInfo.NAME;
        this.description = staticPowerInfo.DESCRIPTIONS[0];
        this.DESCRIPTIONS = staticPowerInfo.DESCRIPTIONS;
        this.region128 = staticPowerInfo.IMG_84;
        this.region48 = staticPowerInfo.IMG_32;
    }
}

