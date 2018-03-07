package ru.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.common.blocks.base.BlockBase;
import ru.pearx.purmag.common.tiles.TileAbstractSingleItem;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 17.08.17 21:28.
 */
public abstract class BlockAbstractSingleItemHorizontal extends BlockBase
{
    public BlockAbstractSingleItemHorizontal(Material materialIn)
    {
        super(materialIn);
    }

    public BlockAbstractSingleItemHorizontal(String name, Material materialIn)
    {
        super(name, materialIn);
    }

    @Override
    public boolean hasTileEntity(IBlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(World world, IBlockState state);

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileAbstractSingleItem)
        {
            TileAbstractSingleItem tasi = (TileAbstractSingleItem) te;
            IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            ItemStack held = p.getHeldItem(hand);
            if (!p.isSneaking() && (tasi.isItemValid(held)))
            {
                ItemStack st = handler.insertItem(0, held, false);
                if(!ItemStack.areItemStacksEqualUsingNBTShareTag(st, held))
                {
                    p.setHeldItem(hand, st);
                    return true;
                }
            }
            if (p.isSneaking() && !handler.getStackInSlot(0).isEmpty())
            {
                p.inventory.addItemStackToInventory(handler.getStackInSlot(0));
                ItemStackUtils.extractAll(handler, 0);
                return true;
            }
            openClientGui(worldIn, pos, state, p, hand, facing, hitX, hitY, hitZ, tasi);
            return true;
        }
        return false;
    }

    public abstract void openClientGui(World worldIn, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ, TileAbstractSingleItem tile);

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, HorizontalFacingController.FACING_H);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return HorizontalFacingController.getStateFromMeta(getDefaultState(), meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return HorizontalFacingController.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return HorizontalFacingController.getStateForPlacement(getDefaultState(), placer);
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
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null && te instanceof TileAbstractSingleItem)
            {
                ItemStackUtils.drop(((TileAbstractSingleItem) te).getHandler(), worldIn, pos);
            }
        }
        super.breakBlock(worldIn, pos, state);
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
}
