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
    private JLabel lblInsercio;
    private JLabel lblReactor;
    private JLabel lblBomba0;
    private JLabel lblBomba2;
    private JLabel lblBomba1;
    private JLabel lblBomba3;

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
        lblReactor.setText("Desactivat");
        lblBomba0.setText("Desactivada");
        lblBomba1.setText("Desactivada");
        lblBomba2.setText("Desactivada");
        lblBomba3.setText("Desactivada");


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
                    lblReactor.setText("Activat");
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
                lblReactor.setText("Desactivat");
                JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Reactor desactivat.");
            }
        });

        btnActivarBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chkBomba0.isSelected()) {
                    try {
                        adaptador.activaBomba(0);
                        lblBomba0.setText("Activada");
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Bomba 0 activada.");
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba1.isSelected()) {
                    try {
                        adaptador.activaBomba(1);
                        lblBomba1.setText("Activada");
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Bomba 1 activada.");
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba2.isSelected()) {
                    try {
                        adaptador.activaBomba(2);
                        lblBomba2.setText("Activada");
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Bomba 2 activada.");
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }

                if(chkBomba3.isSelected()) {
                    try {
                        adaptador.activaBomba(3);
                        lblBomba3.setText("Activada");
                        JOptionPane.showMessageDialog(FrmGestioComponentsCentral.this, "Bomba 3 activada.");
                    } catch (CentralUBException ex) {
                        JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Fora de servei.", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null);

                    }
                }


            }
        });

        btnDesactivarBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(chkBomba0.isSelected()) {
                    adaptador.desactivaBomba(0);
                    lblBomba0.setText("Desactivada");

                }
                if(chkBomba1.isSelected()) {
                    adaptador.desactivaBomba(1);
                    lblBomba1.setText("Desactivada");

                }
                if(chkBomba2.isSelected()) {
                    adaptador.desactivaBomba(2);
                    lblBomba2.setText("Desactivada");
                }
                if(chkBomba3.isSelected()) {
                    adaptador.desactivaBomba(3);
                    lblBomba3.setText("Desactivada");
                }

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

