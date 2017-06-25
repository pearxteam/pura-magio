package net.pearx.purmag.common.items;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientUtils;
import net.pearx.purmag.client.models.IModelProvider;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.sip.SipType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * Created by mrAppleXZ on 21.05.17 18:21.
 */
public class ItemSipAmulet extends ItemBase implements IBauble, IModelProvider
{
    public ItemSipAmulet()
    {
        setRegistryName("sip_amulet");
        setHasSubtypes(true);
        setMaxStackSize(1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> subItems)
    {
        if(isInCreativeTab(tab))
        {
            for (int i = 0; i < 3; i++)
            {
                subItems.add(new ItemStack(this, 1, i));
            }
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + stack.getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
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

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        for(int i = 0; i < 3; i++)
            ClientUtils.setModelLocation(this, i, "." + i);
    }
}
