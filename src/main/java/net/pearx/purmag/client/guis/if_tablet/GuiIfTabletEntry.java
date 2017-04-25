package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.IfEntry;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by mrAppleXZ on 23.04.17 18:42.
 */
public class GuiIfTabletEntry extends Control
{
    public IfEntry entry;
    private int prevX, prevY;

    public GuiIfTabletEntry(IfEntry entry)
    {
        this.entry = entry;
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void render()
    {
        boolean oldBlend = GL11.glIsEnabled(GL11.GL_BLEND);
        GlStateManager.enableBlend();

        float f = 1;
        try
        {
            f = 1 - (Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).getSteps(entry.getId()) / entry.getSteps().size());
        }
        catch(ArithmeticException e){}
        GlStateManager.color(f, 1, f);

        getEntries().runes.draw(0, -4);
        if(!oldBlend)
            GlStateManager.disableBlend();
        entry.getIcon().draw((getWidth() - entry.getIcon().getWidth()) / 2, (getHeight() - entry.getIcon().getHeight()) / 2);
    }

    @Override
    public void postRender()
    {
        if(isFocused())
        {
            DrawingTools.drawHoveringText(entry.getDisplayName(), prevX, prevY);
        }
    }

    public GuiIfTabletEntries getEntries()
    {
        if(getParent() instanceof GuiIfTabletEntries)
        {
            return (GuiIfTabletEntries) getParent();
        }
        return null;
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        prevX = x;
        prevY = y;
    }
}
