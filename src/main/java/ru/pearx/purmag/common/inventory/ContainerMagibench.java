package ru.pearx.purmag.common.inventory;

import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import ru.pearx.libmc.common.inventory.PXLContainer;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.tiles.TileMagibench;

/*
 * Created by mrAppleXZ on 30.10.17 21:44.
 */
public class ContainerMagibench extends PXLContainer
{
    public InventoryCrafting craftingMatrix;
    public InventoryCraftResult result;
    public TileMagibench tile;

    private IInventory inv;

    public ContainerMagibench(TileMagibench tile, IInventory inv)
    {
        MagibenchRegistry.Tier t = MagibenchRegistry.INSTANCE.getTier(tile.getTier());
        super(inv, t.getWidth() * t.getHeight(), 18, 18 + );
        this.tile = tile;
    }
}
