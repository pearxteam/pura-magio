package ru.pearx.purmag.common.networking.packets.code_storage;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.carbide.mc.client.gui.PXLGui;
import ru.pearx.carbide.mc.client.gui.controls.Control;
import ru.pearx.purmag.client.gui.code_storage.GuiCodeStorageUnlock;

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
                if(Minecraft.getMinecraft().currentScreen != null)
                {
                    if (Minecraft.getMinecraft().currentScreen instanceof PXLGui)
                    {
                        for (Control c : ((PXLGui) Minecraft.getMinecraft().currentScreen).gui.getControls())
                        {
                            if (c instanceof GuiCodeStorageUnlock)
                            {
                                ((GuiCodeStorageUnlock) c).handleResponsePacket(message);
                            }
                        }
                    }
                }
            });
            return null;
        }
    }
}
