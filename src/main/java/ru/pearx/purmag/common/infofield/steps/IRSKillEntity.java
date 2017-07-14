package ru.pearx.purmag.common.infofield.steps;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSKillEntityRenderer;
import ru.pearx.purmag.client.gui.if_tablet.steps.IRSRenderer;

/**
 * Created by mrAppleXZ on 27.06.17 21:25.
 */
public class IRSKillEntity extends IRSBase
{
    public Class<? extends EntityLivingBase> clazz;

    public IRSKillEntity(Class<? extends EntityLivingBase> clazz, String unlocDesc)
    {
        this.clazz = clazz;
        setUnlocalizedDescription(unlocDesc);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IRSRenderer getRenderer()
    {
        return new IRSKillEntityRenderer(this);
    }

    @Override
    public String getUnlocalizedName()
    {
        return "kill_entity";
    }
}
