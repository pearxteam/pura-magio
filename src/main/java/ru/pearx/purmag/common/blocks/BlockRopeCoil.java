package ru.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.blocks.controllers.AxisController;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;

/*
 * Created by mrAppleXZ on 14.11.17 16:50.
 */
public class BlockRopeCoil extends BlockBase
{
    public BlockRopeCoil()
    {
        super("rope_coil", Material.CLOTH);
        setSoundType(SoundType.CLOTH);
        setHardness(0.5f);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AxisController.AXIS);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return AxisController.getStateFromMeta(getDefaultState(), meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return AxisController.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return AxisController.getStateForPlacement(getDefaultState(), facing, placer);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return AxisController.withRotation(state, rot);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        //leave this empty.
    }
}
