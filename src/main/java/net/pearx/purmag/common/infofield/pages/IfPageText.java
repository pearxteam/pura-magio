package net.pearx.purmag.common.infofield.pages;

import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.if_tablet.pages.IPRenderer;
import net.pearx.purmag.client.guis.if_tablet.pages.IPTextRenderer;

/**
 * Created by mrAppleXZ on 22.04.17 21:05.
 */
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
        return I18n.translateToLocalFormatted("if_page." + getUnlocalizedText() + ".text", properties);
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
    @SideOnly(Side.CLIENT)
    public IPRenderer getRenderer()
    {
        return new IPTextRenderer(this);
    }
}
