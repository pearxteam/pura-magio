package ru.pearx.purmag.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.particle.PXParticle;
import ru.pearx.libmc.client.particle.ParticlePhysical;
import ru.pearx.purmag.common.Utils;

import java.util.List;

/*
 * Created by mrAppleXZ on 02.12.17 19:09.
 */
@SideOnly(Side.CLIENT)
public class ParticleMultiblock extends ParticlePhysical
{
    public static final ResourceLocation TEXTURE = Utils.gRL("textures/particle/multiblock.png");
    public static final float ACCELERATION = (9.8f * 0.01f) / 20;

    public ParticleMultiblock(double x, double y, double z)
    {
        super(x, y, z);
        setMaxAge(120);
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
        if(getLastMoveY() != 0)
            setDy(getDy() - ACCELERATION);
        else
            setDy(-0.0001f);
        super.onUpdate();
    }

    @Override
    public void onRender()
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(getWidth() / 2, getHeight() / 2, 0);
        GlStateManager.translate(-(getWidth() / 2), -(getHeight() / 2), 0);
        DrawingTools.drawTexture(TEXTURE, 0, 0, (int) getWidth(), (int) getHeight());
        GlStateManager.popMatrix();
    }
}
