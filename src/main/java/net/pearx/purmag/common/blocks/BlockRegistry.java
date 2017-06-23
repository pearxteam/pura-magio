package net.pearx.purmag.common.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.common.Utils;

/**
 * Created by mrAppleXZ on 08.04.17 18:44.
 */
public class BlockRegistry
{
    public static Block crystal;
    public static Block crystal_glass;
    public static BlockOre ore_crysagnetite;
    public static BlockTranslationDesk translation_desk;
    public static BlockCrystalSmall crystal_small;


    public static void setup()
    {
        crystal = new BlockCrystal();
        GameRegistry.register(crystal);

        crystal_glass = new BlockCrystalGlass();
        GameRegistry.register(crystal_glass);

        ore_crysagnetite = new BlockOre(Utils.getRegistryName("ore_crysagnetite"), 3f, 0.1f, 2);
        GameRegistry.register(ore_crysagnetite);

        translation_desk = new BlockTranslationDesk();
        GameRegistry.register(translation_desk);

        crystal_small = new BlockCrystalSmall();
        GameRegistry.register(crystal_small);
    }
}
