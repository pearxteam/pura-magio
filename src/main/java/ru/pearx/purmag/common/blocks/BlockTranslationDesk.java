package ru.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
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
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.tiles.TileTranslationDesk;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 03.06.17 19:07.
 */
public class BlockTranslationDesk extends BlockBase
{
    public BlockTranslationDesk()
    {
        super(Material.WOOD);
        setRegistryName("translation_desk");
        setHarvestLevel("axe", 0);
        setHardness(1.7f);
        setSoundType(SoundType.WOOD);
        setUnlocalizedName(Utils.getUnlocalizedName("translation_desk"));
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
        return new TileTranslationDesk();
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer p, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te != null && te instanceof TileTranslationDesk)
        {
            IItemHandler handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
            ItemStack held = p.getHeldItem(hand);
            if(!p.isSneaking() && held.getItem() == ItemRegistry.papyrus && handler.getStackInSlot(0).isEmpty())
            {
                handler.insertItem(0, held, false);
                p.setHeldItem(hand, ItemStack.EMPTY);
                return true;
            }
            if(p.isSneaking() && !handler.getStackInSlot(0).isEmpty())
            {
                p.inventory.addItemStackToInventory(handler.getStackInSlot(0));
                ItemStackUtils.extractAll(handler, 0);
                return true;
            }
            PurMag.proxy.openTranslationDesk(pos, worldIn);
            return true;
        }
        return false;
    }

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
        if(!worldIn.isRemote)
        {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te != null && te instanceof TileTranslationDesk)
            {
                ItemStackUtils.drop(((TileTranslationDesk)te).handler, worldIn, pos);
            }
        }
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
