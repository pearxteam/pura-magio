package net.pearx.purmag.common.blocks.controllers;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 17.05.17 7:47.
 */
public class CTMController
{
    public static final Map<EnumFacing, IUnlistedProperty<Boolean>> PROPS = new HashMap<>();
    static {
        PROPS.put(EnumFacing.UP, new Properties.PropertyAdapter<>(PropertyBool.create("conn_up")));
        PROPS.put(EnumFacing.DOWN, new Properties.PropertyAdapter<>(PropertyBool.create("conn_down")));
        PROPS.put(EnumFacing.NORTH, new Properties.PropertyAdapter<>(PropertyBool.create("conn_north")));
        PROPS.put(EnumFacing.EAST, new Properties.PropertyAdapter<>(PropertyBool.create("conn_east")));
        PROPS.put(EnumFacing.SOUTH, new Properties.PropertyAdapter<>(PropertyBool.create("conn_south")));
        PROPS.put(EnumFacing.WEST, new Properties.PropertyAdapter<>(PropertyBool.create("conn_west")));
    }

    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos, ICanConnect conn)
    {
        if(state instanceof IExtendedBlockState)
        {
            IExtendedBlockState ext = (IExtendedBlockState) state;
            for(Map.Entry<EnumFacing, IUnlistedProperty<Boolean>> entr : CTMController.PROPS.entrySet())
            {
                ext = ext.withProperty(entr.getValue(), conn.canConnect(state, world, pos, entr.getKey()));
            }
            return ext;
        }
        return state;
    }
}
