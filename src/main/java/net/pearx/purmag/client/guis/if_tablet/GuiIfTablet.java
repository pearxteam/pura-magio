package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;

/**
 * Created by mrAppleXZ on 16.04.17 20:05.
 */
public class GuiIfTablet extends Control
{
    private byte times;
    private byte timesp = 1;

    public int w = 384;
    public int h = 256;
    public ResourceLocation textures;
    public int tier;

    public TexturePart texBg;
    public TexturePart texFrame;

    public GuiIfTablet(int tier)
    {
        this.tier = tier;

        textures = new ResourceLocation(PurMag.ModId, "textures/gui/if_tablet." + tier + ".png");
        texBg = new TexturePart(textures, 0, 0, w, h, 512, 512);
        texFrame = new TexturePart(textures, 0, h, w, h, 512, 512);

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
        times += timesp;
        if(times == 30)
        {
            timesp = -1;
        }
        if(times == 0)
        {
            timesp = 1;
        }
        GlStateManager.color(0.7f + (times / 100f), 0.7f + (times / 100f), 0.7f + (times / 100f));
        texBg.draw(0, 0);
        GlStateManager.color(1, 1, 1);
    }

    @Override
    public void postRender()
    {
        texFrame.draw(0, 0);
    }
}
