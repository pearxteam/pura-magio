package net.pearx.purmag.client.guis.controls;


import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.pearx.purmag.client.guis.PmGui;
import org.lwjgl.util.Point;
import org.lwjgl.util.Rectangle;

/**
 * Created by mrAppleXZ on 16.04.17 13:12.
 */
public class Control
{
    public ControlList controls = new ControlList(this);
    private Control parent;
    private int width;
    private int height;
    private int x;
    private int y;
    private boolean selected;
    private boolean focused;

    public boolean keyEventsRS = true;
    public SelectType selectType = SelectType.CLICK;

    public Control getParent()
    {
        return parent;
    }
    public void setParent(Control parent)
    {
        this.parent = parent;
    }

    public int getWidth()
    {
        return width;
    }
    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return height;
    }
    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getX()
    {
        return x;
    }
    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }
    public void setY(int y)
    {
        this.y = y;
    }

    public boolean isSelected()
    {
        return selected;
    }

    public boolean isFocused()
    {
        return focused;
    }

    public void setFocused(boolean val)
    {
        focused = val;
    }



    public void selectionChanged()
    {

    }

    public void render()
    {

    }

    public void postRender()
    {

    }

    public void keyDown(int keycode)
    {

    }

    public void keyUp(int keycode)
    {

    }

    public void keyPress(char key, int keycode)
    {

    }

    public void mouseDown(int button, int x, int y)
    {

    }

    public void mouseUp(int button, int x, int y)
    {

    }

    public void mouseMove(int x, int y, int dx, int dy)
    {

    }

    public void mouseEnter()
    {

    }

    public void mouseLeave()
    {

    }

    public void mouseWheel(int delta)
    {

    }

    public void invokeSelectionChanged()
    {
        selectionChanged();
    }

    public void invokeRender()
    {
        Point p = getPointOnGui();
        GlStateManager.pushMatrix();
        GlStateManager.translate(p.getX(), p.getY(), 0);
        render();
        GlStateManager.popMatrix();
        for(Control cont : controls)
        {
            cont.invokeRender();
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(p.getX(), p.getY(), 0);
        postRender();
        GlStateManager.popMatrix();
    }

    public void invokeKeyDown(int keycode)
    {
        for (Control cont : controls)
            cont.invokeKeyDown(keycode);
        if (!keyEventsRS || isSelected())
            keyDown(keycode);
    }

    public void invokeKeyUp(int keycode)
    {
        for (Control cont : controls)
            cont.invokeKeyUp(keycode);
        if (!keyEventsRS || isSelected())
            keyUp(keycode);
    }

    public void invokeKeyPress(char key, int keycode)
    {
        for (Control cont : controls)
            cont.invokeKeyPress(key, keycode);
        if (!keyEventsRS || isSelected())
            keyPress(key, keycode);
    }

    public void invokeMouseDown(int button, int x, int y)
    {
        for (Control cont : controls)
        {
            if (new Rectangle(cont.getX(), cont.getY(), cont.getWidth(), cont.getHeight()).contains(x, y))
            {
                cont.invokeMouseDown(button, x - cont.getX(), y - cont.getY());
                return;
            }
        }
        if(selectType == SelectType.CLICK)
            select();
        mouseDown(button, x, y);
    }

    public void invokeMouseUp(int button, int x, int y)
    {
        for (Control cont : controls)
        {
            if (new Rectangle(cont.getX(), cont.getY(), cont.getWidth(), cont.getHeight()).contains(x, y))
            {
                cont.invokeMouseUp(button, x - cont.getX(), y - cont.getY());
                return;
            }
        }
        mouseUp(button, x, y);
    }

    public void invokeMouseMove(int x, int y, int dx, int dy)
    {
        for (Control cont : controls)
        {
            if (new Rectangle(cont.getX(), cont.getY(), cont.getWidth(), cont.getHeight()).contains(x, y))
            {
                cont.invokeMouseMove(x - cont.getX(), y - cont.getY(), dx, dy);
                return;
            }
            if (cont.isFocused())
            {
                cont.setFocused(false);
                cont.invokeMouseLeave();
            }
        }
        if (getParent() != null && getParent().isFocused())
        {
            getParent().setFocused(false);
            getParent().invokeMouseLeave();
        }
        if (!isFocused())
        {
            setFocused(true);
            invokeMouseEnter();
        }
        mouseMove(x, y, dx, dy);
    }

    public void invokeMouseEnter()
    {
        mouseEnter();
    }

    public void invokeMouseLeave()
    {
        mouseLeave();
    }

    public void invokeMouseWheel(int delta)
    {
        for (Control cont : controls)
        {
            cont.invokeMouseWheel(delta);
        }
        mouseWheel(delta);
    }

    public void select()
    {
        unselectAll(getMainParent(this));
        selected = true;
        invokeSelectionChanged();
    }

    public void unselect()
    {
        selected = false;
        invokeSelectionChanged();
    }

    public GuiControlContainer getMainGui()
    {
        if (this instanceof GuiControlContainer)
            return (GuiControlContainer) this;
        return (GuiControlContainer) getMainParent(this);
    }

    public PmGui getGuiScreen()
    {
        return getMainGui().getGs();
    }

    public static void unselectAll(Control c)
    {
        c.unselect();
        for (Control cont : c.controls)
        {
            unselectAll(cont);
        }
    }

    public static Control getMainParent(Control c)
    {
        if (c.getParent() != null)
            return getMainParent(c.getParent());
        return c;
    }

    public Point getPointOnGui()
    {
        return getPointOnGui(this, new Point());
    }

    private static Point getPointOnGui(Control c, Point now)
    {
        if (c != null)
        {
            return getPointOnGui(c.getParent(), new Point(c.getX() + now.getX(), c.getY() + now.getY()));
        }
        return now;
    }
}
