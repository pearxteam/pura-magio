package net.pearx.purmag.common.infofield;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 20.04.17 10:30.
 */
public class IfTier
{
    private int tier;

    public IfTier(int tier)
    {
        setTier(tier);
    }

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        this.tier = tier;
    }

    @SideOnly(Side.CLIENT)
    public String getDisplayName()
    {
        return I18n.format("if_tier." + tier + ".name");
    }
}
