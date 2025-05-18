package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmVisualitzarInformacio extends JDialog {
    private Adaptador adaptador;
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox cmbEscollirInfo;
    private JTextArea txtInfo;
    private JButton btnMostraInfo;

    public FrmVisualitzarInformacio(JFrame parent) {
        super(parent);
        setContentPane(contentPane);
        setSize(600, 600);
        setLocationRelativeTo(parent);
        setModal(true);
        adaptador = AppCentralUB.getAdaptador();
        btnMostraInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = (String) cmbEscollirInfo.getSelectedItem();
                if(info.equals("Estat Central"))
                    txtInfo.setText(adaptador.mostrarEstat());
                if(info.equals("Bitacola"))
                    txtInfo.setText(adaptador.mostrarBitacola());
                if(info.equals("Incidencies"))
                    txtInfo.setText(adaptador.mostrarIncidencies());
            }
        });
    }
}
