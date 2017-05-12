package net.pearx.purmag.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pearx.purmag.common.PMCreativeTab;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 11.04.17 8:27.
 */
public class ItemBase extends Item
{
    @Nullable
    @Override
    public CreativeTabs getCreativeTab()
    {
        return PMCreativeTab.instance;
    }
}
