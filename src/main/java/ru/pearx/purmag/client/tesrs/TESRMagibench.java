package ru.pearx.purmag.client.tesrs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;
import ru.pearx.purmag.common.tiles.TileMagibench;

/*
 * Created by mrAppleXZ on 04.11.17 13:44.
 */
public class TESRMagibench extends PXLFastTESR<TileMagibench>
{
    @Override
    public void render(TileMagibench te, double x, double y, double z, float partialTicks, int destroyStage, float alpha, BufferBuilder buffer, Tessellator tes)
    {

    }

    @Override
    public void renderPre(TileMagibench te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        MagibenchRegistry.Tier t = PurMag.INSTANCE.getMagibenchRegistry().getTier(te.getTier());
        int index = 0;
        float scale = Math.min(1f / t.getWidth(), 1f / t.getHeight());
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0, 0.5f);
        GlStateManager.rotate(te.getWorld().getBlockState(te.getPos()).getValue(HorizontalFacingController.FACING_H).getHorizontalAngle(), 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);
        GlStateManager.translate(0.16f, 1.09f, 0.16f);
        for (int yy = 0; yy < t.getHeight(); yy++)
        {
            for (int xx = 0; xx < t.getWidth(); xx++)
            {
                ItemStack stack = te.handler.getStackInSlot(index);
                if (!stack.isEmpty())
                {
                    GlStateManager.pushMatrix();
                    GlStateManager.scale(0.8f, 0.8f, 0.8f);
                    GlStateManager.translate(0.1f, 0, 0.1f);
                    GlStateManager.scale(scale, scale, scale);
                    GlStateManager.translate(xx, 0, yy);
                    if (!(stack.getItem() instanceof ItemBlock))
                    {
                        GlStateManager.translate(0, -0.22f, 0);
                        GlStateManager.rotate(90, 1, 0, 0);
                    }
                    Minecraft.getMinecraft().getRenderItem().renderItem(stack, ItemCameraTransforms.TransformType.FIXED);
                    GlStateManager.popMatrix();
                }
                index++;
            }
        }
        GlStateManager.popMatrix();
    }
}