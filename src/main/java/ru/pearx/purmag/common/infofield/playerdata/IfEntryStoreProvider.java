package ru.pearx.purmag.common.infofield.playerdata;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import ru.pearx.purmag.common.CapabilityRegistry;

import javax.annotation.Nullable;

/**
 * Created by mrAppleXZ on 22.04.17 18:18.
 */
public class IfEntryStoreProvider implements ICapabilitySerializable<NBTTagCompound>
{
    public IIfEntryStore cap = new IfEntryStore();

    @Override
    public NBTTagCompound serializeNBT()
    {
        return cap.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        cap.deserializeNBT(nbt);
    }


    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityRegistry.ENTRY_STORE_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityRegistry.ENTRY_STORE_CAP)
            return CapabilityRegistry.ENTRY_STORE_CAP.cast(cap);

        return null;
    }
}
