package ru.pearx.purmag.common.blocks.multiblock;

import ru.pearx.carbide.mc.common.structure.multiblock.Multiblock;

/*
 * Created by mrAppleXZ on 14.11.17 15:49.
 */
public final class MultiblockRegistry
{
    public static BlockStoneCrusher.Multiblock STONE_CRUSHER;

    public static void setup()
    {
        Multiblock.REGISTRY.register(STONE_CRUSHER = new BlockStoneCrusher.Multiblock());
    }
}
