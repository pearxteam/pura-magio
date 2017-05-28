package net.pearx.purmag.common.networking.packets;

import baubles.api.BaubleType;
import baubles.api.cap.BaublesCapabilities;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.items.ItemSipAmulet;
import net.pearx.purmag.common.items.ItemUtils;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.sip.SipEffect;
import net.pearx.purmag.common.sip.SipType;
import net.pearx.purmag.common.sip.SipTypeRegistry;
import org.lwjgl.input.Mouse;

import javax.vecmath.Vector3d;
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
                EntityPlayer p = ctx.getServerHandler().player;
                ItemStack amulet = ItemUtils.getBauble(p, BaubleType.AMULET.getValidSlots()[0]);
                Map<String, Integer> sips = amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).getStored();
                for(Map.Entry<String, Integer> entr : sips.entrySet())
                {
                    SipEffect eff = PurMag.instance.sip_effects.getMap().get(entr.getKey());
                    int lvl = amulet.getMetadata();
                    if(eff.getMaxLevel() != -1 && lvl > eff.getMaxLevel())
                        lvl = eff.getMaxLevel();
                    p.addPotionEffect(new PotionEffect(eff.getEffect(), eff.getTicks() * entr.getValue(), lvl, false, false));
                    for(int i = 0; i < entr.getValue() / 16f; i ++)
                    {
                        NetworkManager.sendToAllAround(new CPacketSpawnSipParticle(
                                        new Vector3d(p.posX, p.posY + 1, p.posZ),
                                        new Vector3d(p.posX + (PurMag.rand.nextFloat() * 10 - 5), p.posY + 1 + (PurMag.rand.nextFloat() * 10 - 5), p.posZ + (PurMag.rand.nextFloat() * 10 - 5)),
                                        0.1f,
                                        p.dimension,
                                        entr.getKey(),
                                        entr.getValue() % 16 == 0 ? 16 : entr.getValue() % 16
                                ),
                                (int)p.posX, (int)p.posY, (int)p.posZ, p.dimension, 300);
                    }
                }
                amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).removeAll();
                ItemUtils.setBauble(ctx.getServerHandler().player, BaubleType.AMULET.getValidSlots()[0], amulet);
            }
            return null;
        }
    }
}
