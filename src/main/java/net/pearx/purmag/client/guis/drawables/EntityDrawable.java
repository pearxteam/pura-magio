package net.pearx.purmag.client.guis.drawables;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.client.guis.DrawingTools;
import net.pearx.purmag.common.entities.EntityBeetle;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by mrAppleXZ on 28.06.17 9:44.
 */
@SideOnly(Side.CLIENT)
public class EntityDrawable implements IGuiDrawable
{
    public EntityLivingBase entity;
    public Class<? extends EntityLivingBase> clazz;
    public float scale;
    public float yOffset;

    public EntityDrawable(Class<? extends EntityLivingBase> clazz, float scale, float yOff)
    {
        this.clazz = clazz;
        this.scale = scale;
        this.yOffset = yOff;
    }

    @Override
    public int getWidth()
    {
        return 0;
    }

    @Override
    public int getHeight()
    {
        return 0;
    }

    @Override
    public void draw(int x, int y)
    {
        if(entity == null || entity.getClass() != clazz)
        {
            try
            {
                entity = clazz.getDeclaredConstructor(World.class).newInstance(Minecraft.getMinecraft().world);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, yOffset, 0);
        DrawingTools.drawEntity(entity, x, y, scale, 30, -30, 0);
        GlStateManager.popMatrix();
    }
}
