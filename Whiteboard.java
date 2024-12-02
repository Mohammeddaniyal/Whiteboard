import java.awt.*;
import java.awt.event. *;
import javax.swing.*;
import java.util.*;
class MyDrawingPad extends Canvas
{
private int lastX,lastY;
MyDrawingPad()
{
setBackground(Color.WHITE);
setForeground(Color.RED);
}
public boolean mouseDown(Event e,int x,int y)
{
System.out.println("mouse down");
lastX=x;
lastY=y;
return true;
}
public boolean mouseDrag(Event e,int x,int y)
{
Graphics g=getGraphics();
g.drawLine(lastX,lastY,x,y);
lastX=x;
lastY=y;
return true;
}
}
class WhiteboardCanvas extends JPanel
{
private ArrayList<Point> points;
private int prevX=-1;
private int prevY=-1;
public WhiteboardCanvas()
{
points=new ArrayList<>();
setBackground(Color.WHITE);
addMouseListener(new MouseAdapter(){
@Override
 public void mousePressed(MouseEvent me)
{
points.add(me.getPoint());
repaint();
//prevX=me.getX();
//prevY=me.getY();
}
});
addMouseMotionListener(new MouseAdapter(){
@Override
public void mouseDragged(MouseEvent me)
{
points.add(me.getPoint());
repaint();
/*
int currentX=me.getX();
int currentY=me.getY();
if(prevX!=-1 && prevY!=1) 
{
Graphics g=getGraphics();
g.setColor(Color.BLACK);
g.drawLine(prevX,prevY,currentX,currentY);
prevX=currentX;
prevY=currentY;
}
*/
}
}

);
}
@Override
protected void paintComponent(Graphics g)
{
//call the super class ensure propers painting
super.paintComponent(g);
Graphics2D g2d=(Graphics2D)g;
g2d.setColor(Color.BLACK);
g2d.setStroke(new BasicStroke(2));//line thickness
//draw lines between consecutive points
for(int i=1;i<points.size();i++)
{
Point p1=points.get(i-1);
Point p2=points.get(i);
g2d.drawLine(p1.x,p1.y,p2.x,p2.y);
}
}

}
public class Whiteboard extends JFrame
{
private Container container;
//private JPanel canvasPanel;
//private MyDrawingPad myDrawingPad;
private WhiteboardCanvas canvas;
public Whiteboard()
{
container=getContentPane();
setTitle("My Whiteboard");
setLayout(new BorderLayout());
//canvasPanel=new JPanel();
//myDrawingPad=new MyDrawingPad();
//canvasPanel.add(myDrawingPad);
canvas=new WhiteboardCanvas();
container.add(canvas,BorderLayout.CENTER);
int width=800;
int height=600;
setSize(width,height);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
int x=(d.width/2)-(width/2);
int y=(d.height/2)-(height/2);
setLocation(x,y);
setVisible(true);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public static void main(String gg[])
{
Whiteboard wb=new Whiteboard();
}
}