package net.pearx.purmag.common.sip.store;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.pearx.purmag.common.CapabilityRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 23.05.17 9:38.
 */
public class SipStoreProvider implements ICapabilitySerializable<NBTTagCompound>
{
    private ISipStore store;

    public SipStoreProvider(ISipStore store)
    {
        this.store = store;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityRegistry.SIP_STORE_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityRegistry.SIP_STORE_CAP)
            return CapabilityRegistry.SIP_STORE_CAP.cast(store);
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return store.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        store.deserializeNBT(nbt);
    }
}
