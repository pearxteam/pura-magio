package ru.pearx.purmag.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Color;
import ru.pearx.lib.math.MathUtils;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.particle.PXParticle;
import ru.pearx.libmc.client.particle.ParticlePhysical;
import ru.pearx.purmag.common.Utils;

import java.util.List;
import java.util.Random;

/*
 * Created by mrAppleXZ on 02.12.17 19:09.
 */
@SideOnly(Side.CLIENT)
public class ParticleMultiblock extends ParticlePhysical
{
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/particle/multiblock.png");

    private Color color;

    public ParticleMultiblock(double x, double y, double z, float dx, float dz, Color color)
    {
        super(x, y, z);
        setMaxAge(300);
        setDx(dx);
        setDz(dz);
        setDy(-0.05f);
        this.color = color;
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
    public void onUpdate()
    {
        setDy(getDy() - (9.8f * 0.01f) / 20);
        if(getLastMoveY() != 0)
        {
            move(0, getDy(), 0);
        }
        else
        {
            setDy(-0.05f);
            move(getDx(), getDy(), getDz());
        }
        super.onUpdate();
    }

    @Override
    public void onRender()
    {
        GlStateManager.pushMatrix();
        float scx = (getMaxAge() - getAge()) / (float)getMaxAge();
        GlStateManager.scale(scx, scx, 1);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.color(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f, (MathHelper.sin(MathUtils.toRadians((System.currentTimeMillis()) % 360)) * 0.15f) + 0.6f);
        DrawingTools.drawTexture(TEXTURE, 0, 0, (int) getWidth(), (int) getHeight());
        GlStateManager.color(1, 1, 1);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.popMatrix();
    }
}
