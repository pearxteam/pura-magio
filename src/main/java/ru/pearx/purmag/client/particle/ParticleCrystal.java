package ru.pearx.purmag.client.particle;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import ru.pearx.carbide.RandomUtils;
import ru.pearx.carbide.math.MathUtils;
import ru.pearx.carbide.mc.client.particle.PXParticle;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.resources.PMResources;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 28.08.17 17:55.
 */
public class ParticleCrystal extends PXParticle
{
    private float prevSin, prevCos;
    private float angleDelta;

    public ParticleCrystal(double x, double y, double z, String sip, int age)
    {
        super(x, y, z);
        setLightEnabled(false);
        setMotionFixed(true);
        setColor(PurMag.INSTANCE.getSipRegistry().getType(sip).getColor());
        setMaxAge(age);
        setParticleTexture(PMResources.PARTICLE_CRYSTAL);
        motionY = RandomUtils.nextFloat(0.02f, 0.03f, PurMag.INSTANCE.random);
        setScale(RandomUtils.nextFloat(0.6f, 0.9f, PurMag.INSTANCE.random));
        this.angleDelta = MathUtils.toRadians(10);
    }

    @Override
    public void onUpdate()
    {
        setAlphaF(1f - ((float) getAge() / getMaxAge()));
        float sin = MathHelper.sin(getAngle()) * 0.1f;
        float cos = MathHelper.cos(getAngle()) * 0.1f;
        this.motionX = sin - prevSin;
        this.motionZ = cos - prevCos;
        prevSin = sin;
        prevCos = cos;
        super.onUpdate();
        float angle = getAngle() + angleDelta;
        if (angle > Math.PI * 2)
            angle = 0;
        setAngle(angle);
    }
}
