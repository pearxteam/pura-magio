package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.particles.ParticleSipMovingTo;
import net.pearx.purmag.common.networking.ByteBufTools;

import javax.vecmath.Vector3d;

/**
 * Created by mrAppleXZ on 28.05.17 21:06.
 */
public class CPacketSpawnSipParticle implements IMessage
{
    public Vector3d pos, posTo;
    public float speed;
    public int dim;
    public String sipType;
    public int amount;

    public CPacketSpawnSipParticle() {}
    public CPacketSpawnSipParticle(Vector3d pos, Vector3d posTo, float speed, int dim, String type, int amount)
    {
        this.pos = pos;
        this.posTo = posTo;
        this.speed = speed;
        this.dim = dim;
        this.sipType = type;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = ByteBufTools.readVector3d(buf);
        posTo = ByteBufTools.readVector3d(buf);
        speed = buf.readFloat();
        dim = buf.readInt();
        sipType = ByteBufUtils.readUTF8String(buf);
        amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufTools.writeVector3d(buf, pos);
        ByteBufTools.writeVector3d(buf, posTo);
        buf.writeFloat(speed);
        buf.writeInt(dim);
        ByteBufUtils.writeUTF8String(buf, sipType);
        buf.writeInt(amount);
    }

    public static class Handler implements IMessageHandler<CPacketSpawnSipParticle, IMessage>
    {
        public Handler() {}

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSpawnSipParticle msg, MessageContext ctx)
        {
            Minecraft.getMinecraft().effectRenderer.addEffect(new ParticleSipMovingTo(DimensionManager.getWorld(msg.dim), msg.pos, msg.posTo, msg.sipType, msg.amount, msg.speed));
            return null;
        }
    }
}
