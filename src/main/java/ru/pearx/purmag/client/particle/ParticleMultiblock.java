package ru.pearx.purmag.client.particle;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.math.MathUtils;
import ru.pearx.carbide.mc.client.particle.PXParticle;
import ru.pearx.purmag.client.resources.PMResources;
import ru.pearx.purmag.common.Utils;

import java.util.Random;

/*
 * Created by mrAppleXZ on 02.12.17 19:09.
 */
@SideOnly(Side.CLIENT)
public class ParticleMultiblock extends PXParticle
{
    private static final int TEX_COUNT = 4;
    private static final float SIZE = 0.1f;

    public ParticleMultiblock(double x, double y, double z, float dx, float dz, Random rand)
    {
        super(x, y, z);
        setMaxAge(300);
        setSize(SIZE, SIZE);
        setParticleTexture(PMResources.PARTICLE_MULTIBLOCK);
        this.motionX = dx;
        this.motionZ = dz;
        this.particleGravity = 0.1f;
        //this.motionY = -0.05f;
        //this.texIndex = rand.nextInt(TEX_COUNT);
        this.particleAngle = MathUtils.toRadians(rand.nextInt(360));
    }
}
