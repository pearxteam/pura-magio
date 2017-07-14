package ru.pearx.purmag.common.infofield.steps;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSPapyrusRenderer;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;

/**
 * Created by mrAppleXZ on 07.06.17 21:14.
 */
public class IRSTranslatePapyrus extends IRSPapyrus
{
    public IRSTranslatePapyrus(String id)
    {
        super(id);
    }

    @Override
    public String getUnlocalizedDescription()
    {
        return "translate_papyrus";
    }

    @Override
    public String getUnlocalizedName()
    {
        return "translate_papyrus";
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSPapyrusRenderer(this);
    }
}
