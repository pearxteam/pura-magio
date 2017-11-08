package ru.pearx.purmag.client.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.libmc.client.gui.controls.GuiOnScreenContainer;
import ru.pearx.libmc.client.gui.inventory.PXLGuiContainerControls;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.inventory.ContainerMagibench;

/*
 * Created by mrAppleXZ on 31.10.17 17:48.
 */
@SideOnly(Side.CLIENT)
public class GuiContainerMagibench extends PXLGuiContainerControls
{
    public static final ResourceLocation INFO = Utils.gRL("textures/gui/icons/magibench_info.png");
    public static final int WIDTH = 196;
    public static final int HEIGHT = 190;

    public class Overlay extends GuiOnScreenContainer
    {
        public InfoControl cont = new InfoControl();


        public Overlay()
        {
            super(GuiContainerMagibench.this);
        }

        public class InfoControl extends Control
        {
            boolean canWork;

            public InfoControl()
            {
                setSize(14, 14);
                setPos(GuiContainerMagibench.this.xSize - getWidth() - 8, 8);
                refresh();
            }

            @Override
            public void render()
            {
                DrawingTools.drawTexture(INFO, 0, 0, getWidth(), getHeight());
            }

            @Override
            public void render2()
            {
                if(isFocused())
                {
                    StringBuilder sb = new StringBuilder(canWork ? I18n.format("misc.gui.magibench.fine") : GuiContainerMagibench.this.container.tile.getCantWorkString())
                            .append(System.lineSeparator())
                            .append(I18n.format("misc.gui.magibench.refresh"));
                    drawHoveringText(sb.toString(), getLastMouseX(), getLastMouseY());
                }
            }

            @Override
            public void mouseDown(int button, int x, int y)
            {
                refresh();
            }

            public void refresh()
            {
                canWork = GuiContainerMagibench.this.container.tile.canWork();
            }
        }

        @Override
        public void init()
        {
            controls.add(cont);
        }
    }

    public ContainerMagibench container;

    public GuiContainerMagibench(ContainerMagibench container)
    {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
        this.container = container;
        setControl(new Overlay());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1F, 1F, 1F, 1F);
        DrawingTools.drawTexture(container.tier.getGuiTexture(), (width - xSize) / 2, (height - ySize) / 2, xSize, ySize);
    }
}
