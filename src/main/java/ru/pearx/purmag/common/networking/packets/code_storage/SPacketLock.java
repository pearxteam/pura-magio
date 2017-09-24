package ru.pearx.purmag.common.networking.packets.code_storage;

import io.netty.buffer.ByteBuf;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.pearx.libmc.common.networking.ByteBufTools;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

/*
 * Created by mrAppleXZ on 17.09.17 15:08.
 */
public class SPacketLock implements IMessage
{
    public String text, code;
    public BlockPos pos;

    public SPacketLock(){}
    public SPacketLock(String text, String code, BlockPos pos)
    {
        this.text = text;
        this.code = code;
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        text = ByteBufUtils.readUTF8String(buf);
        code = ByteBufUtils.readUTF8String(buf);
        pos = ByteBufTools.readBlockPos(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, text);
        ByteBufUtils.writeUTF8String(buf, code);
        ByteBufTools.writeBlockPos(buf, pos);
    }

    public static class Handler implements IMessageHandler<SPacketLock, IMessage>
    {

        @Override
        public IMessage onMessage(SPacketLock message, MessageContext ctx)
        {
            (ctx.getServerHandler().player.getServerWorld()).addScheduledTask(() ->
            {
                TileEntity te = ctx.getServerHandler().player.world.getTileEntity(message.pos);
                if(te != null && te instanceof TileCodeStorage)
                {
                    TileCodeStorage storage = (TileCodeStorage) te;
                    storage.tryLock(false, message.text, message.code);
                }
            });
            return null;
        }
    }
}
