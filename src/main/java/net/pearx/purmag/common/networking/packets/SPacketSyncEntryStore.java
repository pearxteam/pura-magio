package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.pearx.purmag.common.CapabilityRegistry;

/**
 * Created by mrAppleXZ on 23.04.17 11:32.
 */
public class SPacketSyncEntryStore implements IMessage
{
    NBTTagCompound tag;

    public SPacketSyncEntryStore() {}
    public SPacketSyncEntryStore(NBTTagCompound tag)
    {
        this.tag = tag;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        tag = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf, tag);
    }

    public static class Handler implements IMessageHandler<SPacketSyncEntryStore, IMessage>
    {

        @Override
        public IMessage onMessage(SPacketSyncEntryStore message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(new Runnable()
            {
                @Override
                public void run()
                {
                    Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).deserializeNBT(message.tag);
                }
            });
            return null;
        }
    }
}
