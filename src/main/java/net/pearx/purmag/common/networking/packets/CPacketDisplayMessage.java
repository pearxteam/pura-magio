package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.DisplayMessage;
import net.pearx.purmag.client.DisplayMessageQuery;
import net.pearx.purmag.common.SoundRegistry;

/**
 * Created by mrAppleXZ on 26.04.17 22:03.
 */
public class CPacketDisplayMessage implements IMessage
{
    public DisplayMessage msg;

    public CPacketDisplayMessage(){}

    public CPacketDisplayMessage(DisplayMessage msg)
    {
        this.msg = msg;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
       msg = DisplayMessage.readFromByteBuf(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        msg.writeToByteBuf(buf);
    }

    public static class Handler implements IMessageHandler<CPacketDisplayMessage, IMessage>
    {
        public Handler(){}

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketDisplayMessage msg, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> DisplayMessageQuery.addMessage(msg.msg));
            return null;
        }
    }
}
