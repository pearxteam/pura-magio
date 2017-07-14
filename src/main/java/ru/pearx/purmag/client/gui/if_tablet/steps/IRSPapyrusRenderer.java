package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.drawables.ItemDrawable;
import ru.pearx.purmag.common.infofield.steps.IRSPapyrus;
import ru.pearx.purmag.common.items.ItemRegistry;
import org.lwjgl.util.Rectangle;

import java.awt.*;


/**
 * Created by mrAppleXZ on 01.06.17 19:42.
 */
@SideOnly(Side.CLIENT)
public class IRSPapyrusRenderer extends IRSRenderer<IRSPapyrus>
{
    private int lastX, lastY;

    public IRSPapyrusRenderer(IRSPapyrus step)
    {
        super(step);
    }

    @Override
    public void render()
    {
        super.render();
        ItemStack stack = ItemRegistry.papyrus.getPapyrusItem(step.id);
        ItemDrawable draw = new ItemDrawable(stack, 5);
        int x = (getWidth() - draw.getWidth()) / 2;
        draw.draw(x, 0);
        DrawingTools.drawString(step.getDescription(), 5, draw.getHeight() + 5, Color.WHITE, getWidth() - 5);
        if(isFocused() && new Rectangle(x, 0, draw.getWidth(), draw.getHeight()).contains(lastX, lastY))
        {
            getGuiScreen().drawTooltip(stack, lastX, lastY);
        }
    }

    @Override
    public void mouseMove(int x, int y, int dx, int dy)
    {
        lastX = x;
        lastY = y;
    }
}
