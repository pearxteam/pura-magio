package net.pearx.purmag.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.common.model.TRSRTransformation;
import net.pearx.purmag.PMCore;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mrAppleXZ on 10.04.17 8:55.
 */
public class ObjModelBase implements IBakedModel
{
    private ResourceLocation objFile;

    public void setObj(String name)
    {
        objFile = new ResourceLocation(PMCore.ModId, name);
    }

    public ResourceLocation getObj()
    {
        return objFile;
    }

    private IBakedModel baked;

    protected IBakedModel getBaked()
    {
        if(baked == null)
        {
            IModel mdl = null;
            try
            {
                mdl = ModelLoaderRegistry.getModel(getObj());
            } catch (Exception e)
            {
                e.printStackTrace();
            }
            baked = mdl.bake(TRSRTransformation.identity(), DefaultVertexFormats.ITEM,
                    location -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(location.toString()));
        }
        return baked;
    }
    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand)
    {
        List<BakedQuad> l = new ArrayList<>();
        for(BakedQuad q : getBaked().getQuads(state, side, rand))
        {
            l.add(new BakedQuad(q.getVertexData(), 0, q.getFace(), q.getSprite()));
        }
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
