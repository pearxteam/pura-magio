package ru.pearx.purmag.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import ru.pearx.libmc.client.particles.ParticleMovingTo;
import ru.pearx.libmc.client.particles.ParticleTrail;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;

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
        Color col = new Color(PurMag.INSTANCE.sip.getType(sip).getColor());
        setRBGColorF (col.getRed() / 255f, col.getGreen() / 255f, col.getBlue() / 255f);
        setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(Utils.getRegistryName("particle/sip").toString()));
        float f = amount / 8f;
        particleScale = 0.5f + f;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        addTrailParticle(prevPosX, prevPosY, prevPosZ);
        float count = speed * 50;
        for(int i = 0; i < count; i++)
        {
            addTrailParticle(prevPosX + (actualSpeed.x * (i / count)), prevPosY + (actualSpeed.y * (i / count)), prevPosZ + (actualSpeed.z * (i / count)));
        }
    }

    private void addTrailParticle(double x, double y, double z)
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleTrail(world, x, y, z, getRedColorF(), getGreenColorF(), getBlueColorF(), particleTexture, particleAlpha, particleScale * 0.4f, 40));
    }

    @Override
    public boolean shouldDisableDepth()
    {
        return true;
    }
}
