package ru.pearx.purmag.client.gui.code_storage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.libmc.client.gui.controls.common.TextBox;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.code_storage.SPacketLock;

/*
 * Created by mrAppleXZ on 17.09.17 20:08.
 */
@SideOnly(Side.CLIENT)
public class GuiCodeStorageLock extends GuiCodeStorageLockUnlock
{
    private BlockPos pos;

    public TextBox text = new TextBox(Utils.getResourceLocation("textures/gui/textbox.png"));
    public TextBox code = new TextBox(Utils.getResourceLocation("textures/gui/textbox.png"));
    public Button done = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.code_storage.lock.lock"), () ->
    {
        NetworkManager.sendToServer(new SPacketLock(text.getBuffer().toString(), code.getBuffer().toString(), pos));
        Minecraft.getMinecraft().displayGuiScreen(null);
    });

    public GuiCodeStorageLock(BlockPos pos)
    {
        this.pos = pos;

        setWidth(288);
        setHeight(128);
        text.setWidth(256);
        text.setMaxRenderLength(64);
        text.setPos(margin, margin + DrawingTools.getFontHeight() + 2);
        code.setWidth(256);
        code.setMaxRenderLength(64);
        code.setPos(margin, text.getY() + margin + DrawingTools.getFontHeight() + 2);
        done.setSize(getWidth() - margin * 2, 24);
        done.setPos(margin, getHeight() - margin - done.getHeight());
    }

    @Override
    public void render()
    {
        super.render();
        DrawingTools.drawString(I18n.format("misc.gui.code_storage.lock.text"), margin, margin, Colors.WHITE);
        DrawingTools.drawString(I18n.format("misc.gui.code_storage.lock.code"), margin, text.getY() + margin, Colors.WHITE);
    }

    @Override
    public void init()
    {
        controls.add(text);
        controls.add(code);
        controls.add(done);
    }
}
