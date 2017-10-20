package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.purmag.client.infofield.pages.IfPageBlocks;

/*
 * Created by mrAppleXZ on 17.10.17 15:06.
 */
@SideOnly(Side.CLIENT)
public class IPBlocksRenderer extends IPRenderer<IfPageBlocks>
{
    private BlockArrayShowcase showcase;

    public IPBlocksRenderer(IfPageBlocks page)
    {
        super(page);
        showcase = new BlockArrayShowcase(page.getArray());
    }

    @Override
    public void init()
    {
        super.init();
        int y = DrawingTools.getStringHeight(page.getStructureName());
        showcase.setSize(getWidth(), getHeight() - y);
        showcase.setPos(0, y);
        controls.add(showcase);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(page.getStructureName(), (getWidth() - DrawingTools.measureString(page.getStructureName())) / 2, 0, Colors.WHITE);
    }
}
