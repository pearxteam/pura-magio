package ru.pearx.purmag.common.blocks.multiblock;

import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import ru.pearx.lib.collections.CollectionUtils;
import ru.pearx.libmc.common.blocks.controllers.AxisController;
import ru.pearx.libmc.common.structure.blockarray.BlockArray;
import ru.pearx.libmc.common.structure.blockarray.BlockArrayEntry;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.items.ItemRegistry;

/*
 * Created by mrAppleXZ on 14.11.17 15:49.
 */
public final class MultiblockRegistry
{
    public static Multiblock STONE_CRUSHER;

    public static void setup()
    {
        Multiblock.REGISTRY.register(STONE_CRUSHER = new Multiblock()
        {{
            BlockArray arr = new BlockArray();
            arr.getSkippedProperties().add(BlockAnvil.FACING);
            BlockArrayEntry anvil = new BlockArrayEntry(Blocks.ANVIL, new ItemStack(Blocks.ANVIL));
            BlockArrayEntry rope = new BlockArrayEntry(BlockRegistry.rope_coil.getDefaultState().withProperty(AxisController.AXIS, EnumFacing.Axis.Z), new ItemStack(ItemRegistry.rope_coil));
            arr.getMap().putAll(CollectionUtils.createMap(BlockPos.class, BlockArrayEntry.class,
                    new BlockPos(0, 0, 0), anvil,
                    new BlockPos(0, 2, 0), rope,
                    new BlockPos(1, 2, 0), rope
            ));
            setStructure(arr);
            setRegistryName("stone_crusher");
            setMasterPos(new BlockPos(0, 2, 0));
            //setMasterState();
        }});
    }
}
