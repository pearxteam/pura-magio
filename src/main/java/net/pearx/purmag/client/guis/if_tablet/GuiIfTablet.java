package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;

/**
 * Created by mrAppleXZ on 16.04.17 20:05.
 */
public class GuiIfTablet extends Control
{
    int degrees = 0;

    public int w = 384;
    public int h = 256;
    public ResourceLocation textures;
    public int tier;

    public TexturePart texBg;
    public TexturePart texFrame;
    public TexturePart texTab;

    public GuiIfTabletSelector selector;

    public GuiIfTablet(EntityPlayer p, int tier)
    {
        this.tier = tier;

        setWidth(w);
        setHeight(h);

        textures = new ResourceLocation(PurMag.ModId, "textures/gui/if_tablet." + tier + ".png");
        texBg = new TexturePart(textures, 0, 0, w, h, 512, 512);
        texFrame = new TexturePart(textures, 0, h, w, h, 512, 512);
        texTab = new TexturePart(textures, w, 0, 32, 32, 512, 512);

        selector = new GuiIfTabletSelector(texTab, p, tier);
        selector.setX(w - selector.getWidth());
        selector.setY((h - selector.getHeight()) / 2);
        controls.add(selector);
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
        degrees++;
        if(degrees == 361)
            degrees = 0;
        float sin = MathHelper.sin((float)Math.toRadians(degrees)) * 0.15f;
        GlStateManager.color(0.85f + sin, 0.85f + sin, 0.85f + sin);
        texBg.draw(0, 0);
        GlStateManager.color(1, 1, 1);
    }

    @Override
    public void postRender()
    {
        texFrame.draw(0, 0);
    }
}
