package net.pearx.purmag.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.sip.SipType;

/**
 * Created by mrAppleXZ on 24.06.17 11:35.
 */
public class ItemSingleSip extends ItemBase implements IModelProvider
{
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab))
        {
            for (SipType t : PurMag.instance.sip.types)
            {
                items.add(new ItemStack(this, 1, t.getId()));
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.instance.sip.getType(stack.getMetadata()).getDisplayName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(SipType t : PurMag.instance.sip.types)
            ClientUtils.setModelLocation(this, t.getId(), "");
    }
}
