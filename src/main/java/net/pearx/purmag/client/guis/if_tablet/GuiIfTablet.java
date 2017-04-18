package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;

/**
 * Created by mrAppleXZ on 16.04.17 20:05.
 */
public class GuiIfTablet extends Control
{
    public int w = 384;
    public int h = 256;
    public static final ResourceLocation TEX = new ResourceLocation(PurMag.ModId, "textures/gui/if_tablet.png");

    public TexturePart texBg = new TexturePart(TEX, 0, 0, w, h, 512, 512);

    public GuiIfTablet()
    {
        setWidth(w);
        setHeight(h);
    }

    @Override
    public int getX()
    {
        return (getGuiScreen().width - w) / 2;
    }

    @Override
    public int getY()
    {
        return (getGuiScreen().height - h) / 2;
    }

    @Override
    public void render()
    {
        texBg.draw(0, 0);
    }
}
