package net.pearx.purmag.common.items;

import baubles.api.BaubleType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 22.05.17 18:55.
 */
public class ItemGlove extends ItemBase
{
    public ItemGlove()
    {
        setRegistryName(Utils.getRegistryName("glove"));
        setUnlocalizedName("glove");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
        ItemStack glove = playerIn.getHeldItem(handIn);
        if (handIn == EnumHand.OFF_HAND)
        {
            //todo sound effect and particles
            ItemStack shard = playerIn.getHeldItem(EnumHand.MAIN_HAND);
            if (shard.getItem() == ItemRegistry.crystal_shard)
            {
                int shrink = playerIn.isSneaking() ? shard.getCount() : 1;
                if (ItemSipAmulet.checkForAmulet(playerIn))
                {
                    ItemStack amulet = ItemUtils.getBauble(playerIn, BaubleType.AMULET.getValidSlots()[0]);
                    amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).add(PurMag.instance.sip.getType(shard.getMetadata()).getName(), shrink);
                    ItemUtils.setBauble(playerIn, BaubleType.AMULET.getValidSlots()[0], amulet);
                }
                playerIn.getHeldItem(EnumHand.MAIN_HAND).shrink(shrink);
                return new ActionResult<>(EnumActionResult.SUCCESS, glove);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, glove);
    }
}
