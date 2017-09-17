package ru.pearx.purmag.common.networking.packets.code_storage;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.pearx.libmc.common.networking.ByteBufTools;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

/*
 * Created by mrAppleXZ on 17.09.17 15:16.
 */
public class SPacketUnlock implements IMessage
{
    public String code;
    public BlockPos pos;

    public SPacketUnlock(){}
    public SPacketUnlock(String code, BlockPos pos)
    {
        this.code = code;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        code = ByteBufUtils.readUTF8String(buf);
        pos = ByteBufTools.readBlockPos(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, code);
        ByteBufTools.writeBlockPos(buf, pos);
    }

    public static class Handler implements IMessageHandler<SPacketUnlock, IMessage>
    {
        @Override
        public IMessage onMessage(SPacketUnlock message, MessageContext ctx)
        {
            ctx.getServerHandler().player.getServerWorld().addScheduledTask(() ->
            {
                boolean result = false;
                TileEntity te = ctx.getServerHandler().player.world.getTileEntity(message.pos);
                if(te != null && te instanceof TileCodeStorage)
                {
                    TileCodeStorage storage = (TileCodeStorage) te;
                    if(!storage.isUnlocked())
                    {
                        if(storage.getCode().equals(message.code))
                        {
                            storage.setUnlocked(true, false);
                            storage.setCode("");
                            storage.setText("", false);
                            storage.sendUpdatesToClients();
                            result = true;
                        }
                    }
                }
                NetworkManager.sendTo(new CPacketUnlockResponse(result), ctx.getServerHandler().player);
            });
            return null;
        }
    }
}
