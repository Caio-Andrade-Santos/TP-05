package tp.pkg05.sql;

import java.awt.*;
import javax.swing.*;

public class Formulario extends JFrame {

    // painel 1
    private JLabel lblMaximo = new JLabel("Máximo:");
    private JLabel lblPrioridade1 = new JLabel("Prioridade 1:");
    private JLabel lblPrioridade2 = new JLabel("Prioridade 2:");
    private JLabel lblDica = new JLabel("Número entre 1 e 10");
    private JTextField txtMaximo = new JTextField("1000");
    private JTextField txtPrioridade1 = new JTextField("1");
    private JTextField txtPrioridade2 = new JTextField("10");
    private JButton btnIniciar = new JButton("Iniciar");

    // painel 2 (os modelos vêm antes das listas, porque as listas usam eles)
    private DefaultListModel<Integer> modelo1 = new DefaultListModel<>();
    private DefaultListModel<Integer> modelo2 = new DefaultListModel<>();
    private JList<Integer> liThread1 = new JList<>(modelo1);
    private JList<Integer> liThread2 = new JList<>(modelo2);
    private JScrollPane scroll1 = new JScrollPane(liThread1);
    private JScrollPane scroll2 = new JScrollPane(liThread2);
    private JLabel lblFinal1 = new JLabel("Final");
    private JLabel lblFinal2 = new JLabel("Final");
    private JButton btnLimpar = new JButton("Limpar");

    public Formulario() {
        super("Trabalho Prático 5");
        setLayout(null);

        lblMaximo.setBounds(40, 20, 90, 25);       txtMaximo.setBounds(130, 20, 140, 25);
        lblPrioridade1.setBounds(40, 55, 90, 25);  txtPrioridade1.setBounds(130, 55, 140, 25);
        lblPrioridade2.setBounds(40, 90, 90, 25);  txtPrioridade2.setBounds(130, 90, 140, 25);
        lblDica.setBounds(290, 55, 150, 25);
        btnIniciar.setBounds(130, 130, 140, 28);

        scroll1.setBounds(60, 180, 90, 110);
        scroll2.setBounds(290, 180, 90, 110);

        lblFinal1.setBounds(85, 295, 60, 25);
        lblFinal2.setBounds(315, 295, 60, 25);
        lblFinal1.setVisible(false);
        lblFinal2.setVisible(false);

        btnLimpar.setBounds(170, 340, 100, 28);

        add(lblMaximo); add(txtMaximo);
        add(lblPrioridade1); add(txtPrioridade1);
        add(lblPrioridade2); add(txtPrioridade2);
        add(lblDica); add(btnIniciar);
        add(scroll1); add(scroll2);
        add(lblFinal1); add(lblFinal2);
        add(btnLimpar);

        btnIniciar.addActionListener(e -> {
            int max, p1, p2;
            try {
                max = Integer.parseInt(txtMaximo.getText().trim());
                p1  = Integer.parseInt(txtPrioridade1.getText().trim());
                p2  = Integer.parseInt(txtPrioridade2.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Digite apenas números inteiros.");
                return;
            }
            if (max < 1 || p1 < 1 || p1 > 10 || p2 < 1 || p2 > 10) {
                JOptionPane.showMessageDialog(this,
                        "O máximo deve ser maior que 0 e as prioridades ficam entre 1 e 10.");
                return;
            }

            limpar();   // evita misturar com uma execução anterior

            MinhaThread t1 = new MinhaThread(max, p1, modelo1, liThread1, lblFinal1);
            MinhaThread t2 = new MinhaThread(max, p2, modelo2, liThread2, lblFinal2);
            t1.start();
            t2.start();
        });

        btnLimpar.addActionListener(e -> limpar());

        setSize(460, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void limpar() {
        modelo1.clear();
        modelo2.clear();
        lblFinal1.setVisible(false);
        lblFinal2.setVisible(false);
    }
}