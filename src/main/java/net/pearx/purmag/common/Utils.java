package net.pearx.purmag.common;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 21.04.17 18:07.
 */
public class Utils
{

    public static ResourceLocation getRegistryName(String name)
    {
        return new ResourceLocation(PurMag.MODID, name);
    }
}
