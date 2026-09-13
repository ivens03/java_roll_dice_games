package configuracoes;

import javax.swing.*;
import java.awt.*;

public class Configuracoes {

    private final JFrame janela;
    private final Resolucoes resolucoes;

    public Configuracoes(JFrame janela) {
        this.janela = janela;
        this.resolucoes = new Resolucoes(janela);
    }

    public void abrir() {

        JTabbedPane abas = new JTabbedPane();
        abas.addTab("Gráfico", criarPainelGrafico());
        abas.addTab("Áudio", criarPainelAudio());

        JDialog dialogo = new JDialog(janela, "Configurações", true);
        dialogo.getContentPane().add(abas);
        dialogo.setSize(400, 300);
        dialogo.setLocationRelativeTo(janela);
        dialogo.setVisible(true);
    }

    private JPanel criarPainelGrafico() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        painel.add(resolucoes.criarSeletorResolucoes());
        return painel;
    }

    private JPanel criarPainelAudio() {
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        painel.add(new JLabel("Configurações de áudio em breve."));
        return painel;
    }
}
