package net.pearx.purmag.common.items;

import net.minecraft.util.text.translation.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.PMCreativeTab;
import net.pearx.purmag.common.blocks.BlockRegistry;

/**
 * Created by mrAppleXZ on 10.04.17 23:00.
 */
public class ItemBlockCrystal extends ItemBlock
{
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
        {
            return I18n.translateToLocalFormatted(getUnlocalizedName() + ".name", PurMag.instance.sip.getType(stack.getTagCompound().getString("type")).getDisplayName());
        }
        return super.getItemStackDisplayName(stack);
    }

    public ItemBlockCrystal()
    {
        super(BlockRegistry.crystal);
        setRegistryName(block.getRegistryName());
        setUnlocalizedName(block.getUnlocalizedName());
        setCreativeTab(PMCreativeTab.instance);
    }

    public static ItemStack getCrystalWithSip(String sip)
    {
        //todo: power
        ItemStack stack = new ItemStack(ItemRegistry.crystal);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("type", sip);
        stack.setTagCompound(tag);
        return stack;
    }
}
