package net.pearx.purmag.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.pearx.purmag.common.PMCreativeTab;

/**
 * Created by mrAppleXZ on 14.05.17 18:12.
 */
public class ItemBlockBase extends ItemBlock
{
    public ItemBlockBase(Block block)
    {
        super(block);
        setCreativeTab(PMCreativeTab.instance);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }
}
