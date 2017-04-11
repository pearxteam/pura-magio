package net.pearx.purmag.registries;

import net.minecraft.block.Block;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.tiles.TileCrystal;
import net.pearx.purmag.blocks.BlockCrystal;

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
