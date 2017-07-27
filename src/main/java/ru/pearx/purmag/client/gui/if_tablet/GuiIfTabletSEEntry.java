package ru.pearx.purmag.client.gui.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.purmag.common.CapabilityRegistry;
import ru.pearx.purmag.common.infofield.IfEntry;

import java.awt.*;

/**
 * Created by mrAppleXZ on 23.04.17 18:42.
 */
@SideOnly(Side.CLIENT)
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
        if((getX() + getWidth()) <= 0 || (getY() + getHeight()) <= 0 || getX() >= getEntries().getWidth() || getY() >= getEntries().getHeight())
            return;
        float f = 1;
        if(getEntries().getTabletScreen().isGlowing())
        {
            if(entry.getSteps().size() == 0)
                f = 0;
            else
                f = 1 - (Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).getSteps(entry.getId()) / (float)entry.getSteps().size());
        }

        GlStateManager.color(f, 1, f);
        GlStateManager.enableBlend();
        getEntries().getTabletScreen().getTablet().entryDrawable.draw(0, 0);
        GlStateManager.disableBlend();
        GlStateManager.color(1, 1, 1);

        entry.getIcon().draw((getWidth() - entry.getIcon().getWidth()) / 2, (getHeight() - entry.getIcon().getHeight()) / 2);
    }

    @Override
    public void postRender()
    {
        if(isFocused())
        {
            DrawingTools.drawHoveringText(entry.getDisplayName(), prevX, prevY, Color.WHITE, true, 1, Minecraft.getMinecraft().fontRenderer);
            DrawingTools.drawHoveringText(entry.getDisplayDescription(), prevX, prevY + 9, Color.WHITE, true, 0.9f, Minecraft.getMinecraft().fontRenderer);
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

    @Override
    public void mouseUp(int button, int x, int y)
    {
        if(button == 0)
            getEntries().getTabletScreen().getTablet().changeScreen(new GuiIfTabletSR(entry));
    }
}
