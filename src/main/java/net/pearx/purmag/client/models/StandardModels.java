package net.pearx.purmag.client.models;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IPerspectiveAwareModel;
import net.minecraftforge.common.model.TRSRTransformation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.Debugging;
import net.pearx.purmag.common.Utils;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.input.Keyboard;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;

/**
 * Created by mrAppleXZ on 17.05.17 8:04.
 */
@SideOnly(Side.CLIENT)
public class StandardModels
{
    public static class Crystal extends OvModelBase implements IPerspectiveAwareModel
    {
        public Crystal()
        {
            super(true);
            setBaseModel(new ResourceLocation(PurMag.ModId, "block/crystal.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            return Pair.of(this, new TRSRTransformation(new Vector3f(0, -0.25f, 0), null, new Vector3f(0.45f, 0.45f, 0.45f), new Quat4f(0, -0.20944f, 0, 1)).getMatrix());
        }
    }

    public static class CrystalGlass extends CTMModelBase
    {
        public CrystalGlass()
        {
            setBaseTexture(new ResourceLocation(PurMag.ModId, "blocks/crystal_glass"));
        }
    }

    public static class Glove extends OvModelBase implements IPerspectiveAwareModel
    {
        public Glove()
        {
            super(true);
            setBaseModel(Utils.getRegistryName("item/glove.obj"));
        }


        float xr, yr, zr;

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || cameraTransformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND)
            {
                return Pair.of(this, new TRSRTransformation(new Vector3f(0f, 0.2f, -0.1f), new Quat4f(0, 0, 45, 1), null, new Quat4f(0, 0, 180, 1)).getMatrix());
            }
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
            {
                return Pair.of(this, new TRSRTransformation(new Vector3f(0.15f, 0.15f, 0), null, new Vector3f(1.2f, 1.2f, 1.2f), new Quat4f(-1.64061f, -4.50295f, 0, 1)).getMatrix());
            }
            return Pair.of(this, new TRSRTransformation(null, null, null, null).getMatrix());
        }
    }

    public static class TranslationDesk extends FacingModel
    {
        public TranslationDesk()
        {
            super(true);
            setBaseModel(Utils.getRegistryName("block/translation_desk.obj"));
        }
    }

    public static class CrystalSmall extends OvModelBase implements IPerspectiveAwareModel
    {
        public CrystalSmall()
        {
            super(true);
            setBaseModel(Utils.getRegistryName("block/crystal_small.obj"));
        }

        @Override
        public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType)
        {
            if(cameraTransformType == ItemCameraTransforms.TransformType.GUI)
            {
                return Pair.of(this, new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, new Vector3f(1.5f, 1.5f, 1.5f), new Quat4f(0.349066f, 0.20944f, 0, 1)).getMatrix());
            }
            return Pair.of(this, new TRSRTransformation(new Vector3f(0, 0.35f, 0), null, null, null).getMatrix());
        }
    }
}
