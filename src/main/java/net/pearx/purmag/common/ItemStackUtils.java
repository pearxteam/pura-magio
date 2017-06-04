package net.pearx.purmag.common;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by mrAppleXZ on 03.06.17 20:16.
 */
public class ItemStackUtils
{
    public static void extractAll(IItemHandler hand, int slot)
    {
        ItemStack stack = hand.getStackInSlot(0);
        if(!stack.isEmpty())
        {
            hand.extractItem(0, stack.getCount(), false);
        }
    }
}
