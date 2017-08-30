package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.common.CapabilityRegistry;

/*
 * Created by mrAppleXZ on 30.08.17 20:00.
 */
public class CPacketSyncAura implements IMessage
{
    private NBTTagCompound tag;

    public CPacketSyncAura()
    {

    }

    public CPacketSyncAura(NBTTagCompound tag)
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

    public static class Handler implements IMessageHandler<CPacketSyncAura, IMessage>
    {

        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(CPacketSyncAura message, MessageContext ctx)
        {
            Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.AURA_CONTAINER_CAP, null).deserializeNBT(message.tag));
            return null;
        }
    }
}
