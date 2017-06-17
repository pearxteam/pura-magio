package net.pearx.purmag.common;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

/**
 * Created by mrAppleXZ on 03.06.17 20:16.
 */
public class ItemStackUtils
{
    public static void extractAll(IItemHandler hand, int slot)
    {
        ItemStack stack = hand.getStackInSlot(slot);
        if(!stack.isEmpty())
        {
            hand.extractItem(slot, stack.getCount(), false);
        }
    }

    public static void drop(IItemHandler hand, World world, BlockPos pos)
    {
        for(int i = 0; i < hand.getSlots(); i++)
        {
            ItemStack stack = hand.getStackInSlot(i);
            if(!stack.isEmpty())
            {
                world.spawnEntity(new EntityItem(world, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, stack));
            }
        }
    }
}
