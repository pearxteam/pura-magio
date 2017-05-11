package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import jdk.nashorn.internal.objects.Global;
import net.minecraft.client.Minecraft;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.GlobalChunkPos;

/**
 * Created by mrAppleXZ on 10.05.17 20:14.
 */
public class CPacketSyncSif implements IMessage
{
    public GlobalChunkPos pos;
    public boolean remove;
    public float power;

    public CPacketSyncSif(){}
    public CPacketSyncSif(GlobalChunkPos pos, boolean remove, float power)
    {
        this.pos = pos;
        this.power = power;
        this.remove = remove;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = new GlobalChunkPos(buf.readInt(), buf.readInt(), buf.readInt());
        remove = buf.readBoolean();
        if(!remove)
            power = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(pos.getX());
        buf.writeInt(pos.getZ());
        buf.writeInt(pos.getDimension());
        buf.writeBoolean(remove);
        if(!remove)
            buf.writeFloat(power);
    }

    public static class Handler implements IMessageHandler<CPacketSyncSif, IMessage>
    {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSyncSif msg, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() ->
            {
                if(msg.remove)
                    PurMag.proxy.getSifStorage().removeChunk(msg.pos);
                else
                    PurMag.proxy.getSifStorage().setPower(msg.pos, msg.power);
            });
            return null;
        }
    }
}
