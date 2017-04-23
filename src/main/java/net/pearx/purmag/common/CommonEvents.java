package net.pearx.purmag.common;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.pearx.purmag.common.infofield.playerdata.IfEntryStoreProvier;


/**
 * Created by mrAppleXZ on 21.04.17 16:41.
 */
public class CommonEvents
{
    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e)
    {
        if(e.getItemStack().hasTagCompound())
        {
            if(e.getItemStack().getTagCompound().hasKey("sipStored"))
            {
                e.getToolTip().add(I18n.format("sip_tooltip") + e.getItemStack().getTagCompound().getInteger("sipStored"));
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
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).sync(e.player);
    }

    @SubscribeEvent
    public void onJoin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).sync(e.player);
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
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).sync(e.player);
    }

}
