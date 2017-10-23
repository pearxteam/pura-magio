package ru.pearx.purmag.common.networking.packets.microscope;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.libmc.common.networking.ByteBufTools;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.steps.IRSMicroscopeResearch;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.tiles.TileMicroscope;

import java.util.Arrays;

/*
 * Created by mrAppleXZ on 20.10.17 15:30.
 */
public class SPacketCheckMicroscopeResearch implements IMessage
{
    public BlockPos pos;
    public boolean[][] data;
    public String entryName;

    public SPacketCheckMicroscopeResearch()
    {
    }

    public SPacketCheckMicroscopeResearch(BlockPos pos, boolean[][] data, String entryName)
    {
        this.pos = pos;
        this.data = data;
        this.entryName = entryName;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = ByteBufTools.readBlockPos(buf);
        int rows = buf.readInt();
        int columns = buf.readInt();
        boolean[][] bools = new boolean[rows][columns];
        for(int r = 0; r < rows; r++)
        {
            for(int c = 0; c < columns; c++)
            {
                bools[r][c] = buf.readBoolean();
            }
        }
        data = bools;
        entryName = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufTools.writeBlockPos(buf, pos);
        buf.writeInt(data.length);
        buf.writeInt(data[0].length);
        for(boolean[] bools : data)
        {
            for(boolean bool : bools)
            {
                buf.writeBoolean(bool);
            }
        }
        ByteBufUtils.writeUTF8String(buf, entryName);
    }

    public static class Handler implements IMessageHandler<SPacketCheckMicroscopeResearch, IMessage>
    {
        @Override
        public IMessage onMessage(SPacketCheckMicroscopeResearch message, MessageContext ctx)
        {
            EntityPlayerMP p = ctx.getServerHandler().player;
            p.getServerWorld().addScheduledTask(() ->
            {
                IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
                for(Pair<IfEntry, IRSMicroscopeResearch> pair : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSMicroscopeResearch.class, p, store))
                {
                    if(pair.getLeft().getId().equals(message.entryName))
                    {
                        if(Arrays.deepEquals(pair.getRight().getPattern(), message.data))
                        {
                            TileEntity te = p.getEntityWorld().getTileEntity(message.pos);
                            if (te != null && te instanceof TileMicroscope)
                            {
                                if(pair.getRight().getIngredient().apply(((TileMicroscope) te).handler.getStackInSlot(0)))
                                {
                                    store.unlockStepAndSync(pair.getLeft().getId(), p);
                                    NetworkManager.sendTo(new CPacketCheckMicroscopeResearchResponse(true), p);
                                    return;
                                }
                            }
                        }
                    }
                }
                NetworkManager.sendTo(new CPacketCheckMicroscopeResearchResponse(false), p);
            });
            return null;
        }
    }
}
