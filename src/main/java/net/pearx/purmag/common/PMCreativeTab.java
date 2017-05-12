package net.pearx.purmag.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.items.ItemBlockCrystal;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.ItemUtils;

/**
 * Created by mrAppleXZ on 08.04.17 18:57.
 */
public class PMCreativeTab extends CreativeTabs
{
    public static PMCreativeTab instance = new PMCreativeTab();
    public PMCreativeTab()
    {
        super(PurMag.ModId);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return ItemUtils.getItemWithSip("flame", ItemRegistry.crystal);
    }
}
