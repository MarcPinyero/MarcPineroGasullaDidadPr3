package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmGestioComponentsCentral extends JDialog {
    private Adaptador adaptador;
    private JPanel contentPane;
    private JButton btnAplicarModificacions;
    private JButton btnCancelarModificacions;
    private JLabel etBarresControl;
    private JSlider sldBarresControl;
    private JTextField txtIntroduirInsercioBarresControl;
    private JButton btnIntroduirInsercioBarresControl;
    private JButton btnDesactivarReactor;
    private JButton btnActivarReactor;
    private JComboBox cmbBomba;
    private JButton btnActivarBomba;
    private JButton btnDesactivarBomba;
    private JList llistaIncidencies;

    public FrmGestioComponentsCentral(JFrame parent) {
        super(parent);
        setContentPane(contentPane);
        setSize(600, 600);
        setLocationRelativeTo(parent);
        setModal(true);
        adaptador = new Adaptador();
        sldBarresControl.setMaximum(100);
        sldBarresControl.setMinimum(0);
        cmbBomba.addItem(0);
        cmbBomba.addItem(1);
        cmbBomba.addItem(2);
        cmbBomba.addItem(3);
        adaptador.finalitzaDia(8798);
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
                int id = (int) cmbBomba.getSelectedItem();
                try{
                    adaptador.activaBomba(id);
                } catch (CentralUBException ex) {
                    JOptionPane.showOptionDialog(FrmGestioComponentsCentral.this, ex.getMessage(), "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null, null, null );

                }

            }
        });

        btnDesactivarBomba.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = (int) cmbBomba.getSelectedItem();
                adaptador.desactivaBomba(id);
            }
        });
    }

    void actualitzaIncidencies(){
        DefaultListModel model = new DefaultListModel();
        model.clear();
        String[] lineas = adaptador.mostrarIncidencies().split("\n");
        model.addElement(lineas[0]);
        model.addElement(lineas[1]);
        model.addElement(lineas[2]);
        model.addElement(lineas[3]);
        llistaIncidencies.setModel(model);    }
}
