package ru.pearx.purmag.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.ClientUtils;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.items.base.ItemBase;

/**
 * Created by mrAppleXZ on 13.04.17 21:54.
 */
public class ItemIfTablet extends ItemBase
{
    public ItemIfTablet()
    {
        super("if_tablet");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World w, EntityPlayer p, EnumHand hand)
    {
        ItemStack stack = p.getHeldItem(hand);
        if (w.isRemote)
        {
            PurMag.proxy.openIfTablet(stack.getItemDamage());
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> sub)
    {
        if (isInCreativeTab(tab))
        {
            for (IfTier t : PurMag.INSTANCE.getIfRegistry().tiers)
            {
                sub.add(new ItemStack(this, 1, t.getTier()));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName(stack) + "." + stack.getItemDamage();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for (IfTier t : PurMag.INSTANCE.getIfRegistry().tiers)
        {
            ClientUtils.setModelLocationDirBased(this, t.getTier());
        }
    }
}
