package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.sip.SipType;

/**
 * Created by mrAppleXZ on 14.05.17 18:51.
 */
public class ItemBlockSingleSip extends ItemBlockBase
{
    public ItemBlockSingleSip(Block block)
    {
        super(block);
        setHasSubtypes(true);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.instance.sip.getType(stack.getMetadata()).getDisplayName());
    }
}
