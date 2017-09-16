package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.tiles.TileSyncable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 09.09.17 20:48.
 */
public class TileCodeChest extends TileSyncable
{
    public ItemStackHandler handler = new ItemStackHandler(40)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileCodeChest.this.markDirty();
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate)
        {
            if(unlocked)
                return super.extractItem(slot, amount, simulate);
            return ItemStack.EMPTY;
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
        {
            if(unlocked)
                return super.insertItem(slot, stack, simulate);
            return stack;
        }
    };

    private String text;
    private String code;
    private boolean unlocked;

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
        sendUpdatesToClients();
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public boolean isUnlocked()
    {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
        sendUpdatesToClients();
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
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler);
        return super.getCapability(capability, facing);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        compound.setTag("items", handler.serializeNBT());
        compound.setString("text", getText());
        compound.setString("code", getCode());
        compound.setBoolean("unlocked", isUnlocked());
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound tag = new NBTTagCompound();
        writeToNBT(tag);
        tag.removeTag("code");
        return tag;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        handler.deserializeNBT(compound.getCompoundTag("items"));
        setText(compound.getString("text"));
        if(compound.hasKey("code", Constants.NBT.TAG_STRING))
            setCode(compound.getString("code"));
        setUnlocked(compound.getBoolean("unlocked"));
    }
}
