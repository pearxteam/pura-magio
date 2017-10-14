package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.util.Point;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.purmag.common.infofield.steps.IRSIngredient;

import java.util.List;

/*
 * Created by mrAppleXZ on 13.10.17 18:41.
 */
@SideOnly(Side.CLIENT)
public class IRSIngredientRenderer<T extends IRSIngredient> extends IRSRenderer<T>
{
    private List<ItemStack> toDisplay;

    public IRSIngredientRenderer(T step)
    {
        super(step);
        toDisplay = ItemStackUtils.getIngredientItems(step.getIngredient());
    }

    @Override
    public void render()
    {
        super.render();
        if (step.shouldShowStack())
        {
            Point pos = getPosOnScreen();
            ItemStack rend = toDisplay.get((int) (System.currentTimeMillis() / 1000 % toDisplay.size()));
            ItemDrawable draw = new ItemDrawable(rend, 5);
            DrawingTools.drawString(step.getDescription(), 5, draw.getWidth(), Colors.WHITE, getWidth() - 5);
            int x = (getWidth() - draw.getWidth()) / 2;
            draw.draw(getGuiScreen(), x, 0);
            if(isFocused())
                draw.drawTooltip(getGuiScreen(), x, 0, getLastMouseX(), getLastMouseY(), pos.getX(), pos.getY());
        }
        else
            DrawingTools.drawString(step.getDescription(), 5, 0, Colors.WHITE, getWidth() - 5);
    }
}
