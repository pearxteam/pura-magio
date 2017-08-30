package ru.pearx.purmag.common.events;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.aura.AuraContainerProvider;
import ru.pearx.purmag.common.infofield.playerdata.IfEntryStoreProvider;

/**
 * Created by mrAppleXZ on 25.06.17 11:58.
 */
@Mod.EventBusSubscriber(modid = PurMag.MODID)
public class CapabilityEvents
{
    @SubscribeEvent
    public static void onEntityCaps(AttachCapabilitiesEvent<Entity> e)
    {
        if (e.getObject() instanceof EntityPlayer)
        {
            e.addCapability(CapabilityRegistry.ENTRY_STORE_NAME, new IfEntryStoreProvider());
            e.addCapability(CapabilityRegistry.AURA_CONTAINER_NAME, new AuraContainerProvider());
        }
    }

    @SubscribeEvent
    public static void onRespawn(PlayerEvent.PlayerRespawnEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).sync((EntityPlayerMP) e.player);
        e.player.getCapability(CapabilityRegistry.AURA_CONTAINER_CAP, null).sync((EntityPlayerMP) e.player);
    }

    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).sync((EntityPlayerMP) e.player);
        e.player.getCapability(CapabilityRegistry.AURA_CONTAINER_CAP, null).sync((EntityPlayerMP) e.player);
    }

    @SubscribeEvent
    public static void onClone(net.minecraftforge.event.entity.player.PlayerEvent.Clone e)
    {
        e.getEntityPlayer().getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).deserializeNBT(e.getOriginal().getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).serializeNBT());
        e.getEntityPlayer().getCapability(CapabilityRegistry.AURA_CONTAINER_CAP, null).deserializeNBT(e.getOriginal().getCapability(CapabilityRegistry.AURA_CONTAINER_CAP, null).serializeNBT());
    }

    @SubscribeEvent
    public static void onChangeDim(PlayerEvent.PlayerChangedDimensionEvent e)
    {
        e.player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).sync((EntityPlayerMP) e.player);
        e.player.getCapability(CapabilityRegistry.AURA_CONTAINER_CAP, null).sync((EntityPlayerMP) e.player);
    }
}
