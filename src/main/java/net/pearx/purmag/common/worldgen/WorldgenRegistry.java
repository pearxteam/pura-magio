package net.pearx.purmag.common.worldgen;

import net.minecraft.init.Biomes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.blocks.BlockRegistry;
import net.pearx.purmag.common.config.ConfigOregenEntry;

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
        if(PurMag.instance.config.genCrystals)
        {
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.SURFACE, "sea", null, Biomes.BEACH));
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.UNDERGROUND, "rock", PurMag.instance.config.genRockCrystalsDimBlacklist));
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.FIRSTAIR, "flame", null, Biomes.HELL));
            GameRegistry.registerWorldGenerator(new WGCrystals(), 5);
        }
        if(PurMag.instance.config.genCrysagnetite.generate)
        {
            ConfigOregenEntry coe = PurMag.instance.config.genCrysagnetite;
            GameRegistry.registerWorldGenerator(new WGOre(coe.minVeinSize, coe.maxVeinSize, coe.minY, coe.maxY, coe.chance, BlockRegistry.ore_crysagnetite.getDefaultState(), coe.dimList, coe.dimListWhitelist), 5);
        }
    }
}
