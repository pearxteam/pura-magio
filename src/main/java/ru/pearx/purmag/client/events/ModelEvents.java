package ru.pearx.purmag.client.events;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.libmc.client.models.IPXModel;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.models.StandardModels;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.infofield.IfTier;
import ru.pearx.purmag.common.magibench.MagibenchRegistry;

/**
 * Created by mrAppleXZ on 09.04.17 15:36.
 */
@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = PurMag.MODID, value = Side.CLIENT)
public class ModelEvents
{
    @SubscribeEvent
    public static void onBake(ModelBakeEvent e)
    {
        putModel(e, new StandardModels.Crystal(), Utils.gRL("crystal"));
        putModel(e, new StandardModels.CrystalGlass(), Utils.gRL("crystal_glass"));
        putModel(e, new StandardModels.Glove(), Utils.gRL("glove"));
        putModel(e, new StandardModels.TranslationDesk(), Utils.gRL("translation_desk"));
        putModel(e, new StandardModels.CrystalSmall(), Utils.gRL("crystal_small"));
        StandardModels.WallIfTablet wit = new StandardModels.WallIfTablet();
        putModel(e, wit, Utils.gRL("wall_if_tablet"));
        putModel(e, wit, Utils.gRL("broken_wall_if_tablet"));
        putModel(e, new StandardModels.Microscope(), Utils.gRL("microscope"));
        putModel(e, new StandardModels.LuminousCrystalGlass(), Utils.gRL("luminous_crystal_glass"));
        putModel(e, new StandardModels.Test(), Utils.gRL("test"));
        putModel(e, new StandardModels.CodeStorage(), Utils.gRL("code_storage"));
        putModel(e, new StandardModels.CodeStorage.Top(), Utils.gRL("code_storage/top"));
        putModel(e, new StandardModels.CodeStorage.Body(), Utils.gRL("code_storage/body"));
        for(MagibenchRegistry.Tier t : PurMag.INSTANCE.getMagibenchRegistry().getTiers())
        {
            putModel(e, new StandardModels.Magibench(t), t.getSmartModel());
        }
    }

    private static void putModel(ModelBakeEvent e, IPXModel model, ResourceLocation loc)
    {
        model.bake();
        e.getModelRegistry().putObject(new ModelResourceLocation(loc, "normal"), model);
    }

    @SubscribeEvent
    public static void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PurMag.MODID, "models/crystal"));
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        e.getMap().registerSprite(new ResourceLocation(PurMag.MODID, "blocks/crystal_glass/" + i + j + k + l));
        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                for (int k = 0; k < 2; k++)
                    for (int l = 0; l < 2; l++)
                        e.getMap().registerSprite(new ResourceLocation(PurMag.MODID, "blocks/luminous_crystal_glass/" + i + j + k + l));
        e.getMap().registerSprite(Utils.gRL("models/glove"));
        e.getMap().registerSprite(Utils.gRL("particle/sip"));
        e.getMap().registerSprite(Utils.gRL("models/crystal_small"));
        e.getMap().registerSprite(Utils.gRL("models/translation_desk"));
        for (IfTier t : PurMag.INSTANCE.getIfRegistry().tiers)
            e.getMap().registerSprite(t.getWallTabletTexture());
        e.getMap().registerSprite(Utils.gRL("models/microscope"));
        e.getMap().registerSprite(Utils.gRL("models/code_storage/code_storage"));
        e.getMap().registerSprite(Utils.gRL("models/code_storage/lock_locked"));
        e.getMap().registerSprite(Utils.gRL("models/code_storage/lock_unlocked"));
        for(MagibenchRegistry.Tier t :  PurMag.INSTANCE.getMagibenchRegistry().getTiers())
            e.getMap().registerSprite(t.getModelTexture());
    }
}
