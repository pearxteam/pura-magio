package net.pearx.purmag.common.entities;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.entities.ModelBeetle;
import net.pearx.purmag.client.models.entities.RenderBeetle;
import net.pearx.purmag.common.Utils;

import java.awt.*;

/**
 * Created by mrAppleXZ on 20.06.17 9:51.
 */
public class EntityRegistry
{
    public static void setup()
    {
        int id = 0;
        registerEntity(Utils.getRegistryName("verda_beetle"), EntityBeetle.class, "verda_beetle", id++, Color.BLACK, Color.GREEN);
        for(Biome b : BiomeDictionary.getBiomes(BiomeDictionary.Type.FOREST))
        {
            net.minecraftforge.fml.common.registry.EntityRegistry.addSpawn(EntityBeetle.class, 5, 1, 4, EnumCreatureType.CREATURE, b);
        }
    }

    private static void registerEntity(ResourceLocation registryName, Class<? extends Entity> entityClass, String entityName, int id, Color eggPrimary, Color eggSecondary)
    {
        net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity(registryName, entityClass, entityName, id, PurMag.instance, 80, 3, true, eggPrimary.getRGB(), eggSecondary.getRGB());
    }

    @SideOnly(Side.CLIENT)
    public static void setupClient()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityBeetle.class, manager -> new RenderBeetle(manager, new ModelBeetle(), 0.5f));
    }
}
