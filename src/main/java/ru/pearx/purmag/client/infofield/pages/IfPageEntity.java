package ru.pearx.purmag.client.infofield.pages;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPEntityRenderer;
import ru.pearx.purmag.client.gui.if_tablet.pages.IPRenderer;

/*
 * Created by mrAppleXZ on 04.07.17 20:34.
 */
@SideOnly(Side.CLIENT)
public class IfPageEntity implements IIfPage
{
    public Class<? extends EntityLivingBase> clazz;
    public String unlocName;

    public IfPageEntity(Class<? extends EntityLivingBase> clazz, String unlocName)
    {
        this.clazz = clazz;
        this.unlocName = unlocName;
    }

    @Override
    public IPRenderer getRenderer()
    {
        return new IPEntityRenderer(this);
    }

    public String getName()
    {
        return I18n.format("if_page." + unlocName + ".name");
    }
}
