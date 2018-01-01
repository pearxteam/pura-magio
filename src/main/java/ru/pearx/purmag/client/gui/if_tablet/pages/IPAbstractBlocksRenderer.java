package ru.pearx.purmag.client.gui.if_tablet.pages;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.BlockArrayShowcase;
import ru.pearx.purmag.client.infofield.pages.IfPageAbstractBlocks;

/*
 * Created by mrAppleXZ on 01.01.18 21:58.
 */
@SideOnly(Side.CLIENT)
public abstract class IPAbstractBlocksRenderer<T extends IfPageAbstractBlocks> extends IPRenderer<T>
{
    private BlockArrayShowcase showcase;

    public IPAbstractBlocksRenderer(T page, BlockArrayShowcase showcase)
    {
        super(page);
        this.showcase = showcase;
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
