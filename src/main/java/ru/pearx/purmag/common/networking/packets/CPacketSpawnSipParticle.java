package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.client.particle.ParticleEngine;
import ru.pearx.carbide.mc.common.networking.ByteBufTools;
import ru.pearx.purmag.client.particle.ParticleSipMovingTo;

import javax.vecmath.Vector3d;

/**
 * Created by mrAppleXZ on 28.05.17 21:06.
 */
public class CPacketSpawnSipParticle implements IMessage
{
    public Vector3d pos, posTo;
    public float speed;
    public String sipType;
    public int amount;

    public CPacketSpawnSipParticle()
    {
    }

    public CPacketSpawnSipParticle(Vector3d pos, Vector3d posTo, float speed, String type, int amount)
    {
        this.pos = pos;
        this.posTo = posTo;
        this.speed = speed;
        this.sipType = type;
        this.amount = amount;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = ByteBufTools.readVector3d(buf);
        posTo = ByteBufTools.readVector3d(buf);
        speed = buf.readFloat();
        sipType = ByteBufUtils.readUTF8String(buf);
        amount = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufTools.writeVector3d(buf, pos);
        ByteBufTools.writeVector3d(buf, posTo);
        buf.writeFloat(speed);
        ByteBufUtils.writeUTF8String(buf, sipType);
        buf.writeInt(amount);
    }

    public static class Handler implements IMessageHandler<CPacketSpawnSipParticle, IMessage>
    {
        public Handler()
        {
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSpawnSipParticle msg, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> ParticleEngine.addParticle(new ParticleSipMovingTo(msg.pos, msg.posTo, msg.sipType, msg.amount, msg.speed)));
            return null;
        }
    }
}
