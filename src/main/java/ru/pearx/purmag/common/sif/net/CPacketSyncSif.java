package ru.pearx.purmag.common.sif.net;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.common.CapabilityRegistry;

/**
 * Created by mrAppleXZ on 27.06.17 15:41.
 */
public class CPacketSyncSif implements IMessage
{
    public int chunkX;
    public int chunkZ;
    public float value;

    public CPacketSyncSif()
    {
    }

    public CPacketSyncSif(int chunkX, int chunkZ, float value)
    {
        this.chunkX = chunkX;
        this.chunkZ = chunkZ;
        this.value = value;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        chunkX = buf.readInt();
        chunkZ = buf.readInt();
        value = buf.readFloat();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(chunkX);
        buf.writeInt(chunkZ);
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
            Minecraft.getMinecraft().addScheduledTask(() ->
                    Minecraft.getMinecraft().world.getChunkFromChunkCoords(message.chunkX, message.chunkZ).getCapability(CapabilityRegistry.SIF_STORAGE, null).setPower(message.value));
            return null;
        }
    }
}
