package net.pearx.purmag.client.models.entities;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.entities.EntityBeetle;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 22.06.17 15:38.
 */
@SideOnly(Side.CLIENT)
public class RenderBeetle extends RenderLiving<EntityBeetle>
{
    public RenderBeetle(RenderManager manager, ModelBase modelbaseIn, float shadowsizeIn)
    {
        super(manager, modelbaseIn, shadowsizeIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(EntityBeetle entity)
    {
        return Utils.getRegistryName("textures/entities/verda_beetle.png");
    }
}
