package net.pearx.purmag.common.items;

import net.minecraft.util.ResourceLocation;

/**
 * Created by mrAppleXZ on 20.05.17 9:37.
 */
public class ItemSimple extends ItemBase
{
    public ItemSimple(ResourceLocation loc)
    {
        setRegistryName(loc);
        setUnlocalizedName(loc.getResourcePath());
    }
}
