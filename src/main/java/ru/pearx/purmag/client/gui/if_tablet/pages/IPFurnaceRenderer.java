package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraft.client.resources.I18n;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.client.gui.recipes.FurnaceControl;
import ru.pearx.purmag.client.infofield.pages.IfPageFurnace;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 14.08.17 14:24.
 */
public class IPFurnaceRenderer extends IPRenderer<IfPageFurnace>
{
    protected List<FurnaceControl> smelts = new ArrayList<>();

    public IPFurnaceRenderer(IfPageFurnace page)
    {
        super(page);
        for (int i = 0; i < page.getInputs().size(); i++)
        {
            smelts.add(new FurnaceControl(page.getInput(i), page.getOutput(i)));
        }
    }

    @Override
    public void init()
    {
        super.init();

        int margin = 8;

        int totalWidth = 0;
        int height = 0;
        for (FurnaceControl cont : smelts)
        {
            totalWidth += cont.getWidth();
            totalWidth += margin;
            if (height < cont.getHeight())
                height = cont.getHeight();
        }
        totalWidth -= margin;
        int x = (getWidth() - totalWidth) / 2;
        int y = (getHeight() - height) / 2;
        for (FurnaceControl cont : smelts)
        {
            cont.setPos(x, y);
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
