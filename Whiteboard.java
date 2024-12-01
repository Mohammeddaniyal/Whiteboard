import java.awt.*;
import java.awt.event. *;
import javax.swing.*;
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
private int prevX=-1;
private int prevY=-1;
public WhiteboardCanvas()
{
setBackground(Color.WHITE);
addMouseListener(new MouseAdapter(){
@Override
public void mousePressed(MouseEvent me)
{
prevX=me.getX();
prevY=me.getY();
}
});
addMouseMotionListener(new MouseAdapter(){
@Override
public void mouseDragged(MouseEvent me)
{
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
}
}
);
}
@Override
protected void paintComponent(Graphics g)
{
//call the super class ensure propers painting
super.paintComponent(g);
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