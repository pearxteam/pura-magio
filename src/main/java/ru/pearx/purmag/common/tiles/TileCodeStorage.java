package ru.pearx.purmag.common.tiles;

import com.google.common.collect.ImmutableMap;
import javafx.animation.Animation;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.model.animation.AnimationStateMachine;
import net.minecraftforge.common.model.animation.CapabilityAnimation;
import net.minecraftforge.common.model.animation.IAnimationStateMachine;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.lib.HashingUtils;
import ru.pearx.libmc.common.tiles.TileSyncable;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.inventory.ContainerCodeStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/*
 * Created by mrAppleXZ on 09.09.17 20:48.
 */
public class TileCodeStorage extends TileSyncable
{
    public ItemStackHandler handler = new ItemStackHandler(ContainerCodeStorage.SLOT_COUNT)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileCodeStorage.this.markDirty();
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

    private String text = null;
    private byte[] hash = null;
    private boolean unlocked;
    private boolean lockable;

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public byte[] getHash()
    {
        return hash;
    }

    protected void setHash(byte[] hash)
    {
        this.hash = hash;
    }

    public boolean isUnlocked()
    {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
    }

    public boolean isLockable()
    {
        return lockable;
    }

    public void setLockable(boolean lockable)
    {
        this.lockable = lockable;
    }

    public void setCode(String code)
    {
        setHash(HashingUtils.getHash("SHA-512", getWorld().getSeed() + code));
    }

    public boolean isCodeValid(String code)
    {
        return getHash() != null && Arrays.equals(getHash(), HashingUtils.getHash("SHA-512", getWorld().getSeed() + code));
    }

    public boolean tryUnlock(String code)
    {
        if(!isUnlocked())
        {
            if(isCodeValid(code))
            {
                setUnlocked(true);
                setHash(null);
                setText(null);
                sendUpdatesToClients();
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean tryLock(boolean force,String text, String code)
    {
        if (isLockable() || force)
        {
            if (isUnlocked() || force)
            {
                setText(text);
                setCode(code);
                setUnlocked(false);
                sendUpdatesToClients();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == CapabilityAnimation.ANIMATION_CAPABILITY || super.hasCapability(capability, facing);
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
        serializeMin(compound);
        compound.setBoolean("lockable", isLockable());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        deserializeMin(compound);
        setLockable(compound.getBoolean("lockable"));
    }

    public NBTTagCompound serializeMin(NBTTagCompound compound)
    {
        compound.setTag("items", handler.serializeNBT());
        if (getText() != null)
            compound.setString("text", getText());
        if (getHash() != null)
            compound.setByteArray("hash", getHash());
        compound.setBoolean("unlocked", isUnlocked());
        return compound;
    }

    public void deserializeMin(NBTTagCompound compound)
    {
        handler.deserializeNBT(compound.getCompoundTag("items"));
        if (compound.hasKey("text", Constants.NBT.TAG_STRING))
            setText(compound.getString("text"));
        if (compound.hasKey("hash", Constants.NBT.TAG_BYTE_ARRAY))
            setHash(compound.getByteArray("hash"));
        setUnlocked(compound.getBoolean("unlocked"));
    }
}
