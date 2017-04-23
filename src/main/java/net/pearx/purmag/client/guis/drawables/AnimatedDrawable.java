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
    private int elemW, elemH, texElemW, texElemH, texW, texH, msDivider;
    private int cycle = 0;
    private int totalCycles;
    private ResourceLocation tex;

    public AnimatedDrawable(ResourceLocation tex, int elemW, int elemH, int texElemW, int texElemH, int texW, int texH, int msDivider)
    {
        this.elemW = elemW;
        this.elemH = elemH;
        this.texW = texW;
        this.texH = texH;
        this.tex = tex;
        this.texElemW = texElemW;
        this.texElemH = texElemH;
        this.msDivider = msDivider;
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
        cycle = (int)(System.currentTimeMillis() / msDivider % totalCycles);

        Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
        GuiScreen.drawScaledCustomSizeModalRect(x, y, 0, cycle * texElemH, texElemW, texElemH, elemW, elemH, texW, texH);
    }
}
