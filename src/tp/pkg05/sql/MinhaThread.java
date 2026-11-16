/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp.pkg05.sql;
import javax.swing.*;

/**
 *
 * @author cavia
 */
public class MinhaThread extends Thread{
    
    
  private int max;
    private DefaultListModel<Integer> modelo;
    private JList<Integer> lista;
    private JLabel lblFinal;

    public MinhaThread(int max, int prioridade, DefaultListModel<Integer> modelo,
                       JList<Integer> lista, JLabel lblFinal) {
        this.max = max;
        this.modelo = modelo;
        this.lista = lista;
        this.lblFinal = lblFinal;
        setPriority(prioridade);
    }
      @Override
    public void run() {
        for (int i = 0; i < max; i++) {
            final int n = i;
            // o Swing só pode ser alterado pela thread da tela
            SwingUtilities.invokeLater(() -> {
                modelo.addElement(n);
                lista.ensureIndexIsVisible(modelo.getSize() - 1);
            });
        }
        SwingUtilities.invokeLater(() -> lblFinal.setVisible(true));
    }

}
