package net.pearx.purmag.client.guis.controls.common;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.TexturePart;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.client.guis.drawables.IGuiDrawable;
import net.pearx.purmag.common.SoundRegistry;
import net.pearx.purmag.common.Utils;

import java.awt.*;

/**
 * Created by mrAppleXZ on 02.05.17 8:53.
 */
@SideOnly(Side.CLIENT)
public class Button extends Control
{
    public ResourceLocation textures;
    public TexturePart lu, u, ru, l, c, r, lb, b, rb;
    public String text;
    public IGuiDrawable image;
    public Runnable clickAction;
    public Color textColor = Color.WHITE;

    public Button()
    {
        textures = Utils.getRegistryName("textures/gui/button.png");
        //I know, it can be made using two "for" cycles, but who cares ¯\_(ツ)_/¯.
        lu = new TexturePart(textures, 0, 0, 8, 8, 24, 24);
        u = new TexturePart(textures, 8, 0, 8, 8, 24, 24);
        ru = new TexturePart(textures, 16, 0, 8, 8, 24, 24);
        l = new TexturePart(textures, 0, 8, 8, 8, 24, 24);
        c = new TexturePart(textures, 8, 8, 8, 8, 24, 24);
        r = new TexturePart(textures, 16, 8, 8, 8, 24, 24);
        lb = new TexturePart(textures, 0, 16, 8, 8, 24, 24);
        b = new TexturePart(textures, 8, 16, 8, 8, 24, 24);
        rb = new TexturePart(textures, 16, 16, 8, 8, 24, 24);
    }

    public Button(String str, Runnable run)
    {
        this();
        this.text = str;
        this.clickAction = run;
    }

    public Button(String str, Runnable run, IGuiDrawable image)
    {
        this(str, run);
        this.image = image;
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
            u.draw(x, 0);
            b.draw(x, h - 8);
        }
        //right and left
        for(int i = 1; i < (h - 8) / 8; i++)
        {
            int y = i * 8;
            l.draw(0, y);
            r.draw(w - 8, y);
        }
        for (int xm = 1; xm < (w - 8) / 8; xm++)
        {
            for(int ym = 1; ym < (h - 8) / 8; ym++)
            {
                c.draw(xm * 8, ym * 8);
            }
        }
        //corners
        lu.draw(0, 0);
        ru.draw(w - 8, 0);
        lb.draw(0, h - 8);
        rb.draw(w - 8, h - 8);
        GlStateManager.color(1, 1, 1, 1);

        DrawingTools.drawString(text, (w - DrawingTools.measureString(text)) / 2, (h - DrawingTools.getFontHeight()) / 2, textColor);
    }
}
