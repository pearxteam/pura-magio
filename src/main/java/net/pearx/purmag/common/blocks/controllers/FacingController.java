package net.pearx.purmag.common.blocks.controllers;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;

/**
 * Created by mrAppleXZ on 04.06.17 11:34.
 */
public class FacingController
{
    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public IBlockState getStateFromMeta(IBlockState state, int meta)
    {
        return state.withProperty(FACING, EnumFacing.values()[meta]);
    }


    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(FACING).ordinal();
    }

    public IBlockState getStateForPlacement(IBlockState state, EntityLivingBase placer)
    {
        return state.withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }
}
