package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
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
            sendUpdates(new NBTTagCompoundBuilder().setTag("items", serializeNBT()).build(), null);
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
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler);
        return super.getCapability(capability, facing);
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        if (tag.hasKey("items"))
            handler.deserializeNBT((NBTTagCompound) tag.getTag("items"));
    }

    @Override
    public void writeCustomData(NBTTagCompound tag)
    {
        tag.setTag("items", handler.serializeNBT());
    }

    public abstract boolean isItemValid(ItemStack stack);
}
