package net.pearx.purmag.client.guis.if_tablet;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.client.guis.controls.common.Button;
import net.pearx.purmag.common.CapabilityRegistry;
import net.pearx.purmag.common.infofield.IfEntry;

import java.awt.*;

/**
 * Created by mrAppleXZ on 30.04.17 21:25.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSR extends GuiIfTabletS
{
    public IfEntry entry;
    public int doneSteps;
    public Button btnBack;

    public GuiIfTabletSR(IfEntry entr)
    {
        super();
        entry = entr;
        doneSteps = Minecraft.getMinecraft().player.getCapability(CapabilityRegistry.ENTRY_STORE_CAP, null).getSteps(entry.getId());
    }

    @Override
    public void init()
    {
        if(doneSteps >= entry.getSteps().size())
        {
            getTablet().changeScreen(new GuiIfTabletSP(entry, 0));
            return;
        }
        btnBack = new Button(I18n.translateToLocal("button.back.name"), this::goBack);
        btnBack.setX(8);
        btnBack.setY(getHeight() - 24 - 8);
        btnBack.setWidth(getWidth() - 16);
        btnBack.setHeight(24);
        controls.add(entry.getSteps().get(doneSteps).getRenderer());
        controls.add(btnBack);
    }

    @Override
    public void render()
    {
        String dn = entry.getDisplayName() + " [" + (doneSteps + 1) + "/" + entry.getSteps().size() + "]";
        String stepName = entry.getSteps().get(doneSteps).getDisplayName();
        DrawingTools.drawString(dn, (getWidth() - DrawingTools.measureString(dn)) / 2, 8, Color.WHITE);
        DrawingTools.drawString(stepName, (getWidth() - DrawingTools.measureString(stepName)) / 2, 26, Color.WHITE);
    }
}
