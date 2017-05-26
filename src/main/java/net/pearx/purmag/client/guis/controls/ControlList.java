package net.pearx.purmag.client.guis.controls;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by mrAppleXZ on 16.04.17 13:12.
 */
@SideOnly(Side.CLIENT)
public class ControlList implements Collection<Control>
{
    private ConcurrentLinkedQueue<Control> lst = new ConcurrentLinkedQueue<>();

    public Control parent;

    public ControlList(Control parent)
    {
        this.parent = parent;
    }

    @Override
    public int size()
    {
        return lst.size();
    }

    @Override
    public boolean isEmpty()
    {
        return lst.isEmpty();
    }

    @Override
    public boolean contains(Object o)
    {
        return lst.contains(o);
    }

    @Override
    public Iterator<Control> iterator()
    {
        return lst.iterator();
    }

    @Override
    public Object[] toArray()
    {
        return lst.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts)
    {
        return lst.toArray(ts);
    }

    @Override
    public boolean add(Control control)
    {
        control.setParent(parent);
        boolean bool = lst.add(control);
        control.invokeInit();
        return bool;
    }

    @Override
    public boolean remove(Object o)
    {
        if(o instanceof Control)
            ((Control)o).setParent(null);
        return lst.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection)
    {
        return lst.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends Control> collection)
    {
        for (Control c : collection)
            c.setParent(parent);
        return lst.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection)
    {
        for(Object o : collection)
            if(o instanceof Control)
                ((Control)o).setParent(null);
        return lst.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection)
    {
        for(Control c : this)
        {
            if(!collection.contains(c))
            {
                c.setParent(null);
            }
        }
        return lst.retainAll(collection);
    }

    @Override
    public void clear()
    {
        for (Control c : this)
            c.setParent(null);
        lst.clear();
    }
}
