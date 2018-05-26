package ru.pearx.purmag.common.items.base;

import ru.pearx.purmag.common.PMCreativeTab;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 14.07.17 11:45.
 */
public class ItemBase extends ru.pearx.carbide.mc.common.items.ItemBase
{
    public ItemBase()
    {
        setCreativeTab(PMCreativeTab.INSTANCE);
    }

    public ItemBase(String name)
    {
        this();
        setRegistryName(name);
        setUnlocalizedName(Utils.getUnlocalizedName(name));
    }
}
