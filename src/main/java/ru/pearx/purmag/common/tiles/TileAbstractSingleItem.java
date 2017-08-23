package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.tiles.TileSyncable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 17.08.17 21:28.
 */
public abstract class TileAbstractSingleItem extends TileSyncable
{
    public ItemStackHandler handler = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileAbstractSingleItem.this.markDirty();
            sendUpdatesToClients();
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
        {
            if (!isItemValid(stack))
                return stack;
            return super.insertItem(slot, stack, simulate);
        }
    };

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return true;
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return (T) handler;
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("items", handler.serializeNBT());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        if (compound.hasKey("items"))
            handler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
    }

    public abstract boolean isItemValid(ItemStack stack);
}
