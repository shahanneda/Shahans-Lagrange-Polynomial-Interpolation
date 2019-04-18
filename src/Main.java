import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {

    public static SGraphPanel g;
    public static void main(String []args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,1000);

        g = new SGraphPanel();
        g.drawEqs();
        frame.add(g);

        frame.setVisible(true);
        JFrame pointsFrame = new JFrame();
        pointsFrame.setSize(500,500);
        JPanel optWrapper = new JPanel(new BorderLayout());

        JButton newPoint = new JButton("New Point");
        JButton removePoint = new JButton("Remove Point");


        optWrapper.add(newPoint,BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(5,1));
        frame.setAlwaysOnTop(true);
        pointsFrame.setAlwaysOnTop(true);
        pointsFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newPoint.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JPanel xy = xyPairGen(SGraphPanel.EqPoints.size());
                optionsPanel.add(xy);
                optionsPanel.repaint();
                optionsPanel.validate();

            }
        });

        removePoint.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = optionsPanel.getComponentCount()-1;
                optionsPanel.remove(index);
                optionsPanel.repaint();
                SGraphPanel.EqPoints.remove(index);
                g.drawEqs();
                optionsPanel.validate();
            }
        });
        optWrapper.add(removePoint,BorderLayout.SOUTH);
//        JPanel xy2 = xyPairGen(2);


//        optionsPanel.add(xy2);

        optWrapper.add(optionsPanel,BorderLayout.CENTER);
        pointsFrame.add(optWrapper);
        pointsFrame.setVisible(true);




        g.drawEqs();


    }

    private static JPanel xyPairGen(int num){
        SpinnerModel xmodel = new SpinnerNumberModel(0,-500,500,1);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        SGraphPanel.EqPoints.add(new Vector2(0,0));
        final int myIndex = SGraphPanel.EqPoints.size()-1;


        JPanel x = new JPanel();
        JSpinner xspinner = new JSpinner(xmodel);
        xspinner.addChangeListener(e -> {
            JSpinner s = (JSpinner) e.getSource();
            SGraphPanel.EqPoints.get(myIndex).x = (int)s.getValue();
            g.drawEqs();


        });
        x.add(xspinner);
//        x.setBorder(BorderFactory.createTitledBorder("Point " + num+ " X:"));

        SpinnerModel ymodel = new SpinnerNumberModel(0,-500,500,1);
        JPanel y = new JPanel();
        JSpinner yspinner = new JSpinner(ymodel);
        yspinner.addChangeListener(e -> {
            JSpinner s = (JSpinner) e.getSource();
            SGraphPanel.EqPoints.get(myIndex).y = (int)s.getValue();//TODO: REMOVE THIS HOROR
            g.drawEqs();

        });
        y.add(yspinner);
        panel.setBorder(BorderFactory.createTitledBorder("Point " + num+ " :"));

        panel.add(x);
        panel.add(y);

        return  panel;
    }
}

