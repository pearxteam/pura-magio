package ru.pearx.purmag.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.structure.multiblock.IMultiblockPart;
import ru.pearx.libmc.common.structure.multiblock.events.MultiblockActivatedEvent;
import ru.pearx.libmc.common.structure.multiblock.events.MultiblockBreakEvent;
import ru.pearx.purmag.common.blocks.multiblock.MultiblockRegistry;

import javax.annotation.Nullable;

/*
 * Created by mrAppleXZ on 16.11.17 20:31.
 */
public class TileStoneCrusher extends TilePMMultiblockMaster
{
    public ItemStackHandler handler = new ItemStackHandler()
    {
        @Override
        protected void onContentsChanged(int slot)
        {
            TileStoneCrusher.this.markDirty();
            //sendUpdates(ItemStackUtils.writeSlotUpdate(this, slot, "slotUpdateStack", "slotUpdateStack"), null);
        }
    };

    public int getMaxSpins()
    {
        return 10;
    }

    public int getCooldownBetweenSpins()
    {
        return 6;
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        super.readCustomData(tag);
        if (tag.hasKey("items", Constants.NBT.TAG_COMPOUND))
            handler.deserializeNBT((NBTTagCompound) tag.getTag("items"));

        //update only
       // ItemStackUtils.loadSlotUpdate(tag, handler, "slotUpdateSlot", "slotUpdateStack");
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
        //elevate the anvil
        BlockPos original = getOriginalPos(part.getPos());
       /* if (MultiblockRegistry.STONE_CRUSHER.handle.equals(original))
        {
            long delta = part.getWorld().getTotalWorldTime() - getPreviousAction();
            if (getSpins() < getMaxSpins() && (delta < 0 || delta > getCooldownBetweenSpins()))
            {
                setSpins(getSpins() + 1, true, evt.getPlayer());
                setPreviousSpin(part.getWorld().getTotalWorldTime(), true, evt.getPlayer());
                return true;
            }
        }
        //drop the anvil
        else if (MultiblockRegistry.STONE_CRUSHER.lever.equals(original))
        {
            if(getSpins() > 0)
            {
                setCrushes(getCrushes() + getSpins(), true, evt.getPlayer());
                setSpins(0, true, evt.getPlayer());
                setPreviousSpin(part.getWorld().getTotalWorldTime(), true, evt.getPlayer());
                return true;
            }
        }
        //insert the item
        else if(MultiblockRegistry.STONE_CRUSHER.anvil.equals(original) && getSpins() > 0)
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
        }*/
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
