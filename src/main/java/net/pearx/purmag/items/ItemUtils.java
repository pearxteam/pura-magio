package net.pearx.purmag.items;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.pearx.purmag.PMCreativeTab;

/**
 * Created by mrAppleXZ on 08.04.17 18:50.
 */
public class ItemUtils
{
    public static Item itemFromBlock(Block b)
    {
        return new ItemBlock(b).setRegistryName(b.getRegistryName()).setCreativeTab(PMCreativeTab.instance);
    }
}
