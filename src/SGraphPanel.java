import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.List;

public class SGraphPanel extends JPanel {
    BufferedImage img = new BufferedImage(1000,1000, ColorModel.TRANSLUCENT);

    Boolean printing = true;

    ArrayList<Vector2> points = new ArrayList<Vector2>();

    public static ArrayList<Vector2> EqPoints = new ArrayList<Vector2>();


    @Override
    public void paintComponent(Graphics g){
        g.drawImage(img,0,0,this);
    }

    public void drawEqs(){
//        for(int i =  -400; i < 400; i++){
//            points.add(new Vector2(i,0));
//        }
        CalculateEquations();

        if(printing) {
            SCalculateEquations();
        }

        Draw();
    }

    public void Draw(){
        Graphics2D g = (Graphics2D)img.getGraphics();
        g.clearRect(0,0,1000,1000);
        g.setColor(Color.red);
        if(points.size()>1){
            for (int i = 0; i < points.size(); i++){
                Vector2 v = points.get(i);

                g.drawRect((int)v.x+500,(int)v.y+500,1,1);// TODO: remove these magic numbers for scaling
                if(i+1<points.size()){
                    Vector2 v2 = points.get(i+1);
                    g.drawLine((int)v.x+500,(int)v.y+500,(int)v2.x+500,(int)v2.y+500);
                }

            }
        }



        g.setColor(Color.blue);
        for(Vector2 point : EqPoints){
            g.fillOval((int)point.x+500-5,(int)point.y+500-5,10,10);
        }



        repaint();
    }

    public void CalculateEquations(){
        points.clear();

        for(float l = -500; l < 500; l+= 0.1f){
            float count = 0;
            int n = EqPoints.size()-1;

            for (int i = 0; i <= n; i++){
                Vector2 point = EqPoints.get(i);
                count += POfJ(n,l,i) * point.y;
            }

            float y = count;
            points.add(new Vector2(l,y));
        }

    }

    public float POfJ(int n, float x, int i){
        float count = 1;
        for(int j = 0; j <= n; j++){
            if(j==i){
                continue;
            }
            count *= ((x - EqPoints.get(j).x)/(EqPoints.get(i).x - EqPoints.get(j).x));
        }
        return  count;
    }


    public void SCalculateEquations(){
            String count = "y = ";
            int n = EqPoints.size()-1;

            for (int i = 0; i <= n; i++){
                Vector2 point = EqPoints.get(i);
                count +=  SPOfJ(n,i) + "*" + point.y  + "  +  ";
            }


            System.out.println(count);


    }

    public String SPOfJ(int n, int i){
        String count = "";
        for(int j = 0; j <= n; j++){
            if(j==i){
                continue;
            }
            count += "((x - " + EqPoints.get(j).x + ")) / ("+EqPoints.get(i).x + " - " + EqPoints.get(j).x + ")";
        }
        return  count;
    }


}

