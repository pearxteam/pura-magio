package net.pearx.purmag.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.translation_desk.GuiTranslationDesk;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.IfEntry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IRSPapyrus;
import net.pearx.purmag.common.infofield.steps.IRSTranslatePapyrus;
import net.pearx.purmag.common.networking.ByteBufTools;
import net.pearx.purmag.common.tiles.TileTranslationDesk;

/**
 * Created by mrAppleXZ on 07.06.17 22:25.
 */
public class SPacketDoneTranslation implements IMessage
{
    public BlockPos pos;

    public SPacketDoneTranslation() {}
    public SPacketDoneTranslation(BlockPos pos)
    {
        this.pos = pos;
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        pos = ByteBufTools.readBlockPos(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufTools.writeBlockPos(buf, pos);
    }

    public static class Handler implements IMessageHandler<SPacketDoneTranslation, IMessage>
    {
        @Override
        public IMessage onMessage(SPacketDoneTranslation message, MessageContext ctx)
        {
            ((WorldServer)ctx.getServerHandler().player.world).addScheduledTask(() ->
            {
                TileEntity te = ctx.getServerHandler().player.world.getTileEntity(message.pos);
                if(te != null && te instanceof TileTranslationDesk)
                {
                    TileTranslationDesk ttd = (TileTranslationDesk) te;
                    ItemStack papyrus = ttd.handler.getStackInSlot(0);
                    if(!papyrus.isEmpty())
                    {
                        IIfEntryStore store = ctx.getServerHandler().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
                        for (IfEntry entr : PurMag.instance.if_registry.entries)
                        {
                            int steps = store.getSteps(entr.getId());
                            if (steps < entr.getSteps().size())
                            {
                                IIfResearchStep step = entr.getSteps().get(steps);
                                if (step instanceof IRSTranslatePapyrus)
                                {
                                    if(((IRSTranslatePapyrus) step).isSuitable(papyrus))
                                    {
                                        store.unlockStepAndSync(entr.getId(), ctx.getServerHandler().player);
                                    }
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
