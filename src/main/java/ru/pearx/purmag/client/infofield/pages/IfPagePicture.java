package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.gui.drawables.IGuiDrawable;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPPictureRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/*
 * Created by mrAppleXZ on 07.10.17 12:23.
 */
@SideOnly(Side.CLIENT)
public class IfPagePicture implements IIfPage
{
    private IGuiDrawable picture;
    private String unlocalizedText;
    private Object[] properties;

    public IfPagePicture(IGuiDrawable picture, String unlocalizedText, Object... properties)
    {
        setPicture(picture);
        setUnlocalizedText(unlocalizedText);
        setProperties(properties);
    }

    public IfPagePicture(IGuiDrawable picture)
    {
        setPicture(picture);
    }

    public IGuiDrawable getPicture()
    {
        return picture;
    }

    public void setPicture(IGuiDrawable picture)
    {
        this.picture = picture;
    }

    public String getUnlocalizedText()
    {
        return unlocalizedText;
    }

    public void setUnlocalizedText(String unlocalizedText)
    {
        this.unlocalizedText = unlocalizedText;
    }

    public Object[] getProperties()
    {
        return properties;
    }

    public void setProperties(Object[] properties)
    {
        this.properties = properties;
    }

    public String getText()
    {
        return getUnlocalizedText() == null ? "" : I18n.format("if_page." + getUnlocalizedText() + ".text", getProperties());
    }

    @Override
    public IPRenderer getRenderer()
    {
        return new IPPictureRenderer(this);
    }
}
