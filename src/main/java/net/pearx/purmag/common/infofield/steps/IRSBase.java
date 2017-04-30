package net.pearx.purmag.common.infofield.steps;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.client.guis.if_tablet.steps.IRSRenderer;

/**
 * Created by mrAppleXZ on 26.04.17 14:48.
 */
public class IRSBase implements IIfResearchStep
{
    private String unlocDesc;

    public String getUnlocalizedName()
    {
        return null;
    }

    public String getUnlocalizedDescription()
    {
        return unlocDesc;
    }

    public void setUnlocalizedDescription(String s)
    {
        unlocDesc = s;
    }

    @Override
    public String getDescription()
    {
        return getUnlocalizedDescription() == null ? I18n.translateToLocal("if_step.none.desc") : I18n.translateToLocal("if_step." + getUnlocalizedDescription() + ".desc");
    }

    @Override
    public String getDisplayName()
    {
        return I18n.translateToLocal("if_step." + getUnlocalizedName() + ".name");
    }

    @Override
    public IRSRenderer getRenderer()
    {
        return null;
    }
}
