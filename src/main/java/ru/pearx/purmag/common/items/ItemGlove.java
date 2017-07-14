package ru.pearx.purmag.common.items;

import baubles.api.BaubleType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.SoundRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 22.05.17 18:55.
 */
public class ItemGlove extends ItemBase
{
    //todo SOMEONE, PLS, MAKE NORMAL GLOVE TEXTURE ;(
    public ItemGlove()
    {
        setRegistryName("glove");
        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {

        ItemStack glove = playerIn.getHeldItem(handIn);
        if (handIn == EnumHand.OFF_HAND)
        {
            ItemStack shard = playerIn.getHeldItem(EnumHand.MAIN_HAND);
            if (shard.getItem() == ItemRegistry.crystal_shard)
            {
                int shrink = playerIn.isSneaking() ? shard.getCount() : 1;
                playerIn.playSound(SoundRegistry.GLASS, 1, 1);
                if (ItemSipAmulet.checkForAmulet(playerIn))
                {
                    ItemStack amulet = ItemUtils.getBauble(playerIn, BaubleType.AMULET.getValidSlots()[0]);
                    amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).add(SipUtils.getSipInStack(shard), shrink);
                    ItemUtils.setBauble(playerIn, BaubleType.AMULET.getValidSlots()[0], amulet);
                }
                playerIn.getHeldItem(EnumHand.MAIN_HAND).shrink(shrink);
                return new ActionResult<>(EnumActionResult.SUCCESS, glove);
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, glove);
    }
}
