package ru.pearx.purmag.client.particle;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import ru.pearx.carbide.Color;
import ru.pearx.carbide.math.MathUtils;
import ru.pearx.carbide.mc.client.gui.DrawingTools;
import ru.pearx.carbide.mc.client.particle.PXParticle;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 28.08.17 17:55.
 */
public class ParticleCrystal extends PXParticle
{
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/particle/crystal.png");

    private Color color;
    private float alpha;

    public ParticleCrystal(double x, double y, double z, String sip, int age)
    {
        super(x, y, z);
        setMaxAge(age);
        color = PurMag.INSTANCE.getSipRegistry().getType(sip).getColor();
    }

    @Override
    public float getWidth()
    {
        return 8;
    }

    @Override
    public float getHeight()
    {
        return 8;
    }

    @Override
    public void onRender()
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(getWidth() / 2, getHeight() / 2, 0);
        GlStateManager.rotate(degrees, 0, 0, 1);
        GlStateManager.translate(-(getWidth() / 2), -(getHeight() / 2), 0);
        GlStateManager.enableBlend();
        GlStateManager.depthMask(false);
        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, alpha);
        DrawingTools.drawTexture(TEXTURE, 0, 0, (int) getWidth(), (int) getHeight());
        GlStateManager.depthMask(true);
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    private float prevSin, prevCos;
    private int degrees = 0;

    @Override
    public void onUpdate()
    {
        alpha = 1f - ((float) getAge() / getMaxAge());
        float sin = MathHelper.sin(MathUtils.toRadians(degrees)) * 0.1f;
        float cos = MathHelper.cos(MathUtils.toRadians(degrees)) * 0.1f;
        move(sin - prevSin, 0.02f, cos - prevCos);
        prevSin = sin;
        prevCos = cos;
        super.onUpdate();
        degrees += 10;
        if (degrees > 360)
            degrees = 0;
    }
}
