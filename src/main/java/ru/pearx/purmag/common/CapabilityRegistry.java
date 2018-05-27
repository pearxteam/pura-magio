package ru.pearx.purmag.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import ru.pearx.carbide.mc.common.CapabilityStorageSimple;
import ru.pearx.purmag.common.aura.AuraContainer;
import ru.pearx.purmag.common.aura.IAuraContainer;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.playerdata.IfEntryStore;
import ru.pearx.purmag.common.sif.ISifStorage;
import ru.pearx.purmag.common.sif.SifStorage;
import ru.pearx.purmag.common.sip.store.ISipStore;
import ru.pearx.purmag.common.sip.store.SipStore;

/**
 * Created by mrAppleXZ on 22.04.17 17:05.
 */
public class CapabilityRegistry
{
    @CapabilityInject(IIfEntryStore.class)
    public static final Capability<IIfEntryStore> ENTRY_STORE_CAP = null;
    @CapabilityInject(ISipStore.class)
    public static final Capability<ISipStore> SIP_STORE_CAP = null;
    @CapabilityInject(IAuraContainer.class)
    public static final Capability<IAuraContainer> AURA_CONTAINER_CAP = null;
    @CapabilityInject(ISifStorage.class)
    public static final Capability<ISifStorage> SIF_STORAGE = null;

    public static final ResourceLocation ENTRY_STORE_NAME = Utils.gRL("if_entry_store");
    public static final ResourceLocation SIP_STORE_NAME = Utils.gRL("sip_store");
    public static final ResourceLocation AURA_CONTAINER_NAME = Utils.gRL("aura_container");
    public static final ResourceLocation SIF_STORAGE_NAME = Utils.gRL("sif_storage");

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IIfEntryStore.class, new CapabilityStorageSimple<>(NBTTagCompound.class), IfEntryStore.class);
        CapabilityManager.INSTANCE.register(ISipStore.class, new CapabilityStorageSimple<>(NBTTagCompound.class), SipStore.class);
        CapabilityManager.INSTANCE.register(IAuraContainer.class, new CapabilityStorageSimple<>(NBTTagCompound.class), AuraContainer.class);
        CapabilityManager.INSTANCE.register(ISifStorage.class, new CapabilityStorageSimple<>(NBTTagCompound.class), SifStorage.class);
    }
}
