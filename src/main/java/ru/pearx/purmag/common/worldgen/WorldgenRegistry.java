package ru.pearx.purmag.common.worldgen;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.pearx.libmc.common.worldgen.WGOre;
import ru.pearx.libmc.common.worldgen.WGOreDouble;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.blocks.BlockCrystalSmall;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.config.ConfigOregenEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 04.05.17 15:10.
 */
public class WorldgenRegistry
{
    public static List<WGCrystalsEntry> crystalGen = new ArrayList<>();

    public static void setup()
    {
        if(PurMag.INSTANCE.config.genCrystals)
        {
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.SURFACE, "sea", null, BiomeDictionary.Type.BEACH, BiomeDictionary.Type.RIVER));
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.UNDERGROUND, "rock", PurMag.INSTANCE.config.genRockCrystalsDimBlacklist));
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.FIRSTAIR, "flame", null, BiomeDictionary.Type.NETHER));
            GameRegistry.registerWorldGenerator(new WGCrystals(), 5);
        }
        if(PurMag.INSTANCE.config.genCrysagnetite.generate)
        {
            ConfigOregenEntry coe = PurMag.INSTANCE.config.genCrysagnetite;
            GameRegistry.registerWorldGenerator(new WGOre(coe.minVeinSize, coe.maxVeinSize, coe.minY, coe.maxY, coe.chance, BlockRegistry.ore_crysagnetite.getDefaultState(), coe.dimList, coe.dimListWhitelist), 5);
        }
        if(PurMag.INSTANCE.config.genCrystallizedRedstone.generate)
        {
            ConfigOregenEntry coe = PurMag.INSTANCE.config.genCrystallizedRedstone;
            GameRegistry.registerWorldGenerator(new WGOreDouble(coe.minVeinSize, coe.maxVeinSize, coe.minY, coe.maxY, coe.chance,
                    BlockRegistry.crystal_small.getDefaultState().withProperty(BlockCrystalSmall.TYPE, BlockCrystalSmall.Type.REDSTONE),
                    Blocks.REDSTONE_ORE.getDefaultState(),
                    coe.dimList, coe.dimListWhitelist), 6);
        }
        if(PurMag.INSTANCE.config.genCrystallizedGlowstone.generate)
        {
            ConfigOregenEntry coe = PurMag.INSTANCE.config.genCrystallizedGlowstone;
            GameRegistry.registerWorldGenerator(new WGOreDouble(coe.minVeinSize, coe.maxVeinSize, coe.minY, coe.maxY, coe.chance,
                    BlockRegistry.crystal_small.getDefaultState().withProperty(BlockCrystalSmall.TYPE, BlockCrystalSmall.Type.GLOWSTONE),
                    Blocks.GLOWSTONE.getDefaultState(),
                    coe.dimList, coe.dimListWhitelist), 6);
        }
    }
}