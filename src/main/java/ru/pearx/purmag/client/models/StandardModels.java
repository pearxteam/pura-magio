package ru.pearx.purmag.client.models;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.models.*;
import ru.pearx.libmc.client.models.processors.FacingProcessor;
import ru.pearx.libmc.client.models.processors.IQuadProcessor;
import ru.pearx.libmc.client.models.processors.TintProcessor;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.blocks.AbstractWallIfTablet;
import org.apache.commons.lang3.tuple.Pair;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

/**
 * Created by mrAppleXZ on 17.05.17 8:04.
 */
@SideOnly(Side.CLIENT)
public class StandardModels
{
    public static class Crystal extends OvModel
    {
        public Crystal()
        {
            setBaseModel(new ResourceLocation(PurMag.MODID, "block/crystal.obj"));
            quadProcessors.add(new TintProcessor(0));
        }

        private Matrix4f mat = new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), new Quat4f(0, -0.20944f, 0, 1)).getMatrix();
        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            return Pair.of(this, mat);
        }
    }

    public static class CrystalGlass extends ConnectedModel
    {
        public CrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.MODID, "blocks/crystal_glass"));
            quadProcessors.add(new TintProcessor(0));
        }
    }

    public static class Glove extends OvModel
    {
        public Glove()
        {
            setBaseModel(Utils.getRegistryName("item/glove.obj"));
        }

        private Matrix4f mat_fp = new TRSRTransformation(new Vector3f(0f, 0.2f, -0.1f), new Quat4f(0, 0, 45, 1), null, new Quat4f(0, 0, 180, 1)).getMatrix();
        private Matrix4f mat_gui = new TRSRTransformation(new Vector3f(0.15f, 0.15f, 0), null, new Vector3f(1.2f, 1.2f, 1.2f), new Quat4f(-1.64061f, -4.50295f, 0, 1)).getMatrix();
        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            {
                return Pair.of(this, mat_fp);
            }
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
            {
                return Pair.of(this, mat_gui);
            }
            return Pair.of(this, null);
        }
    }

    public static class TranslationDesk extends OvModel
    {
        public TranslationDesk()
        {
            vertexProcessors.add(new FacingProcessor());
            setBaseModel(Utils.getRegistryName("block/translation_desk.obj"));
        }

        private Matrix4f mat_gui = new TRSRTransformation(null, null, new Vector3f(0.6f, 0.6f, 0.6f), new Quat4f(0.523599f, 3.92699f, 1.5708f, 1)).getMatrix();
        private Matrix4f mat = new TRSRTransformation(null, null, new Vector3f(0.375f, 0.375f, 0.375f), null).getMatrix();
        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, mat_gui);
            return Pair.of(this, mat);
        }
    }

    public static class CrystalSmall extends OvModel
    {
        public CrystalSmall()
        {
            setBaseModel(Utils.getRegistryName("block/crystal_small.obj"));
            quadProcessors.add(new TintProcessor(0));
        }

        private Matrix4f mat_gui = new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, new Vector3f(1.5f, 1.5f, 1.5f), new Quat4f(0.349066f, 0.20944f, 0, 1)).getMatrix();
        private Matrix4f mat = new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, null, null).getMatrix();
        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
                return Pair.of(this, mat_gui);
            return Pair.of(this, mat);
        }
    }

    public static class WallIfTablet extends OvModel
    {
        public WallIfTablet()
        {
            setBaseModel(Utils.getRegistryName("block/wall_if_tablet.obj"));
            vertexProcessors.add(new FacingProcessor());
            quadProcessors.add(new IQuadProcessor()
            {
                @Override
                public void process(List<BakedQuad> quads, @Nullable IBlockState state, @Nullable EnumFacing side, long rand, IPXModel model)
                {
                    int tier;
                    if (state != null && state instanceof IExtendedBlockState)
                        tier = ((IExtendedBlockState) state).getValue(AbstractWallIfTablet.IF_TIER);
                    else
                        tier = getStack().getMetadata();

                    for (int q = 0; q < quads.size(); q++)
                    {
                        String s = PurMag.INSTANCE.if_registry.getTier(tier).getWallTabletTexture().toString();
                        quads.set(q, new BakedQuadWNT(quads.get(q), Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(s)));
                    }
                }

                @Override
                public boolean processState()
                {
                    return true;
                }

                @Override
                public boolean processStack()
                {
                    return true;
                }
            });
        }

        public Matrix4f mat = new TRSRTransformation(new Vector3f(0, 0, -0.3f), null, new Vector3f(0.625f, 0.625f, 0.625f), null).getMatrix();
        public Matrix4f mat_gui = new TRSRTransformation(new Vector3f(0.3f, -0.2f, 0), TRSRTransformation.quatFromXYZDegrees(new Vector3f(30, 225, 0)), null, null).getMatrix();

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            switch (cameraTransformType)
            {
                case GUI:
                    return Pair.of(this, mat_gui);
            }
            return Pair.of(this, mat);
        }
    }
}
