package net.pearx.purmag.client;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

import javax.vecmath.Quat4f;

/**
 * Created by mrAppleXZ on 05.06.17 21:43.
 */
@SideOnly(Side.CLIENT)
/**
 * A simple tool for making your model's rotation directly in the game.
 */
public class Debugging
{
    private static int x, y, z;

    public static Quat4f getRotation()
    {
        boolean plus = Keyboard.isKeyDown(Keyboard.KEY_X);
        boolean min = Keyboard.isKeyDown(Keyboard.KEY_Z);
        if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL))
        {
            if(plus && y < 360)
            {
                y++;
            }
            if(min && y > -360)
            {
                y--;
            }
        }
        else if(Keyboard.isKeyDown(Keyboard.KEY_LMENU))
        {
            if(plus && z < 360)
            {
                z++;
            }
            if(min && z > -360)
            {
                z--;
            }
        }
        else
        {
            if(plus && x < 360)
            {
                x++;
            }
            if(min && x > -360)
            {
                x--;
            }
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_C))
        {
            x = 0;
            y = 0;
            z = 0;
        }
        return new Quat4f((float)Math.toRadians(x), (float)Math.toRadians(y), (float)Math.toRadians(z), 1);
    }
}
