package ru.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.drawables.AnimatedDrawable;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.libmc.client.gui.drawables.SimpleDrawable;
import ru.pearx.purmag.common.Utils;

import java.util.HashMap;

/**
 * Created by mrAppleXZ on 28.04.17 12:21.
 */
@SideOnly(Side.CLIENT)
public class GuiDrawableRegistry
{
    public static IGuiDrawable runes;
    public static IGuiDrawable displayMessage;
    public static IGuiDrawable paperEntry;
    public static IGuiDrawable splitter;
    public static HashMap<Integer, IGuiDrawable> ifTabletEntryBgs = new HashMap<>();

    public static void setup()
    {
        runes = new AnimatedDrawable(Utils.getRegistryName("textures/gui/runes.png"), 32, 38, 32, 38, 32, 380, 100, 0, -6);
        displayMessage = new AnimatedDrawable(Utils.getRegistryName("textures/gui/message.png"), 192, 32, 192, 32, 192, 1024, 100);
        paperEntry = new SimpleDrawable(Utils.getRegistryName("textures/gui/sticker.png"), 32, 32, 32, 32);
        splitter = new SimpleDrawable(Utils.getRegistryName("textures/gui/splitter.png"), 276, 4, 276, 4);
        ifTabletEntryBgs.put(0, paperEntry);
        ifTabletEntryBgs.put(1, runes);
        ifTabletEntryBgs.put(2, runes);
    }
}