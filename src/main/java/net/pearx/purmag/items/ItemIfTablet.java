package net.pearx.purmag.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pearx.purmag.client.guis.PmGui;
import net.pearx.purmag.client.guis.if_tablet.GuiIfTabletContainer;

/**
 * Created by mrAppleXZ on 13.04.17 21:54.
 */
public class ItemIfTablet extends ItemBase
{
    public ItemIfTablet()
    {
        setUnlocalizedName("if_tablet");
        setRegistryName("if_tablet");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World w, EntityPlayer p, EnumHand hand)
    {
        if(w.isRemote)
            Minecraft.getMinecraft().displayGuiScreen(new PmGui(new GuiIfTabletContainer()));
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
}
