package net.pearx.purmag.common.worldgen;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by mrAppleXZ on 05.06.17 9:28.
 */
public class WGOreWithDownblock extends WGOre
{
    IBlockState up;

    public WGOreWithDownblock(int minVeinSize, int maxVeinSize, int minY, int maxY, float chance, IBlockState up, IBlockState down, List<Integer> dims, boolean whitelist)
    {
        super(minVeinSize, maxVeinSize, minY, maxY, chance, down, dims, whitelist);
        this.up = up;
    }

    @Override
    public void setState(World world, BlockPos pos, IBlockState state)
    {
        super.setState(world, pos, state);
        world.setBlockState(pos.offset(EnumFacing.UP), up, 2);
    }
}
