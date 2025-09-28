import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Platformer2D extends JPanel implements ActionListener, KeyListener {
    int px=60, py=400, vx=0, vy=0;
    boolean left, right, jump;
    Rectangle[] platforms = {
        new Rectangle(0,460,640,40),
        new Rectangle(80,340,120,16),
        new Rectangle(280,280,100,16),
        new Rectangle(420,200,140,16)
    };
    Timer timer=new Timer(16,this);

    public Platformer2D() {
        JFrame f=new JFrame("2D Platformer");
        f.setSize(640,540); f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(this); f.setVisible(true);
        f.addKeyListener(this); timer.start();
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan); g.fillRect(0,0,640,540);
        g.setColor(Color.orange); g.fillRect(px,py,32,32);
        g.setColor(Color.green.darker());
        for(Rectangle p:platforms) g.fillRect(p.x,p.y,p.width,p.height);
    }
    public void actionPerformed(ActionEvent e) {
        vx=0;
        if(left)vx=-5;if(right)vx=5;
        vy+=1;
        px+=vx;py+=vy;
        for(Rectangle p:platforms){
            if(new Rectangle(px,py,32,32).intersects(p)){
                if(vy>0){py=p.y-32;vy=0;jump=false;}
                else if(vy<0){py=p.y+p.height;vy=0;}
            }
        }
        if(py>500){px=60;py=400;}
        repaint();
    }
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)left=true;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)right=true;
        if(e.getKeyCode()==KeyEvent.VK_SPACE&&!jump){vy=-16;jump=true;}
    }
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT)left=false;
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)right=false;
    }
    public void keyTyped(KeyEvent e){}
    public static void main(String[] args){ new Platformer2D(); }
}