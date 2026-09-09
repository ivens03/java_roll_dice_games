package configuracoesDisplayerDoJogador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Displayer extends JFrame {

    // Construtor: tudo aqui roda automaticamente quando a tela for criada
    public Displayer() {

        // Titulo que fica na barra de tarefas.
        super("Meu jogo");

        Dimension tamanhoTelaAchado = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamanhoTelaAchado.width, tamanhoTelaAchado.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(0, 0, 0));

        criarMenu();
    }

    private void criarMenu() {

        // refatorar!
        JPanel painelMenu = new JPanel(new GridLayout(3, 1, 10, 10));
        painelMenu.setOpaque(false); // Deixa o fundo transparente para o verde da tela aparecer

        JButton btnResolucao = new JButton("Configurar Resolução");
        btnResolucao.setFont(new Font("Arial", Font.BOLD, 20));

        btnResolucao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mudarResolucao();
            }
        });

        // 2. Criando o botão de Sair
        JButton btnSair = new JButton("Sair do Jogo");
        btnSair.setFont(new Font("Arial", Font.BOLD, 20));

        // Adicionando a ação de clique no botão Sair
        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // O zero significa que fechou sem dar erro
            }
        });

        JButton bntIniciar = new JButton("Começar a aventura!");
        bntIniciar.setFont(new Font("Arial", Font.BOLD, 20));

        // Coloca os botões dentro do painel
        painelMenu.add(bntIniciar);
        painelMenu.add(btnResolucao);
        painelMenu.add(btnSair);

        // Coloca o painel no centro da tela principal
        add(painelMenu);
    }

    private void mudarResolucao() {

        // Lista das resoluções que vamos oferecer
        String[] opcoes = {
                "800x600 (4:3)",
                "1024x768 (4:3)",
                "1280x720 (16:9)",
                "1600x900 (16:9)",
                "1920x1080 (16:9)",
                "2560x1080 (21:9)",
                "2560x1440 (16:9)",
                "3440x1440 (21:9)",
                "3840x2160 (16:9)",
                "5120x1440 (32:9)"
        };

        // Abre uma janelinha perguntando qual resolução o jogador quer
        String escolha = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o tamanho da tela:",
                "Configuração de Resolução",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0] // Opção padrão selecionada
        );

        // Se o cara escolheu algo e não clicou em "Cancelar"
        if (escolha != null) {
            // Quebra o texto "800x600" usando a letra "x" como cortador
            String[] dimensoes = escolha.split("x");

            // Converte os textos para números inteiros
            int largura = Integer.parseInt(dimensoes[0]);
            int altura = Integer.parseInt(dimensoes[1]);

            setExtendedState(JFrame.NORMAL);

            // Aplica a nova resolução na tela na mesma hora
            setSize(largura, altura);

            // Recentraliza a janela no monitor para não ficar torta com o novo tamanho
            setLocationRelativeTo(null);
        }
    }

}
