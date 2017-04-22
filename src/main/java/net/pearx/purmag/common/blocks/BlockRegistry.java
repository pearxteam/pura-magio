package net.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by mrAppleXZ on 08.04.17 18:44.
 */
public class BlockRegistry
{
    public static Block crystal;


    public static void setup()
    {
        crystal = new BlockCrystal();
        GameRegistry.register(crystal);
    }
}
