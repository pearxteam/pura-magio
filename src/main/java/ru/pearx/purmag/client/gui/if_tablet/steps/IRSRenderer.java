package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.purmag.client.gui.if_tablet.GuiIfTabletSR;
import ru.pearx.purmag.common.infofield.steps.IIfResearchStep;

/**
 * Created by mrAppleXZ on 29.04.17 20:01.
 */
@SideOnly(Side.CLIENT)
public class IRSRenderer<T extends IIfResearchStep> extends Control
{
    public T step;
    public IRSRenderer(T step)
    {
        this.step = step;
    }

    @Override
    public void init()
    {
        setWidth(getTablet().getWidth() - 16);
        setHeight(getTablet().getHeight() - (8 + 36 + 24));
        setX(8);
        setY(36);
    }

    public GuiIfTabletSR getTablet()
    {
        return (GuiIfTabletSR)getParent();
    }
}
