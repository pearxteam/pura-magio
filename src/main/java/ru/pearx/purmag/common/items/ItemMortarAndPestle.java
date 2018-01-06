package ru.pearx.purmag.common.items;

import ru.pearx.purmag.common.items.base.ItemBase;

/*
 * Created by mrAppleXZ on 02.09.17 13:59.
 */
public class ItemMortarAndPestle extends ItemBase
{
    public ItemMortarAndPestle()
    {
        super("mortar_and_pestle");
        setContainerItem(this);
        setMaxStackSize(1);
    }
}
