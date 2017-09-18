package ru.pearx.purmag.client.gui.code_storage;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.GuiOnScreen;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.libmc.client.gui.controls.common.TextBox;
import ru.pearx.purmag.common.Utils;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.code_storage.CPacketUnlockResponse;
import ru.pearx.purmag.common.networking.packets.code_storage.SPacketLock;
import ru.pearx.purmag.common.networking.packets.code_storage.SPacketUnlock;

/*
 * Created by mrAppleXZ on 18.09.17 22:59.
 */
@SideOnly(Side.CLIENT)
public class GuiCodeStorageUnlock extends GuiOnScreen
{
    private BlockPos pos;
    private String text;
    private int margin = 16;

    private boolean wrong = false;

    public TextBox code = new TextBox(Utils.getResourceLocation("textures/gui/textbox.png"));
    public Button done = new Button(Utils.getResourceLocation("textures/gui/button.png"), I18n.format("misc.gui.code_storage.unlock.unlock"), () ->
            NetworkManager.sendToServer(new SPacketUnlock(code.getBuffer().toString(), pos)));

    public GuiCodeStorageUnlock(BlockPos pos, String text)
    {
        this.pos = pos;
        this.text = text;

        setWidth(288);
        setHeight(128);
        code.setWidth(256);
        code.setMaxRenderLength(64);
        code.setPos(margin, margin + DrawingTools.getFontHeight() + 8 + DrawingTools.getFontHeight() + 2);

        done.setSize(getWidth() - margin * 2, 24);
        done.setPos(margin, getHeight() - margin - done.getHeight());
    }

    @Override
    public void render()
    {
        DrawingTools.drawString(text, margin, margin, Colors.WHITE);
        DrawingTools.drawString(I18n.format("misc.gui.code_storage.unlock.code"), margin, margin + DrawingTools.getFontHeight() + 8, Colors.WHITE);
        if(wrong)
            DrawingTools.drawString(I18n.format("misc.gui.code_storage.unlock.wrong"), margin, code.getY() + code.getHeight() + 2, Colors.RED_600);
    }

    public void handleResponsePacket(CPacketUnlockResponse packet)
    {
        if(packet.success)
            Minecraft.getMinecraft().displayGuiScreen(null);
        else
            wrong = true;
    }

    @Override
    public void init()
    {
        controls.add(code);
        controls.add(done);
    }
}
