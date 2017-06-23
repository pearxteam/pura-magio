package net.pearx.purmag.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
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
