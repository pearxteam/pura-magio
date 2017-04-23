package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.renderer.GlStateManager;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.common.infofield.IfEntry;
import org.lwjgl.opengl.GL11;

/**
 * Created by mrAppleXZ on 23.04.17 18:42.
 */
public class GuiIfTabletEntry extends Control
{
    public IfEntry entry;

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
        getEntries().runes.draw(0, -4);
        if(!oldBlend)
            GlStateManager.disableBlend();
    }

    public GuiIfTabletEntries getEntries()
    {
        if(getParent() instanceof GuiIfTabletEntries)
        {
            return (GuiIfTabletEntries) getParent();
        }
        return null;
    }
}
