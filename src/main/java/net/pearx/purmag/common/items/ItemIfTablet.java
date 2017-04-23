package net.pearx.purmag.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.infofield.IfTier;

import java.util.List;

/**
 * Created by mrAppleXZ on 13.04.17 21:54.
 */
public class ItemIfTablet extends ItemBase
{
    public ItemIfTablet()
    {
        setUnlocalizedName("if_tablet");
        setRegistryName("if_tablet");
        setHasSubtypes(true);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World w, EntityPlayer p, EnumHand hand)
    {
        if(!w.isRemote)
            PurMag.proxy.openIfTablet(p, stack.getItemDamage());
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    public void getSubItems(Item itm, CreativeTabs tab, List<ItemStack> sub)
    {
        for (IfTier t : PurMag.instance.if_registry.tiers)
        {
            sub.add(new ItemStack(itm, 1, t.getTier()));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + stack.getItemDamage();
    }


}
