package gamefacades;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Date;
import java.text.SimpleDateFormat;

import prototypes.EnemyPrototype;
import enemies.EnemyMonsterProtype;
import enemies.EnemyDiscoVoadorPrototype;
import suporte.GameGridModel;
import suporte.GameGridRenderer;
import observers.ObservadorEnemy;
import adapters.JogoSalvo;
import commands.ExecutarShot;
import commands.MoverHeroDireita;
import commands.MoverHeroiEsquerda;
import enemies.EnemyMonsterProtype;
import gameitenslogs.HeroLog;
import invokers.Controle;
import prototypes.EnemyPrototype;

/**
 *
 * @author Gabriel Schenkel Albino, Cristiano Flores, Julio Vinicius
 */

@SuppressWarnings("serial")
public class GameFacade extends JInternalFrame implements GameOpcoes {
    
    public GameFacade() {
        setSize(800, 600);
        setLocation(40, 15);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        
        initComponents();
    }
    
    private Thread t;
    private Object[] opcoesArmas = {"Bumerangue", "Míssel"}; // Opções para seleção de armas
    private Object[] opcoes = {"Sim", "Não"};
    private int lastKey;
    public JLabel labelVidas;
    public JLabel labelScore;
    private final GameGridModel model = new GameGridModel();
    private Controle controle = new Controle(); // Objeto Controle para adicionar os commands das ações executadas
    
    private void initComponents() {
        this.labelVidas = new JLabel("Vidas: " + this.model.getVidas());
        getContentPane().add(this.labelVidas, "South");
        
        this.labelScore = new JLabel("Score: " + this.model.getScore());
        getContentPane().add(this.labelScore, "North");
        
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

        // Objeto HeroLog para adicionar aos commands
        HeroLog logHero = new HeroLog();
        
        final Random sorteio = new Random();
        final Random sorteioEnemy = new Random(); // sorteia os Enemies que aparecem no jogo
        t = new Thread() {
            @Override
            public void run() {
                try {
                    // Joption para abrir ou não um jogo salvo
                    int opcAbreJogoSalvo = JOptionPane.showOptionDialog(null, "Você deseja carregar um jogo salvo?", "Abrir jogo salvo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                    
                    if(opcAbreJogoSalvo == 0) {
                        String nomeArquivo = JOptionPane.showInputDialog("Informe o nome do arquivo de jogo salvo: ");
                        
                        try {
                            BufferedReader br = new BufferedReader(new FileReader("game data/" + nomeArquivo + ".txt"));
                            while(br.ready()){
                                String conteudo = br.readLine();
                                
                                // Validação do arquivo de jogo salvo
                                JogoSalvo jogoInfo = new JogoSalvo();
                                jogoInfo.getConteudoArquivo(conteudo);
                                if(jogoInfo.verificaJogoValido() == true) {
                                    model.addScore(jogoInfo.getScore());
                                    model.setVida(jogoInfo.getVidas());
                                    model.setVidaHeroInstance(jogoInfo.getVidas());
                                } else {
                                    throw new Exception("Arquivo de jogo salvo inválido!");
                                }
                            }
                            br.close();
                        } catch(Exception eBuffered) {
                            JOptionPane.showMessageDialog(null, eBuffered);
                        }
                    }
                    
                    // Joption para seleção da arma
                    int opcao = JOptionPane.showOptionDialog(null, "Escolha a arma que deseja utilizar", "Selecionar arma", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoesArmas, opcoesArmas[0]);
                    
                    /*
                     * Instancia um objeto concreto de EnemyMonsterProtype e EnemyDiscoVoadorPrototype para clonar
                     * outros objetos durante a execução do jogo
                     */
                    EnemyMonsterProtype enemyMonster = new EnemyMonsterProtype();
                    EnemyDiscoVoadorPrototype enemyDiscoVoador = new EnemyDiscoVoadorPrototype();
                    
                    int rodada = 0;
                    while (true) {
                        // lerInputs
                        switch (lastKey) {
                            case 32: 
                                model.heroShot(opcao);
                                // Cria e adiciona o command do log para a ação
                                ExecutarShot exeShot = new ExecutarShot(logHero);
                                controle.addCommand(exeShot);
                                break;
                            case 37: 
                                model.heroGoLeft();
                                // Cria e adiciona o command do log para a ação
                                MoverHeroiEsquerda moverEsq = new MoverHeroiEsquerda(logHero);
                                controle.addCommand(moverEsq);
                                break;
                            case 39: 
                                model.heroGoRight();
                                // Cria e adiciona o command do log para a ação
                                MoverHeroDireita moverDir = new MoverHeroDireita(logHero);
                                controle.addCommand(moverDir);
                                break;
                        }
                        lastKey = 0;

                        // mudar o estado dos objetos
                        model.atualizar();
                        // atualiza o valor da label score baseado nas pontuações do game em tempo real
                        labelScore.setText("Score: " + model.getScore());
                        // atualiza o valor da label vidas baseado nas vezes em que o herói for atingido por um inimigo em tempo real
                        labelVidas.setText("Vidas: " + model.getVidas());

                        if (rodada % 5 == 0) {
                            int col = sorteio.nextInt(10); // coluna na qual o Enemy será posicionado
                            EnemyPrototype novoEnemy;
                            if((sorteioEnemy.nextInt(2) % 2) == 0) {
                                novoEnemy = enemyMonster.clonar();
                            } else {
                                novoEnemy = enemyDiscoVoador.clonar();
                            }
                            // Seta as propriedades do Enemy
                            novoEnemy.setY(0);
                            novoEnemy.setX(col);
                            novoEnemy.setModel(model);
                            // Observador par atualizar score e vidas
                            ObservadorEnemy obs = new ObservadorEnemy();
                            novoEnemy.anexar(obs);
                            model.addObjeto(novoEnemy, 0, col);
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
                
                salvarArquivoJogo();
            }
        };
        t.start();
        
    }

    /*
     * Cria um arquivo com os dados do jogo salvo
     */
    @Override
    public void salvarArquivoJogo() {
        t.stop();
        
        // Executa todos os comandos imprimindo-os no arquivo de log
        this.controle.executarCommands();
        
        // Joption para salvar jogo
        int opcao = JOptionPane.showOptionDialog(null, "Você deseja salvar este jogo?", "Salvar jogo", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        // Chama a função para salvar um arquivo
        if(opcao == 0) {
            FileWriter arquivo;

            try {
                String nomeArquivo = JOptionPane.showInputDialog("Informe o nome do arquivo:");

                if(nomeArquivo.length() > 0) {
                    String conteudoArq = "score: " + String.valueOf(model.getScore()) + "; ";
                    conteudoArq += "vidas: " + String.valueOf(model.getVidas()) + "; ";

                    Date data = new Date();
                    SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
                    String dataFormatada = formatada.format(data);
                    conteudoArq += "Data : " + dataFormatada + ";";

                    arquivo = new FileWriter(new File("game data/" + nomeArquivo + ".txt"));
                    arquivo.write(conteudoArq);
                    arquivo.close();
                }
            } catch(Exception efile) {
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar salvar o arquivo do jogo");
            }
        }
    }
    
}