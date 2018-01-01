package ru.pearx.purmag.common.blocks.multiblock;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockLever;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.pearx.lib.collections.CollectionUtils;
import ru.pearx.libmc.common.blocks.controllers.AxisController;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.libmc.common.structure.blockarray.BlockArrayEntry;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.blocks.BlockRopeCoil;
import ru.pearx.purmag.common.items.ItemRegistry;

/*
 * Created by mrAppleXZ on 14.11.17 15:49.
 */
public final class MultiblockRegistry
{
    public static PMMultiblock STONE_CRUSHER;

    public static void setup()
    {
        {
            BlockArray arr = new BlockArray();
            BlockArrayEntry rope = new BlockArrayEntry(BlockRegistry.rope_coil.getDefaultState().withProperty(AxisController.AXIS, EnumFacing.Axis.Z), new ItemStack(ItemRegistry.rope_coil));
            BlockArrayEntry rope_cog = new BlockArrayEntry(BlockRegistry.rope_coil.getDefaultState().withProperty(AxisController.AXIS, EnumFacing.Axis.Z).withProperty(BlockRopeCoil.TYPE, BlockRopeCoil.Type.COG), new ItemStack(ItemRegistry.rope_coil));

            arr.getCheckers().add((BlockArrayEntry entr, IBlockState state, IBlockState worldState, World w, BlockPos pos, Rotation rot) ->
            {
                for(IProperty<?> prop : state.getPropertyKeys())
                {
                    if(prop == BlockAnvil.DAMAGE || prop == BlockRopeCoil.TYPE)
                        return state.getValue(prop).equals(worldState.getValue(prop));
                }
                return true;
            });
            arr.getMap().putAll(CollectionUtils.createMap(BlockPos.class, BlockArrayEntry.class,
                    new BlockPos(0, 0, 0), new BlockArrayEntry(Blocks.ANVIL, new ItemStack(Blocks.ANVIL)),
                    new BlockPos(0, 2, 0), rope,
                    new BlockPos(1, 2, 0), rope,
                    new BlockPos(1, 0, 0), rope_cog,
                    new BlockPos(2, 0, 0), new BlockArrayEntry(Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.UP_X).withProperty(BlockLever.POWERED, true), new ItemStack(Blocks.LEVER)),
                    new BlockPos(1, 0, 1), new BlockArrayEntry(Blocks.LEVER.getDefaultState().withProperty(BlockLever.FACING, BlockLever.EnumOrientation.SOUTH), new ItemStack(Blocks.LEVER))
            ));
            Multiblock.REGISTRY.register(STONE_CRUSHER = new PMMultiblock(0, arr, new BlockPos(1, 0, 0), BlockRegistry.stone_crusher.getDefaultState(), new ModelResourceLocation(Utils.gRL("stone_crusher"), "normal"), Utils.gRL("stone_crusher")));
        }
    }
}
