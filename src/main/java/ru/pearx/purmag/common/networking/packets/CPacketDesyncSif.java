package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.common.GlobalChunkPos;
import ru.pearx.purmag.client.PurMagClient;

/**
 * Created by mrAppleXZ on 27.06.17 16:20.
 */
public class CPacketDesyncSif implements IMessage
{
    public GlobalChunkPos pos;

    public CPacketDesyncSif()
    {
    }

    public CPacketDesyncSif(GlobalChunkPos pos)
    {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = GlobalChunkPos.readBytes(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        pos.writeBytes(buf);
    }

    public static class Handler implements IMessageHandler<CPacketDesyncSif, IMessage>
    {
        public Handler()
        {
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketDesyncSif message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> PurMagClient.INSTANCE.sif_storage.remove(message.pos));
            return null;
        }
    }
}
