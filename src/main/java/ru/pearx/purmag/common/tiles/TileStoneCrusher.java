package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.carbide.mc.common.misc.ItemStackUtils;
import ru.pearx.carbide.mc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.carbide.mc.common.nbt.serialization.NBTSerializer;
import ru.pearx.carbide.mc.common.structure.multiblock.IMultiblockPart;
import ru.pearx.carbide.mc.common.structure.multiblock.events.MultiblockActivatedEvent;
import ru.pearx.carbide.mc.common.structure.multiblock.events.MultiblockBreakEvent;
import ru.pearx.purmag.common.blocks.multiblock.MultiblockRegistry;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 16.11.17 20:31.
 */
public class TileStoneCrusher extends TilePMMultiblockMaster
{
    public static final String NBT_ITEMS = "items";
    public static final String NBT_SLOT_UPDATE = "items_update";
    public static final String NBT_SPINS = "spins";

    public ItemStackHandler handler = new ItemStackHandler()
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileStoneCrusher.this.markDirty();
            sendUpdates(null, new NBTTagCompoundBuilder().setTag(NBT_SLOT_UPDATE, ItemStackUtils.writeSlotUpdate(this, slot)).build());
        }
    };
    private int spins;

    public TileStoneCrusher()
    {
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_ITEMS, NBTTagCompound.class, (tag) -> handler.deserializeNBT(tag), () -> handler.serializeNBT()));
        getSerializers().add(new NBTSerializer.ReaderWriter<>(NBT_SPINS, int.class, this::setSpins, this::getSpins));
        getSerializers().add(new NBTSerializer.Reader<>(NBT_SLOT_UPDATE, NBTTagCompound.class, (tag) -> ItemStackUtils.loadSlotUpdate(tag, handler)));
    }

    public int getSpins()
    {
        return spins;
    }

    public void setSpins(int spins)
    {
        this.spins = spins;
        markDirty();
    }

    public void setSpinsAndSync(int spins, EntityPlayer p)
    {
        setSpins(spins);
        sendUpdates(p, NBT_SPINS);
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

    @Override
    public boolean handleActivated(MultiblockActivatedEvent evt, IMultiblockPart part)
    {
        BlockPos original = getOriginalPos(part.getPos());
        //insert the item
        if(MultiblockRegistry.STONE_CRUSHER.anvil.equals(original) && getSpins() > 0)
        {
            if(evt.getPlayer().isSneaking())
            {
                if(!handler.getStackInSlot(0).isEmpty())
                {
                    evt.getPlayer().addItemStackToInventory(handler.extractItem(0, 64, false));
                    return true;
                }
            }
            else
            {
                ItemStack remainder = handler.insertItem(0, evt.getPlayer().getHeldItem(evt.getHand()), false);
                boolean changed = !ItemStack.areItemStacksEqualUsingNBTShareTag(remainder, evt.getPlayer().getHeldItem(evt.getHand()));
                if(changed)
                {
                    evt.getPlayer().setHeldItem(evt.getHand(), remainder);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void handleBreak(MultiblockBreakEvent evt, IMultiblockPart part)
    {
        if(getOriginalPos(part.getPos()).equals(MultiblockRegistry.STONE_CRUSHER.anvil))
        {
            ItemStackUtils.drop(handler, part.getWorld(), part.getPos());
        }
    }
}
