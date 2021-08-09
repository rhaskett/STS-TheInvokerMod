package theInvoker.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theInvoker.powers.CustomSetMovePower;

@SpirePatch(
        clz = AbstractMonster.class,
        method = "setMove",
        paramtypez={String.class, byte.class, AbstractMonster.Intent.class, int.class, int.class, boolean.class}
)
public class SetMoveActionPatch {
    public static void Postfix(AbstractMonster __instance) {
        for (AbstractPower power : __instance.powers)
            if(power instanceof CustomSetMovePower)
                ((CustomSetMovePower) power).atSetMove();
    }
}