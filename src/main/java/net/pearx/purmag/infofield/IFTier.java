package net.pearx.purmag.infofield;

import net.minecraft.client.resources.I18n;

/**
 * Created by mrAppleXZ on 20.04.17 10:30.
 */
public class IFTier
{
    private int tier;

    public IFTier(int tier)
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

    public String getDisplayName()
    {
        return I18n.format("if_tier." + tier + ".name");
    }
}
