package ru.pearx.purmag.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import ru.pearx.libmc.common.nbt.NBTTagCompoundBuilder;
import ru.pearx.libmc.common.structure.multiblock.IMultiblockPart;
import ru.pearx.libmc.common.structure.multiblock.Multiblock;
import ru.pearx.libmc.common.structure.multiblock.events.MultiblockActivatedEvent;
import ru.pearx.libmc.common.tiles.TileMultiblockMaster;
import ru.pearx.purmag.common.blocks.multiblock.MultiblockRegistry;
import scala.reflect.api.Trees;

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

    private int crushes;
    private int spins;
    private long previousSpin;

    public int getCrushes()
    {
        return crushes;
    }

    public void setCrushes(int crushes, boolean sync)
    {
        this.crushes = crushes;
        if(sync)
            sendUpdates(new NBTTagCompoundBuilder().setInteger("crushes", crushes).build(), null);
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
        this.spins = spins;
        if(sync)
            sendUpdates(new NBTTagCompoundBuilder().setInteger("spins", spins).build(), p);
    }

    public long getPreviousSpin()
    {
        return previousSpin;
    }

    public void setPreviousSpin(long previousSpin, boolean sync, EntityPlayer p)
    {
        this.previousSpin = previousSpin;
        if(sync)
            sendUpdates(new NBTTagCompoundBuilder().setLong("previousSpin", previousSpin).build(), p);
    }

    @Override
    public void readCustomData(NBTTagCompound tag)
    {
        super.readCustomData(tag);
        if (tag.hasKey("items", Constants.NBT.TAG_COMPOUND))
            handler.deserializeNBT((NBTTagCompound) tag.getTag("items"));
        if(tag.hasKey("crushes", Constants.NBT.TAG_INT))
            setCrushes(tag.getInteger("crushes"), false);
        if(tag.hasKey("spins", Constants.NBT.TAG_INT))
            setSpins(tag.getInteger("spins"), false, null);
        if(tag.hasKey("previousSpin", Constants.NBT.TAG_LONG))
            setPreviousSpin(tag.getLong("previousSpin"), false, null);
    }

    @Override
    public void writeCustomData(NBTTagCompound tag)
    {
        super.writeCustomData(tag);
        tag.setTag("items", handler.serializeNBT());
        tag.setInteger("crushes", getCrushes());
        tag.setInteger("spins", getSpins());
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
            long delta = part.getWorld().getTotalWorldTime() - getPreviousSpin();
            if (getSpins() < getMaxSpins() && (delta < 0 || delta > getCooldownBetweenSpins()))
            {
                setSpins(getSpins() + 1, true, evt.getPlayer());
                setPreviousSpin(part.getWorld().getTotalWorldTime(), true, evt.getPlayer());
                return true;
            }
        }
        //drop the anvil
        if (MultiblockRegistry.STONE_CRUSHER.lever.equals(original))
        {

        }
        return false;
    }
}
