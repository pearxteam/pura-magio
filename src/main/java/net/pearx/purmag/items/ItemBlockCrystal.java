package net.pearx.purmag.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.pearx.purmag.PMCreativeTab;
import net.pearx.purmag.registries.BlockRegistry;

/**
 * Created by mrAppleXZ on 10.04.17 23:00.
 */
public class ItemBlockCrystal extends ItemBlock
{
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

    public ItemBlockCrystal()
    {
        super(BlockRegistry.crystal);
        setRegistryName(block.getRegistryName());
        setUnlocalizedName(block.getUnlocalizedName());
        setCreativeTab(PMCreativeTab.instance);
    }
}
