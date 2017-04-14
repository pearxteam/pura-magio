package net.pearx.purmag.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatisticsManager;
import net.pearx.purmag.PMCreativeTab;
import net.pearx.purmag.registries.BlockRegistry;

/**
 * Created by mrAppleXZ on 10.04.17 23:00.
 */
public class ItemBlockCrystal extends ItemBlock
{
    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        String add = "";
        if(stack.hasTagCompound() && stack.getTagCompound().hasKey("type"))
        {
            return add + I18n.format("tile.crystal.name", I18n.format("sip." + stack.getTagCompound().getString("type")));
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
}
