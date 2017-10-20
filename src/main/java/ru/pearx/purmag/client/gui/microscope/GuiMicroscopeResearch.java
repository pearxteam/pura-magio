package ru.pearx.purmag.client.gui.microscope;

import net.minecraft.util.math.BlockPos;
import ru.pearx.lib.Color;
import ru.pearx.lib.Colors;
import ru.pearx.lib.math.MathUtils;
import ru.pearx.libmc.client.gui.DrawingTools;
import ru.pearx.libmc.client.gui.controls.Control;
import ru.pearx.purmag.common.infofield.IfEntry;
import ru.pearx.purmag.common.infofield.steps.IRSMicroscopeResearch;
import sun.java2d.loops.DrawGlyphList;

/*
 * Created by mrAppleXZ on 17.10.17 19:48.
 */
public class GuiMicroscopeResearch extends GuiAbstractMicroscope
{
    private BlockPos pos;
    private IRSMicroscopeResearch step;
    private String entryName;
    private Panel panel;

    public GuiMicroscopeResearch(BlockPos pos, IRSMicroscopeResearch step, String entryName)
    {
        this.pos = pos;
        this.step = step;
        this.entryName = entryName;
        panel = new Panel();
    }

    @Override
    public void init()
    {
        controls.add(panel);
    }

    @Override
    public void renderMainPart()
    {

    }

    public class Panel extends Control
    {
        private int buttSize = 20;
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
                boolean[] bools = new boolean[step.getPattern()[0].length];
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

            setSize(maxLeft + buttSize*step.getPattern()[0].length, maxUp + buttSize*step.getPattern().length);
            setPos(margin, margin);
        }

        @Override
        public void render()
        {
            int y = maxUp;
            for(String s : leftTexts)
            {
                DrawingTools.drawString(s, maxLeft - DrawingTools.measureString(s), y, Colors.WHITE);
                y += buttSize;
            }
            int x = maxLeft;
            for(String s : upTexts)
            {
                DrawingTools.drawString(s, x, maxUp - DrawingTools.getStringHeight(s), Colors.WHITE);
                x += buttSize;
            }

            for(int r = 0; r < current.length; r++)
            {
                for(int c = 0; c < current[0].length; c++)
                {
                    DrawingTools.drawGradientRect(maxLeft + r * buttSize, maxUp + c * buttSize, buttSize, buttSize, current[r][c] ? Colors.GREEN_500 : Colors.BLUE_500);
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
                int col = y / buttSize;
                int row = x / buttSize;
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
