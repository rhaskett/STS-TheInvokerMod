package theInvoker.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theInvoker.vfx.InvokeHelper;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "update"
)
public class EnergyPanelUpdatePatch {
    public static void Prefix(EnergyPanel __instance) {
        if (InvokeHelper.doStuff) {
            InvokeHelper.update();
        }
    }
}