package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;
import ru.pearx.purmag.common.items.ItemRegistry;

/**
 * Created by mrAppleXZ on 03.06.17 19:09.
 */
public class TileTranslationDesk extends TileAbstractSingleItem
{
    @Override
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() == ItemRegistry.papyrus;
    }
}
