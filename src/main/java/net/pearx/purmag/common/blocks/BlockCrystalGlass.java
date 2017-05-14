package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 14.05.17 18:30.
 */
public class BlockCrystalGlass extends BlockSingleSip
{
    public BlockCrystalGlass()
    {
        super(Material.GLASS);
        setRegistryName(Utils.getRegistryName("crystal_glass"));
        setUnlocalizedName("crystal_glass");
        setHardness(1f);
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
