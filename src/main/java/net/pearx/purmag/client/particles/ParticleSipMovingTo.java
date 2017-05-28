package net.pearx.purmag.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.Utils;

import javax.vecmath.Vector3d;
import java.awt.*;

/**
 * Created by mrAppleXZ on 28.05.17 8:14.
 */
public class ParticleSipMovingTo extends ParticleMovingTo
{
    public ParticleSipMovingTo(World worldIn, Vector3d loc, Vector3d locTo, String sip, int amount, float speed)
    {
        super(worldIn, loc, locTo, speed);
        Color col = new Color(PurMag.instance.sip.getType(sip).getColor());
        setRBGColorF (col.getRed() / 255f, col.getGreen() / 255f, col.getBlue() / 255f);
        setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Utils.getRegistryName("particle/sip").toString()));
        setAlphaF(0.8f);
        float f = amount / 16f * 0.5f;
        particleScale = 0.5f + f;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleTrail(world, prevPosX, prevPosY, prevPosZ, getRedColorF(), getGreenColorF(), getBlueColorF(), particleTexture, particleAlpha, particleScale * 0.5f, 40));
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleTrail(world, prevPosX + (actualSpeed.x / 2f), prevPosY + (actualSpeed.y / 2f), prevPosZ + (actualSpeed.z / 2f), getRedColorF(), getGreenColorF(), getBlueColorF(), particleTexture, particleAlpha, particleScale * 0.5f, 40));
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }

    @Override
    public boolean shouldDisableDepth()
    {
        return true;
    }


}
