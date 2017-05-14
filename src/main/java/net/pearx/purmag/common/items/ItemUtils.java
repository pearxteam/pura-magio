package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.PMCreativeTab;

/**
 * Created by mrAppleXZ on 08.04.17 18:50.
 */
public class ItemUtils
{
    public static Item getItemFromBlock(Block b)
    {
        return new ItemBlock(b).setRegistryName(b.getRegistryName()).setCreativeTab(PMCreativeTab.instance);
    }

    public static ItemStack getItemWithSip(String sip, Item itm)
    {
        ItemStack stack = new ItemStack(itm);
        return new ItemStack(itm, 1, PurMag.instance.sip.getType(sip).getId());
    }
}
