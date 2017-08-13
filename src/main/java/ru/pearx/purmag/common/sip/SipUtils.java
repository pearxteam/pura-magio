package ru.pearx.purmag.common.sip;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.tiles.TileSingleSip;

/*
 * Created by mrAppleXZ on 09.07.17 8:57.
 */

/**
 * Utilities for getting the single sip type in stacks or blocks.
 */
public class SipUtils
{
    public static ItemStack getStackWithSip(ItemStack base, String type)
    {
        if(!base.hasTagCompound())
            base.setTagCompound(new NBTTagCompound());
        base.getTagCompound().setString("sip_type", type);
        return base;
    }

    public static String getSipInStack(ItemStack stack)
    {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("sip_type"))
            return stack.getTagCompound().getString("sip_type");
        return PurMag.INSTANCE.sip.getDefaultType().getName();
    }

    public static String getSipInBlock(IBlockAccess access, BlockPos pos)
    {
        TileEntity te = access.getTileEntity(pos);
        if(te != null && te instanceof TileSingleSip)
        {
            TileSingleSip tss = (TileSingleSip) te;
            return tss.getType();
        }
        return PurMag.INSTANCE.sip.getDefaultType().getName();
    }

    public static void setSipInBlock(IBlockAccess access, BlockPos pos, String type, boolean sync)
    {
        TileEntity te = access.getTileEntity(pos);
        if(te != null && te instanceof TileSingleSip)
        {
            ((TileSingleSip) te).setType(type, sync);
        }
    }
}
