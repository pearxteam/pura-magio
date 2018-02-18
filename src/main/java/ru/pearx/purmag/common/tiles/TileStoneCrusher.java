package ru.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.tiles.TileMultiblockMaster;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 16.11.17 20:31.
 */
public class TileStoneCrusher extends TileMultiblockMaster
{
    public ItemStackHandler handler = new ItemStackHandler()
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileStoneCrusher.this.markDirty();
            sendUpdates(new NBTTagCompoundBuilder().setTag("items", serializeNBT()).build());
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
        if (compound.hasKey("items", Constants.NBT.TAG_COMPOUND))
            handler.deserializeNBT((NBTTagCompound) compound.getTag("items"));
    }
}
