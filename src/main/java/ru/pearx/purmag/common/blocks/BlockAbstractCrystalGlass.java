package ru.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.common.blocks.controllers.ConnectionsController;
import ru.pearx.purmag.common.blocks.base.BlockSingleSip;
import ru.pearx.purmag.common.sip.SipUtils;

/*
 * Created by mrAppleXZ on 24.08.17 11:19.
 */
public abstract class BlockAbstractCrystalGlass extends BlockSingleSip
{
    public ConnectionsController ctm = new ConnectionsController();

    public BlockAbstractCrystalGlass(String name)
    {
        super(name, Material.GLASS);
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
        return new ExtendedBlockState(this, new IProperty[]{}, ConnectionsController.PROPS.values().toArray(new IUnlistedProperty[0]));
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        return ctm.getExtendedState(state, world, pos, this::canConnect);
    }

    public boolean canConnect(IBlockState state, IBlockAccess blockAccess, BlockPos pos, EnumFacing side)
    {
        IBlockState off = blockAccess.getBlockState(pos.offset(side));
        if (off.getBlock() instanceof BlockAbstractCrystalGlass)
        {
            return SipUtils.getSipInBlock(blockAccess, pos.offset(side)).equals(SipUtils.getSipInBlock(blockAccess, pos));
        }
        return false;
    }
}
