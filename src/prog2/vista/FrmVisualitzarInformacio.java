package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe té un atribut de tipus adaptador. Després té tots els atributs per a la gestió d'events, que en aquest cas
 * son una caixa on pots escollir la informació que desitges veure, ja sigui l'esta de la central, la bitacola o les
 * incidències. Per a mostrar aquesta informació fem servir un text area. Finalment tenim un botó OK per a sortir de la
 * pestanya amb l'informació i tornar al menu principal.
 */

public class FrmVisualitzarInformacio extends JDialog {
    private Adaptador adaptador;
    private JPanel contentPane;
    private JButton buttonOK;
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
        txtInfo.setEnabled(false);
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
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
