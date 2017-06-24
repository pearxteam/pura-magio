package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.GuiDrawableRegistry;
import net.pearx.purmag.client.guis.GuiOnScreen;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;

/**
 * Created by mrAppleXZ on 16.04.17 20:05.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTablet extends GuiOnScreen
{
    public ResourceLocation textures;
    public IGuiDrawable entryDrawable;
    public boolean shouldGlow;


    public TexturePart texBg;
    public TexturePart texFrame;

    public int tier;

    public GuiIfTabletSE se = new GuiIfTabletSE();
    public GuiIfTabletS if_screen;

    public GuiIfTablet(int tier)
    {
        this.tier = tier;
        setWidth(384);
        setHeight(256);
    }

    @Override
    public void init()
    {
        textures = new ResourceLocation(PurMag.MODID, "textures/gui/if_tablet." + tier + ".png");
        texBg = new TexturePart(textures, 0, 0, getWidth(), getHeight(), 512, 512);
        texFrame = new TexturePart(textures, 0, getHeight(), getWidth(), getHeight(), 512, 512);
        entryDrawable = GuiDrawableRegistry.ifTabletEntryBgs.get(tier);
        shouldGlow = tier != 0;

        changeScreen(se);
    }

    @Override
    public void render()
    {
        float sin = if_screen.isGlowing() ? MathHelper.sin((float) Math.toRadians(System.currentTimeMillis() / 10 % 360)) * 0.15f : 0.15f;
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
        controls.remove(if_screen);
        if_screen = screen;
        controls.add(screen);
    }
}
