package net.pearx.purmag.client.guis;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 16.04.17 20:49.
 */
@SideOnly(Side.CLIENT)
public class TexturePart
{
    public ResourceLocation loc;
    public int u;
    public int v;
    public int width;
    public int height;
    public int textureWidth;
    public int textureHeight;

    public TexturePart(ResourceLocation loc, int u, int v, int width, int height, int texW, int texH)
    {
        this.loc = loc;
        this.u = u;
        this.v = v;
        this.textureWidth = texW;
        this.textureHeight = texH;
        this.width = width;
        this.height = height;
    }

    public void draw(int x, int y)
    {
        DrawingTools.drawTexture(loc, x, y, width, height, u, v, textureWidth, textureHeight);
    }
}
