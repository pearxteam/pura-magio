package net.pearx.purmag.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.vecmath.Vector3d;

/**
 * Created by mrAppleXZ on 28.05.17 18:45.
 */
public class ParticleMovingTo extends Particle
{
    protected Vector3d actualSpeed;

    protected ParticleMovingTo(World worldIn, Vector3d loc, Vector3d locTo, float speed)
    {
        super(worldIn, loc.getX(), loc.getY(), loc.getZ());

        Vector3d vec = new Vector3d(locTo);
        vec.sub(loc);
        double length = vec.length();
        setMaxAge(MathHelper.ceil(length / speed));
        actualSpeed = new Vector3d((vec.getX() / length) * speed, (vec.getY() / length) * speed, (vec.getZ() / length) * speed);
        canCollide = false;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        move(actualSpeed.getX(), actualSpeed.getY(), actualSpeed.getZ());

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }
    }
}
