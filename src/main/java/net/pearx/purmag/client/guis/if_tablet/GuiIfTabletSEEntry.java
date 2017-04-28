package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.IfEntry;

/**
 * Created by mrAppleXZ on 23.04.17 18:42.
 */
public class GuiIfTabletSEEntry extends Control
{
    public IfEntry entry;
    private int prevX, prevY;

    public GuiIfTabletSEEntry(IfEntry entry)
    {
        this.entry = entry;
        setWidth(32);
        setHeight(32);
    }

    @Override
    public void render()
    {
        GlStateManager.enableBlend();
        float f = 1;
        try
        {
            f = 1 - (Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAPABILITY, null).getSteps(entry.getId()) / entry.getSteps().size());
        } catch (ArithmeticException e) {}
        GlStateManager.color(f, 1, f);
        getEntries().runes.draw(0, -4);
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

    public GuiIfTabletSEEntries getEntries()
    {
        if(getParent() instanceof GuiIfTabletSEEntries)
        {
            return (GuiIfTabletSEEntries) getParent();
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
