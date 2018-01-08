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

    @Override
    public void render(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer, Tessellator tessellator)
    {
        IBlockState st = te.getWorld().getBlockState(te.getPos());
        if(st.getBlock() != BlockRegistry.code_storage)
            return;

        GlStateManager.pushMatrix();
        resetTrans(te);
        GlStateManager.translate(0.5f, 0, 0.5f);
        GlStateManager.rotate(PXLMC.getHorizontalRotation(st.getValue(HorizontalFacingController.FACING_H)), 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);
        setTrans(te);

        long rnd = MathHelper.getPositionRandom(te.getPos());

        //body
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), BODY.get(), st, te.getPos(), buffer, false, rnd);
        tessellator.draw();

        //top
        GlStateManager.pushMatrix();
        String state = te.anim.getState();
        if(!state.equals("closed"))
        {
            resetTrans(te);
            GlStateManager.translate(0.5f, 0, 0.5f);
            GlStateManager.translate(0, 0.703f, 0.4457f);

            if(state.equals("opened"))
                GlStateManager.rotate(90, 1, 0, 0);
            if(state.equals("opening") || state.equals("closing"))
            {
                if(!te.anim_data.startedOpeningAnim)
                {
                    te.anim_data.animStartTime = System.currentTimeMillis();
                    te.anim_data.startedOpeningAnim = true;
                }
                int degrees = (int)((System.currentTimeMillis() - te.anim_data.animStartTime) / 5);
                if(degrees >= 90)
                {
                    degrees = 90;
                    te.anim.changeState(state.equals("opening") ? "opened" : "closed");
                    te.anim_data.startedOpeningAnim = false;
                }
                GlStateManager.rotate(state.equals("opening") ? degrees : 90 - degrees, 1, 0, 0);
            }

            GlStateManager.translate(0, -0.703f, -0.4457f);
            GlStateManager.translate(-0.5f, 0, -0.5f);
            setTrans(te);
        }
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        PXLModelRenderer.renderModelTESR(te.getWorld(), TOP.get(), st.getBlock().getExtendedState(st, te.getWorld(), te.getPos()), te.getPos(), buffer, false, rnd);
        tessellator.draw();
        GlStateManager.popMatrix();

        GlStateManager.popMatrix();
    }
}
