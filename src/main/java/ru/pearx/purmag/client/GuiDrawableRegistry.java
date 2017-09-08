package ru.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.drawables.AnimatedDrawable;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.libmc.client.gui.drawables.SimpleDrawable;
import ru.pearx.purmag.common.Utils;

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
    public static IGuiDrawable furnace;
    public static IGuiDrawable crafting;


    public static void setup()
    {
        runes = new AnimatedDrawable(Utils.getResourceLocation("textures/gui/runes.png"), 32, 38, 32, 38, 32, 380, 100, 0, -6);
        displayMessage = new AnimatedDrawable(Utils.getResourceLocation("textures/gui/message.png"), 192, 32, 192, 32, 192, 1024, 100);
        paperEntry = new SimpleDrawable(Utils.getResourceLocation("textures/gui/sticker.png"), 32, 32, 32, 32);
        splitter = new SimpleDrawable(Utils.getResourceLocation("textures/gui/splitter.png"), 276, 4, 276, 4);
        furnace = new AnimatedDrawable(Utils.getResourceLocation("textures/gui/recipes/furnace.png"), 112, 112, 112, 112, 112, 1456, 100);
        crafting = new SimpleDrawable(Utils.getResourceLocation("textures/gui/recipes/crafting.png"), 128, 88, 128, 88);
    }
}