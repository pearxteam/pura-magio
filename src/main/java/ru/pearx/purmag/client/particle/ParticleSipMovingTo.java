package ru.pearx.purmag.client.particle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import ru.pearx.carbide.Color;
import ru.pearx.carbide.mc.client.gui.DrawingTools;
import ru.pearx.carbide.mc.client.particle.ParticleEngine;
import ru.pearx.carbide.mc.client.particle.ParticleMovingTo;
import ru.pearx.carbide.mc.client.particle.ParticleTrail;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

import javax.vecmath.Vector3d;


/**
 * Created by mrAppleXZ on 28.05.17 8:14.
 */
public class ParticleSipMovingTo extends ParticleMovingTo
{
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/particle/sip.png");

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
    public float getWidth()
    {
        return 32;
    }

    @Override
    public float getHeight()
    {
        return 32;
    }

    @Override
    public void onRender()
    {
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
        DrawingTools.drawTexture(TEXTURE, 0, 0, (int) getWidth(), (int) getHeight());
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
    }

    @Override
    public void onUpdate()
    {
        spawnTrail(getX(), getY(), getZ());
        super.onUpdate();
    }

    public void spawnTrail(double x, double y, double z)
    {
        ParticleEngine.addParticle(new ParticleTrail(x, y, z, color, 0.75f, TEXTURE, (int) getWidth(), (int) getHeight(), 0.75f, 30));
    }
}
