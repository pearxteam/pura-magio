package net.pearx.purmag.client.guis.translation_desk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemRegistry;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 08.06.17 11:10.
 */
@SideOnly(Side.CLIENT)
public class GuiTranslationDeskPanel extends Control
{
    public boolean translating;
    public List<Entry> entries = new ArrayList<>();
    public long lastms = 0;

    public GuiTranslationDeskPanel()
    {
        setWidth(128);
        setHeight(230);
    }

    @Override
    public void init()
    {
        setX(getDesk().getWidth() - 5 - getWidth());
        setY(5);
    }

    @Override
    public void render()
    {
        DrawingTools.drawRectangle(0, 0, getWidth(), getHeight());

        List<Entry> toRemove = new ArrayList<>();
        for(Entry entr : entries)
        {
            entr.range -= ((System.currentTimeMillis() - lastms) * 0.08f);

            int x = 32 * entr.line;
            int y = getHeight() - entr.range;
            int w = 32;
            int h = 20;
            if(y > 0)
            {
                if(entr.isCompleted())
                    GlStateManager.color(0, 1, 0);
                else
                {
                    if (y > getHeight() - (45 + h))
                    {
                        boolean compl = false;
                        switch (entr.line)
                        {
                            case 0:
                                compl = Keyboard.isKeyDown(Keyboard.KEY_S);
                                break;
                            case 1:
                                compl = Keyboard.isKeyDown(Keyboard.KEY_D);
                                break;
                            case 2:
                                compl = Keyboard.isKeyDown(Keyboard.KEY_F);
                                break;
                            case 3:
                                compl = Keyboard.isKeyDown(Keyboard.KEY_G);
                                break;
                        }
                        if(compl)
                        {
                            entr.setCompleted(true);
                        }
                        GlStateManager.color(0, 0, 1);
                    }
                    else
                        GlStateManager.color(1, 0, 0);
                }
                GlStateManager.enableBlend();
                DrawingTools.drawTexture(Utils.getRegistryName("textures/gui/translation_desk/semibutton.png"), x, y, w, h, 0, 0, w, h);
                GlStateManager.disableBlend();
            }
            if(entr.range <= 0)
                toRemove.add(entr);
        }
        entries.removeAll(toRemove);
        GlStateManager.color(0, 0, 1);
        DrawingTools.drawRectangle(0, getHeight() - 45, getWidth(), 1);
        GlStateManager.color(1, 1, 1);
        lastms = System.currentTimeMillis();
    }

    public GuiTranslationDesk getDesk()
    {
        return (GuiTranslationDesk) getParent();
    }

    public void start()
    {
        if(!translating)
        {
            translating = true;
            getDesk().updateStatus();
            char[] chars = ItemRegistry.papyrus.getPapyrus(ItemRegistry.papyrus.getId(getDesk().stack)).getDisplayText().toCharArray();
            for(int i = 0; i < chars.length / 3; i++)
            {
                entries.add(new Entry((byte) PurMag.rand.nextInt(4), 250 + (i * 40)));
            }
        }
    }

    public void stop()
    {
        if(translating)
        {
            translating = false;
            getDesk().updateStatus();
            entries.clear();
        }
    }



    @SideOnly(Side.CLIENT)
    public static class Entry
    {
        private int range;
        private byte line;
        private boolean completed;

        public Entry(byte line, int range)
        {
            this.range = range;
            this.line = line;
        }

        public boolean isCompleted()
        {
            return completed;
        }

        public void setCompleted(boolean completed)
        {
            this.completed = completed;
        }
    }
}
