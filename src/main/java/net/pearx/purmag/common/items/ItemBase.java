package net.pearx.purmag.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pearx.purmag.common.PMCreativeTab;

/**
 * Created by mrAppleXZ on 11.04.17 8:27.
 */
public class ItemBase extends Item
{
    public ItemBase()
    {
        setCreativeTab(PMCreativeTab.instance);
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        return new ActionResult(EnumActionResult.PASS, stack);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        return onItemRightClick(player.getHeldItem(hand), world, player, hand);
    }
}
