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
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.sip.SipType;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        setMaxStackSize(1);
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

    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        for(Map.Entry<String, Integer> entr : stack.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).getStored().entrySet())
        {
            SipType t = PurMag.instance.sip.getType(entr.getKey());
            tooltip.add(t.getFormatting() + t.getDisplayName() + ": " + entr.getValue() + TextFormatting.RESET);
        }
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