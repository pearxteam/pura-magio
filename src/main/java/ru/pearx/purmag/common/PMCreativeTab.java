package ru.pearx.purmag.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.sip.SipUtils;

/**
 * Created by mrAppleXZ on 08.04.17 18:57.
 */
public class PMCreativeTab extends CreativeTabs
{
    public static PMCreativeTab INSTANCE = new PMCreativeTab();
    public PMCreativeTab()
    {
        super(PurMag.MODID);
    }

    @Override
    public ItemStack getTabIconItem()
    {
        return SipUtils.getStackWithSip(new ItemStack(ItemRegistry.crystal), "flame");
    }
}
