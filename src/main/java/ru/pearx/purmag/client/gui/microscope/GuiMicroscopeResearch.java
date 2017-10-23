package ru.pearx.purmag.client.gui.microscope;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.math.BlockPos;
import ru.pearx.lib.Color;
import ru.pearx.lib.Colors;
import ru.pearx.lib.math.MathUtils;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.PXLGui;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.libmc.client.gui.controls.common.Button;
import ru.pearx.purmag.client.PurMagClient;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.steps.IRSMicroscopeResearch;
import ru.pearx.purmag.common.networking.NetworkManager;
import ru.pearx.purmag.common.networking.packets.microscope.CPacketCheckMicroscopeResearchResponse;
import ru.pearx.purmag.common.networking.packets.microscope.SPacketCheckMicroscopeResearch;
import sun.java2d.loops.DrawGlyphList;

import java.util.Arrays;

/*
 * Created by mrAppleXZ on 17.10.17 19:48.
 */
public class GuiMicroscopeResearch extends GuiAbstractMicroscope
{
    private BlockPos pos;
    private IRSMicroscopeResearch step;
    private String entryName;
    private Panel panel;
    private Button btnCheck = new Button(PurMagClient.BUTTON_TEXTURE, I18n.format("misc.gui.microscope.check"), () ->
    {
        if (Arrays.deepEquals(panel.current, step.getPattern()))
        {
            NetworkManager.sendToServer(new SPacketCheckMicroscopeResearch(pos, panel.current, entryName));
        }
        else
        {
            setFailed();
        }
    });
    private String text = "";

    public GuiMicroscopeResearch(BlockPos pos, IRSMicroscopeResearch step, String entryName)
    {
        this.pos = pos;
        this.step = step;
        this.entryName = entryName;
        panel = new Panel();
        btnCheck.setSize(128, 32);
        btnCheck.setPos(getWidth() - margin - btnCheck.getWidth(), getHeight() - margin - btnCheck.getHeight());
    }

    @Override
    public void init()
    {
        controls.add(panel);
        controls.add(btnCheck);
    }

    @Override
    public void renderMainPart()
    {
        DrawingTools.drawString(text, margin, btnCheck.getY() + (btnCheck.getHeight() - DrawingTools.getStringHeight(text)) / 2, Colors.RED_500);
    }

    public void setFailed()
    {
        text = I18n.format("misc.gui.microscope.wrong");
    }

    public void handleResponsePacket(CPacketCheckMicroscopeResearchResponse p)
    {
        if(p.success)
            Minecraft.getMinecraft().displayGuiScreen(null);
        else
            setFailed();
    }

    public class Panel extends Control
    {
        private int buttSize;
        private int maxLeft;
        private int maxUp;
        private String[] upTexts;
        private String[] leftTexts;
        private boolean[][] current;

        public Panel()
        {
            upTexts = new String[step.getPattern()[0].length];
            leftTexts = new String[step.getPattern().length];
            for (int r = 0; r < step.getPattern().length; r++)
            {
                leftTexts[r] = createValuesString(step.getPattern()[r], ' ');
            }
            for (int c = 0; c < step.getPattern()[0].length; c++)
            {
                boolean[] bools = new boolean[step.getPattern().length];
                for(int i = 0; i < bools.length; i++)
                {
                    bools[i] = step.getPattern()[i][c];
                }
                upTexts[c] = createValuesString(bools, '\n');
            }
            int maxLeft = 0;
            int maxUp = 0;
            for(String s : leftTexts)
            {
                int i = DrawingTools.measureString(s);
                if(i > maxLeft)
                    maxLeft = i;
            }
            for(String s : upTexts)
            {
                int i = DrawingTools.getStringHeight(s);
                if(i > maxUp)
                    maxUp = i;
            }
            this.maxLeft = maxLeft;
            this.maxUp = maxUp;
            current = new boolean[step.getPattern().length][step.getPattern()[0].length];
            for(int r = 0; r < current.length; r++)
                for(int c = 0; c < current[0].length; c++)
                    current[r][c] = false;

            int bsH = (GuiMicroscopeResearch.this.getHeight() - maxUp - margin * 2 - 34) / step.getPattern().length;
            int bsW = (GuiMicroscopeResearch.this.getWidth() - maxLeft - margin * 2) / step.getPattern().length;
            buttSize = Math.min(bsH, bsW);
            setSize(maxLeft + buttSize*step.getPattern()[0].length, maxUp + buttSize*step.getPattern().length);
            setPos((GuiMicroscopeResearch.this.getWidth() - getWidth()) / 2 - maxLeft, (GuiMicroscopeResearch.this.getHeight() - getHeight() - 34) / 2);
        }

        @Override
        public void render()
        {
            int y = maxUp;
            for(String s : leftTexts)
            {
                DrawingTools.drawString(s, maxLeft - DrawingTools.measureString(s), y + (buttSize - DrawingTools.getStringHeight(s)) / 2, Colors.WHITE);
                y += buttSize;
            }
            int x = maxLeft;
            for(String s : upTexts)
            {
                DrawingTools.drawString(s, x + (buttSize - DrawingTools.measureString(s)) / 2, maxUp - DrawingTools.getStringHeight(s), Colors.WHITE);
                x += buttSize;
            }

            for(int r = 0; r < current.length; r++)
            {
                for(int c = 0; c < current[0].length; c++)
                {
                    int xx = maxLeft + c * buttSize;
                    int yy = maxUp + r * buttSize;
                    DrawingTools.drawGradientRect(xx, yy, buttSize, buttSize, current[r][c] ? Colors.GREEN_500 : Colors.BLUE_500);
                    DrawingTools.drawGradientRect(xx, yy, buttSize, 1, Colors.GREY_800);
                    DrawingTools.drawGradientRect(xx, yy + buttSize - 1, buttSize, 1, Colors.GREY_800);
                    DrawingTools.drawGradientRect(xx, yy, 1, buttSize, Colors.GREY_800);
                    DrawingTools.drawGradientRect(xx + buttSize - 1, yy, 1, buttSize, Colors.GREY_800);
                }
            }
        }

        @Override
        public void mouseDown(int button, int x, int y)
        {
            x = x - maxLeft;
            y = y - maxUp;
            if(x > 0 && y > 0)
            {
                int col = x / buttSize;
                int row = y / buttSize;
                current[row][col] = !current[row][col];
            }
        }

        private String createValuesString(boolean[] bools, char spacer)
        {
            StringBuilder sb = new StringBuilder();
            int count = 0;
            for(int i = 0; i < bools.length; i++)
            {
                if(bools[i])
                    count++;
                if(!bools[i] || i == bools.length - 1)
                {
                    if(count > 0)
                    {
                        sb.append(count);
                        sb.append(spacer);
                        count = 0;
                    }
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            return sb.toString();
        }
    }
}
