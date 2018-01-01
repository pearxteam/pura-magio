package ru.pearx.purmag.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.blocks.controllers.AxisController;

/*
 * Created by mrAppleXZ on 14.11.17 16:50.
 */
public class BlockRopeCoil extends BlockBase
{
    public enum Type implements IStringSerializable
    {
        NORMAL,
        COG;


        @Override
        public String getName()
        {
            return toString().toLowerCase();
        }
    }

    public static final PropertyEnum<Type> TYPE = PropertyEnum.create("type", Type.class);

    public BlockRopeCoil()
    {
        super("rope_coil", Material.CLOTH);
        setSoundType(SoundType.CLOTH);
        setHardness(0.5f);
    }

    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, AxisController.AXIS, TYPE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return getDefaultState().withProperty(TYPE, Type.values()[meta & 0b1]).withProperty(AxisController.AXIS, EnumFacing.Axis.values()[meta >> 2]);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        int res = state.getValue(TYPE).ordinal();
        res |= (state.getValue(AxisController.AXIS).ordinal() << 2);
        return res;
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return AxisController.getStateForPlacement(getDefaultState(), facing, placer).withProperty(TYPE, Type.values()[meta]);
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return AxisController.withRotation(state, rot);
    }

    @Override
    public int damageDropped(IBlockState state)
    {
        return state.getValue(TYPE).ordinal();
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items)
    {
        for(Type t : Type.values())
            items.add(new ItemStack(this, 1, t.ordinal()));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void setupModels()
    {
        //leave this empty.
    }
}
