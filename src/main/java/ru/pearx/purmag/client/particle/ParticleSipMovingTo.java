package ru.pearx.purmag.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
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

    public ParticleSipMovingTo(Vector3d loc, Vector3d locTo, String sip, int amount, float speed)
    {
        super(loc, locTo, speed);
        setColor(PurMag.INSTANCE.getSipRegistry().getType(sip).getColor());
        setScale(0.5f + (amount / 8f));
    }

    @Override
    public boolean shouldDisableDepth()
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        spawnTrail();
        super.onUpdate();
    }

    public void spawnTrail()
    {
        Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleTrail(this, 0.75f, 30));
    }
}
