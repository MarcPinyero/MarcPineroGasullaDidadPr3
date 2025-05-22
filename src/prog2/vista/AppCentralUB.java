package prog2.vista;

import prog2.adaptador.Adaptador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Marc Piñero i Dídac Gasulla
 *
 * Aquesta classe conté 5 variables finals, que son la demanda màxima i mínima, i els valors de la variable
 * normal mean, standard i seed. després té un atribut de tipus variable normal, un de tipus adaptador i
 * un float per la demanda de potència.
 * en quant als esdeveniments, té un panell per l'app central UB, i tres botons, que utilitzem per a la
 * gestió de components, per informacio general i un per finalitzar el dia.
 * finalment té un mètode per a genrar la demanda de potència d'acord amb els limits de demanda màxima i
 * mínima
 */

public class AppCentralUB extends JFrame {
    public final static float DEMANDA_MAX = 1800;
    public final static float DEMANDA_MIN = 250;
    public final static float VAR_NORM_MEAN = 1000;
    public final static float VAR_NORM_STD = 800;
    public final static long VAR_NORM_SEED = 123;

    /** Generador aleatori de la demanda de potència **/
    private VariableNormal variableNormal;

    /** Demanda de potència del dia actual **/
    private float demandaPotencia;
    private static Adaptador adaptador;
    private JPanel appCentralUB;
    private JButton btnGestioComponentsCentral;
    private JButton btnInfo;
    private JButton btnFinalitzarDia;
    private JButton btnCarregar;
    private JButton btnDesar;


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
        adaptador = new Adaptador();
        variableNormal = new VariableNormal(VAR_NORM_MEAN, VAR_NORM_STD, VAR_NORM_SEED);
        demandaPotencia = generaDemandaPotencia();
        btnGestioComponentsCentral.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmGestioComponentsCentral gestioComponents = new FrmGestioComponentsCentral(AppCentralUB.this);
                gestioComponents.setVisible(true);


            }
        });
        btnInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FrmVisualitzarInformacio informacio = new FrmVisualitzarInformacio(AppCentralUB.this);
                informacio.setVisible(true);
            }
        });
        btnFinalitzarDia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = adaptador.finalitzaDia(demandaPotencia);
                JOptionPane.showMessageDialog(AppCentralUB.this, info, "Actualitzaciò central", JOptionPane.INFORMATION_MESSAGE);
                demandaPotencia = generaDemandaPotencia();


            }
        });

        btnDesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String file = JOptionPane.showInputDialog("Digues la direcció del ficher.");
                adaptador.guardaDades(file);
            }
        });
        btnCarregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("C:\\Users\\didac\\IdeaProjects\\MarcPineroGasullaDidacPr3.2");
                int returnVal = fileChooser.showDialog(AppCentralUB.this, "Seleccionar");
                adaptador.carregaDades(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
    }
    /**
     * Aquest mètode calcula la demanda a partir de seguent valor de la variable normal i l'ajusta de tal
     * manera que no sigui superior al màxim ni inferior al mínim establerts al principi d'aquesta classe
     * @return float valor (demanda de potencia )
     */

    private float generaDemandaPotencia(){
        float valor = Math.round(variableNormal.seguentValor());
        if (valor > DEMANDA_MAX)
            return DEMANDA_MAX;
        else
        if (valor < DEMANDA_MIN)
            return DEMANDA_MIN;
        else
            return valor;
    }

    static Adaptador getAdaptador() {
        return adaptador;
    }
}
