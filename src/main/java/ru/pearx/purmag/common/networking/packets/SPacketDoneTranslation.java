package ru.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
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
import ru.pearx.purmag.common.infofield.steps.IRSTranslatePapyrus;
import ru.pearx.purmag.common.tiles.TileTranslationDesk;

/**
 * Created by mrAppleXZ on 07.06.17 22:25.
 */
public class SPacketDoneTranslation implements IMessage
{
    public BlockPos pos;
    public String expected;

    public SPacketDoneTranslation()
    {
    }

    public SPacketDoneTranslation(BlockPos pos, String expected)
    {
        this.pos = pos;
        this.expected = expected;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = ByteBufTools.readBlockPos(buf);
        expected = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufTools.writeBlockPos(buf, pos);
        ByteBufUtils.writeUTF8String(buf, expected);
    }

    public static class Handler implements IMessageHandler<SPacketDoneTranslation, IMessage>
    {
        @Override
        public IMessage onMessage(SPacketDoneTranslation message, MessageContext ctx)
        {
            EntityPlayerMP p = ctx.getServerHandler().player;
            p.getServerWorld().addScheduledTask(() ->
            {
                TileEntity te = p.getEntityWorld().getTileEntity(message.pos);
                if (te != null && te instanceof TileTranslationDesk)
                {
                    TileTranslationDesk ttd = (TileTranslationDesk) te;
                    ItemStack papyrus = ttd.getHandler().getStackInSlot(0);
                    if (!papyrus.isEmpty())
                    {
                        IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
                        for (Pair<IfEntry, IRSTranslatePapyrus> pair : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSTranslatePapyrus.class, p, store))
                        {
                            if (pair.getLeft().getId().equals(message.expected))
                            {
                                if (pair.getRight().isSuitable(papyrus))
                                {
                                    store.unlockStepAndSync(pair.getLeft().getId(), ctx.getServerHandler().player);
                                }
                            }
                        }
                    }
                }
            });
            return null;
        }
    }
}
