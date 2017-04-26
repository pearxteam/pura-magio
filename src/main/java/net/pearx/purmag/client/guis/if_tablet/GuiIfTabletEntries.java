package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.ClientProxy;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.common.infofield.IfEntry;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.awt.*;

/**
 * Created by mrAppleXZ on 23.04.17 19:12.
 */
public class GuiIfTabletEntries extends ControlIfTabletPart
{
    public int entrSize = 32;
    public AnimatedDrawable runes;
    public int minOffsetX, minOffsetY, maxOffsetX, maxOffsetY;
    public int offsetX, offsetY;
    public int prevMouseX, prevMouseY;

    public GuiIfTabletEntries()
    {

    }

    @Override
    public void render()
    {
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GlStateManager.clear(GL11.GL_STENCIL_BUFFER_BIT);

        GL11.glStencilMask(0xFF);
        GL11.glStencilFunc(GL11.GL_ALWAYS, 1, 0xFF);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
        GL11.glColorMask(false, false, false, false);
        DrawingTools.drawRectangle(0, 0, getWidth(), getHeight());
        GL11.glColorMask(true, true, true, true);
        GL11.glStencilMask(0x00);
        GL11.glStencilFunc(GL11.GL_NOTEQUAL, 0, 0xFF);
    }

    @Override
    public void postRender()
    {
        GL11.glDisable(GL11.GL_STENCIL_TEST);
    }

    @Override
    public void init()
    {
        setWidth(getTablet().w - 10);
        setHeight(getTablet().h - 10);
        setX(5);
        setY(5);
        runes = new AnimatedDrawable(new ResourceLocation(PurMag.ModId, "textures/runes.png"), 32, 38, 32, 38, 32, 380, 100);
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        int px = x - prevMouseX;
        int py = y - prevMouseY;
        if(Mouse.isButtonDown(0))
        {
            setOffset(px, py);
        }
        prevMouseX = x;
        prevMouseY = y;
    }

    public void reload()
    {
        controls.clear();

        int minX = 0, minY = 0, maxX = 0, maxY = 0;

        for(IfEntry entr : PurMag.instance.if_registry.entries)
        {
            if(getTablet().selector.getSelectedChannel().containsEntry(entr.getId()))
            {
                if(entr.isAvailable(Minecraft.getMinecraft().player, getTablet().tier))
                {
                    if(entr.getX() < minX)
                        minX = entr.getX();
                    if(entr.getY() < minY)
                        minY = entr.getY();
                    if(entr.getX() > maxX)
                        maxX = entr.getX();
                    if(entr.getY() > maxY)
                        maxY = entr.getY();
                    GuiIfTabletEntry entrC = new GuiIfTabletEntry(entr);
                    entrC.setX(entr.getX() * entrSize + ((getTablet().w - entrSize) / 2));
                    entrC.setY(entr.getY() * entrSize + ((getTablet().h - entrSize) / 2));
                    controls.add(entrC);
                }
            }
        }

        offsetX = 0;
        offsetY = 0;
        minOffsetX = (minX * entrSize) - (getTablet().w / 2) - (entrSize / 2);
        minOffsetY = (minY * entrSize) - (getTablet().h / 2) - (entrSize / 2);
        maxOffsetX = (maxX * entrSize) + getTablet().w / 2;
        maxOffsetY = (maxY * entrSize) + (getTablet().h / 2);
    }

    public void setOffset(int pX, int pY)
    {
        int newX = offsetX + pX;
        int newY = offsetY + pY;
        if(newX >= minOffsetX && newY >= minOffsetY && newX <= maxOffsetX && newY <= maxOffsetY)
        {
            offsetX = newX;
            offsetY = newY;
            for(Control cont : controls)
            {
                cont.setX(cont.getX() + pX);
                cont.setY(cont.getY() + pY);
            }
        }
    }
}
