import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kolmogorov {
    private JFrame frame;
    private JTextField nivelConfianzaTextField;
    private JTextField valoresTextField;
    private JButton calcularButton;
    private JTable tabla;
   

    public Kolmogorov() {
        frame = new JFrame("Prueba de Kolmogorov-Smirnov");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 400);
        frame.setLayout(null);
       Color colorAzulAgua = new Color(173, 216, 230); // Color azul agua
        frame.getContentPane().setBackground(colorAzulAgua);

        JLabel labelPorcentaje = new JLabel("Porcentaje de Aceptación:");
        labelPorcentaje.setBounds(20, 20, 150, 30);
        frame.add(labelPorcentaje);
        
        nivelConfianzaTextField = new JTextField();
        nivelConfianzaTextField.setBounds(175, 20, 100, 30);
         frame.add(nivelConfianzaTextField);
         
        JLabel labelValores = new JLabel("Valores:");
        labelValores.setBounds(300, 20, 150, 30);
        frame.add(labelValores);
        
        valoresTextField = new JTextField();
        valoresTextField.setBounds(350, 20, 250, 30);
        frame.add(valoresTextField);

        calcularButton = new JButton("Calcular");
        calcularButton.setBounds(700, 20, 150, 30);

        frame.add(calcularButton);

        tabla = new JTable();
        tabla.setBounds(20, 70, 830, 300);
        tabla.setBackground(new Color(0, 59, 70));
        tabla.setForeground(Color.WHITE); // Texto blanco
        frame.add(tabla);

        calcularButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener nivel de confianza
                String confianzaStr = nivelConfianzaTextField.getText();
                

                if (confianzaStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "El campo de nivel de confianza está vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        double confianza = Double.parseDouble(confianzaStr);

                        // Obtener valores separados por comas
                        String valoresStr = valoresTextField.getText();
                        String[] valoresArray = valoresStr.split(",");
                        int n = valoresArray.length;

                        // Ordenar los valores de menor a mayor
                        double[] valoresNumericos = Arrays.stream(valoresArray)
                                .mapToDouble(Double::parseDouble)
                                .sorted()
                                .toArray();

                        // Crear una lista de listas para almacenar los datos
                        List<List<String>> data = new ArrayList<>();

                        // Agregar los nombres de las columnas
                        List<String> columnaNombres = new ArrayList<>();
                        columnaNombres.add("i");
                        columnaNombres.add("i/n");
                        columnaNombres.add("ri");
                        columnaNombres.add("(i-1)/n");
                        columnaNombres.add("(i/n)-ri");
                        columnaNombres.add("ri-((i-1)/n)");
                        data.add(columnaNombres);

                        // Llenar la matriz con los cálculos
                        double Dmas = 0.0;
                        double Dmenos = 0.0;

                        for (int i = 0; i < n; i++) {
                            List<String> rowData = new ArrayList<>();
                            rowData.add(Integer.toString(i + 1));  // i+1 instead of i
                            rowData.add(String.format("%.2f", (double) (i + 1) / n));  // i+1 instead of i
                            double ri = valoresNumericos[i];
                            rowData.add(String.format("%.2f", ri));
                            double iMinus1DividedByN = (i * 1.0) / n;  // i instead of (i-1)
                            rowData.add(String.format("%.2f", iMinus1DividedByN));
                            double diPos = (double) (i + 1) / n - ri;  // i+1 instead of i
                            rowData.add(String.format("%.2f", diPos));
                            double diNeg = ri - (i * 1.0) / n;  // i instead of (i-1)
                            rowData.add(String.format("%.2f", diNeg));

                            Dmas = Math.max(Dmas, diPos);
                            Dmenos = Math.max(Dmenos, diNeg);

                            data.add(rowData);
                        }

                        // Calculate D based on Dmas and Dmenos
                        double D = Math.max(Dmas, Dmenos);

                        // Add the last row (results) here
                        List<String> resultados = new ArrayList<>();
                        resultados.add("D+ = " + String.format("%.2f", Dmas));
                        resultados.add("D- = " + String.format("%.2f", Dmenos));
                        resultados.add("D = " + String.format("%.2f", D));
                        data.add(resultados);

                        // Create the table model and assign the data
                        DefaultTableModel model = new DefaultTableModel(
                            data.stream().map(row -> row.toArray(new String[0])).toArray(String[][]::new),
                            data.get(0).toArray(new String[0])
                        );
                        tabla.setModel(model);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "El nivel de confianza no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
  frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Kolmogorov();
            }
        });
    }
}