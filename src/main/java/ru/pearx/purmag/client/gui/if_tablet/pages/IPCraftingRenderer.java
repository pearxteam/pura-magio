package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraft.client.resources.I18n;
import ru.pearx.lib.Colors;
import ru.pearx.lib.Supplied;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.client.gui.controls.recipes.CraftingControl;
import ru.pearx.purmag.client.infofield.pages.IfPageCrafting;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 03.09.17 21:34.
 */
public class IPCraftingRenderer extends IPAbstractMultipleRenderer<IfPageCrafting>
{
    public IPCraftingRenderer(IfPageCrafting page)
    {
        super(page);
    }

    @Override
    public void init()
    {
        super.init();
        List<CraftingControl> lst = new ArrayList<>();
        for(Supplied<CraftingControl> sup : page.getCrafts())
        {
            lst.add(sup.get());
        }
        setupControls(lst);
    }

    @Override
    public void render()
    {
        String s = I18n.format("if_page.crafting_recipes.name");
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, 0, Colors.WHITE);
    }
}
