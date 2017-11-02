package ru.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.tiles.TileSyncable;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 30.10.17 21:48.
 */
public class TileMagibench extends TileSyncable
{
    private int tier;
    public ItemStackHandler handler;

    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier, boolean sync)
    {
        MagibenchRegistry.Tier t = PurMag.INSTANCE.getMagibenchRegistry().getTier(tier);
        if(handler != null && getTier() != tier && !getWorld().isRemote)
        {
            ItemStackUtils.drop(handler, getWorld(), getPos());
        }
        this.tier = tier;
        handler = new ItemStackHandler(t.getWidth() * t.getHeight());
        if(sync)
            sendUpdatesToClients();
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler) : super.getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        setTier(compound.getInteger("tier"), false);
        handler.deserializeNBT(compound.getCompoundTag("items"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound.setInteger("tier", getTier());
        compound.setTag("items", handler.serializeNBT());
        return super.writeToNBT(compound);
    }

    public boolean canWork()
    {
        //todo check the structure here.
        return true;
    }
}
