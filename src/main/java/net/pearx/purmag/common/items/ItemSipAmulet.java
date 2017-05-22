package net.pearx.purmag.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Tuple;
import net.minecraftforge.common.util.Constants;
import net.pearx.purmag.common.Utils;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 21.05.17 18:21.
 */
public class ItemSipAmulet extends ItemBase implements IBauble
{
    public ItemSipAmulet()
    {
        setRegistryName(Utils.getRegistryName("sip_amulet"));
        setHasSubtypes(true);
        setUnlocalizedName("sip_amulet");
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
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + stack.getMetadata();
    }

    public static void addSip(ItemStack stack, String type, int count)
    {
        NBTTagCompound nbt;
        if(stack.hasTagCompound())
            nbt = stack.getTagCompound();
        else
            nbt = new NBTTagCompound();

        NBTTagCompound sips;
        if(nbt.hasKey("sips"))
            sips = nbt.getCompoundTag("sips");
        else
            sips = new NBTTagCompound();
        int base = 0;
        if(sips.hasKey(type))
            base = sips.getInteger(type);
        sips.setInteger(type, base + count);
        nbt.setTag("sips", sips);

        stack.setTagCompound(nbt);
    }

    public static void clearSip(ItemStack stack)
    {
        NBTTagCompound nbt;
        if(stack.hasTagCompound())
            nbt = stack.getTagCompound();
        else
            nbt = new NBTTagCompound();

        nbt.removeTag("sips");

        stack.setTagCompound(nbt);
    }

    public static HashMap<String, Integer> getSips(ItemStack stack)
    {
        HashMap<String, Integer> map = new HashMap<>();
        if(stack.hasTagCompound())
        {
            NBTTagCompound nbt = stack.getTagCompound();
            if(nbt.hasKey("sips"))
            {
                NBTTagCompound sips = nbt.getCompoundTag("sips");
                for(String key : sips.getKeySet())
                {
                    map.put(key, sips.getInteger(key));
                }
            }
        }
        return map;
    }

    public static boolean checkForAmulet(EntityPlayer p)
    {
        ItemStack amul = ItemUtils.getBauble(p, BaubleType.AMULET.getValidSlots()[0]);
        if(amul != ItemStack.EMPTY && amul.getItem() == ItemRegistry.sip_amulet)
        {
            return true;
        }
        return false;
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack)
    {
        return BaubleType.AMULET;
    }
}
