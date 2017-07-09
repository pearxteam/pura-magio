package net.pearx.purmag.common.blocks.controllers;

import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;

/**
 * Created by mrAppleXZ on 04.06.17 11:34.
 */
public class FacingController
{
    public static final PropertyDirection FACING_H = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    public static IBlockState getStateFromMetaHorizontal(IBlockState state, int meta)
    {
        return state.withProperty(FACING_H, EnumFacing.getHorizontal(meta));
    }

    public static int getMetaFromStateHorizontal(IBlockState state)
    {
        return state.getValue(FACING_H).getHorizontalIndex();
    }

    public static IBlockState getStateForPlacementHorizontal(IBlockState state, EntityLivingBase placer)
    {
        return state.withProperty(FACING_H, placer.getHorizontalFacing().getOpposite());
    }

    public static IBlockState withMirrorHorizontal(IBlockState state, Mirror mirror)
    {
        return state.withProperty(FacingController.FACING_H, mirror.mirror(state.getValue(FacingController.FACING_H)));
    }

    public static IBlockState withRotationHoizontal(IBlockState state, Rotation rot)
    {
        return state.withProperty(FacingController.FACING_H, rot.rotate(state.getValue(FacingController.FACING_H)));
    }
}
