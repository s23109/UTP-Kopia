package zad1;

import javax.swing.*;
import java.awt.*;

public class UI {
    private JButton stopButt;
    private JButton startButt;
    private JPanel bottButtPanel;
    private JPanel glowny;
    private JTextArea textArea;
    private Test main;

    public UI(Test maia) {

        this.main = maia;

        JFrame frame = new JFrame("s23109");
        frame.setContentPane(glowny);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        init();
    }

    private void init(){
        textArea.setText("Program Start!");

        startButt.addActionListener(a->{

            if(main.countTasks() < main.maxTasks()){
                Button tmp = new Button("tmp");
                tmp.setVisible(true);

                bottButtPanel.add(tmp);
                repaint();

                main.createNewTask(tmp);
            }
        });

        stopButt.addActionListener(a->{
            main.cancelAll();
            startButt.setEnabled(false);
            stopButt.setEnabled(false);
        });
    }

    public void appendText(String s){
        textArea.append(s);
    }

    public void removeComp(Component s){
        bottButtPanel.remove(s);
        repaint();
    }

    public void repaint(){
        bottButtPanel.revalidate();
        bottButtPanel.repaint();
    }
}
