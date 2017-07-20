package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.networking.ByteBufTools;

import javax.vecmath.Vector3d;

/*
 * Created by mrAppleXZ on 20.07.17 11:47.
 */
public class CPacketSpawnParticle implements IMessage
{
    public EnumParticleTypes type;
    public Vector3d pos;
    public Vector3d motion;

    public CPacketSpawnParticle()
    {
    }

    public CPacketSpawnParticle(EnumParticleTypes type, Vector3d pos, Vector3d motion)
    {
        this.type = type;
        this.pos = pos;
        this.motion = motion;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        type = EnumParticleTypes.values()[buf.readInt()];
        pos = ByteBufTools.readVector3d(buf);
        motion = ByteBufTools.readVector3d(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(type.ordinal());
        ByteBufTools.writeVector3d(buf, pos);
        ByteBufTools.writeVector3d(buf, motion);
    }

    public static class Handler implements IMessageHandler<CPacketSpawnParticle, IMessage>
    {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSpawnParticle message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() ->
                    Minecraft.getMinecraft().world.spawnParticle(message.type,
                            message.pos.getX(), message.pos.getY(), message.pos.getZ(),
                            message.motion.getX(), message.motion.getY(), message.motion.getZ()));
            return null;
        }
    }
}
