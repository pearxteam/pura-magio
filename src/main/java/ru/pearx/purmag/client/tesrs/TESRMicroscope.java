package ru.pearx.purmag.client.tesrs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.pearx.libmc.client.ClientDebugger;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.purmag.common.tiles.TileMicroscope;

import javax.vecmath.Vector3f;

/*
 * Created by mrAppleXZ on 18.08.17 16:16.
 */
public class TESRMicroscope extends PXLFastTESR<TileMicroscope>
{
    @Override
    public void renderPre(TileMicroscope te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        ItemStack stack = te.handler.getStackInSlot(0);
        if(!stack.isEmpty())
        {
            GlStateManager.pushMatrix();
            GlStateManager.scale(0.25f, 0.25f, 0.25f);
            Vector3f v = ClientDebugger.getTranslation();
            if(stack.getItem() instanceof ItemBlock)
                GlStateManager.translate(1.97f, 1.27f, 2.12f);
            else
            {
                GlStateManager.translate(1.97f, 1.05f, 2.16f);
                GlStateManager.rotate(90, 1, 0, 0);
            }
            Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public void renderPost(TileMicroscope te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {

    }
}
