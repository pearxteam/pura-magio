package ru.pearx.purmag.common.sif;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import ru.pearx.purmag.common.CapabilityRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 27.05.18 12:02.
 */
public class SifStorageProvider implements ICapabilitySerializable<NBTTagCompound>
{
    private ISifStorage storage;

    public SifStorageProvider(ISifStorage storage)
    {
        this.storage = storage;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityRegistry.SIF_STORAGE;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityRegistry.SIF_STORAGE)
            return CapabilityRegistry.SIF_STORAGE.cast(storage);
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return storage.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        storage.deserializeNBT(nbt);
    }
}
