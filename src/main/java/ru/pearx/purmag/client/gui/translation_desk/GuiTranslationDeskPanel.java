package ru.pearx.purmag.client.gui.translation_desk;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.controls.common.ProgressBar;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.SoundRegistry;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.items.ItemRegistry;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.SPacketDoneTranslation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 08.06.17 11:10.
 */
@SideOnly(Side.CLIENT)
public class GuiTranslationDeskPanel extends Control
{
    public static final float REQUIRED_RATE = 0.65f;
    public static final ResourceLocation TEXTURE_BUTTON = Utils.gRL("textures/gui/translation_desk/semibutton.png");
    public static final ResourceLocation TEXTURE_DISPLAY = Utils.gRL("textures/gui/translation_desk/display.png");
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
    private int[] cooldowns = new int[]{0, 0, 0, 0};

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
        int bit = DrawingTools.drawStencil(getWidth(), getHeight());
        DrawingTools.drawRectangle(0, 0, getWidth(), getHeight());

        List<Entry> toRemove = new ArrayList<>();
        for (int i = 0; i < cooldowns.length; i++)
            if (cooldowns[i] > 0)
                cooldowns[i] -= (System.currentTimeMillis() - lastms);
        for (Entry entr : entries)
        {
            entr.range -= ((System.currentTimeMillis() - lastms) * 0.1f);

            int x = 32 * entr.line;
            int y = getHeight() - entr.range;
            int w = 32;
            int h = 20;
            if (y > 0)
            {
                if (entr.isCompleted())
                    GlStateManager.color(0, 1, 0);
                else
                {
                    if (y > getHeight() - (45 + h))
                    {
                        cooldowns[entr.line] = 300;
                        boolean compl = Keyboard.isKeyDown(keyMap.get(entr.line));
                        if (compl)
                        {
                            entr.setCompleted(true);
                            rate++;
                            Minecraft.getMinecraft().player.playSound(SoundEvents.BLOCK_NOTE_SNARE, 1, 1);
                        }
                        GlStateManager.color(0, 0, 1);
                    }
                    else
                        GlStateManager.color(1, 0, 0);
                }
                GlStateManager.enableBlend();
                DrawingTools.drawTexture(TEXTURE_BUTTON, x, y, w, h, 0, 0, w, h);
                GlStateManager.disableBlend();
            }
            if (entr.range <= 0)
            {
                if (!entr.isCompleted())
                    minRate();
                toRemove.add(entr);
            }
        }
        entries.removeAll(toRemove);
        GlStateManager.color(0, 0, 1);
        DrawingTools.drawRectangle(0, getHeight() - 45, getWidth(), 1);
        GlStateManager.color(1, 1, 1);
        lastms = System.currentTimeMillis();

        GlStateManager.enableBlend();
        DrawingTools.drawTexture(TEXTURE_DISPLAY, 0, 0, getWidth(), getHeight());
        GlStateManager.disableBlend();

        if (entries.size() == 0 && getDesk().status == GuiTranslationDesk.Status.TRANSLATING)
            done();
        DrawingTools.removeStencil(bit);
    }

    public void minRate()
    {
        if (rate > 0)
        {
            rate -= 2;
        }
        Minecraft.getMinecraft().player.playSound(SoundRegistry.ERROR, 1, 1);
    }

    @Override
    public void keyDown(int keycode)
    {
        if (translating)
        {
            if (keyMap.contains(keycode))
            {
                for (int i = 0; i < keyMap.size(); i++)
                {
                    if (keyMap.get(i).equals(keycode))
                        if (cooldowns[i] <= 0)
                        {
                            minRate();
                            cooldowns[i] = 50;
                        }
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
        if (getDesk().status == GuiTranslationDesk.Status.CAN_TRANSLATE)
        {
            translating = true;
            getDesk().updateStatus();
            char[] chars = PurMag.INSTANCE.getPapyrusRegistry().getPapyrus(ItemRegistry.papyrus.getId(getDesk().stack)).getDisplayText().toCharArray();
            for (int i = 0; i < chars.length / 4; i++)
            {
                entries.add(new Entry((byte) PurMag.INSTANCE.random.nextInt(4), 250 + (i * 40)));
            }
            totalEntries = entries.size();
            getDesk().barRate.getMarks().add(new ProgressBar.Mark((int) (totalEntries * REQUIRED_RATE), Colors.BLACK));
        }
    }

    public void stop()
    {
        if (getDesk().status == GuiTranslationDesk.Status.TRANSLATING)
        {
            translating = false;
            getDesk().updateStatus();
            entries.clear();
            totalEntries = 0;
            rate = 0;
            getDesk().barRate.getMarks().clear();
        }
    }

    public void done()
    {
        boolean updSt = false;
        if (rate >= (totalEntries * REQUIRED_RATE))
        {
            NetworkManager.sendToServer(new SPacketDoneTranslation(getDesk().pos, getDesk().entryName));
            updSt = true;
        }
        else
        {
            Minecraft.getMinecraft().player.playSound(SoundRegistry.ERROR, 1, 0.1f);
        }
        stop();
        if (updSt)
            getDesk().status = GuiTranslationDesk.Status.CANT_TRANSLATE;
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
