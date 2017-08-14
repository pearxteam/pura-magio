package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPTextRenderer;

/**
 * Created by mrAppleXZ on 22.04.17 21:05.
 */
@SideOnly(Side.CLIENT)
public class IfPageText implements IIfPage
{
    private String unlocalizedText;
    private String[] properties;

    public IfPageText(String unlocalized)
    {
        setUnlocalizedText(unlocalized);
    }
    public IfPageText(String unlocalized, String... properties)
    {
        setUnlocalizedText(unlocalized);
        setProperties(properties);
    }

    public String getUnlocalizedText()
    {
        return unlocalizedText;
    }

    public void setUnlocalizedText(String unlocalizedText)
    {
        this.unlocalizedText = unlocalizedText;
    }

    public String getDisplayText()
    {
        return I18n.format("if_page." + getUnlocalizedText() + ".text", getProperties());
    }

    public String[] getProperties()
    {
        return properties;
    }

    public void setProperties(String[] properties)
    {
        this.properties = properties;
    }

    @Override
    public IPRenderer getRenderer()
    {
        return new IPTextRenderer(this);
    }
}
