package ru.pearx.purmag.client.gui.if_tablet.steps;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.common.ItemStackUtils;
import ru.pearx.purmag.common.infofield.steps.IRSBlockInteract;

/*
 * Created by mrAppleXZ on 13.10.17 20:25.
 */
@SideOnly(Side.CLIENT)
public class IRSInteractRenderer extends IRSRenderer<IRSBlockInteract>
{
    public IRSInteractRenderer(IRSBlockInteract step)
    {
        super(step);
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(step.getDescription(), 0, 0, Colors.WHITE, getWidth());
        int y = DrawingTools.getStringHeight(step.getDescription(), getWidth());
        if(step.getRenderStates() != null)
        {
            Pair<IBlockState, ItemStack> state = step.getRenderStates().get((int) (System.currentTimeMillis() / 1000 % step.getRenderStates().size()));
            Minecraft.getMinecraft().
        }
    }
}
