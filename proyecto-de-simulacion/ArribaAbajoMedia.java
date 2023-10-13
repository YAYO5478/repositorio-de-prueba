import java.awt.Color;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ArribaAbajoMedia extends JFrame {
    private JTextField textField;
    private JButton calcularButton;
    private JLabel resultadosLabel;
    private JLabel valorEsperadoLabel;
    private JLabel varianzaLabel;
    private JLabel valorEstadisticoLabel;

    public ArribaAbajoMedia() {
        super("Calculadora de Números");
         
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        

        JPanel panel = new JPanel();
 panel.setBackground(new Color(0,59,70));
        textField = new JTextField(20);
        calcularButton = new JButton("Calcular");
        resultadosLabel = new JLabel("S = ");  
        valorEsperadoLabel = new JLabel("Valor Esperado: ");
        varianzaLabel = new JLabel("Varianza del número de corridas: ");
        valorEstadisticoLabel = new JLabel("Valor Estadístico: ");
        
resultadosLabel.setForeground(Color.WHITE);
        valorEsperadoLabel.setForeground(Color.WHITE);
        varianzaLabel.setForeground(Color.WHITE);
        valorEstadisticoLabel.setForeground(Color.WHITE);
        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calcularOperacion();
            }
        });

        panel.add(textField);
        panel.add(calcularButton);
        panel.add(resultadosLabel);
        panel.add(valorEsperadoLabel);
        panel.add(varianzaLabel);
        panel.add(valorEstadisticoLabel);

        add(panel);
    }

    private void calcularOperacion() {
        String entrada = textField.getText();
        String[] numeros = entrada.split(",");
        int n = numeros.length;
        List<Integer> resultados = new ArrayList<Integer>();

        int n1 = 0;
        int n0 = 0;

        for (String numero : numeros) {
            try {
                double valor = Double.parseDouble(numero);
                int resultado = (valor >= 0.5) ? 1 : 0;
                resultados.add(resultado);

                if (resultado == 1) {
                    n1++;
                } else {
                    n0++;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Entrada no válida: " + numero);
                return;
            }
        }

      resultadosTextField.setText("S = " + resultados);

        // Calcular el valor esperado
        double valorEsperado = (2.0 * n0 * n1) / n + 0.5;
        valorEsperadoLabel.setText("Valor Esperado: " + valorEsperado);

        // Calcular la varianza del número de corridas
        double varianza = (2.0 * n0 * n1 * (2.0 * n0 * n1 - n)) / (n * n * (n - 1));
        varianzaLabel.setText("Varianza del número de corridas: " + varianza);

        // Calcular el valor estadístico
        double valorEstadistico = (varianza-valorEsperado)/(Math.sqrt(varianza));
        valorEstadisticoLabel.setText("Valor Estadístico: " + valorEstadistico);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ArribaAbajoMedia calc = new ArribaAbajoMedia();
                calc.setVisible(true);
            }
        });
    }
}