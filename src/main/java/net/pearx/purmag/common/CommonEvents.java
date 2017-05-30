package net.pearx.purmag.common;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.infofield.IfEntry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.playerdata.IfEntryStoreProvier;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IRSCollect;
import net.pearx.purmag.common.items.ItemSipAmulet;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.CPacketDisplayMessage;
import net.pearx.purmag.common.sip.store.SipStoreAll;
import net.pearx.purmag.common.sip.store.SipStoreProvider;


/**
 * Created by mrAppleXZ on 21.04.17 16:41.
 */
public class CommonEvents
{
    @SubscribeEvent
    public void onPickup(EntityItemPickupEvent e)
    {
        if(e.getEntityPlayer() instanceof EntityPlayerMP)
        {
            EntityPlayerMP p = (EntityPlayerMP)e.getEntityPlayer();
            IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null);
            for (IfEntry entr : PurMag.instance.if_registry.entries)
            {
                int steps = store.getSteps(entr.getId());
                if(steps < entr.getSteps().size())
                {
                    IIfResearchStep step = entr.getSteps().get(steps);
                    if (step instanceof IRSCollect)
                    {
                        if (entr.isAvailableToResearch(p))
                        {
                            IRSCollect s = (IRSCollect) step;
                            if (s.isSuitable(e.getItem().getEntityItem()))
                            {
                                store.unlockStepAndSync(entr.getId(), p);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onEntityCaps(AttachCapabilitiesEvent<Entity> e)
    {
        if(e.getObject() instanceof EntityPlayer)
            e.addCapability(CapabilityRegistry.ENTRY_STORE_NAME, new IfEntryStoreProvier());
    }

    @SubscribeEvent
    public void onItemCaps(AttachCapabilitiesEvent<Item> e)
    {
        if(e.getObject() instanceof ItemSipAmulet)
            e.addCapability(CapabilityRegistry.SIP_STORE_NAME, new SipStoreProvider(new SipStoreAll()));
    }

    @SubscribeEvent
    public void onRespawn(PlayerEvent.PlayerRespawnEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).sync((EntityPlayerMP)e.player);
    }

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).sync((EntityPlayerMP)e.player);
    }

    @SubscribeEvent
    public void onClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone e)
    {
        NBTTagCompound old = e.getOriginal().getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).serializeNBT();
        e.getEntityPlayer().getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).deserializeNBT(old);
    }

    @SubscribeEvent
    public void onChangeDim(PlayerEvent.PlayerChangedDimensionEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).sync((EntityPlayerMP)e.player);
    }
}
