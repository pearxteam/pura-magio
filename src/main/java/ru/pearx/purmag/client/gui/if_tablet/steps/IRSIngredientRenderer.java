package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.Point;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.drawables.item.MultiItemDrawable;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.purmag.common.infofield.steps.IRSIngredient;

/*
 * Created by mrAppleXZ on 13.10.17 18:41.
 */
@SideOnly(Side.CLIENT)
public class IRSIngredientRenderer<T extends IRSIngredient> extends IRSRenderer<T>
{
    private MultiItemDrawable display;

    public IRSIngredientRenderer(T step)
    {
        super(step);
        display = new MultiItemDrawable(ItemStackUtils.getIngredientItems(step.getIngredient()), 5);
    }

    @Override
    public void render()
    {
        super.render();
        if (step.shouldShowStack())
        {
            Point pos = getPosOnScreen();
            DrawingTools.drawString(step.getDescription(), 5, display.getWidth(), Colors.WHITE, getWidth() - 5);
            int x = (getWidth() - display.getWidth()) / 2;
            display.draw(getGuiScreen(), x, 0);
            if(isFocused())
                display.drawTooltip(getGuiScreen(), x, 0, getLastMouseX(), getLastMouseY(), pos.getX(), pos.getY());
        }
        else
            DrawingTools.drawString(step.getDescription(), 5, 0, Colors.WHITE, getWidth() - 5);
    }
}
