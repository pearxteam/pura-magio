package net.pearx.purmag.client.guis.papyrus;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.Control;
import net.pearx.purmag.common.items.ItemRegistry;

import java.awt.*;

/**
 * Created by mrAppleXZ on 31.05.17 19:46.
 */
@SideOnly(Side.CLIENT)
public class GuiPapyrus extends Control
{
    private String text;

    public GuiPapyrus(String papyrusId)
    {
        text = ItemRegistry.papyrus.getPapyrus(papyrusId).getDisplayText();
        setWidth(192);
        setHeight(240);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(text, 0, 0, Color.LIGHT_GRAY, getWidth());
    }

    @Override
    public int getX()
    {
        return (getGuiScreen().getWidth() - getWidth()) / 2;
    }

    @Override
    public int getY()
    {
        return (getGuiScreen().getHeight() - getHeight()) / 2;
    }
}
