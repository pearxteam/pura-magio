package net.pearx.purmag.common.networking.packets;

import baubles.api.BaubleType;
import baubles.api.cap.BaublesCapabilities;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.items.ItemSipAmulet;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.sip.SipEffect;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.sip.SipTypeRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mrAppleXZ on 21.05.17 18:37.
 */
public class SPacketUseSipAmulet implements IMessage
{
    public SPacketUseSipAmulet()
    {

    }

    @Override
    public void fromBytes(ByteBuf buf)
    {

    }

    @Override
    public void toBytes(ByteBuf buf)
    {

    }

    public static class Handler implements IMessageHandler<SPacketUseSipAmulet, IMessage>
    {
        public Handler()
        {

        }

        @Override
        public IMessage onMessage(SPacketUseSipAmulet message, MessageContext ctx)
        {
            if(ItemSipAmulet.checkForAmulet(ctx.getServerHandler().player))
            {
                //todo spawn particles
                ItemStack amulet = ItemUtils.getBauble(ctx.getServerHandler().player, BaubleType.AMULET.getValidSlots()[0]);
                Map<String, Integer> sips = amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).getStored();
                for(Map.Entry<String, Integer> entr : sips.entrySet())
                {
                    SipEffect eff = PurMag.instance.sip_effects.getMap().get(entr.getKey());
                    int lvl = amulet.getMetadata();
                    if(eff.getMaxLevel() != -1 && lvl > eff.getMaxLevel())
                        lvl = eff.getMaxLevel();
                    ctx.getServerHandler().player.addPotionEffect(new PotionEffect(eff.getEffect(), eff.getTicks() * entr.getValue(), lvl, false, false));
                }
                amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).removeAll();
                ItemUtils.setBauble(ctx.getServerHandler().player, BaubleType.AMULET.getValidSlots()[0], amulet);
            }
            return null;
        }
    }
}
