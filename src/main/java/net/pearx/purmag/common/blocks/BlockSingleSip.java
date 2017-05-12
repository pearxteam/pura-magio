package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.tiles.TileSingleSip;

/**
 * Created by mrAppleXZ on 12.05.17 22:00.
 */
public class BlockSingleSip extends BlockBase
{
    public BlockSingleSip(Material materialIn)
    {
        super(materialIn);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileSingleSip();
    }

    @Override
    public void getSubBlocks(Item itemIn, CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for(SipType t : PurMag.instance.sip.types)
        {
            list.add(ItemUtils.getItemWithSip(t.getName(), Item.getItemFromBlock(this)));
        }
    }
}
