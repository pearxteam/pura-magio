package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.entity.minecart.MinecartCollisionEvent;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;

import java.util.Map;

/**
 * Created by mrAppleXZ on 13.05.17 20:22.
 */
public class CPacketSyncSipIds implements IMessage
{
    Map<String, Integer> map;

    public CPacketSyncSipIds()
    {

    }

    public CPacketSyncSipIds(Map<String, Integer> map)
    {
        this.map = map;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        int count = buf.readInt();
        for(int i = 0; i < count; i++)
        {
            map.put(ByteBufUtils.readUTF8String(buf), buf.readInt());
        }
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(map.entrySet().size());
        for(Map.Entry<String, Integer> entr : map.entrySet())
        {
            ByteBufUtils.writeUTF8String(buf, entr.getKey());
            buf.writeInt(entr.getValue());
        }
    }

    public static class Handler implements IMessageHandler<CPacketSyncSipIds, IMessage>
    {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSyncSipIds message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> PurMag.proxy.getSipIdStorage().loadMap(message.map));
            return null;
        }
    }
}
