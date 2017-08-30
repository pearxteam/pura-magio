package ru.pearx.purmag.common.aura;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import ru.pearx.purmag.common.CapabilityRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 30.08.17 19:40.
 */
public class AuraContainerProvider implements ICapabilitySerializable<NBTTagCompound>
{
    public AuraContainer container = new AuraContainer();
    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityRegistry.AURA_CONTAINER_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityRegistry.AURA_CONTAINER_CAP)
        {
            return CapabilityRegistry.AURA_CONTAINER_CAP.cast(container);
        }
        return null;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        return container.serializeNBT();
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        container.deserializeNBT(nbt);
    }
}
