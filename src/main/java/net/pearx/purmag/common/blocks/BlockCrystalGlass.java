package net.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState off = blockAccess.getBlockState(pos.offset(side));
        if(off.equals(blockState))
            return false;
        return super.shouldSideBeRendered(blockState, blockAccess, pos, side);
    }
}
