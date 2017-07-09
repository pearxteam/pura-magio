package net.pearx.purmag.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 04.06.17 18:22.
 */
public class BlockCrystalSmall extends BlockBase
{
    public static final PropertyEnum<Type> TYPE = PropertyEnum.create("type", Type.class);

    public BlockCrystalSmall()
    {
        super(Material.ROCK);
        setRegistryName(Utils.getRegistryName("crystal_small"));
        setUnlocalizedName("crystal_small");
        setHardness(6);
        setHarvestLevel("pickaxe", 2);
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
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, TYPE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(TYPE, Type.values()[meta]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return state.getValue(TYPE).ordinal();
    }

    @Override
    public void getSubBlocks(CreativeTabs tab, NonNullList<ItemStack> list)
    {
        for(Type t : Type.values())
        {
            list.add(new ItemStack(this, 1, t.ordinal()));
        }
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(TYPE).ordinal();
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return new AxisAlignedBB(0.2f, 0f, 0.2f, 0.8f, 0.5f, 0.8f);
    }

    @Override
    public BlockFaceShape getBlockFaceShape(IBlockAccess p_193383_1_, IBlockState p_193383_2_, BlockPos p_193383_3_, EnumFacing p_193383_4_)
    {
        return BlockFaceShape.UNDEFINED;
    }

    public enum Type implements IStringSerializable
    {
        GLOWSTONE(0xd2d200), REDSTONE(0xff0000);

        private int color;
        Type(int color)
        {
            setColor(color);
        }

        public int getColor()
        {
            return color;
        }

        public void setColor(int color)
        {
            this.color = color;
        }

        @Override
        public String getName()
        {
            return toString().toLowerCase();
        }
    }
}
