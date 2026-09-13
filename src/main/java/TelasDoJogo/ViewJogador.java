package TelasDoJogo;

import javax.swing.*;
import java.awt.*;

public class ViewJogador extends JFrame {

    // Construtor: tudo aqui roda automaticamente quando a tela for criada
    public ViewJogador() {

        // Titulo que fica na barra de tarefas.
        super("Jorguinho");

        Dimension tamanhoTelaAchado = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(tamanhoTelaAchado.width, tamanhoTelaAchado.height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(0, 0, 0));

        Menu menu = new Menu(this);
        add(menu.criarPainel());
    }
}
