package ru.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

/*
 * Created by mrAppleXZ on 29.08.17 21:27.
 */
public class BlockTest extends BlockBase
{
    public BlockTest()
    {
        super("test", Material.ROCK);
    }

    @Override
    public boolean isFullBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state)
    {
        return false;
    }
}
