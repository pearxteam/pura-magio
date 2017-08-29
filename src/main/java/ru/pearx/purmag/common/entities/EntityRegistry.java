package ru.pearx.purmag.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Color;
import ru.pearx.lib.Colors;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.models.entities.ModelBeetle;
import ru.pearx.purmag.client.models.entities.RenderBeetle;
import ru.pearx.purmag.common.Utils;


/**
 * Created by mrAppleXZ on 20.06.17 9:51.
 */
public class EntityRegistry
{
    public static void register()
    {
        int id = 0;
        registerEntity(Utils.getResourceLocation("verda_beetle"), EntityBeetle.class, "verda_beetle", id++, Colors.BROWN_500, Colors.GREEN_500);
    }

    public static void setupSpawns()
    {
        for (Biome b : ForgeRegistries.BIOMES)
        {
            if (BiomeDictionary.hasType(b, BiomeDictionary.Type.FOREST))
            {
                net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn(EntityBeetle.class, 3, 4, 5, EnumCreatureType.CREATURE, b);
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void registerClient()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, manager -> new RenderBeetle(manager, new ModelBeetle(), 0.5f));
    }

    private static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Color eggPrimary, Color eggSecondary)
    {
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, PurMag.INSTANCE, 80, 3, true, eggPrimary.getARGB(), eggSecondary.getARGB());
    }
}
