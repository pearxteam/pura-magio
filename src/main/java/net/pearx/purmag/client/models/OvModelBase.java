package net.pearx.purmag.client.models;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelCustomData;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;
import net.minecraftforge.client.model.pipeline.LightUtil;
import net.minecraftforge.client.model.pipeline.UnpackedBakedQuad;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 10.04.17 8:55.
 */
@SideOnly(Side.CLIENT)
public class OvModelBase implements IModelBase
{
    public boolean flipV;

    public OvModelBase(boolean flipV)
    {
        this.flipV = flipV;
    }
    private ResourceLocation baseModel;

    public void setBaseModel(ResourceLocation loc)
    {
        baseModel = loc;
    }

    public ResourceLocation getBaseModel()
    {
        return baseModel;
    }

    private IBakedModel baked;

    protected IBakedModel getBaked()
    {
        return baked;
    }

    protected void setBaked(IBakedModel baked)
    {
        this.baked = baked;
    }

    @Override
    public void bake()
    {
        IModel mdl = null;
        try
        {
            mdl = ModelLoaderRegistry.getModel(getBaseModel());
            if(mdl instanceof IModelCustomData && flipV)
            {
                mdl = ((IModelCustomData) mdl).process(ImmutableMap.of("flip-v", "true"));
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        baked = mdl.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM,
                location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        List<BakedQuad> l = new ArrayList<>();
        /*if(flipV)
        {
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
                        if (q.getFormat().getElement(e).getUsage() == VertexFormatElement.EnumUsage.UV)
                        {
                            float v = 1 - (q.getSprite().getUnInterpolatedV(lst[1]) / 16);
                            lst[1] = q.getSprite().getInterpolatedV(v * 16);
                        }
                        bld.put(e, lst);
                    }
                }
                l.add(bld.build());
            }
        }*/
        for (BakedQuad quad : getBaked().getQuads(state, side, rand))
            l.add(new BakedQuad(quad.getVertexData(), 1, quad.getFace(), quad.getSprite(), quad.shouldApplyDiffuseLighting(), quad.getFormat()));
        return l;
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return getBaked().isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return getBaked().isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return getBaked().isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return getBaked().getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms()
    {
        return getBaked().getItemCameraTransforms();
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return getBaked().getOverrides();
    }
}
