package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;

/*
 * Created by mrAppleXZ on 17.08.17 21:23.
 */
public class TileMicroscope extends TileAbstractSingleItem
{
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return !stack.isEmpty();
    }
}
