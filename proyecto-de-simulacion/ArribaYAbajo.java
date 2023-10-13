
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class ArribaYAbajo extends JFrame {
    private JLabel jLabel1;
    private JTextField jTextField1;
    private JButton jButton1;
    private ArrayList<Integer> sequence;

    public ArribaYAbajo() {
        initComponents();
         setLocationRelativeTo(null);
         setSize(650, 200);
          getContentPane().setBackground(new Color(0, 59, 70));
        
    }

    private void initComponents() {
        jLabel1 = new JLabel("Ingrese una serie de números separados por comas:");
        jTextField1 = new JTextField(20);
        jButton1 = new JButton("Procesar");
        sequence = new ArrayList<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ArribaYAbajo");
        setLayout(new BorderLayout());
        

        JPanel topPanel = new JPanel();
        topPanel.add(jLabel1);
        topPanel.add(jTextField1);
        topPanel.add(jButton1);
        add(topPanel, BorderLayout.NORTH);
        

        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
        String entrada = jTextField1.getText();
        String[] numeros = entrada.split(",");

        if (numeros.length > 1) {
            sequence.clear();
            int c = 1;
            int n = 1; // Inicializar n en 1

            for (int i = 0; i < numeros.length; i++) {
                double currentValue = Double.parseDouble(numeros[i]);
                if (i > 0) {
                    double previousValue = Double.parseDouble(numeros[i - 1]);
                    if (currentValue > previousValue) {
                        sequence.add(1);
                    } else {
                        sequence.add(0);
                    }

                    if (i > 1 && sequence.get(i - 1) != sequence.get(i - 2)) {
                        c++;
                    }
                }
                n++; // Incrementar n en cada iteración
            }

            // Restar 1 a n para compensar el incremento inicial
            n--;

            // Calcular resultados
            double valorEsperado = ((2.0 * n) - 1) / 3.0;
            double varianza = ((16.0 * n) - 29.0) / 90.0;
            double valorEstadistico = (c - valorEsperado) / Math.sqrt(varianza);


            // Mostrar los resultados
            JOptionPane.showMessageDialog(this, "Secuencia s = " + sequence + "\n" +
                    "Valor de n = " + n + "\n" +
                    "Valor de c = " + c + "\n" +        
                    "Valor Esperado = " + valorEsperado + "\n" +
                    "Varianza del número de corridas = " + varianza + "\n" +
                    "Valor Estadístico = " + valorEstadistico, "Resultados", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Ingrese al menos dos números para calcular la secuencia s.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArribaYAbajo().setVisible(true);
            }
        });
    }
}