package net.pearx.purmag.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.PurmagCore;
import net.pearx.purmag.Utils;
import net.pearx.purmag.registries.ItemRegistry;

import java.util.Iterator;
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
        Iterator<String> it = PurmagCore.CrystalReg.registry.keySet().iterator();
        while(it.hasNext())
        {
            String s = it.next();
            NBTTagCompound tag = new NBTTagCompound();
            tag.setString("type", s);
            ItemStack st = new ItemStack(ItemRegistry.crystal_shard);
            st.setTagCompound(tag);
            list.add(st);
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        String add = "";
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
        {
            add = "." + stack.getTagCompound().getString("type");
        }
        return super.getUnlocalizedName(stack) + add;
    }
}
