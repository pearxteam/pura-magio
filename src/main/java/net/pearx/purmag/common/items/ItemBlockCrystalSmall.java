package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.pearx.purmag.common.blocks.BlockCrystalSmall;
import net.pearx.purmag.common.blocks.BlockRegistry;

/**
 * Created by mrAppleXZ on 04.06.17 18:49.
 */
public class ItemBlockCrystalSmall extends ItemBlockBase
{
    public ItemBlockCrystalSmall()
    {
        super(BlockRegistry.crystal_small);
        setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return getUnlocalizedName() + "." + BlockCrystalSmall.Type.values()[stack.getMetadata()].getName();
    }
}
