package net.pearx.purmag.client.guis.if_tablet;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.pearx.purmag.common.infofield.IfEntry;
import org.lwjgl.input.Keyboard;

/**
 * Created by mrAppleXZ on 30.04.17 21:25.
 */
@SideOnly(Side.CLIENT)
public class GuiIfTabletSR extends GuiIfTabletS
{
    public GuiIfTabletSR(IfEntry entr)
    {
        keyEventsRS = false;
    }

    @Override
    public void keyUp(int keycode)
    {
        if(keycode == Keyboard.KEY_BACK)
        {
            getTablet().changeScreen(getTablet().se);
        }
    }
}
