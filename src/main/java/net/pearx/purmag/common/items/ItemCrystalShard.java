package net.pearx.purmag.common.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> list)
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("type", t.getName());
            ItemStack st = new ItemStack(ItemRegistry.crystal_shard);
            st.setTagCompound(tag);
            list.add(st);
        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
        {
            return I18n.format(getUnlocalizedName() + ".name", PurMag.instance.sip.getType(stack.getTagCompound().getString("type")).getDisplayName());
        }
        return super.getItemStackDisplayName(stack);
    }
}
