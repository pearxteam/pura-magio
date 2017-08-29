package ru.pearx.purmag.common;

import net.minecraft.util.ResourceLocation;
import ru.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 21.04.17 18:07.
 */
public class Utils
{
    public static ResourceLocation getResourceLocation(String name)
    {
        return new ResourceLocation(PurMag.MODID, name);
    }

    public static String getUnlocalizedName(String un)
    {
        return PurMag.MODID + "." + un;
    }
}
