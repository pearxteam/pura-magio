package net.pearx.purmag.common.items;

import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 08.04.17 18:50.
 */
public class ItemUtils
{
    public static ItemStack getBauble(EntityPlayer p, int slot)
    {
        return p.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null).getStackInSlot(slot);
    }

    public static void setBauble(EntityPlayer p, int slot, ItemStack stack)
    {
        IBaublesItemHandler hand = p.getCapability(BaublesCapabilities.CAPABILITY_BAUBLES, null);
        hand.setStackInSlot(slot, stack);
    }
}
