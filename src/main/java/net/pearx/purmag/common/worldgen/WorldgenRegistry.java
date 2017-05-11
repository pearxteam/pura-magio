package net.pearx.purmag.common.worldgen;

import net.minecraft.init.Biomes;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.PurMag;

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
            crystalGen.add(new WGCrystalsEntry(WGCrystalsType.SURFACE, "sea",  Biomes.BEACH));
        }

        GameRegistry.registerWorldGenerator(new WGCrystals(), 5);
    }
}
