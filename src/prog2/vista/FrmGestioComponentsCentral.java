package prog2.vista;

import prog2.adaptador.Adaptador;
import prog2.model.BombaRefrigerant;
import prog2.model.SistemaRefrigeracio;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public FrmGestioComponentsCentral(JFrame parent) {
        super(parent);
        setContentPane(contentPane);
        setSize(600, 600);
        setLocationRelativeTo(parent);
        setModal(true);
        adaptador = AppCentralUB.getAdaptador();
        sldBarresControl.setMaximum(100);
        sldBarresControl.setMinimum(0);

        actualitzaIncidencies();

        sldBarresControl.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int insercio = sldBarresControl.getValue();
                adaptador.setInsercio(insercio);
                txtIntroduirInsercioBarresControl.setText(Integer.toString(insercio));


            }
        });
        btnIntroduirInsercioBarresControl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float insercio = Float.parseFloat(txtIntroduirInsercioBarresControl.getText());
                adaptador.setInsercio(insercio);
                sldBarresControl.setValue((int) insercio);
            }
        });
        btnActivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    adaptador.activaReactor();
                } catch (CentralUBException ex) {
                    JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null );
                }
            }
        });
        btnDesactivarReactor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adaptador.desactivaReactor();
            }
        });

        btnActivarBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkBomba0.isSelected()) {
                    try {
                        adaptador.activaBomba(0);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba1.isSelected()) {
                    try {
                        adaptador.activaBomba(1);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba2.isSelected()) {
                    try {
                        adaptador.activaBomba(2);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba3.isSelected()) {
                    try {
                        adaptador.activaBomba(3);
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

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

