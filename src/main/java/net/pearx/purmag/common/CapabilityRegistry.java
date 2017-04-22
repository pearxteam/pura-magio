package net.pearx.purmag.common;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.pearx.purmag.common.infofield.playerdata.IIfEntryStore;
import net.pearx.purmag.common.infofield.playerdata.IfEntryStore;

/**
 * Created by mrAppleXZ on 22.04.17 17:05.
 */
public class CapabilityRegistry
{
    @CapabilityInject(IIfEntryStore.class)
    public static final Capability<IIfEntryStore> ENTRY_STORE_CAPABILITY = null;
    public static final ResourceLocation ENTRY_STORE_NAME = Utils.getRegistryName("if_entry_store");

    public static void setup()
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
                instance.deserializeNBT((NBTTagCompound)nbt);
            }
        }, IfEntryStore.class);
    }
}
