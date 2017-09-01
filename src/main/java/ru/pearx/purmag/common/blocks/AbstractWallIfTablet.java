package ru.pearx.purmag.common.blocks;

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
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.blocks.properties.PropertyIfTier;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.tiles.TileWallIfTablet;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/*
 * Created by mrAppleXZ on 06.07.17 10:55.
 */
public abstract class AbstractWallIfTablet extends BlockBase
{
    public static final PropertyIfTier IF_TIER = new PropertyIfTier("if_tier");
    public static final Map<EnumFacing, AxisAlignedBB> BOUNDING_BOXES = new HashMap<>();

    static
    {
        BOUNDING_BOXES.put(EnumFacing.NORTH, new AxisAlignedBB(0.15f, 0.15f, 0.95f, 0.85f, 0.85f, 1));
        BOUNDING_BOXES.put(EnumFacing.SOUTH, new AxisAlignedBB(0.15f, 0.15f, 0.05f, 0.85f, 0.85f, 0));
        BOUNDING_BOXES.put(EnumFacing.WEST, new AxisAlignedBB(0.95f, 0.15f, 0.15f, 1, 0.85f, 0.85f));
        BOUNDING_BOXES.put(EnumFacing.EAST, new AxisAlignedBB(0.05f, 0.15f, 0.15f, 0, 0.85f, 0.85f));
    }

    public AbstractWallIfTablet(String name)
    {
        super(name, Material.CIRCUITS);
        setHardness(1);
        setLightLevel(0.07f);
    }

    private static boolean canAttachToBlock(World world, BlockPos pos, EnumFacing facing)
    {
        IBlockState state = world.getBlockState(pos);
        return facing.getHorizontalIndex() != -1 && !isExceptBlockForAttachWithPiston(state.getBlock()) && state.getBlockFaceShape(world, pos, facing) == BlockFaceShape.SOLID;
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

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new ExtendedBlockState(this, new IProperty[]{HorizontalFacingController.FACING_H}, new IUnlistedProperty[]{IF_TIER});
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return HorizontalFacingController.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return HorizontalFacingController.getStateFromMeta(getDefaultState(), meta);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return getDefaultState().withProperty(HorizontalFacingController.FACING_H, facing);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileWallIfTablet)
        {
            ((TileWallIfTablet) te).setTier(stack.getMetadata(), false);
        }
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for (IfTier t : PurMag.INSTANCE.getIfRegistry().tiers)
        {
            items.add(new ItemStack(this, 1, t.getTier()));
        }
    }

    @Override
    public boolean canPlaceBlockOnSide(World world, BlockPos pos, EnumFacing side)
    {
        return canAttachToBlock(world, pos.offset(side.getOpposite()), side);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
        EnumFacing face = state.getValue(HorizontalFacingController.FACING_H);
        if (!canAttachToBlock(world, pos.offset(face.getOpposite()), face))
        {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if (world.isRemote)
        {
            TileEntity te = world.getTileEntity(pos);
            if (te != null && te instanceof TileWallIfTablet)
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
        if (te != null && te instanceof TileWallIfTablet)
            tier = ((TileWallIfTablet) te).getTier();
        return new ItemStack(this, 1, tier);
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirror)
    {
        return HorizontalFacingController.withMirror(state, mirror);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return HorizontalFacingController.withRotation(state, rot);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        if (state instanceof IExtendedBlockState)
        {
            TileEntity te = world.getTileEntity(pos);
            if (te != null && te instanceof TileWallIfTablet)
            {
                return ((IExtendedBlockState) state).withProperty(IF_TIER, ((TileWallIfTablet) te).getTier());
            }
        }
        return state;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOXES.get(state.getValue(HorizontalFacingController.FACING_H));
    }

    @Nullable
    @Override
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }

    @Override
    public boolean removedByPlayer(IBlockState state, World world, BlockPos pos, EntityPlayer player, boolean willHarvest)
    {
        return willHarvest || super.removedByPlayer(state, world, pos, player, willHarvest);
    }

    @Override
    public void harvestBlock(World world, EntityPlayer player, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack)
    {
        super.harvestBlock(world, player, pos, state, te, stack);
        world.setBlockToAir(pos);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune)
    {
        TileEntity te = world.getTileEntity(pos);
        if (te != null && te instanceof TileWallIfTablet)
            drops.add(new ItemStack(this, 1, ((TileWallIfTablet) te).getTier()));
    }
}
