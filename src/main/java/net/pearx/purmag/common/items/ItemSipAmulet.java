package net.pearx.purmag.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 21.05.17 18:21.
 */
public class ItemSipAmulet extends ItemBase implements IBauble
{
    public ItemSipAmulet()
    {
        setRegistryName(Utils.getRegistryName("sip_amulet"));
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item itm, CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        for(int i = 0; i < 3; i++)
        {
            subItems.add(new ItemStack(this, 1, i));
        }
    }



    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.AMULET;
    }
}
