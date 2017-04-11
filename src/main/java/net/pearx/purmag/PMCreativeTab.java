package net.pearx.purmag;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.pearx.purmag.registries.ItemRegistry;

/**
 * Created by mrAppleXZ on 08.04.17 18:57.
 */
public class PMCreativeTab extends CreativeTabs
{
    public static PMCreativeTab instance = new PMCreativeTab();
    public PMCreativeTab()
    {
        super(PMCore.ModId);
    }

    @Override
    public Item getTabIconItem()
    {
        return ItemRegistry.crystal;
    }
}
