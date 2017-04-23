package net.pearx.purmag.client.guis.drawables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * Created by mrAppleXZ on 23.04.17 12:55.
 */
public class AnimatedDrawable implements IGuiDrawable
{
    private int elemW, elemH, texElemW, texElemH;
    private int texW, texH, timesToChange;
    private int timesElapsed = -1;
    private int cycle = 0;
    private int totalCycles;
    private ResourceLocation tex;

    public AnimatedDrawable(ResourceLocation tex, int elemW, int elemH, int texElemW, int texElemH, int texW, int texH, int timesToChange)
    {
        this.elemW = elemW;
        this.elemH = elemH;
        this.texW = texW;
        this.texH = texH;
        this.tex = tex;
        this.texElemW = texElemW;
        this.texElemH = texElemH;
        this.timesToChange = timesToChange;
        totalCycles = texH / texElemH - 1;
    }

    @Override
    public int getWidth()
    {
        return elemW;
    }

    @Override
    public int getHeight()
    {
        return elemH;
    }

    @Override
    public void draw(int x, int y)
    {
        timesElapsed++;
        if(timesElapsed == timesToChange)
        {
            timesElapsed = -1;
            cycle++;
        }
        if(cycle > totalCycles)
            cycle = 0;
        Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
        GuiScreen.drawScaledCustomSizeModalRect(x, y, 0, cycle * texElemH, texElemW, texElemH, elemW, elemH, texW, texH);
    }
}