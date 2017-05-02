package net.pearx.purmag.client.guis.if_tablet;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;

import java.awt.*;

/**
 * Created by mrAppleXZ on 28.04.17 11:26.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSE extends GuiIfTabletS
{
    public GuiIfTabletSESelector selector;
    public GuiIfTabletSEEntries entries;

    @Override
    public void init()
    {
        selector = new GuiIfTabletSESelector();
        controls.add(selector);
        selector.setX(getWidth() - selector.getWidth());
        selector.setY((getHeight() - selector.getHeight()) / 2);

        entries = new GuiIfTabletSEEntries();
        controls.add(entries);

        entries.reload();
    }

    @Override
    public void postRender()
    {
        DrawingTools.drawString(selector.getSelectedChannel().getDisplayName(), 8, 8, Color.WHITE);
    }

    @Override
    public boolean isGlowing()
    {
        return true;
    }
}
