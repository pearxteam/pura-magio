package ru.pearx.purmag.client.gui.code_storage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.PXLGui;
import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.libmc.client.gui.inventory.PXLGuiContainerControls;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.inventory.ContainerCodeStorage;

/*
 * Created by mrAppleXZ on 17.09.17 15:51.
 */
@SideOnly(Side.CLIENT)
public class GuiContainerCodeStorage extends PXLGuiContainerControls
{
    public static class Overlay extends GuiOnScreen
    {
        public Button lock;

        public Overlay(ContainerCodeStorage container)
        {
            setWidth(196);
            setHeight(180);
            if(container.storage.isLockable())
            {
                lock = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.code_storage.lock"), () ->
                {
                    Minecraft.getMinecraft().player.closeScreen();
                    Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiCodeStorageLock(container.storage.getPos())));
                });
                lock.setSize(48, 12);
                lock.setPos(4, 4);
            }
        }

        @Override
        public void init()
        {
            if(lock != null)
            {
                controls.add(lock);
            }
        }
    }

    public static final ResourceLocation TEXTURE = Utils.gRL("textures/gui/inventory/code_storage.png");

    public GuiContainerCodeStorage(ContainerCodeStorage container)
    {
        super(container, new Overlay(container));
        xSize = 196;
        ySize = 180;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1F, 1F, 1F, 1F);
        DrawingTools.drawTexture(TEXTURE, (width - xSize) / 2, (height - ySize) / 2, xSize, ySize);
    }
}
