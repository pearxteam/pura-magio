package ru.pearx.purmag.common.tiles;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.lib.HashingUtils;
import ru.pearx.libmc.common.PXLCapabilities;
import ru.pearx.libmc.common.caps.animation.AnimationElement;
import ru.pearx.libmc.common.caps.animation.AnimationStateManager;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.tiles.TileSyncable;
import ru.pearx.purmag.common.SoundRegistry;
import ru.pearx.purmag.common.inventory.ContainerCodeStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/*
 * Created by mrAppleXZ on 09.09.17 20:48.
 */
public class TileCodeStorage extends TileSyncable
{
    @SideOnly(Side.CLIENT)
    public static class ClientAnimData
    {
        public boolean startedOpeningAnim;
        public long animStartTime;
    }

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

    public AnimationStateManager anim = new AnimationStateManager(this, new AnimationElement("head", "closed", "closed", "closing", "opened", "opening"));
    @SideOnly(Side.CLIENT)
    public ClientAnimData anim_data;

    private String text = null;
    private byte[] hash = null;
    private boolean unlocked;
    private boolean lockable;
    private boolean opened;

    public TileCodeStorage()
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
            anim_data = new ClientAnimData();
    }

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

    public boolean isOpened()
    {
        return opened;
    }

    public void setOpened(boolean opened)
    {
        if(opened != this.opened)
        {
            if(!world.isRemote)
                getWorld().playSound(null, getPos(), SoundRegistry.CODE_STORAGE_OPEN, SoundCategory.BLOCKS, 1, 1);
            if(opened)
                anim.changeState("head", "opening");
            else
                anim.changeState("head", "closing");
        }
        this.opened = opened;
    }

    @Override
    public NBTTagCompound getUpdateTag()
    {
        NBTTagCompound tag = super.getUpdateTag();
        tag.setBoolean("opened", isOpened());
        return tag;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt)
    {
        super.onDataPacket(net, pkt);
        setOpened(pkt.getNbtCompound().getBoolean("opened"));
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
                sendUpdates(serializeLockUpdate(new NBTTagCompound()), null);
                return true;
            }
            return false;
        }
        return true;
    }

    public boolean tryLock(boolean force, String text, String code)
    {
        if (isLockable() || force)
        {
            if (isUnlocked() || force)
            {
                setText(text);
                setCode(code);
                setUnlocked(false);
                sendUpdates(serializeLockUpdate(new NBTTagCompound()), null);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || capability == PXLCapabilities.ASM || super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(handler);
        if(capability == PXLCapabilities.ASM)
            return PXLCapabilities.ASM.cast(anim);
        return super.getCapability(capability, facing);
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        deserializeMin(tag);
        if(tag.hasKey("lockable", Constants.NBT.TAG_BYTE))
            setLockable(tag.getBoolean("lockable"));
    }

    @Override
    public void writeCustomData(NBTTagCompound tag)
    {
        serializeMin(tag);
        tag.setBoolean("lockable", isLockable());
    }

    public NBTTagCompound serializeMin(NBTTagCompound compound)
    {
        compound.setTag("items", handler.serializeNBT());
        serializeLockUpdate(compound);
        return compound;
    }

    private NBTTagCompound serializeLockUpdate(NBTTagCompound compound)
    {
        if (getText() != null)
            compound.setString("text", getText());
        if (getHash() != null)
            compound.setByteArray("hash", getHash());
        compound.setBoolean("unlocked", isUnlocked());
        return compound;
    }

    public void deserializeMin(NBTTagCompound compound)
    {
        if(compound.hasKey("items", Constants.NBT.TAG_COMPOUND))
            handler.deserializeNBT(compound.getCompoundTag("items"));
        if (compound.hasKey("text", Constants.NBT.TAG_STRING))
            setText(compound.getString("text"));
        if (compound.hasKey("hash", Constants.NBT.TAG_BYTE_ARRAY))
            setHash(compound.getByteArray("hash"));
        if(compound.hasKey("unlocked", Constants.NBT.TAG_BYTE))
            setUnlocked(compound.getBoolean("unlocked"));
    }
}
