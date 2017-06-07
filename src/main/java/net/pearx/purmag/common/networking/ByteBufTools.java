package net.pearx.purmag.common.networking;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.BlockPos;

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
        return new Vector3d(buf.readDouble(), buf.readDouble(), buf.readDouble());
    }

    public static void writeBlockPos(ByteBuf buf, BlockPos pos)
    {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getY());
        buf.writeInt(pos.getZ());
    }

    public static BlockPos readBlockPos(ByteBuf buf)
    {
        return new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
    }
}
