package ru.pearx.purmag.common.tiles;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.nbt.serialization.NBTSerializer;
import ru.pearx.libmc.common.tiles.syncable.TileSyncableComposite;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 30.10.17 21:48.
 */
public class TileMagibench extends TileSyncableComposite
{
    public static final String NBT_ITEMS = "items";
    public static final String NBT_TIER = "tier";
    public static final String NBT_ITEMS_UPDATE = "items_update";

    private int tier;
    public ItemStackHandler handler;

    public TileMagibench()
    {
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_TIER, int.class, this::setTier, this::getTier));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_ITEMS, NBTTagCompound.class, (tag) -> handler.deserializeNBT(tag), () -> handler.serializeNBT()));
    }


    public int getTier()
    {
        return tier;
    }

    public void setTier(int tier)
    {
        MagibenchRegistry.Tier t = PurMag.INSTANCE.getMagibenchRegistry().getTier(tier);
        if(handler != null && getTier() != tier && !getWorld().isRemote)
        {
            ItemStackUtils.drop(handler, getWorld(), getPos());
        }
        handler = new ItemStackHandler(t.getWidth() * t.getHeight())
        {
            @Override
            protected void onContentsChanged(int slot)
            {
                markDirty();
                sendUpdates(null, new NBTTagCompoundBuilder().setTag(NBT_ITEMS_UPDATE, ItemStackUtils.writeSlotUpdate(handler, slot)).build());
            }
        };
        this.tier = tier;
        markDirty();
    }

    public void setTierAndSync(int tier, EntityPlayer p)
    {
        setTier(tier);
        sendUpdates(p, NBT_TIER);
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

    public boolean canWork()
    {
        return true;
    }

    @SideOnly(Side.CLIENT)
    public String getCantWorkString()
    {
        return I18n.format("misc.gui.magibench.incorrectStructure");
    }
}
