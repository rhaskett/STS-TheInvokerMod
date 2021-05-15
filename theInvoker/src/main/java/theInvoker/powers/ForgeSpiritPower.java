package theInvoker.powers;

import basemod.interfaces.OnPowersModifiedSubscriber;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theInvoker.actions.ForgeSpiritAction;

// Attack a random enemy for !D! damage at the end of each turn.
public class ForgeSpiritPower extends CustomInvokerModPower implements OnPowersModifiedSubscriber {
    public static final StaticPowerInfo STATIC = StaticPowerInfo.Load(ForgeSpiritPower.class);
    public static final String POWER_ID = STATIC.ID;

    private final AbstractCard backingCard;

    private static long instanceCounter = 0;

    public ForgeSpiritPower(final AbstractCreature owner, final AbstractCard backingCard) {
        super(STATIC);
        ID = POWER_ID + "__" + (++instanceCounter);

        this.type = AbstractPower.PowerType.BUFF;
        this.owner = owner;
        this.backingCard = backingCard.makeStatEquivalentCopy();

        updateDescription();
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer) {
            // Deferring the random selection to a separate action is important for preventing multiple Forge Spirits
            // from picking the same enemy and then having some of them attack a corpse.
            AbstractDungeon.actionManager.addToBottom(new ForgeSpiritAction(backingCard));
        }
    }

    @Override
    public void updateDescription() {
        backingCard.applyPowers();
        amount = backingCard.magicNumber;
        description = String.format(DESCRIPTIONS[0], backingCard.isMagicNumberModified ? "#g" : "#b", backingCard.magicNumber);
    }

    @Override
    public void receivePowersModified() {
        updateDescription();
    }
}

