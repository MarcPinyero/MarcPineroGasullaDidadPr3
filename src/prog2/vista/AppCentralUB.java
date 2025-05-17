package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppCentralUB extends JFrame {
    private JPanel appCentralUB;
    private JButton btnGestioComponentsCentral;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppCentralUB appCentralUB = new AppCentralUB();
            appCentralUB.setVisible(true);
        });
    }

    public AppCentralUB() {
        setTitle("Gestió Central UB");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(appCentralUB);
        setSize(500,400);
        setLocationRelativeTo(null);
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral gestioComponents = new FrmGestioComponentsCentral(AppCentralUB.this);
                gestioComponents.setVisible(true);


            }
        });
    }
}
