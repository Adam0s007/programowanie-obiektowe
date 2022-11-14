package agh.ics.oop;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class JframeOutput {

    private JFrame frame;
    private JPanel panel;
    private ArrayList<JLabel> labels = new ArrayList<>();

    private RectangularMap map;

    public JframeOutput(ArrayList<Animal> animals,RectangularMap map){
        this.map = map;
        this.frame = new JFrame();
        this.panel = new JPanel();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(map.getTopRight().x*50,map.getTopRight().y*50);
        panel.setLayout(null);
        frame.add(panel);

        animals.stream().forEach(animal-> {
            JLabel label = new JLabel(animal.toString());//orientation
            label.setBounds(animal.getPosition().x*50, -100+ map.getTopRight().y*50 - animal.getPosition().y*50,50,50);
            Border border = BorderFactory.createLineBorder(Color.BLACK, 5);
            label.setBorder(border);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            panel.add(label);
            labels.add(label);
        });
        frame.setVisible(true);
    }
    public void toSleep(int milis){
        try{
            Thread.sleep(milis);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    public void setPositions(ArrayList<Animal> animals, int index){ //index aby odwolac sie do konkretnego miejsca w liscie labels

        this.toSleep(400);
        labels.get(index)
                .setBounds(animals.get(index).getPosition().x*50,-100+ map.getTopRight().y*50 -
                        animals.get(index).getPosition().y*50,
                        50,
                        50);

        labels.get(index).setText(animals.get(index).toString());

    }

    public void discard(){
        this.frame.dispose();
    }




}
