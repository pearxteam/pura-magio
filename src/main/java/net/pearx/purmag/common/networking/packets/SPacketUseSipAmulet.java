package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

/**
 * Created by mrAppleXZ on 21.05.17 18:37.
 */
public class SPacketUseSipAmulet implements IMessage
{
    public SPacketUseSipAmulet()
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

    public static class Handler implements IMessageHandler<SPacketUseSipAmulet, IMessage>
    {
        public Handler()
        {

        }

        @Override
        public IMessage onMessage(SPacketUseSipAmulet message, MessageContext ctx)
        {
            return null;
        }
    }
}
