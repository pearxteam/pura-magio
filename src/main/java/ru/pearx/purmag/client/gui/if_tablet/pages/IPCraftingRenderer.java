package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.purmag.client.gui.recipes.crafting.CraftingControl;
import ru.pearx.purmag.client.infofield.pages.IfPageCrafting;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by mrAppleXZ on 03.09.17 21:34.
 */
public class IPCraftingRenderer extends IPAbstractMultipleRenderer<IfPageCrafting>
{
    protected List<CraftingControl> crafts = new ArrayList<>();

    public IPCraftingRenderer(IfPageCrafting page)
    {
        super(page);
        for(ResourceLocation loc : page.getIds())
        {
            crafts.add(new CraftingControl(loc));
        }
    }

    @Override
    public void init()
    {
        super.init();
        setupControls(crafts);
    }

    @Override
    public void render()
    {
        String s = I18n.format("if_page.crafting_recipes.name");
        DrawingTools.drawString(s, (getWidth() - DrawingTools.measureString(s)) / 2, 0, Colors.WHITE);
    }
}
