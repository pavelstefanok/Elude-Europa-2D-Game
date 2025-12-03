package PaooGame.Input;

import java.awt.event.*;

public class Controls implements KeyListener, MouseListener, MouseMotionListener {

    public static boolean W_pressed;
    public static boolean S_pressed;
    public static boolean D_pressed;
    public static boolean A_pressed;
    public static boolean E_pressed;
    public static boolean Left_pressed;
    public static boolean Right_pressed;
    public static boolean Up_pressed;
    public static boolean Down_pressed;
    public static boolean Space_pressed;
    public static boolean Enter_pressed;

    public static boolean Mouse_pressed;
    public static boolean Mouse_hover;
    public static int Mouse_Click_x;
    public static int Mouse_Click_y;
    public static int Mouse_Position_x;
    public static int Mouse_Position_y;
    public static boolean Mouse_drag;

    @Override
    public void mouseClicked(MouseEvent e) {}    @Override



    public void mousePressed(MouseEvent e) {
        Mouse_Click_x = e.getX();
        Mouse_Click_y = e.getY();
        Mouse_pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Mouse_pressed = false;
        Mouse_drag = false;
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        Mouse_hover = true;
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        Mouse_hover = false;
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
        Mouse_Position_x =e.getX();
        Mouse_Position_y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Mouse_Position_x = e.getX();
        Mouse_Position_y = e.getY();
        Mouse_drag=true;
    }

    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)
        {
            W_pressed=true;
        }
        if (key == KeyEvent.VK_S)
        {
            S_pressed=true;
        }
        if (key == KeyEvent.VK_A)
        {
            A_pressed=true;
        }
        if (key == KeyEvent.VK_D)
        {
            D_pressed=true;
        }
        if (key == KeyEvent.VK_E)
        {
            E_pressed = true;
        }
        if (key == KeyEvent.VK_LEFT)
        {
            Left_pressed=true;
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            Right_pressed=true;
        }
        if (key == KeyEvent.VK_DOWN)
        {
            Down_pressed=true;
        }
        if (key == KeyEvent.VK_UP)
        {
            Up_pressed=true;
        }
        if (key == KeyEvent.VK_SPACE)
        {
            Space_pressed=true;
        }
        if (key == KeyEvent.VK_ENTER)
        {
            Enter_pressed=true;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W)
        {
            W_pressed=false;
        }
        if (key == KeyEvent.VK_S)
        {
            S_pressed=false;
        }
        if (key == KeyEvent.VK_A)
        {
            A_pressed=false;
        }
        if (key == KeyEvent.VK_D)
        {
            D_pressed=false;
        }
        if (key == KeyEvent.VK_D)
        {
            E_pressed=false;
        }
        if (key == KeyEvent.VK_LEFT)
        {
            Left_pressed=false;
        }
        if (key == KeyEvent.VK_RIGHT)
        {
            Right_pressed=false;
        }
        if (key == KeyEvent.VK_DOWN)
        {
            Down_pressed=false;
        }
        if (key == KeyEvent.VK_UP)
        {
            Up_pressed=false;
        }
        if (key == KeyEvent.VK_SPACE)
        {
            Space_pressed=false;
        }
        if (key == KeyEvent.VK_ENTER)
        {
            Enter_pressed=false;
        }
    }

}
