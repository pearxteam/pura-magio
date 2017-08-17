package ru.pearx.purmag.common;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.pearx.lib.Colors;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.OverlayGui;
import ru.pearx.purmag.PurMag;
import ru.pearx.purmag.client.GuiDrawableRegistry;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mrAppleXZ on 26.04.17 22:51.
 */
public class DisplayMessage
{
    private String subject;
    private String description;
    private List<String> args;

    public DisplayMessage()
    {

    }

    public DisplayMessage(String subj, String desc, String... args)
    {
        setDescription(desc);
        setSubject(subj);
        setArgs(Arrays.asList(args));
    }

    @SideOnly(Side.CLIENT)
    public String formatSting(String s)
    {
        for (int i = 0; i < args.size(); i++)
        {
            String arg = getArgs().get(i);
            int ind = arg.indexOf(":");
            String type = arg.substring(0, ind);
            String cont = arg.substring(ind + 1);
            String localized = "";
            switch (type)
            {
                case "if_entry":
                    localized = PurMag.INSTANCE.if_registry.getEntry(cont).getDisplayName();
                    break;
                case "i18n":
                    localized = I18n.format(cont);
                    break;
            }
            s = s.replace("%" + i, localized);
        }
        return s;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<String> getArgs()
    {
        return args;
    }

    public void setArgs(List<String> args)
    {
        this.args = args;
    }

    public void writeToByteBuf(ByteBuf buf)
    {
        ByteBufUtils.writeUTF8String(buf, subject);
        ByteBufUtils.writeUTF8String(buf, description);
        buf.writeInt(args.size());
        for(String s : args)
        {
            ByteBufUtils.writeUTF8String(buf, s);
        }
    }

    public static DisplayMessage readFromByteBuf(ByteBuf buf)
    {
        DisplayMessage msg = new DisplayMessage();
        msg.setSubject(ByteBufUtils.readUTF8String(buf));
        msg.setDescription(ByteBufUtils.readUTF8String(buf));
        int size = buf.readInt();
        ArrayList<String> s = new ArrayList<>();
        for(int i = 0; i < size; i++)
        {
            s.add(ByteBufUtils.readUTF8String(buf));
        }
        msg.setArgs(s);
        return msg;
    }

    @SideOnly(Side.CLIENT)
    public void draw(int x, int y)
    {
        x += 3;
        y += 3;
        GlStateManager.pushMatrix();
        GlStateManager.translate(0, 0, 500);
        GlStateManager.enableBlend();
        GuiDrawableRegistry.displayMessage.draw(OverlayGui.INSTANCE, 0, 0);
        GlStateManager.disableBlend();
        DrawingTools.drawString(formatSting(getSubject()), x, y, Colors.YELLOW_500);
        DrawingTools.drawString(formatSting(getDescription()), x + 5, y + 11, Colors.WHITE);
        GlStateManager.popMatrix();
    }
}
