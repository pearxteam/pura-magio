package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;

/**
 * Created by mrAppleXZ on 14.05.17 12:47.
 */
public class CPacketCleanSipIds implements IMessage
{
    public CPacketCleanSipIds()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

    public static class Handler implements IMessageHandler<CPacketCleanSipIds, IMessage>
    {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketCleanSipIds message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> PurMag.proxy.getSipIdStorage().cleanMap());
            return null;
        }
    }
}
