package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.carbide.HashingUtils;
import ru.pearx.carbide.mc.CarbideMC;
import ru.pearx.carbide.mc.common.ItemStackUtils;
import ru.pearx.carbide.mc.common.nbt.serialization.NBTSerializer;
import ru.pearx.carbide.mc.common.tiles.syncable.TileSyncableComposite;
import ru.pearx.carbide.mc.common.tiles.syncable.WriteTarget;
import ru.pearx.purmag.common.SoundRegistry;
import ru.pearx.purmag.common.inventory.ContainerCodeStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;

/*
 * Created by mrAppleXZ on 09.09.17 20:48.
 */
public class TileCodeStorage extends TileSyncableComposite
{
    public static final String NBT_LOCKABLE = "lockable";
    public static final String NBT_ITEMS = "items";
    public static final String NBT_TEXT = "text";
    public static final String NBT_HASH = "hash";
    public static final String NBT_UNLOCKED = "unlocked";
    public static final String NBT_SLOT_UPDATE = "slot_update";
    public static final String NBT_OPENED = "opened";
    public static final String NBT_OPENED_UPDATE = "opened_u";

    public static final String[] NBT_LOCK_UPDATE = new String[]{NBT_TEXT, NBT_HASH, NBT_UNLOCKED};
    public static final String[] NBT_ITEM_DATA = new String[]{NBT_ITEMS, NBT_TEXT, NBT_HASH, NBT_UNLOCKED};

    private boolean lockable;
    private ItemStackHandler handler = new ItemStackHandler(ContainerCodeStorage.SLOT_COUNT)
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            markDirty();
            sendUpdates(null, ItemStackUtils.writeSlotUpdate(this, slot));
        }

        @Nonnull
        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate)
        {
            if (unlocked)
                return super.extractItem(slot, amount, simulate);
            return ItemStack.EMPTY;
        }

        @Nonnull
        @Override
        public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
        {
            if (unlocked)
                return super.insertItem(slot, stack, simulate);
            return stack;
        }
    };
    private String text = null;
    private byte[] hash = null;
    private boolean unlocked = true;
    private boolean opened;
    @SideOnly(Side.CLIENT)
    private long openTime;

    public TileCodeStorage()
    {
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_LOCKABLE, boolean.class, this::setLockable, this::isLockable));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_ITEMS, NBTTagCompound.class, handler::deserializeNBT, handler::serializeNBT));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_TEXT, String.class, this::setText, this::getText));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_HASH, byte[].class, this::setHash, this::getHash));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_UNLOCKED, boolean.class, this::setUnlocked, this::isUnlocked));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_OPENED, boolean.class, this::setOpened, this::isOpened, WriteTarget.SAVE));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_OPENED_UPDATE, boolean.class, (Boolean b) -> setOpenedAndUpdate(CarbideMC.PROXY.getClientPlayer(), b), this::isOpened, WriteTarget.PARTIAL_UPDATE));
        getSerializers().add(new NBTSerializer.Reader<>(NBT_SLOT_UPDATE, NBTTagCompound.class, (tag) -> ItemStackUtils.loadSlotUpdate(tag, handler)));
    }

    public ItemStackHandler getHandler()
    {
        return handler;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
        markDirty();
    }

    public byte[] getHash()
    {
        return hash;
    }

    protected void setHash(byte[] hash)
    {
        this.hash = hash;
        markDirty();
    }

    public boolean isUnlocked()
    {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked)
    {
        this.unlocked = unlocked;
        markDirty();
    }

    public boolean isLockable()
    {
        return lockable;
    }

    public void setLockable(boolean lockable)
    {
        this.lockable = lockable;
        markDirty();
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
        this.opened = opened;
        markDirty();
    }

    @SideOnly(Side.CLIENT)
    public long getOpenTime()
    {
        return openTime;
    }

    @SideOnly(Side.CLIENT)
    public void setOpenTime(long openTime)
    {
        this.openTime = openTime;
    }

    public void setOpenedAndUpdate(EntityPlayer p, boolean opened)
    {
        if(opened != this.opened)
        {
            setOpened(opened);
            if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && world.isRemote)
            {
                setOpenTime(System.currentTimeMillis());
                world.playSound(p, getPos(), SoundRegistry.CODE_STORAGE_OPEN, SoundCategory.BLOCKS, 1, 1);
            }
            else
                sendUpdates(p, NBT_OPENED_UPDATE);
        }
    }

    public boolean isCodeValid(String code)
    {
        return getHash() != null && Arrays.equals(getHash(), HashingUtils.getHash("SHA-512", getWorld().getSeed() + code));
    }

    public boolean canInteract(EntityPlayer p)
    {
        return p == null || p.getDistanceSq(getPos()) <= 64;
    }

    public boolean tryUnlock(EntityPlayer p, String code)
    {
        if(canInteract(p))
        {
            if (!isUnlocked())
            {
                if (isCodeValid(code))
                {
                    setUnlocked(true);
                    setHash(null);
                    setText(null);
                    sendUpdates(null, NBT_LOCK_UPDATE);
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public boolean tryLock(EntityPlayer p, boolean force, String text, String code)
    {
        if(canInteract(p))
        {
            if (isLockable() || force)
            {
                if (isUnlocked() || force)
                {
                    setText(text);
                    setCode(code);
                    setUnlocked(false);
                    sendUpdates(null, NBT_LOCK_UPDATE);
                    return true;
                }
            }
        }
        return false;
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
}
