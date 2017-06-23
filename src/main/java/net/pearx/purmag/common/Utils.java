package net.pearx.purmag.common;

import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 21.04.17 18:07.
 */
public class Utils
{
    public static ResourceLocation getRegistryName(String name)
    {
        return new ResourceLocation(PurMag.ModId, name);
    }
}
