package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.pearx.purmag.common.ItemStackUtils;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.controllers.FacingController;
import net.pearx.purmag.common.items.ItemRegistry;
import net.pearx.purmag.common.tiles.TileTranslationDesk;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 03.06.17 19:07.
 */
public class BlockTranslationDesk extends BlockBase
{
    public FacingController facing = new FacingController();

    public BlockTranslationDesk()
    {
        super(Material.WOOD);
        setRegistryName(Utils.getRegistryName("translation_desk"));
        setHarvestLevel("axe", 0);
        setUnlocalizedName("translation_desk");
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
        }
        return false;
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, FacingController.FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return facing.getStateFromMeta(getDefaultState(), meta);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return facing.getMetaFromState(state);
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.facing.getStateForPlacement(getDefaultState(), placer);
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