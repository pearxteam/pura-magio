package net.pearx.purmag.client.guis.controls.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.GuiDrawableRegistry;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.common.SoundRegistry;
import net.pearx.purmag.common.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 02.05.17 8:53.
 */
@SideOnly(Side.CLIENT)
public class Button extends Control
{
    protected ResourceLocation textures;
    /* lu, u, ru,
       l, c, r,
       lb, b, rb */
    protected List<TexturePart> parts;

    private String text;
    public Runnable clickAction;
    private Color textColor = Color.WHITE;

    public Button(ResourceLocation textures, String str, Runnable run)
    {
        this.textures = textures;
        this.parts = new ArrayList<>();
        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 3; x++)
            {
                parts.add(new TexturePart(textures, x * 8, y * 8, 8, 8, 24, 24));
            }
        }
        setText(str);
        this.clickAction = run;
    }

    @Override
    public void mouseUp(int button, int x, int y)
    {
        Minecraft.getMinecraft().player.playSound(SoundRegistry.magicalClick, 1, 1);
        clickAction.run();
    }

    @Override
    public void render()
    {
        int w = getWidth();
        int h = getHeight();
        if(isFocused())
            GlStateManager.color(.8f, .8f, .8f, .8f);
        //upper and bottom
        for(int i = 1; i < (w - 8) / 8; i++)
        {
            int x = i * 8;
            parts.get(1).draw(x, 0);
            parts.get(7).draw(x, h - 8);
        }
        //right and left
        for(int i = 1; i < (h - 8) / 8; i++)
        {
            int y = i * 8;
            parts.get(3).draw(0, y);
            parts.get(5).draw(w - 8, y);
        }
        for (int xm = 1; xm < (w - 8) / 8; xm++)
        {
            for(int ym = 1; ym < (h - 8) / 8; ym++)
            {
                parts.get(4).draw(xm * 8, ym * 8);
            }
        }
        //corners
        parts.get(0).draw(0, 0);
        parts.get(2).draw(w - 8, 0);
        parts.get(6).draw(0, h - 8);
        parts.get(8).draw(w - 8, h - 8);

        GlStateManager.color(1, 1, 1, 1);
        DrawingTools.drawString(getText(), (w - DrawingTools.measureString(getText())) / 2, (h - DrawingTools.getFontHeight()) / 2, getTextColor());
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
}
