package ru.pearx.purmag.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.items.SlotItemHandler;
import ru.pearx.libmc.common.inventory.PXLContainer;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

/*
 * Created by mrAppleXZ on 17.09.17 15:36.
 */
public class ContainerCodeStorage extends PXLContainer
{
    public static final int SLOT_COUNT = 40;

    public TileCodeStorage storage;
    private IInventory inv;

    public ContainerCodeStorage(TileCodeStorage storage, IInventory inv)
    {
        super(inv, SLOT_COUNT, 18, 97);
        this.storage = storage;
        this.inv = inv;

        addSlots();
        addPlayerSlots();
    }

    public void addSlots()
    {
        int index = 0;
        int offx = 9;
        int offy = 19;
        for(int cy = 0; cy < 4; cy++)
        {
            for (int cx = 0; cx < 10; cx++)
            {
                addSlotToContainer(new SlotItemHandler(storage.handler, index, offx + cx * 18, offy + cy * 18));
                index++;
            }
        }
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        storage.setOpenedAndUpdate(playerIn, false);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return playerIn.getDistanceSq(storage.getPos()) < 64;
    }
}
