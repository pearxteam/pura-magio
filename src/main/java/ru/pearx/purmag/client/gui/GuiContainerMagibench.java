package ru.pearx.purmag.client.gui;

import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.PXLGui;
import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.libmc.client.gui.inventory.PXLGuiContainer;
import ru.pearx.libmc.client.gui.inventory.PXLGuiContainerControls;
import ru.pearx.purmag.common.inventory.ContainerMagibench;

/*
 * Created by mrAppleXZ on 31.10.17 17:48.
 */
@SideOnly(Side.CLIENT)
public class GuiContainerMagibench extends PXLGuiContainerControls
{
    public static class Overlay extends GuiOnScreen
    {

    }

    public ContainerMagibench container;

    public GuiContainerMagibench(ContainerMagibench container)
    {
        super(container, new Overlay());
        this.container = container;
        xSize = 196;
        ySize = 190;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        DrawingTools.drawTexture(container.tier.getGuiTexture(), (width - xSize) / 2, (height - ySize) / 2, xSize, ySize);
    }
}
