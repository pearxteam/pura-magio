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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mrAppleXZ on 08.06.17 11:10.
 */
@SideOnly(Side.CLIENT)
public class GuiTranslationDeskPanel extends Control
{
    public static final List<Integer> keyMap = new ArrayList<>();
    static
    {
        keyMap.add(Keyboard.KEY_S);
        keyMap.add(Keyboard.KEY_D);
        keyMap.add(Keyboard.KEY_F);
        keyMap.add(Keyboard.KEY_G);
    }
    public boolean translating;
    public List<Entry> entries = new ArrayList<>();
    public int totalEntries;
    public int rate;
    private long lastms = 0;
    private boolean[] canPress = new boolean[4];

    public GuiTranslationDeskPanel()
    {
        setWidth(128);
        setHeight(230);
        keyEventsRS = false;
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
        boolean[] cp = new boolean[] {false, false, false, false};
        for(Entry entr : entries)
        {
            entr.range -= ((System.currentTimeMillis() - lastms) * 0.1f);

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
                        boolean compl = Keyboard.isKeyDown(keyMap.get(entr.line));
                        cp[entr.line] = true;
                        if(compl)
                        {
                            entr.setCompleted(true);
                            rate++;
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
        canPress = cp;
        entries.removeAll(toRemove);
        GlStateManager.color(0, 0, 1);
        DrawingTools.drawRectangle(0, getHeight() - 45, getWidth(), 1);
        GlStateManager.color(1, 1, 1);
        lastms = System.currentTimeMillis();

        GlStateManager.enableBlend();
        DrawingTools.drawTexture(Utils.getRegistryName("textures/gui/translation_desk/display.png"), 0, 0, getWidth(), getHeight());
        GlStateManager.disableBlend();
    }

    @Override
    public void keyDown(int keycode)
    {
        if(rate > 0)
        {
            if (keyMap.contains(keycode))
            {
                for (int i = 0; i < keyMap.size(); i++)
                {
                    if (keyMap.get(i).equals(keycode))
                        if (!canPress[i])
                            rate--;
                }
            }
        }
    }

    public GuiTranslationDesk getDesk()
    {
        return (GuiTranslationDesk) getParent();
    }

    public void start()
    {
        if(getDesk().status == GuiTranslationDesk.Status.CAN_TRANSLATE)
        {
            translating = true;
            getDesk().updateStatus();
            char[] chars = ItemRegistry.papyrus.getPapyrus(ItemRegistry.papyrus.getId(getDesk().stack)).getDisplayText().toCharArray();
            for(int i = 0; i < chars.length / 4; i++)
            {
                entries.add(new Entry((byte) PurMag.rand.nextInt(4), 250 + (i * 40)));
            }
            totalEntries = entries.size();
        }
    }

    public void stop()
    {
        if(getDesk().status == GuiTranslationDesk.Status.TRANSLATING)
        {
            translating = false;
            getDesk().updateStatus();
            entries.clear();
            totalEntries = 0;
            rate = 0;
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
