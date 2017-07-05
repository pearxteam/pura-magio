package net.pearx.purmag.client.guis;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;

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

    public static void drawTexture(ResourceLocation tex, int x, int y, int width, int height)
    {
        Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
        GuiScreen.drawModalRectWithCustomSizedTexture(x, y, x, y, width, height, width, height);
    }

    public static void drawString(String str, int x, int y, Color col, boolean shadow, float scale, FontRenderer rend)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0);
        GlStateManager.scale(scale, scale, 0);
        rend.drawString(str, 0, 0, col.getRGB(), shadow);
        GlStateManager.popMatrix();
    }

    public static void drawString(String str, int x, int y, Color col, boolean shadow, float scale)
    {
        drawString(str, x, y, col, shadow, scale, Minecraft.getMinecraft().fontRenderer);
    }

    public static void drawString(String str, int x, int y, Color col, boolean shadow)
    {
        drawString(str, x, y, col, shadow, 1, Minecraft.getMinecraft().fontRenderer);
    }

    public static void drawString(String str, int x, int y, Color col)
    {
        drawString(str, x, y, col, true);
    }

    public static void drawString(String str, int x, int y, Color col, int width, boolean shadow, FontRenderer rend)
    {
        GlStateManager.pushMatrix();
        rend.resetStyles();
        rend.textColor = col.getRGB();
        str = rend.trimStringNewline(str);
        if(shadow)
            rend.renderSplitString(str, x + 1, y + 1, width, true);
        rend.renderSplitString(str, x, y, width, false);
        GlStateManager.color(1, 1, 1);
        GlStateManager.popMatrix();
    }

    public static void drawString(String str, int x, int y, Color col, int width, boolean shadow)
    {
        drawString(str, x, y, col, width, shadow, Minecraft.getMinecraft().fontRenderer);
    }

    public static void drawString(String str, int x, int y, Color col, int width)
    {
        drawString(str, x, y, col, width, true);
    }

    public static int measureString(String str)
    {
        return Minecraft.getMinecraft().fontRenderer.getStringWidth(str);
    }

    public static int getFontHeight()
    {
        return Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    }

    public static void drawHoveringText(String str, int x, int y, Color c, boolean shadow, float scale, FontRenderer rend)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(10, 0, 1);
        drawString(str, x, y, c, shadow, scale, rend);
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
        BufferBuilder bld = tessellator.getBuffer();
        bld.begin(7, DefaultVertexFormats.POSITION_COLOR);
        bld.pos((double) right, (double) y, 0).color(f1, f2, f3, f).endVertex();
        bld.pos((double) x, (double) y, 0).color(f1, f2, f3, f).endVertex();
        bld.pos((double) x, (double) bottom, 0).color(f5, f6, f7, f4).endVertex();
        bld.pos((double) right, (double) bottom, 0).color(f5, f6, f7, f4).endVertex();
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
        BufferBuilder bld = tessellator.getBuffer();
        GlStateManager.disableTexture2D();
        bld.begin(7, DefaultVertexFormats.POSITION);
        bld.pos((double)x, (double)bottom, 0.0D).endVertex();
        bld.pos((double)right, (double)bottom, 0.0D).endVertex();
        bld.pos((double)right, (double)y, 0.0D).endVertex();
        bld.pos((double)x, (double)y, 0.0D).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
    }

    public static int drawStencil(int w, int h)
    {
        //todo REWRITE STENCILING
        int bit = MinecraftForgeClient.reserveStencilBit();
        int flag = 1 << bit;

        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glStencilFunc(GL11.GL_ALWAYS, flag, flag);
        GL11.glStencilOp(GL11.GL_KEEP, GL11.GL_KEEP, GL11.GL_REPLACE);
        GL11.glStencilMask(flag);
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthMask(false);
        GL11.glClearStencil(0);
        GL11.glClear(GL11.GL_STENCIL_BUFFER_BIT);
        DrawingTools.drawRectangle(0, 0, w, h);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glStencilFunc(GL11.GL_EQUAL, flag, flag);
        GL11.glStencilMask(0);
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(true);
        return bit;
    }

    public static void removeStencil(int bit)
    {
        GL11.glDisable(GL11.GL_STENCIL_TEST);
        MinecraftForgeClient.releaseStencilBit(bit);
    }

    public static void drawEntity(Entity ent, float x, float y, float scale, float rotX, float rotY, float rotZ)
    {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 50);
        GlStateManager.scale(-scale, scale, scale);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(rotX, 1, 0, 0);
        GlStateManager.rotate(rotY, 0, 1, 0);
        GlStateManager.rotate(rotZ, 0, 0, 1);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.doRenderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }
}
