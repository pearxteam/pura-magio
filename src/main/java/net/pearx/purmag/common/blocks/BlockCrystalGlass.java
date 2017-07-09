package net.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.blocks.controllers.CTMController;
import net.pearx.purmag.common.sip.SipUtils;
import net.pearx.purmag.common.tiles.TileSingleSip;

/**
 * Created by mrAppleXZ on 14.05.17 18:30.
 */
public class BlockCrystalGlass extends BlockSingleSip
{
    public CTMController ctm = new CTMController();
    public BlockCrystalGlass()
    {
        super(Material.GLASS);
        setRegistryName(Utils.getRegistryName("crystal_glass"));
        setUnlocalizedName("crystal_glass");
        setHardness(1f);
        setSoundType(SoundType.GLASS);
        setHarvestLevel("pickaxe", 0);
    }

    @Override
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.TRANSLUCENT;
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
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        return !canConnect(blockState, blockAccess, pos, side);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new ExtendedBlockState(this, new IProperty[] {}, CTMController.PROPS.values().toArray(new IUnlistedProperty[0]));
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return ctm.getExtendedState(state, world, pos, this::canConnect);
    }

    public boolean canConnect(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState off = blockAccess.getBlockState(pos.offset(side));
        if(off.getBlock() == state.getBlock())
        {
            return SipUtils.getSipInBlock(blockAccess, pos.offset(side)).equals(SipUtils.getSipInBlock(blockAccess, pos));
        }
        return false;
    }
}
