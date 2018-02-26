package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
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
            sendUpdates(new NBTTagCompoundBuilder().setTag("items", serializeNBT()).build(), null);
        }
    };

    @SideOnly(Side.CLIENT)
    private int previousSpins;

    @SideOnly(Side.CLIENT)
    public int getPreviousSpins()
    {
        return previousSpins;
    }

    @SideOnly(Side.CLIENT)
    public void setPreviousSpins(int previousSpins)
    {
        this.previousSpins = previousSpins;
    }

    private int crushes;
    private int spins;
    private long previousAction;

    public int getCrushes()
    {
        return crushes;
    }

    public void setCrushes(int crushes, boolean sync, EntityPlayer p)
    {
        this.crushes = crushes;
        if(sync)
            sendUpdates(new NBTTagCompoundBuilder().setInteger("crushes", crushes).build(), p);
    }

    public int getSpins()
    {
        return spins;
    }

    public int getMaxSpins()
    {
        return 10;
    }

    public int getCooldownBetweenSpins()
    {
        return 6;
    }

    public void setSpins(int spins, boolean sync, EntityPlayer p)
    {
        if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT &&  getWorld().isRemote)
            setPreviousSpins(getSpins());
        this.spins = spins;
        if(sync)
            sendUpdates(new NBTTagCompoundBuilder().setInteger("spins", spins).build(), p);
    }

    public long getPreviousAction()
    {
        return previousAction;
    }

    public void setPreviousSpin(long previousSpin, boolean sync, EntityPlayer p)
    {
        this.previousAction = previousSpin;
        if(sync)
            sendUpdates(new NBTTagCompoundBuilder().setLong("previousAction", previousSpin).build(), p);
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        super.readCustomData(tag);
        if (tag.hasKey("items", Constants.NBT.TAG_COMPOUND))
            handler.deserializeNBT((NBTTagCompound) tag.getTag("items"));
        if(tag.hasKey("crushes", Constants.NBT.TAG_INT))
            setCrushes(tag.getInteger("crushes"), false, null);
        if(tag.hasKey("spins", Constants.NBT.TAG_INT))
            setSpins(tag.getInteger("spins"), false, null);
        if(tag.hasKey("previousAction", Constants.NBT.TAG_LONG))
            setPreviousSpin(tag.getLong("previousAction"), false, null);
    }

    @Override
    public void writeCustomData(NBTTagCompound tag)
    {
        super.writeCustomData(tag);
        tag.setTag("items", handler.serializeNBT());
        tag.setInteger("crushes", getCrushes());
        tag.setInteger("spins", getSpins());
        tag.setLong("previousAction", getPreviousAction());
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
        if (MultiblockRegistry.STONE_CRUSHER.handle.equals(original))
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
