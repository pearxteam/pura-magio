package net.pearx.purmag.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.TRSRTransformation;
import net.pearx.purmag.common.blocks.controllers.FacingController;
import org.lwjgl.util.vector.Vector3f;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 04.06.17 11:31.
 */
public class FacingModel extends OvModelBase
{
    public FacingModel(boolean flipV)
    {
        super(flipV);
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        if (state != null)
        {
            EnumFacing face = state.getValue(FacingController.FACING);
            Matrix4f trans = TRSRTransformation.getMatrix(face);
            List<BakedQuad> quads = new ArrayList<>();
            for (BakedQuad q : getBaked().getQuads(state, side, rand))
            {
                UnpackedBakedQuad.Builder bld = new UnpackedBakedQuad.Builder(q.getFormat());
                bld.setQuadTint(1);
                bld.setQuadOrientation(q.getFace());
                bld.setTexture(q.getSprite());
                bld.setApplyDiffuseLighting(q.shouldApplyDiffuseLighting());
                for (int i = 0; i < 4; i++)
                {
                    for (int e = 0; e < q.getFormat().getElementCount(); e++)
                    {
                        float[] lst = new float[q.getFormat().getElement(e).getElementCount()];
                        LightUtil.unpack(q.getVertexData(), lst, q.getFormat(), i, e);
                        if (q.getFormat().getElement(e).getUsage() == VertexFormatElement.EnumUsage.POSITION)
                        {
                            Vector3f vec = new Vector3f(lst[0], lst[1], lst[2]);
                            ForgeHooksClient.transform(vec, trans);
                            lst[0] = vec.x;
                            lst[1] = vec.y;
                            lst[2] = vec.z;
                        }
                        bld.put(e, lst);
                    }
                }
                quads.add(bld.build());
            }
            return quads;
        }
        return super.getQuads(state, side, rand);
    }
}
