package TelasDoJogo;

import configuracoes.Configuracoes;

import javax.swing.*;
import java.awt.*;

public class Menu {

    private final JFrame janela;
    private final Configuracoes configuracoes;

    public Menu(JFrame janela) {
        this.janela = janela;
        this.configuracoes = new Configuracoes(janela);
    }

    public JPanel criarPainel() {

        JPanel painelMenu = new JPanel(new GridLayout(3, 1, 10, 10));
        painelMenu.setOpaque(false); // Deixa o fundo transparente para o verde da tela aparecer

        JButton bntIniciar = new JButton("Começar a aventura!");
        bntIniciar.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnConfiguracoes = new JButton("Configurações");
        btnConfiguracoes.setFont(new Font("Arial", Font.BOLD, 20));
        btnConfiguracoes.addActionListener(e -> configuracoes.abrir());

        // Criando o botão de Sair
        JButton btnSair = new JButton("Sair do Jogo");
        btnSair.setFont(new Font("Arial", Font.BOLD, 20));

        // Adicionando a ação de clique no botão Sair
        btnSair.addActionListener(e -> System.exit(0)); // O zero significa que fechou sem dar erro

        // Coloca os botões dentro do painel
        painelMenu.add(bntIniciar);
        painelMenu.add(btnConfiguracoes);
        painelMenu.add(btnSair);

        return painelMenu;
    }
}
