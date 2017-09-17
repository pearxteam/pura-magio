package ru.pearx.purmag.client.gui.inventory;

import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.controls.GuiControlContainer;
import ru.pearx.libmc.client.gui.inventory.PXLGuiContainer;
import ru.pearx.libmc.client.gui.inventory.PXLGuiContainerControls;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 17.09.17 15:51.
 */
@SideOnly(Side.CLIENT)
public class GuiContainerCodeStorage extends PXLGuiContainerControls
{
    public GuiContainerCodeStorage(Container container)
    {
        super(container, new Control());
        xSize = 196;
        ySize = 170;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        DrawingTools.drawTexture(Utils.getResourceLocation("textures/gui/inventory/code_storage.png"), (width - xSize) / 2, (height - ySize) / 2, xSize, ySize);
    }
}
