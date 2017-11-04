package ru.pearx.purmag.client.tesrs;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import ru.pearx.libmc.client.PXLFastTESR;
import ru.pearx.libmc.common.blocks.controllers.HorizontalFacingController;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.tiles.TileCodeStorage;

/*
 * Created by mrAppleXZ on 20.09.17 20:20.
 */
public class TESRCodeStorage extends PXLFastTESR<TileCodeStorage>
{
    public static final ModelResourceLocation MDL_BODY = new ModelResourceLocation(Utils.gRL("code_storage/body"), "normal");
    public static final ModelResourceLocation MDL_TOP = new ModelResourceLocation(Utils.gRL("code_storage/top"), "normal");

    @Override
    public void renderPre(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.5f, 0, 0.5f);
        EnumFacing face = te.getWorld().getBlockState(te.getPos()).getValue(HorizontalFacingController.FACING_H);
        int angle = -10;
        switch (face)
        {
            case NORTH:
                angle = 0;
                break;
            case WEST:
                angle = 90;
                break;
            case SOUTH:
                angle = 180;
                break;
            case EAST:
                angle = 270;
                break;
        }
        GlStateManager.rotate(angle, 0, 1, 0);
        GlStateManager.translate(-0.5f, 0, -0.5f);
    }

    @Override
    public void render(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float partial, BufferBuilder buffer, Tessellator tessellator)
    {
        BlockRendererDispatcher dis = Minecraft.getMinecraft().getBlockRendererDispatcher();
        IBakedModel body = dis.getBlockModelShapes().getModelManager().getModel(MDL_BODY);
        IBakedModel top = dis.getBlockModelShapes().getModelManager().getModel(MDL_TOP);

        //body
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);
        dis.getBlockModelRenderer().renderModel(te.getWorld(), body, te.getWorld().getBlockState(te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
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
        IBlockState st = te.getWorld().getBlockState(te.getPos());
        dis.getBlockModelRenderer().renderModel(te.getWorld(), top, st.getBlock().getExtendedState(st, te.getWorld(), te.getPos()), te.getPos(), buffer, false, MathHelper.getPositionRandom(te.getPos()));
        tessellator.draw();
        GlStateManager.popMatrix();
    }

    @Override
    public void renderPost(TileCodeStorage te, double x, double y, double z, float partialTicks, int destroyStage, float alpha)
    {
        GlStateManager.popMatrix();
    }
}
