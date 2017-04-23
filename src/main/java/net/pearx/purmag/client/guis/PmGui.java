package net.pearx.purmag.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.controls.GuiControlContainer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.io.IOException;

/**
 * Created by mrAppleXZ on 16.04.17 20:02.
 */
public class PmGui extends GuiScreen
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
        drawDefaultBackground();

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
        if(gui != null)
        {
            int x = Mouse.getEventX() * width / mc.displayWidth;
            int y = height - Mouse.getEventY() * height / mc.displayHeight - 1;
            if(Mouse.getEventButton() != -1)
            {
                if(Mouse.getEventButtonState())
                    gui.invokeMouseDown(Mouse.getEventButton(), x - gui.getX(), y - gui.getY());
                else
                    gui.invokeMouseUp(Mouse.getEventButton(), x - gui.getX(), y - gui.getY());
            }
            if(Mouse.getEventDX() != 0 || Mouse.getEventDY() != 0)
            {
                gui.invokeMouseMove(x - gui.getX(), y - gui.getY(), Mouse.getEventDX(), Mouse.getEventDY());
            }
            if(Mouse.getEventDWheel() != 0)
            {
                gui.invokeMouseWheel(Mouse.getEventDWheel());
            }
        }
    }

    @Override
    public void initGui()
    {
        System.out.println("init");
    }

    public void drawRectangle(int x, int y, int width, int height, int color)
    {
        drawRect(x, y, x + width, y + height, color);
    }

    public void drawString(String text, int x, int y, Color col)
    {
        drawString(Minecraft.getMinecraft().fontRendererObj, text, x, y, col.getRGB());
    }
}
