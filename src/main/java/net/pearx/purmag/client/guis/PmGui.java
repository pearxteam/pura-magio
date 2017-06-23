package net.pearx.purmag.client.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.controls.GuiControlContainer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.io.IOException;

/**
 * Created by mrAppleXZ on 16.04.17 20:02.
 */
@SideOnly(Side.CLIENT)
public class PmGui extends GuiScreen implements IGuiScreen
{
    public GuiControlContainer gui;

    public PmGui(GuiControlContainer cg)
    {
        gui = cg;
        gui.setGs(this);
    }

    public PmGui(Control cont)
    {
        this(new GuiControlContainer(cont));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {

        if(gui != null)
            gui.invokeRender();
    }

    @Override
    public void handleKeyboardInput() throws IOException
    {
        super.handleKeyboardInput();

        if(gui != null)
        {
            if(Keyboard.getEventKeyState())
                gui.invokeKeyDown(Keyboard.getEventKey());
            else
                gui.invokeKeyUp(Keyboard.getEventKey());
        }
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException
    {
        super.keyTyped(typedChar, keyCode);

        if(gui != null)
            gui.invokeKeyPress(typedChar, keyCode);
    }

    @Override
    public void handleMouseInput() throws IOException
    {
        if (gui != null)
        {
            int x = getMouseX();
            int y = getMouseY();
            if (Mouse.getEventButton() != -1)
            {
                if (Mouse.getEventButtonState())
                    gui.invokeMouseDown(Mouse.getEventButton(), x - gui.getX(), y - gui.getY());
                else
                    gui.invokeMouseUp(Mouse.getEventButton(), x - gui.getX(), y - gui.getY());
            }
            if (Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0)
            {
                gui.invokeMouseMove(x - gui.getX(), y - gui.getY(), Mouse.getEventDX(), -Mouse.getEventDY());
            }
            if (Mouse.getEventDWheel() != 0)
            {
                gui.invokeMouseWheel(Mouse.getEventDWheel());
            }
        }
    }

    @Override
    public void drawTooltip(ItemStack stack, int x, int y)
    {
        GlStateManager.pushMatrix();
        renderToolTip(stack, x, y);
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableLighting();
        GlStateManager.disableDepth();
        GlStateManager.color(1, 1, 1, 1);
        GlStateManager.popMatrix();
    }

    @Override
    public int getWidth()
    {
        return width;
    }

    @Override
    public int getHeight()
    {
        return height;
    }

    @Override
    public int getMouseX()
    {
        return Mouse.getEventX() * width / mc.displayWidth;
    }

    @Override
    public int getMouseY()
    {
        return height - Mouse.getEventY() * height / mc.displayHeight - 1;
    }

    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
}
