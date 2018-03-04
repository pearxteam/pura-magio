package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.nbt.serialization.NBTSerializer;
import ru.pearx.libmc.common.tiles.syncable.TileSyncableComposite;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 17.08.17 21:28.
 */
public abstract class TileAbstractSingleItem extends TileSyncableComposite
{
    public static final String NBT_ITEMS = "items";
    public static final String NBT_ITEMS_UPDATE = "items_update";

    public ItemStackHandler handler = new ItemStackHandler(1)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            markDirty();
            sendUpdates(null, new NBTTagCompoundBuilder().setTag(NBT_ITEMS_UPDATE, ItemStackUtils.writeSlotUpdate(this, slot)).build());
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

    public TileAbstractSingleItem()
    {
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_ITEMS, NBTTagCompound.class, handler::deserializeNBT, handler::serializeNBT));
        getSerializers().add(new NBTSerializer.Reader<>(NBT_ITEMS_UPDATE, NBTTagCompound.class, (tag) -> ItemStackUtils.loadSlotUpdate(tag, handler)));
    }

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

    public abstract boolean isItemValid(ItemStack stack);
}
