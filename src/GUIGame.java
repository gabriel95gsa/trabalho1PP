import gamefacades.GameFacade;
import invokers.Controle;
import commands.*;
import gamefacades.ScoreFacade;
import gameitenslogs.HeroLog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GUIGame extends JFrame {

    public GUIGame() {
        setTitle("Trabalho 1 55PPR");
        setSize(900, 690);
        setLocation(220, 20);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evento) {
                if(painelGame != null) {
                    try {
                        painelGame.setClosed(true);
                    } catch (PropertyVetoException ex) {
                        JOptionPane.showMessageDialog(null, ex);
                    }
                    painelGame.salvarArquivoJogo();
                }
            }
        });
        
        initComponents();
    }
    
    private GameFacade painelGame;
    
    // Variables declaration - do not modify                     
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration
    
    // Listeners
    ActionListener abrirGame = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            painelGame = new GameFacade();
            jDesktopPane1.add(painelGame);
            painelGame.setVisible(true);
        }
    };
    
    ActionListener abrirScore = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ScoreFacade painelScore = new ScoreFacade();
            jDesktopPane1.add(painelScore);
            painelScore.setVisible(true);
        }
    };
    
    private int lastKey;

    private void initComponents() {
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        JMenuItem jMenuItem3 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 277, Short.MAX_VALUE)
        );
        
        jMenu1.setIcon(new javax.swing.ImageIcon("brick.png")); // NOI18N
        jMenu1.setText("Jogo");

        jMenuItem3.setIcon(new javax.swing.ImageIcon("joystick.png")); // NOI18N
        jMenuItem3.setText("Jogar");
        jMenuItem3.addActionListener(abrirGame);
        jMenu1.add(jMenuItem3);
        
        jMenuBar1.add(jMenu1);

        jMenu2.setIcon(new javax.swing.ImageIcon("award_star_gold_1.png")); // NOI18N
        jMenu2.setText("Score");

        jMenuItem2.setIcon(new javax.swing.ImageIcon("application_view_detail.png")); // NOI18N
        jMenuItem2.setText("Histório de Score");
        jMenuItem2.addActionListener(abrirScore);
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
    }

    public static void main(String[] args) {
        GUIGame a = new GUIGame();
        a.setVisible(true);
    }

}