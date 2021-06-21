package theInvoker.orbs;

import basemod.abstracts.CustomOrb;
import theInvoker.vfx.InvokeHelper;

public abstract class InvokerOrb extends CustomOrb {
    public InvokerOrb(String ID, String NAME, int basePassiveAmount, int baseEvokeAmount, String passiveDescription,
                      String evokeDescription, String imgPath) {
        super(ID, NAME, basePassiveAmount, baseEvokeAmount, passiveDescription, evokeDescription, imgPath);
    }

    @Override
    public void applyFocus() {
        passiveAmount = basePassiveAmount;
        evokeAmount = baseEvokeAmount;
    }
}
