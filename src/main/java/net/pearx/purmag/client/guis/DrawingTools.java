package net.pearx.purmag.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.List;

/**
 * Created by mrAppleXZ on 16.04.17 20:45.
 */
@SideOnly(Side.CLIENT)
public class DrawingTools
{
    public static void drawTexture(ResourceLocation tex, int x, int y, int width, int height, int u, int v, int texw, int texh)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
        GuiScreen.drawModalRectWithCustomSizedTexture(x, y, u, v, width, height, texw, texh);
    }

    public static void drawString(String str, int x, int y, Color col, boolean dropShadow)
    {
        Minecraft.getMinecraft().fontRenderer.drawString(str, x, y, col.getRGB(), dropShadow);
    }

    public static void drawString(String str, int x, int y, Color col)
    {
        Minecraft.getMinecraft().fontRenderer.drawString(str, x, y, col.getRGB(), true);
    }

    public static int measureString(String str)
    {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(str);
    }

    public static int getFontHeight()
    {
        return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    }

    public static void drawHoveringText(String str, int x, int y)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(10, 0, 500);
        drawString(str, x, y, Color.WHITE, true);
        GlStateManager.popMatrix();
    }

    public static void drawGradientRect(int x, int y, int width, int height, Color start, Color end)
    {
        int right = x + width;
        int bottom = y + height;
        int startColor = start.getRGB();
        int endColor = end.getRGB();
        float f = (float) (startColor >> 24 & 255) / 255.0F;
        float f1 = (float) (startColor >> 16 & 255) / 255.0F;
        float f2 = (float) (startColor >> 8 & 255) / 255.0F;
        float f3 = (float) (startColor & 255) / 255.0F;
        float f4 = (float) (endColor >> 24 & 255) / 255.0F;
        float f5 = (float) (endColor >> 16 & 255) / 255.0F;
        float f6 = (float) (endColor >> 8 & 255) / 255.0F;
        float f7 = (float) (endColor & 255) / 255.0F;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vertexbuffer.pos((double) right, (double) y, 0).color(f1, f2, f3, f).endVertex();
        vertexbuffer.pos((double) x, (double) y, 0).color(f1, f2, f3, f).endVertex();
        vertexbuffer.pos((double) x, (double) bottom, 0).color(f5, f6, f7, f4).endVertex();
        vertexbuffer.pos((double) right, (double) bottom, 0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void drawRectangle(int x, int y, int width, int height)
    {
        int bottom = y + height;
        int right = x + width;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        GlStateManager.disableTexture2D();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION);
        vertexbuffer.pos((double)x, (double)bottom, 0.0D).endVertex();
        vertexbuffer.pos((double)right, (double)bottom, 0.0D).endVertex();
        vertexbuffer.pos((double)right, (double)y, 0.0D).endVertex();
        vertexbuffer.pos((double)x, (double)y, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }
}
