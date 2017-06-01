package net.pearx.purmag.client.guis.drawables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by mrAppleXZ on 26.05.17 14:18.
 */
@SideOnly(Side.CLIENT)
public class SimpleDrawable implements IGuiDrawable
{
    private ResourceLocation texture;
    private int texWidth, texHeight;
    private int targetWidth, targetHeight;

    public SimpleDrawable(ResourceLocation texture, int texWidth, int texHeight, int targetWidth, int targetHeight)
    {
        this.texture = texture;
        this.texWidth = texWidth;
        this.texHeight = texHeight;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }

    @Override
    public int getWidth()
    {
        return targetWidth;
    }

    @Override
    public int getHeight()
    {
        return targetHeight;
    }

    @Override
    public void draw(int x, int y)
    {
        GlStateManager.enableBlend();
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        GuiScreen.drawScaledCustomSizeModalRect(x, y, 0, 0, texWidth, texHeight, targetWidth, targetHeight, texWidth, texHeight);
        GlStateManager.disableBlend();
    }
}
