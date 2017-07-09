package net.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.blocks.controllers.CTMController;
import net.pearx.purmag.common.blocks.controllers.FacingController;
import net.pearx.purmag.common.blocks.properties.PropertyIfTier;
import net.pearx.purmag.common.infofield.IfTier;
import net.pearx.purmag.common.tiles.TileWallIfTablet;

import javax.annotation.Nullable;
import java.util.Map;

/*
 * Created by mrAppleXZ on 06.07.17 10:55.
 */
public class BlockWallIfTablet extends BlockBase
{
    public static final PropertyIfTier IF_TIER = new PropertyIfTier("if_tier");

    public BlockWallIfTablet()
    {
        super(Material.CIRCUITS);
        setRegistryName("wall_if_tablet");
        setHardness(3);
        setUnlocalizedName("wall_if_tablet");
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
    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new ExtendedBlockState(this, new IProperty[] {FacingController.FACING_H}, new IUnlistedProperty[] {IF_TIER});
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return FacingController.getMetaFromStateHorizontal(state);
    }


    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return FacingController.getStateFromMetaHorizontal(getDefaultState(), meta);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return getDefaultState().withProperty(FacingController.FACING_H, facing);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = world.getTileEntity(pos);
        if(te != null && te instanceof TileWallIfTablet)
        {
            ((TileWallIfTablet) te).setTier(stack.getMetadata());
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (IfTier t : PurMag.INSTANCE.if_registry.tiers)
        {
            items.add(new ItemStack(this, 1, t.getTier()));
        }
    }

    private static boolean canAttachToBlock(World world, BlockPos pos, EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        return facing.getHorizontalIndex() != -1 && !isExceptBlockForAttachWithPiston(state.getBlock()) && state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.SOLID;
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        return canAttachToBlock(world, pos.offset(side.getOpposite()), side);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing face = state.getValue(FacingController.FACING_H);
        if(!canAttachToBlock(world, pos.offset(face.getOpposite()), face))
        {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            TileEntity te = world.getTileEntity(pos);
            if(te != null && te instanceof TileWallIfTablet)
                PurMag.proxy.openIfTablet(((TileWallIfTablet) te).getTier());
        }
        return true;
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state)
    {
        return new TileWallIfTablet();
    }

    @Override
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
    {
        TileEntity te = world.getTileEntity(pos);
        int tier = 0;
        if(te != null && te instanceof TileWallIfTablet)
            tier = ((TileWallIfTablet) te).getTier();
        return new ItemStack(this, 1, tier);
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror)
    {
        return FacingController.withMirrorHorizontal(state, mirror);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return FacingController.withRotationHoizontal(state, rot);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if(state instanceof IExtendedBlockState)
        {
            TileEntity te = world.getTileEntity(pos);
            if(te != null && te instanceof TileWallIfTablet)
            {
                return ((IExtendedBlockState)state).withProperty(IF_TIER, ((TileWallIfTablet) te).getTier());
            }
        }
        return state;
    }

    //todo bounding box
}
