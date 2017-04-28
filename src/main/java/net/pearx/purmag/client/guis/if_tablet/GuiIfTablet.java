package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.AnimatedDrawable;
import net.pearx.purmag.common.infofield.IfEntry;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 16.04.17 20:05.
 */
public class GuiIfTablet extends Control
{
    public int w = 384;
    public int h = 256;
    public ResourceLocation textures;
    public int tier;

    public TexturePart texBg;
    public TexturePart texFrame;

    public GuiIfTabletSE se = new GuiIfTabletSE();
    public GuiIfTabletS if_screen;

    public GuiIfTablet(int tier)
    {
        this.tier = tier;
    }

    @Override
    public void init()
    {
        setWidth(w);
        setHeight(h);

        textures = new ResourceLocation(PurMag.ModId, "textures/gui/if_tablet." + tier + ".png");
        texBg = new TexturePart(textures, 0, 0, w, h, 512, 512);
        texFrame = new TexturePart(textures, 0, h, w, h, 512, 512);

        changeScreen(se);
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
        float sin = MathHelper.sin((float)Math.toRadians(System.currentTimeMillis() / 10 % 360)) * 0.15f;
        GlStateManager.color(0.85f + sin, 0.85f + sin, 0.85f + sin);
        texBg.draw(0, 0);
        GlStateManager.color(1, 1, 1);
    }

    @Override
    public void postRender()
    {
        GlStateManager.enableBlend();
        texFrame.draw(0, 0);
        GlStateManager.disableBlend();
    }

    public void changeScreen(GuiIfTabletS screen)
    {
        if_screen = screen;
        controls.remove(if_screen);
        controls.add(screen);
    }
}
