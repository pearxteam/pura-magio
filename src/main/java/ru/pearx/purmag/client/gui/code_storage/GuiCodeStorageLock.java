package ru.pearx.purmag.client.gui.code_storage;

import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.libmc.client.gui.controls.common.TextBox;
import ru.pearx.purmag.common.Utils;

/*
 * Created by mrAppleXZ on 17.09.17 20:08.
 */
public class GuiCodeStorageLock extends GuiOnScreen
{
    public TextBox code = new TextBox(Utils.getResourceLocation("textures/gui/textbox.png"));

    public GuiCodeStorageLock()
    {
        setWidth(128);
        setHeight(64);
        code.setWidth(128);
        code.setMaxLength(128);
        code.setMaxRenderLength(32);
    }

    @Override
    public void init()
    {
        controls.add(code);
    }
}
