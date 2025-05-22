package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe conté un atribut de tipus adaptador, i diversos atributs de gestió d'events ( botons,
 * panells, labels...)
 * La gestió d'events es fa a partir de dos botons per activar i desactivar el reactor. Dos botons més per
 * a activar i desactivar la bomba, amb l'ajuda de 4 CheckBoxes (un per a cada bomba) i amb la seva gestió
 * d'incidències. Més endavant, per a les barres de control tenim un Slider que ens permet seleccionar la
 * quatitat de barres de control desitjada, i un botó per a introduir les barres seleccionades.
 * Finalment un botó per a aplicar tots els cavnis anteriors. I un mètode per a actualitzar la llista
 * d'incidències.
 */
public class FrmGestioComponentsCentral extends JDialog {
    private Adaptador adaptador;
    private JPanel contentPane;
    private JButton btnAplicarModificacions;
    private JLabel etBarresControl;
    private JSlider sldBarresControl;
    private JTextField txtIntroduirInsercioBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JButton btnDesactivarReactor;
    private JButton btnActivarReactor;
    private JButton btnActivarBomba;
    private JButton btnDesactivarBomba;
    private JList llistaIncidencies;
    private JCheckBox chkBomba0;
    private JCheckBox chkBomba1;
    private JCheckBox chkBomba2;
    private JCheckBox chkBomba3;
    private JLabel lblInsercio;

    public FrmGestioComponentsCentral(JFrame parent) {
        super(parent);
        setContentPane(contentPane);
        setSize(600, 600);
        setLocationRelativeTo(parent);
        setModal(true);
        adaptador = AppCentralUB.getAdaptador();
        sldBarresControl.setMaximum(100);
        sldBarresControl.setMinimum(0);
        lblInsercio.setText((String.valueOf(adaptador.getInsercio())));

        actualitzaIncidencies();

        sldBarresControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int insercio = sldBarresControl.getValue();
                adaptador.setInsercio(insercio);
                lblInsercio.setText((String.valueOf(adaptador.getInsercio())));



            }
        });
        btnIntroduirInsercioBarresControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float insercio = Float.parseFloat(txtIntroduirInsercioBarresControl.getText());
                adaptador.setInsercio(insercio);
                lblInsercio.setText((String.valueOf(adaptador.getInsercio())));

            }
        });
        btnActivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adaptador.activaReactor();
                    JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Reactor activat.");
                } catch (CentralUBException ex) {
                    JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null );
                }
            }
        });
        btnDesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.desactivaReactor();
                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Reactor desactivat.");
            }
        });

        btnActivarBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkBomba0.isSelected()) {
                    try {
                        adaptador.activaBomba(0);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba1.isSelected()) {
                    try {
                        adaptador.activaBomba(1);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba2.isSelected()) {
                    try {
                        adaptador.activaBomba(2);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba3.isSelected()) {
                    try {
                        adaptador.activaBomba(3);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }
                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Bombas activades.");

            }
        });

        btnDesactivarBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(chkBomba0.isSelected())
                    adaptador.desactivaBomba(0);
                if(chkBomba1.isSelected())
                    adaptador.desactivaBomba(1);
                if(chkBomba2.isSelected())
                    adaptador.desactivaBomba(2);
                if(chkBomba3.isSelected())
                    adaptador.desactivaBomba(3);

                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Bombas desactivadas.");



            }
        });
        btnAplicarModificacions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    void actualitzaIncidencies(){
            DefaultListModel model = new DefaultListModel();
            model.clear();
        SistemaRefrigeracio refri = adaptador.mostraRefrigeracio();
        for (BombaRefrigerant bomba : refri.getBombas()){
            if(bomba.getForaDeServei())
                model.addElement("La bomba refrig. " + bomba.getId() + " està fora de servei.");
        }

            llistaIncidencies.setModel(model);
        }
    }

