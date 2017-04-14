package net.pearx.purmag.registries;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.pearx.purmag.PurmagCore;
import net.pearx.purmag.Utils;

/**
 * Created by mrAppleXZ on 13.04.17 10:59.
 */
public class SoundRegistry
{
    public static SoundEvent CrystalCutter;

    public static void setup()
    {
        CrystalCutter = new SoundEvent(new ResourceLocation(PurmagCore.ModId, "crystal_cutter")).setRegistryName(Utils.getRegistryName("crystal_cutter"));
        GameRegistry.register(CrystalCutter);
    }
}
