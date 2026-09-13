package configuracoes;

import javax.swing.*;
import java.awt.*;

public class Resolucoes {

    private final JFrame janela;

    public Resolucoes(JFrame janela) {
        this.janela = janela;
    }

    // Monta a linha "Resolução: [dropdown]" que fica na aba Gráfico
    public JPanel criarSeletorResolucoes() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel label = new JLabel("Resolução:");
        label.setFont(new Font("Arial", Font.BOLD, 16));

        JComboBox<TamanhosDeTelas> seletor = new JComboBox<>(TamanhosDeTelas.values());
        seletor.setFont(new Font("Arial", Font.PLAIN, 16));

        // Guarda a resolução realmente confirmada (para saber pra onde voltar se o usuário cancelar)
        TamanhosDeTelas[] valorConfirmado = {resolucaoAtual()};
        seletor.setSelectedItem(valorConfirmado[0]);

        seletor.addActionListener(e -> {
            TamanhosDeTelas escolha = (TamanhosDeTelas) seletor.getSelectedItem();
            if (escolha != null && escolha != valorConfirmado[0]) {
                aplicarResolucao(escolha, seletor, valorConfirmado);
            }
        });

        painel.add(label);
        painel.add(seletor);
        return painel;
    }

    // Descobre qual opção da lista corresponde ao tamanho atual da janela
    private TamanhosDeTelas resolucaoAtual() {
        Dimension tamanho = janela.getSize();

        for (TamanhosDeTelas opcao : TamanhosDeTelas.values()) {
            if (opcao.getLargura() == tamanho.width && opcao.getAltura() == tamanho.height) {
                return opcao;
            }
        }
        return TamanhosDeTelas.values()[0];
    }

    private void aplicarResolucao(TamanhosDeTelas escolha, JComboBox<TamanhosDeTelas> seletor, TamanhosDeTelas[] valorConfirmado) {
        // Guarda o tamanho atual para poder voltar caso o usuário não confirme
        Dimension tamanhoAnterior = janela.getSize();

        janela.setExtendedState(JFrame.NORMAL);

        // Aplica a nova resolução na tela na mesma hora
        janela.setSize(escolha.getLargura(), escolha.getAltura());

        // Recentraliza a janela no monitor para não ficar torta com o novo tamanho
        janela.setLocationRelativeTo(null);

        confirmarResolucao(
                tamanhoAnterior,
                () -> valorConfirmado[0] = escolha,
                () -> seletor.setSelectedItem(valorConfirmado[0])
        );
    }

    // Mostra uma caixa perguntando se o jogador quer manter a nova resolução.
    // Se não confirmar em 15 segundos (ou clicar em Cancelar), volta para a resolução anterior.
    private void confirmarResolucao(Dimension tamanhoAnterior, Runnable aoConfirmar, Runnable aoReverter) {
        final int SEGUNDOS_PARA_REVERTER = 15;

        JDialog dialogo = new JDialog(janela, "Manter esta resolução?", true);
        dialogo.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JLabel label = new JLabel(
                "Manter esta resolução? Revertendo em " + SEGUNDOS_PARA_REVERTER + "s...",
                SwingConstants.CENTER
        );
        label.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton btnConfirmar = new JButton("Confirmar");
        JButton btnCancelar = new JButton("Cancelar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.add(btnConfirmar);
        painelBotoes.add(btnCancelar);

        dialogo.getContentPane().setLayout(new BorderLayout(10, 10));
        dialogo.getContentPane().add(label, BorderLayout.CENTER);
        dialogo.getContentPane().add(painelBotoes, BorderLayout.SOUTH);
        dialogo.setSize(350, 130);
        dialogo.setLocationRelativeTo(janela);

        // Contagem regressiva: a cada 1 segundo, atualiza o texto e checa se o tempo acabou
        int[] segundosRestantes = {SEGUNDOS_PARA_REVERTER};
        Timer cronometro = new Timer(1000, null);
        cronometro.addActionListener(e -> {
            segundosRestantes[0]--;
            if (segundosRestantes[0] <= 0) {
                cronometro.stop();
                janela.setSize(tamanhoAnterior);
                janela.setLocationRelativeTo(null);
                dialogo.dispose();
                aoReverter.run();
            } else {
                label.setText("Manter esta resolução? Revertendo em " + segundosRestantes[0] + "s...");
            }
        });

        btnConfirmar.addActionListener(e -> {
            cronometro.stop();
            dialogo.dispose();
            aoConfirmar.run();
        });

        btnCancelar.addActionListener(e -> {
            cronometro.stop();
            janela.setSize(tamanhoAnterior);
            janela.setLocationRelativeTo(null);
            dialogo.dispose();
            aoReverter.run();
        });

        cronometro.start();
        dialogo.setVisible(true);
    }

    public void mudarModoDeTela() {
        String[] opcoes = {"Janela", "Tela Cheia sem Bordas", "Tela Cheia Exclusiva"};

        String escolha = (String) JOptionPane.showInputDialog(
                janela, "Selecione o modo de exibição:", "Modo de Tela",
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]
        );

        // Falha rápida se cancelar
        if (escolha == null) return;

        janela.dispose();
        GraphicsDevice monitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

        if (escolha.equals("Janela")) {
            janela.setUndecorated(false);
            monitor.setFullScreenWindow(null);
            janela.setExtendedState(JFrame.NORMAL);
            janela.setLocationRelativeTo(null);
            janela.setVisible(true);
            return;
        }

        if (escolha.equals("Tela Cheia sem Bordas")) {
            janela.setUndecorated(true);
            monitor.setFullScreenWindow(null);
            janela.setExtendedState(JFrame.MAXIMIZED_BOTH);
            janela.setVisible(true);
            return;
        }

        if (escolha.equals("Tela Cheia Exclusiva")) {
            janela.setUndecorated(true);
            monitor.setFullScreenWindow(janela);
            janela.setVisible(true);
        }
    }
}
