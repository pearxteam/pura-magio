package ru.pearx.purmag.common.networking.packets.code_storage;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Created by mrAppleXZ on 17.09.17 15:18.
 */
public class CPacketUnlockResponse implements IMessage
{
    public boolean success;

    public CPacketUnlockResponse(){}
    public CPacketUnlockResponse(boolean success)
    {
        this.success = success;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        success = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeBoolean(success);
    }

    public static class Handler implements IMessageHandler<CPacketUnlockResponse, IMessage>
    {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketUnlockResponse message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() ->
            {
                //todo
            });
            return null;
        }
    }
}
