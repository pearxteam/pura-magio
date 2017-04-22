package net.pearx.purmag.common;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
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

    public static NBTTagCompound posToNbt(BlockPos pos)
    {
       NBTTagCompound tag = new NBTTagCompound();
       tag.setInteger("x", pos.getX());
       tag.setInteger("y", pos.getY());
       tag.setInteger("z", pos.getZ());
       return tag;
    }

    public static BlockPos posFromNbt(NBTTagCompound tag)
    {
        return new BlockPos(tag.getInteger("x"), tag.getInteger("y"), tag.getInteger("z"));
    }
}
