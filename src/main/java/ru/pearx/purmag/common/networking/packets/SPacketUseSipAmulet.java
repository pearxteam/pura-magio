package ru.pearx.purmag.common.networking.packets;

import baubles.api.BaubleType;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.items.ItemSipAmulet;
import ru.pearx.purmag.common.items.ItemUtils;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.sip.SipEffect;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;
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
            ((WorldServer)ctx.getServerHandler().player.world).addScheduledTask(() ->
            {
                EntityPlayer p = ctx.getServerHandler().player;
                if (ItemSipAmulet.checkForAmulet(p))
                {
                    ItemStack amulet = ItemUtils.getBauble(p, BaubleType.AMULET.getValidSlots()[0]);
                    Map<String, Integer> sips = amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).getStored();
                    for (Map.Entry<String, Integer> entr : sips.entrySet())
                    {
                        SipEffect eff = PurMag.INSTANCE.sip_effects.getMap().get(entr.getKey());
                        int lvl = amulet.getMetadata();
                        if (eff.getMaxLevel() != -1 && lvl > eff.getMaxLevel())
                            lvl = eff.getMaxLevel();
                        p.addPotionEffect(new PotionEffect(eff.getEffect(), eff.getTicks() * entr.getValue(), lvl, false, false));
                        float rads = (float) Math.toRadians(p.rotationYaw + 180);
                        Vector2f vec = new Vector2f(MathHelper.sin(rads), -MathHelper.cos(rads));
                        for (int i = entr.getValue(); i > 0; i -= 8)
                        {
                            NetworkManager.sendToAllAround(new CPacketSpawnSipParticle(
                                            new Vector3d(p.posX, p.posY + 1.3f, p.posZ),
                                            new Vector3d(p.posX + (vec.x * 5) + (PurMag.INSTANCE.random.nextFloat() * 4 - 2), p.posY + 1.3f + (PurMag.INSTANCE.random.nextFloat() * 4 - 2), p.posZ + (vec.y * 5) + (PurMag.INSTANCE.random.nextFloat() * 4 - 2)),
                                            0.1f,
                                            p.dimension,
                                            entr.getKey(),
                                            i >= 8 ? 8 : i
                                    ),
                                    (int) p.posX, (int) p.posY, (int) p.posZ, p.dimension, 300);
                        }
                    }
                    amulet.getCapability(CapabilityRegistry.SIP_STORE_CAP, null).removeAll();
                    ItemUtils.setBauble(ctx.getServerHandler().player, BaubleType.AMULET.getValidSlots()[0], amulet);
                }
            });
            return null;
        }
    }
}