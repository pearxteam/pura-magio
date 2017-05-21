package net.pearx.purmag.client;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

/**
 * Created by mrAppleXZ on 21.05.17 18:28.
 */
@SideOnly(Side.CLIENT)
public class KeyBindings
{
    public static KeyBinding USE_SIP_AMULET;

    public static void setup()
    {
        USE_SIP_AMULET = new KeyBinding("key.use_sip_amulet", KeyConflictContext.IN_GAME, Keyboard.KEY_U, "key.categories.purmag");
        ClientRegistry.registerKeyBinding(USE_SIP_AMULET);
    }
}
