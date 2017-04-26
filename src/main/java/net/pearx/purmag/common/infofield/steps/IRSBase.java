package net.pearx.purmag.common.infofield.steps;

import net.minecraft.client.resources.I18n;

/**
 * Created by mrAppleXZ on 26.04.17 14:48.
 */
public class IRSBase implements IIfResearchStep
{
    private String unloc;

    public String getUnlocalizedName()
    {
        return unloc;
    }

    public void setUnlocalizedName(String s)
    {
        unloc = s;
    }

    @Override
    public String getDescription()
    {
        return getUnlocalizedName() == null ? I18n.format("if_step.none.desc") : I18n.format("if_step." + unloc + ".desc");
    }
}
