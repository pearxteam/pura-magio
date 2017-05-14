package net.pearx.purmag.common.items;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.sip.SipTypeRegistry;

import java.util.List;

/**
 * Created by mrAppleXZ on 11.04.17 8:24.
 */
public class ItemCrystalShard extends ItemBase
{
    public ItemCrystalShard()
    {
        setRegistryName(Utils.getRegistryName("crystal_shard"));
        setUnlocalizedName("crystal_shard");
        setHasSubtypes(true);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            list.add(new ItemStack(ItemRegistry.crystal_shard, 1, t.getId()));
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.instance.sip.getType(stack.getMetadata()).getDisplayName());
    }
}
