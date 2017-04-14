package net.pearx.purmag.items;

import net.minecraft.item.Item;
import net.pearx.purmag.PMCreativeTab;

/**
 * Created by mrAppleXZ on 11.04.17 8:27.
 */
public class ItemBase extends Item
{
    public ItemBase()
    {
        setCreativeTab(PMCreativeTab.instance);
    }
}
