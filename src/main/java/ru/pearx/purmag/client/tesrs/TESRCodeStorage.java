package ru.pearx.purmag.client.tesrs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import ru.pearx.libmc.PXLMC;
import ru.pearx.libmc.client.ModelSupplied;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.libmc.client.models.PXLModelRenderer;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.BlockRegistry;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

/*
 * Created by mrAppleXZ on 20.09.17 20:20.
 */
public class TESRCodeStorage extends PXLFastTESR<TileCodeStorage>
{
    public static final ModelSupplied BODY = new ModelSupplied(new ModelResourceLocation(Utils.gRL("code_storage/body"), "normal"));
    public static final ModelSupplied TOP = new ModelSupplied(new ModelResourceLocation(Utils.gRL("code_storage/top"), "normal"));
    public static final int ANIMATION_DURATION = 400;

    @Override
    public void render(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer, Tessellator tessellator)
    {
        IBlockState st = te.getWorld().getBlockState(te.getPos());
        if(st.getBlock() != BlockRegistry.code_storage)
            return;

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0, 0.5f);
        GlStateManager.rotate(PXLMC.getHorizontalRotation(st.getValue(HorizontalFacingController.FACING_H)), 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);

        long rnd = MathHelper.getPositionRandom(te.getPos());

        //body
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), BODY.get(), st, te.getPos(), buffer, false, rnd);
        tessellator.draw();

        //top
        GlStateManager.pushMatrix();
        double delta = (System.currentTimeMillis() - te.getOpenTime());
        delta += delta / 4;
        if(delta >= 0)
        {
            float degrees;
            if(delta >= ANIMATION_DURATION)
            {
                degrees = te.isOpened() ? 90 : 0;
            }
            else
            {

                float percentage = ((float)delta / (float)ANIMATION_DURATION);
                degrees = te.isOpened() ? 90 * percentage : 90 - 90*percentage;
            }
            GlStateManager.translate(0.5f, 0, 0.5f);
            GlStateManager.translate(0, 0.703f, 0.4457f);
            GlStateManager.rotate(degrees, 1, 0, 0);
            GlStateManager.translate(0, -0.703f, -0.4457f);
            GlStateManager.translate(-0.5f, 0, -0.5f);
        }
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), TOP.get(), st.getBlock().getExtendedState(st, te.getWorld(), te.getPos()), te.getPos(), buffer, false, rnd);
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
    }
}
