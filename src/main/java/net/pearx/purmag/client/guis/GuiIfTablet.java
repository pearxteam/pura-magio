package net.pearx.purmag.client.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.pearx.purmag.PurmagCore;

/**
 * Created by mrAppleXZ on 13.04.17 22:10.
 */
public class GuiIfTablet extends GuiScreen
{
    private float times = -0.01f;
    private float timesPlus = 0.01f;

    public static ResourceLocation bg = new ResourceLocation(PurmagCore.ModId, "textures/gui/if_tablet.png");

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        if(times >= 0.31f)
            timesPlus = -0.01f;
        if(times <= -0.01f)
            timesPlus = 0.01f;
        times += timesPlus;
        drawDefaultBackground();

        GlStateManager.color(0.7f + times, 0.7f + times, 0.7f + times, 1f);
        mc.getTextureManager().bindTexture(bg);
        int ts = 256;
        drawModalRectWithCustomSizedTexture((width - ts) / 2, (height - ts) / 2, 0, 0, ts, ts, 512, 256);
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.color(1f, 1f, 1f, 1f);

        mc.getTextureManager().bindTexture(bg);
        drawModalRectWithCustomSizedTexture((width - ts) / 2, (height - ts) / 2, ts, 0, ts, ts, 512, 256);
    }
}
