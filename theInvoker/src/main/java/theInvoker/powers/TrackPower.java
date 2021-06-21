package theInvoker.powers;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;

public class TrackPower extends CustomInvokerModPower {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(TrackPower.class);

    private static final int MAX_PENNIES = 50;

    public TrackPower(AbstractCreature owner, final int amount) {
        super(STATIC);

        this.owner = owner;
        this.amount = amount;
        if (this.amount >= 999) {
            this.amount = 999;
        }
        this.type = PowerType.DEBUFF;

        updateDescription();
    }

    @Override
    public void onDeath() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractCreature m = this.owner;

        int pennies = Math.min(this.amount, MAX_PENNIES);
        for(int i = 0; i < pennies; ++i)
            AbstractDungeon.effectList.add(new GainPennyEffect(p, m.hb.cX, m.hb.cY, p.hb.cX, p.hb.cY, true));

        if (this.amount > MAX_PENNIES)
            this.addToBot(new GainGoldAction(this.amount - MAX_PENNIES));
    }

    @Override
    public void atEndOfRound() {
        this.addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }


    public void updateDescription() {
        this.description = String.format(DESCRIPTIONS[0], this.amount);
    }

//    @Override
//    public void stackPower(AbstractPower newInstanceOfSamePower) {
//        if(newInstanceOfSamePower.amount >= this.amount)
//            this.amount = newInstanceOfSamePower.amount;
//    }
}
