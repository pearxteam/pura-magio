package net.pearx.purmag.common.blocks.controllers;

import jdk.nashorn.internal.objects.annotations.Function;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by mrAppleXZ on 17.05.17 7:50.
 */
@FunctionalInterface
public interface ICanConnect
{
    boolean canConnect(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side);
}
