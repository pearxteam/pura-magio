package net.pearx.purmag.common.infofield.pages;

import net.minecraft.client.resources.I18n;

/**
 * Created by mrAppleXZ on 22.04.17 21:05.
 */
public class IfPageText implements IIfPage
{
    private String unlocalizedText;

    public IfPageText(String unlocalized)
    {
        setUnlocalizedText(unlocalized);
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
        return I18n.format(getUnlocalizedText());
    }

    @Override
    public void render()
    {

    }
}
