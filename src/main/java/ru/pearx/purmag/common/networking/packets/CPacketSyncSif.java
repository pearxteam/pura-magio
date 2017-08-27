package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.common.GlobalChunkPos;
import ru.pearx.purmag.client.PurMagClient;

/**
 * Created by mrAppleXZ on 27.06.17 15:41.
 */
public class CPacketSyncSif implements IMessage
{
    public GlobalChunkPos pos;
    public float value;

    public CPacketSyncSif()
    {
    }

    public CPacketSyncSif(GlobalChunkPos pos, float value)
    {
        this.pos = pos;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = GlobalChunkPos.readBytes(buf);
        value = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        pos.writeBytes(buf);
        buf.writeFloat(value);
    }

    public static class Handler implements IMessageHandler<CPacketSyncSif, IMessage>
    {
        public Handler()
        {
        }

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSyncSif message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> PurMagClient.INSTANCE.sif_storage.set(message.pos, message.value));
            return null;
        }
    }
}
