package net.pearx.purmag.common;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.common.infofield.IfEntry;
import net.pearx.purmag.common.infofield.IfRegistry;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.playerdata.IfEntryStoreProvier;
import net.pearx.purmag.common.infofield.steps.IIfResearchStep;
import net.pearx.purmag.common.infofield.steps.IIfResearchStepCollect;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.CPacketDisplayMessage;


/**
 * Created by mrAppleXZ on 21.04.17 16:41.
 */
public class CommonEvents
{
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e)
    {

    }

    @SubscribeEvent
    public void onPickup(EntityItemPickupEvent e)
    {
        if(e.getEntityPlayer() instanceof EntityPlayerMP)
        {
            EntityPlayerMP p = (EntityPlayerMP)e.getEntityPlayer();
            IIfEntryStore store = p.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null);
            for (IfEntry entr : PurMag.instance.if_registry.entries)
            {
                int steps = store.getSteps(entr.getId());
                if(steps < entr.getSteps().size())
                {
                    IIfResearchStep step = entr.getSteps().get(steps);
                    if (step instanceof IIfResearchStepCollect)
                    {
                        if (entr.isAllParentsUnlocked(p))
                        {
                            IIfResearchStepCollect s = (IIfResearchStepCollect) step;
                            if (s.isSuitable(e.getItem().getEntityItem()))
                            {
                                store.setSteps(entr.getId(), steps + 1);
                                store.sync(p);
                                NetworkManager.sendTo(new CPacketDisplayMessage(new DisplayMessage("%0", "%1", "if_entry:" + entr.getId(), "i18n:if_step.unlocked.text")), p);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onCaps(AttachCapabilitiesEvent<Entity> e)
    {
        if(e.getObject() instanceof EntityPlayer)
            e.addCapability(CapabilityRegistry.ENTRY_STORE_NAME, new IfEntryStoreProvier());
    }

    @SubscribeEvent
    public void onRespawn(PlayerEvent.PlayerRespawnEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).sync((EntityPlayerMP)e.player);
    }

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).sync((EntityPlayerMP)e.player);
    }

    @SubscribeEvent
    public void onClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone e)
    {
        NBTTagCompound old = e.getOriginal().getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).serializeNBT();
        e.getEntityPlayer().getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).deserializeNBT(old);
    }

    @SubscribeEvent
    public void onChangeDim(PlayerEvent.PlayerChangedDimensionEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).sync((EntityPlayerMP)e.player);
    }
}
