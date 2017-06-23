package net.pearx.purmag.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.World;

/**
 * Created by mrAppleXZ on 28.05.17 20:19.
 */
public class ParticleTrail extends Particle
{
    public ParticleTrail(World w, double x, double y, double z, float r, float g, float b, TextureAtlasSprite sprite, float alpha, float scale, int age)
    {
        super(w, x, y, z);
        setRBGColorF(r, g, b);
        setParticleTexture(sprite);
        setAlphaF(alpha);
        particleScale = scale;
        setMaxAge(age);
        canCollide = false;
    }

    @Override
    public void onUpdate()
    {
        particleAlpha = 1f / 100 * (100f / particleMaxAge * (particleMaxAge - particleAge));
        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
    }

    @Override
    public boolean shouldDisableDepth()
    {
        return true;
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }
}
