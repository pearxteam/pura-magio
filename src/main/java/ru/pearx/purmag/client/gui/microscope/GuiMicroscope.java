package ru.pearx.purmag.client.gui.microscope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.PXLGui;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.steps.IRSMicroscopeResearch;

/*
 * Created by mrAppleXZ on 18.08.17 20:48.
 */
@SideOnly(Side.CLIENT)
public class GuiMicroscope extends GuiAbstractMicroscope
{
    private String entrName;
    private IRSMicroscopeResearch step;
    private Button btnResearch;
    private BlockPos pos;
    private ItemStack stack;

    public GuiMicroscope(ItemStack stack, BlockPos pos)
    {
        this.stack = stack;
        this.pos = pos;
        for(Pair<IfEntry, IRSMicroscopeResearch> p : PurMag.INSTANCE.getIfRegistry().getAllResearchableSteps(IRSMicroscopeResearch.class, Minecraft.getMinecraft().player))
        {
            if(p.getRight().getIngredient().apply(stack))
            {
                entrName = p.getLeft().getId();
                step = p.getRight();
            }
        }
    }

    @Override
    public void init()
    {
        if(entrName != null)
        {
            btnResearch = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.microscope.research"), () ->
                    Minecraft.getMinecraft().displayGuiScreen(new PXLGui(new GuiMicroscopeResearch(pos, step, entrName))));
            btnResearch.setSize(128, 32);
            btnResearch.setPos(getWidth() - btnResearch.getWidth() - margin, getHeight() - btnResearch.getHeight() - margin);
            controls.add(btnResearch);
        }
    }

    @Override
    public void renderMainPart()
    {
        int y = margin;
        for (String s : PurMagClient.INSTANCE.getMicroscopeDataBuilder().build(stack))
        {
            DrawingTools.drawString(s, margin, y, Colors.WHITE);
            y += DrawingTools.getStringHeight(s);
        }
    }
}
