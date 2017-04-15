package net.pearx.purmag.client.guis;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

/**
 * Created by mrAppleXZ on 15.04.17 9:07.
 */
public class GuiPart implements IGuiDrawable
{
    public ResourceLocation tex;
    public short u;
    public short v;
    public short width;
    public short height;
    public short texWidth;
    public short texHeight;

    public GuiPart(ResourceLocation tex, int u, int v, int width, int height, int texWidth, int texHeight)
    {
        this.tex = tex;
        this.u = (short)u;
        this.v = (short)v;
        this.width = (short)width;
        this.height = (short)height;
        this.texWidth = (short)texWidth;
        this.texHeight = (short)texHeight;
    }

    public void draw(GuiScreen gs, int x, int y)
    {
        gs.mc.getTextureManager().bindTexture(tex);
        GuiScreen.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, texWidth, texHeight);
    }
}
