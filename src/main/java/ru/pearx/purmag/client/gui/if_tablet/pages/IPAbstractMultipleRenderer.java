package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraft.client.resources.I18n;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.purmag.client.infofield.pages.IIfPage;

import java.util.List;

/*
 * Created by mrAppleXZ on 03.09.17 21:35.
 */
public abstract class IPAbstractMultipleRenderer<T extends IIfPage> extends IPRenderer<T>
{
    public IPAbstractMultipleRenderer(T page)
    {
        super(page);
    }

    protected void setupControls(List<? extends Control> lst)
    {
        int margin = 2;

        int totalWidth = 0;
        for (Control cont : lst)
        {
            totalWidth += cont.getWidth();
            totalWidth += margin;
        }
        totalWidth -= margin;
        int x = (getWidth() - totalWidth) / 2;
        for (Control cont : lst)
        {
            cont.setPos(x, (getHeight() - cont.getHeight()) / 2);
            controls.add(cont);
            x += cont.getWidth();
            x += margin;
        }
    }

    @Override
    public void render()
    {
        String s = I18n.format("if_page.furnace_recipes.name");
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, 0, Colors.WHITE);
    }
}
