package net.pearx.purmag.common.infofield.steps;

import net.minecraft.client.resources.I18n;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;

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
        return getUnlocalizedDescription() == null ? I18n.format("if_step.none.desc") : I18n.format("if_step." + getUnlocalizedDescription() + ".desc");
    }

    @Override
    public String getDisplayName()
    {
        return I18n.format("if_step." + getUnlocalizedName() + ".name");
    }

    @Override
    public IGuiDrawable getIcon()
    {
        return null;
    }
}