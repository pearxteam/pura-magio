package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;

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
    @SideOnly(Side.CLIENT)
    public String getDescription()
    {
        return getUnlocalizedDescription() == null ? I18n.format("if_step.none.desc") : I18n.format("if_step." + getUnlocalizedDescription() + ".desc");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getDisplayName()
    {
        return I18n.format("if_step." + getUnlocalizedName() + ".name");
    }

    @Override
    public IRSRenderer getRenderer()
    {
        return null;
    }
}
