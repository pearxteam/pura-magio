package ru.pearx.purmag.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import ru.pearx.lib.Color;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.particle.PXParticle;
import ru.pearx.libmc.client.particle.ParticleEngine;
import ru.pearx.libmc.client.particle.ParticleMovingTo;
import ru.pearx.libmc.client.particle.ParticleTrail;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.models.StandardModels;
import ru.pearx.purmag.common.Utils;

import javax.vecmath.Vector3d;


/**
 * Created by mrAppleXZ on 28.05.17 8:14.
 */
public class ParticleSipMovingTo extends ParticleMovingTo
{
    public static final ResourceLocation TEXTURE = Utils.getRegistryName("textures/particle/sip.png");

    private Color color;
    private float scale;

    public ParticleSipMovingTo(Vector3d loc, Vector3d locTo, String sip, int amount, float speed)
    {
        super(loc, locTo, speed);
        color = PurMag.INSTANCE.getSipRegistry().getType(sip).getColor();
        scale = 0.5f + (amount / 8f);
    }

    @Override
    public float getScaleFactor()
    {
        return super.getScaleFactor() * scale;
    }

    @Override
    public double getWidth()
    {
        return 32;
    }

    @Override
    public double getHeight()
    {
        return 32;
    }

    @Override
    public void onRender()
    {
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
        DrawingTools.drawTexture(TEXTURE, 0, 0, (int)getWidth(), (int)getHeight());
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
    }
}
