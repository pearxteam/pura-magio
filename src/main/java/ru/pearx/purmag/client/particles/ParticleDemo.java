package ru.pearx.purmag.client.particles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.particle.PXParticle;
import ru.pearx.libmc.client.particle.ParticleEngine;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 27.08.17 16:57.
 */
@Mod.EventBusSubscriber(value = Side.CLIENT)
@SideOnly(Side.CLIENT)
public class ParticleDemo extends PXParticle
{
    @SubscribeEvent
    public static void onKeyPress(InputEvent.KeyInputEvent e)
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_G))
        {
            ParticleEngine.addParticle(new ParticleDemo(Minecraft.getMinecraft().player.posX, Minecraft.getMinecraft().player.posY, Minecraft.getMinecraft().player.posZ));
        }
    }

    public ParticleDemo()
    {
    }

    public ParticleDemo(double x, double y, double z)
    {
        super(x, y, z);
    }

    @Override
    public double getWidth()
    {
        return 32;
    }

    @Override
    public double getHeight()
    {
        return 32;
    }

    @Override
    public void onRender()
    {
        GlStateManager.enableBlend();
        DrawingTools.drawTexture(Utils.getRegistryName("textures/particle/sip.png"), 0, 0, 32, 32);
        GlStateManager.disableBlend();
    }

    @Override
    public void onUpdate()
    {
        move(0, 0, 0);
        super.onUpdate();
    }
}
