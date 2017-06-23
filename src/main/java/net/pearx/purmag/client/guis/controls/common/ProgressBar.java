package net.pearx.purmag.client.guis.controls.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.Utils;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 10.06.17 9:31.
 */
@SideOnly(Side.CLIENT)
public class ProgressBar extends Control
{
    private int maxValue;
    private int value;
    private Color color = Color.WHITE;
    private Color textColor = Color.BLACK;
    private Color backgroundColor = Color.GRAY;
    private String text;
    private List<Mark> marks = new ArrayList<>();

    public ProgressBar()
    {

    }

    public int getMaxValue()
    {
        return maxValue;
    }

    public void setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
        if(getValue() > maxValue)
            setValue(maxValue);
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int value)
    {
        if(value > maxValue)
            value = maxValue;
        this.value = value;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Color getTextColor()
    {
        return textColor;
    }

    public void setTextColor(Color textColor)
    {
        this.textColor = textColor;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }

    public String getFormattedText()
    {
        return getText() == null ? "" : String.format(getText(), getValue(), getMaxValue(), (int)(100f / getMaxValue() * getValue()) + "%");
    }

    public List<Mark> getMarks()
    {
        return marks;
    }

    @Override
    public void render()
    {
        DrawingTools.drawGradientRect(0, 0, getWidth(), getHeight(), getBackgroundColor(), getBackgroundColor());
        Minecraft.getMinecraft().getTextureManager().bindTexture(Utils.getRegistryName("textures/gui/progress_bar/" + (System.currentTimeMillis() / 50 % 4) + ".png"));
        double w = (double)getWidth() / getMaxValue() * getValue();
        Tessellator tes = Tessellator.getInstance();
        BufferBuilder buf = tes.getBuffer();
        buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        buf.pos(0, getHeight(), 0).tex(0, 0).color(color.getRed(), color.getGreen(), color.getBlue(), 255).endVertex();
        buf.pos(w, getHeight(), 0).tex(w / 4d, 0).color(color.getRed(), color.getGreen(), color.getBlue(), 255).endVertex();
        buf.pos(w, 0, 0).tex(w / 4d, getHeight() / 4).color(color.getRed(), color.getGreen(), color.getBlue(), 255).endVertex();
        buf.pos(0, 0, 0).tex(0, getHeight() / 4).color(color.getRed(), color.getGreen(), color.getBlue(), 255).endVertex();
        tes.draw();

        for(Mark mark : getMarks())
        {
            DrawingTools.drawGradientRect((int)((double) getWidth() / getMaxValue() * mark.getOnValue()), 0, 1, getHeight(), mark.getColor(), mark.getColor());
        }

        GlStateManager.color(0, 0, 0);
        DrawingTools.drawRectangle(0, 0, getWidth(), 1);
        DrawingTools.drawRectangle(getWidth() - 1, 0, 1, getHeight());
        DrawingTools.drawRectangle(0, getHeight() - 1, getWidth(), 1);
        DrawingTools.drawRectangle(0, 0, 1, getHeight());
        GlStateManager.color(1, 1, 1);

        String form = getFormattedText();
        DrawingTools.drawString(form, (getWidth() - DrawingTools.measureString(form)) / 2, (getHeight() - DrawingTools.getFontHeight()) / 2, getTextColor());
    }

    public static class Mark
    {
        private int onValue;
        private Color color;

        public Mark(int onValue, Color color)
        {
            this.onValue = onValue;
            this.color = color;
        }

        public int getOnValue()
        {
            return onValue;
        }

        public void setOnValue(int onValue)
        {
            this.onValue = onValue;
        }

        public Color getColor()
        {
            return color;
        }

        public void setColor(Color color)
        {
            this.color = color;
        }
    }
}
