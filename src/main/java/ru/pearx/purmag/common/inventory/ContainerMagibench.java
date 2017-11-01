package ru.pearx.purmag.common.inventory;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.libmc.common.inventory.PXLContainer;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.tiles.TileMagibench;

/*
 * Created by mrAppleXZ on 30.10.17 21:44.
 */
public class ContainerMagibench extends PXLContainer
{
    public class Crafting extends InventoryCrafting
    {
        public Crafting()
        {
            super(ContainerMagibench.this, ContainerMagibench.this.tier.getWidth(), ContainerMagibench.this.tier.getHeight());
        }

        @Override
        public int getSizeInventory()
        {
            return ContainerMagibench.this.tile.handler.getSlots();
        }

        @Override
        public boolean isEmpty()
        {
            ItemStackHandler hand = ContainerMagibench.this.tile.handler;
            for(int i = 0; i < hand.getSlots(); i++)
                if(!hand.getStackInSlot(i).isEmpty())
                    return false;
            return true;
        }

        @Override
        public ItemStack getStackInSlot(int index)
        {
            return ContainerMagibench.this.tile.handler.getStackInSlot(index);
        }

        public ItemStack getStackInRowAndColumn(int row, int column)
        {
            return getStackInSlot(row + column * getWidth());
        }

        @Override
        public ItemStack removeStackFromSlot(int index)
        {
            return ItemStackUtils.extractAll(ContainerMagibench.this.tile.handler, index);
        }

        @Override
        public ItemStack decrStackSize(int index, int count)
        {
            ItemStack st = ContainerMagibench.this.tile.handler.extractItem(index, count, false);
            ContainerMagibench.this.onCraftMatrixChanged(this);
            return st;
        }

        @Override
        public void setInventorySlotContents(int index, ItemStack stack)
        {
            ContainerMagibench.this.tile.handler.setStackInSlot(index, stack);
            ContainerMagibench.this.onCraftMatrixChanged(this);
        }

        @Override
        public void clear()
        {
            ItemStackUtils.clear(ContainerMagibench.this.tile.handler);
        }

        @Override
        public void fillStackedContents(RecipeItemHelper helper)
        {
            ItemStackHandler handler = ContainerMagibench.this.tile.handler;
            for (int i = 0; i < handler.getSlots(); i++)
            {
                helper.accountStack(handler.getStackInSlot(i));
            }
        }

        public ContainerMagibench getMagibench()
        {
            return ContainerMagibench.this;
        }
    }

    public World world;

    public InventoryCrafting matrix;
    public InventoryCraftResult result;
    public TileMagibench tile;
    public MagibenchRegistry.Tier tier;
    public InventoryPlayer inv;

    public ContainerMagibench(TileMagibench tile, World world, InventoryPlayer inv)
    {
        this(tile, world, PurMag.INSTANCE.getMagibenchRegistry().getTier(tile.getTier()), inv);
    }

    private ContainerMagibench(TileMagibench tile, World world, MagibenchRegistry.Tier t, InventoryPlayer inv)
    {
        super(inv, t.getWidth() * t.getHeight() + 1, 18, 107);
        this.world = world;
        this.tile = tile;
        this.tier = t;
        this.inv = inv;
        this.matrix = new Crafting();
        this.result = new InventoryCraftResult();
        addSlots();
        addPlayerSlots();
        onCraftMatrixChanged(matrix);
    }

    public void addSlots()
    {
        addSlotToContainer(new SlotCrafting(inv.player, matrix, result, 0, tier.getGuiResultX(), tier.getGuiResultY()));
        int index = 0;
        for(int y = 0; y < tier.getWidth(); y++)
        {
            for (int x = 0; x < tier.getHeight(); x++)
            {
                addSlotToContainer(new Slot(matrix, index, tier.getGuiGridX() + 18 * x, tier.getGuiGridY() + 18 * y));
                index++;
            }
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return playerIn.getDistanceSq(tile.getPos()) < 64;
    }

    @Override
    public void onCraftMatrixChanged(IInventory inventoryIn)
    {
        slotChangedCraftingGrid(world, inv.player, matrix, result);
    }

    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn)
    {
        return slotIn.inventory != result && super.canMergeSlot(stack, slotIn);
    }
}
