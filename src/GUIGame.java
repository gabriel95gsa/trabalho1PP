import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import suporte.Enemy;
import suporte.GameGridModel;
import suporte.GameGridRenderer;


@SuppressWarnings("serial")
public class GUIGame extends JFrame {

    public GUIGame() {
        setTitle("Trabalho 1 55PPR");
        setSize(510, 530);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private int lastKey;

    private void initComponents() {
        final GameGridModel model = new GameGridModel();

        final JTable espaco = new JTable(model);
        for (int x=0;x<espaco.getColumnModel().getColumnCount();x++) {
            espaco.getColumnModel().getColumn(x).setWidth(50);
            espaco.getColumnModel().getColumn(x).setMinWidth(50);
            espaco.getColumnModel().getColumn(x).setMaxWidth(50);
        }
        espaco.setRowHeight(50);
        espaco.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        espaco.setDefaultRenderer(Object.class, new GameGridRenderer());

        getContentPane().add(espaco);

        espaco.addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent e) {
                lastKey = e.getKeyCode();
            }

        });

        final Random sorteio = new Random();
        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    // Opções de seleção de armas
                    Object[] opcoesArmas = {"Bumerangue", "Míssel"};
                    int opcao = JOptionPane.showOptionDialog(null, "Escolha a arma que deseja utilizar", "Selecionar arma", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesArmas, opcoesArmas[0]);
                    
                    int rodada = 0;
                    while (true) {
                        // lerInputs
                        switch (lastKey) {
                            case 32: model.heroShot(opcao); break;
                            case 37: model.heroGoLeft(); break;
                            case 39: model.heroGoRight(); break;
                        }
                        lastKey = 0;

                        // mudar o estado dos objetos
                        model.atualizar();

                        if (rodada % 5 == 0) {
                            int col = sorteio.nextInt(10);
                            model.addObjeto(new Enemy(0, col, model), 0, col);
                            rodada = 0;
                        }

                        // renderizar
                        espaco.repaint();

                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    espaco.repaint();
                    JOptionPane.showMessageDialog(null, e.getMessage());
                    if (e.getMessage().equals("Fim de Jogo")) 
                        System.exit(0);
                }
            }
        };
        t.start();

    }

    public static void main(String[] args) {
        GUIGame a = new GUIGame();
        a.setVisible(true);
    }

}
