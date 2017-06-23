package net.pearx.purmag.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.pearx.purmag.PurMag;
import net.pearx.purmag.client.models.IModelBase;
import net.pearx.purmag.client.models.StandardModels;
import net.pearx.purmag.common.DisplayMessage;
import net.pearx.purmag.common.SoundRegistry;
import net.pearx.purmag.common.Utils;
import net.pearx.purmag.common.items.ItemSipAmulet;
import net.pearx.purmag.common.networking.NetworkManager;
import net.pearx.purmag.common.networking.packets.SPacketUseSipAmulet;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by mrAppleXZ on 09.04.17 15:36.
 */
@SideOnly(Side.CLIENT)
public class ClientEvents
{
    private Timer timerDM = new Timer();
    private boolean isDMDisplayed = false;

    @SubscribeEvent
    public void onBake(ModelBakeEvent e)
    {
        putModel(e, new StandardModels.Crystal(), Utils.getRegistryName("crystal"));
        putModel(e, new StandardModels.CrystalGlass(), Utils.getRegistryName("crystal_glass"));
        putModel(e, new StandardModels.Glove(), Utils.getRegistryName("glove"));
        putModel(e, new StandardModels.TranslationDesk(), Utils.getRegistryName("translation_desk"));
        putModel(e, new StandardModels.CrystalSmall(), Utils.getRegistryName("crystal_small"));
    }

    private void putModel(ModelBakeEvent e, IModelBase model, ResourceLocation loc)
    {
        model.bake();
        e.getModelRegistry().putObject(new ModelResourceLocation(loc, "normal"), model);
    }

    @SubscribeEvent
    public void stitch(TextureStitchEvent.Pre e)
    {
        e.getMap().registerSprite(new ResourceLocation(PurMag.ModId, "models/crystal"));
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < 2; k++)
                    for(int l = 0; l < 2; l++)
                        e.getMap().registerSprite(new ResourceLocation(PurMag.ModId, "blocks/crystal_glass/" + i + j + k + l));
        e.getMap().registerSprite(Utils.getRegistryName("models/glove"));
        e.getMap().registerSprite(Utils.getRegistryName("particle/sip"));
        e.getMap().registerSprite(Utils.getRegistryName("models/crystal_small"));

        e.getMap().registerSprite(Utils.getRegistryName("models/translation_desk/wood"));
        e.getMap().registerSprite(Utils.getRegistryName("models/translation_desk/crystal"));
        e.getMap().registerSprite(Utils.getRegistryName("models/translation_desk/panel"));
    }

    @SubscribeEvent
    public void renderMessage(RenderGameOverlayEvent.Pre e)
    {
        if(e.getType() == RenderGameOverlayEvent.ElementType.TEXT)
        {
            DisplayMessage msg = DisplayMessageQuery.getMessage();
            if (msg != null)
            {
                if(!isDMDisplayed)
                {
                    Minecraft.getMinecraft().player.playSound(SoundRegistry.NOTIFICATION, 1, 1);
                    timerDM.schedule(new TimerTask()
                    {
                        @Override
                        public void run()
                        {
                            isDMDisplayed = false;
                            DisplayMessageQuery.removeMessage();
                        }
                    }, 5000); //5 seconds
                }
                isDMDisplayed = true;
                GlStateManager.color(1, 1, 1);
                msg.draw(0, 0);
            }
        }
    }

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent e)
    {
        if(KeyBindings.USE_SIP_AMULET.isPressed())
        {
            if(ItemSipAmulet.checkForAmulet(Minecraft.getMinecraft().player))
            {
                NetworkManager.sendToServer(new SPacketUseSipAmulet());
            }
        }
    }

    @SubscribeEvent
    public void onTooltip(ItemTooltipEvent e)
    {
        //todo REMOVE DEBUG PRINT!
        int[] ids = OreDictionary.getOreIDs(e.getItemStack());
        for(int id : ids)
        {
            e.getToolTip().add(OreDictionary.getOreName(id));
        }
    }
}
