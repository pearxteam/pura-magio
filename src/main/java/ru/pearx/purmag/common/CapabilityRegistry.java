package ru.pearx.purmag.common;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import ru.pearx.purmag.common.aura.AuraContainer;
import ru.pearx.purmag.common.aura.IAuraContainer;
import ru.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import ru.pearx.purmag.common.infofield.playerdata.IfEntryStore;
import ru.pearx.purmag.common.sip.store.ISipStore;
import ru.pearx.purmag.common.sip.store.SipStore;

import javax.annotation.Nullable;

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

    public static final ResourceLocation ENTRY_STORE_NAME = Utils.gRL("if_entry_store");
    public static final ResourceLocation SIP_STORE_NAME = Utils.gRL("sip_store");
    public static final ResourceLocation AURA_CONTAINER_NAME = Utils.gRL("aura_container");

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IIfEntryStore.class, new Capability.IStorage<IIfEntryStore>()
        {
            @Override
            public NBTBase writeNBT(Capability<IIfEntryStore> capability, IIfEntryStore instance, EnumFacing side)
            {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IIfEntryStore> capability, IIfEntryStore instance, EnumFacing side, NBTBase nbt)
            {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, IfEntryStore.class);

        CapabilityManager.INSTANCE.register(ISipStore.class, new Capability.IStorage<ISipStore>()
        {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<ISipStore> capability, ISipStore instance, EnumFacing side)
            {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<ISipStore> capability, ISipStore instance, EnumFacing side, NBTBase nbt)
            {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, SipStore.class);

        CapabilityManager.INSTANCE.register(IAuraContainer.class, new Capability.IStorage<IAuraContainer>()
        {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IAuraContainer> capability, IAuraContainer instance, EnumFacing side)
            {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IAuraContainer> capability, IAuraContainer instance, EnumFacing side, NBTBase nbt)
            {
                instance.deserializeNBT((NBTTagCompound) nbt);
            }
        }, AuraContainer.class);
    }
}
