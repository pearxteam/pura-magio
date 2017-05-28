package net.pearx.purmag.common.networking;

import io.netty.buffer.ByteBuf;

import javax.vecmath.Vector3d;

/**
 * Created by mrAppleXZ on 28.05.17 21:08.
 */
public class ByteBufTools
{
    public static void writeVector3d(ByteBuf buf, Vector3d vec)
    {
        buf.writeDouble(vec.x);
        buf.writeDouble(vec.y);
        buf.writeDouble(vec.z);
    }

    public static Vector3d readVector3d(ByteBuf buf)
    {
        Vector3d vec = new Vector3d();
        vec.x = buf.readDouble();
        vec.y = buf.readDouble();
        vec.z = buf.readDouble();
        return vec;
    }
}
