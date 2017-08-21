package ru.pearx.purmag.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.ClientUtils;
import ru.pearx.purmag.common.sip.SipType;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 24.06.17 11:35.
 */
public class ItemSingleSip extends ItemBase
{
    public ItemSingleSip()
    {
    }

    public ItemSingleSip(String name)
    {
        super(name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
    {
        if(isInCreativeTab(tab))
        {
            for (SipType t : PurMag.INSTANCE.getSipRegistry().getTypes())
            {
                items.add(SipUtils.getStackWithSip(new ItemStack(this), t.getName()));
            }
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.INSTANCE.getSipRegistry().getType(SipUtils.getSipInStack(stack)).getDisplayName());
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        ClientUtils.setModelLocation(this);
    }
}
