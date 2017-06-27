package net.pearx.purmag.client.models;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.vecmath.Quat4f;

/**
 * Created by mrAppleXZ on 15.05.17 7:12.
 */
@SideOnly(Side.CLIENT)
public class ModelUtils
{
    public static Quat4f getRotation(int x, int y, int z)
    {
        return new Quat4f((float)Math.toRadians(x), (float)Math.toRadians(y), (float)Math.toRadians(z), 1);
    }
}
