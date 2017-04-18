package net.pearx.purmag.client.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.items.ItemRegistry;

/**
 * Created by mrAppleXZ on 13.04.17 22:10.
 */
public class _GuiIfTablet extends GuiScreen
{
    /*public static final ResourceLocation BG_TEX = new ResourceLocation(PurMag.ModId, "textures/gui/if_tablet.png");
    public static final int ID = 0;

    public GuiPart pBg = new GuiPart(BG_TEX, 0, 0, 384, 256, 512, 512);
    public GuiPart pFrame = new GuiPart(BG_TEX, 0, 256, 384, 256, 512, 512);
    public GuiPart pTab = new GuiPart(BG_TEX, 384, 0, 32, 32, 512, 512);

    private float times = -0.01f;
    private float timesPlus = 0.01f;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if(times >= 0.31f)
            timesPlus = -0.01f;
        if(times <= -0.01f)
            timesPlus = 0.01f;
        times += timesPlus;
        drawDefaultBackground();

        int posx = (width - 384) / 2;
        int posy = (height - 256) / 2;

        GlStateManager.color(0.7f + times, 0.7f + times, 0.7f + times, 1f);
        pBg.draw(this, posx, posy);
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.color(1f, 1f, 1f, 1f);

        pFrame.draw(this, posx, posy);
        pTab.draw(this, posx + 352, posy);
        new ItemDrawable(ItemRegistry.getDefaultCrystal()).draw(this, posx + 362, posy + 10);
    }*/
}