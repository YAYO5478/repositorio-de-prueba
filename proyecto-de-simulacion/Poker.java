import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Poker extends JFrame implements ActionListener {

    private JTextArea inputTextArea;
    private JButton analizarButton;
    private JTable resultadosTable;

    public Poker() {
        // Configuración de la ventana
        setTitle("Análisis de Números Decimales");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear componentes
        inputTextArea = new JTextArea(5, 20);
        analizarButton = new JButton("Analizar");
        analizarButton.addActionListener(this);
        resultadosTable = new JTable();

        // Configurar la tabla
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Categoría");
        model.addColumn("Frecuencia Observada (Oi)");
        model.addColumn("Frecuencia Esperada (Ei)");
        model.addColumn("(Ei-Oi)^2/Ei");
        model.addRow(new Object[]{"Todos Diferentes (TD)", 0, 0, 0});
        model.addRow(new Object[]{"Exactamente 1 par (1P)", 0, 0, 0});
        model.addRow(new Object[]{"2 Pares (2P)", 0, 0, 0});
        model.addRow(new Object[]{"1 Tercia y 1 Par (TP)", 0, 0, 0});
        model.addRow(new Object[]{"Tercia (T)", 0, 0, 0});
        model.addRow(new Object[]{"Poker (P)", 0, 0, 0});
        model.addRow(new Object[]{"Quintilla (Q)", 0, 0, 0});
        resultadosTable.setModel(model);

        // Configurar el diseño
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JScrollPane(inputTextArea));
        inputPanel.add(analizarButton);

        JPanel tablePanel = new JPanel();
        tablePanel.add(new JScrollPane(resultadosTable));

        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(tablePanel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if (e.getSource() == analizarButton) {
        String inputText = inputTextArea.getText();
        String[] numerosStr = inputText.split("\\s+"); 
        int n = numerosStr.length; // Número total de números ingresados
}
        if (e.getSource() == analizarButton) {
            String inputText = inputTextArea.getText();
            String[] numerosStr = inputText.split("\\s+");

            int todosDiferentesCount = 0;
            int unParCount = 0;
            int dosParesCount = 0;
            int terciaParCount = 0;
            int terciaCount = 0;
            int pokerCount = 0;
            int quintillaCount = 0;
            
            String parteDecimal = "";

            for (String numeroStr : numerosStr) {
                if (numeroStr.matches("^\\d{1,5}(\\.\\d{1,5})?$")) {
                    // Para trabajar solo con los decimales
                    
                    if (numeroStr.contains(".")) {
                        parteDecimal = numeroStr.split("\\.")[1];
                    }

                    int cantidadDecimales = parteDecimal.length();

                    switch (cantidadDecimales) {
                        case 3:
                            if (esTodosDiferentes(parteDecimal)) {
                                todosDiferentesCount++;
                            } else if (tieneUnPar(parteDecimal)) {
                                unParCount++;
                            } else if (tieneTercia(parteDecimal)) {
                                terciaCount++;
                            }
                            break;
                        case 4:
                            if (esTodosDiferentes(parteDecimal)) {
                                todosDiferentesCount++;
                            } else if (tieneUnPar(parteDecimal)) {
                                unParCount++;
                            } else if (tieneDosPares(parteDecimal)) {
                                dosParesCount++;
                            } else if (tieneTercia(parteDecimal)) {
                                terciaCount++;
                            } else if (tienePoker(parteDecimal)) {
                                pokerCount++;
                            }
                            break;
                        case 5:
                            if (esTodosDiferentes(parteDecimal)) {
                                todosDiferentesCount++;
                            } else if (tieneTerciaPar(parteDecimal)) {
                                terciaParCount++;
                            } else if (tieneDosPares(parteDecimal)) {
                                dosParesCount++;
                            } else if (tieneUnPar(parteDecimal)) {
                                unParCount++;
                            } else if (tieneTercia(parteDecimal)) {
                                terciaCount++;
                            } else if (tienePoker(parteDecimal)) {
                                pokerCount++;
                            } else if (tieneQuintilla(parteDecimal)) {
                                quintillaCount++;
                            }
                            break;
                        default:
                            
                            break;
                    }
                }
            }

            // Calcular las frecuencias esperadas
            int cantidadDecimales = parteDecimal.length();
            int n = numerosStr.length;
            double freqEsperadaTodosDiferentes = 0;
            double freqEsperadaUnPar = 0;
            double freqEsperadaDosPares = 0;
            double freqEsperadaTerciaPar = 0;
            double freqEsperadaTercia = 0;
            double freqEsperadaPoker = 0;
            double freqEsperadaQuintilla = 0;
            
            
            

            // Calcular frecuencias esperadas para cada caso, 3, 4 o 5 decimales
            if (todosDiferentesCount > 0) {
                switch (cantidadDecimales) {
                    case 3:
                        freqEsperadaTodosDiferentes = n * 0.72;
                        break;
                    case 4:
                        freqEsperadaTodosDiferentes = n * 0.5040;
                        break;
                    case 5:
                        freqEsperadaTodosDiferentes = n * 0.3024;
                        break;
                }
            }

            if (unParCount > 0) {
                switch (cantidadDecimales) {
                    case 3:
                        freqEsperadaUnPar = n * 0.27;
                        break;
                    case 4:
                        freqEsperadaUnPar = n * 0.4320;
                        break;
                    case 5:
                        freqEsperadaUnPar = n * 0.5040;
                        break;
                }
            }

            if (dosParesCount > 0) {
                switch (cantidadDecimales) {
                    case 4:
                        freqEsperadaDosPares = n * 0.0270;
                        break;
                    case 5:
                        freqEsperadaDosPares = n * 0.1080;
                        break;
                }
            }

            if (terciaParCount > 0) {
                freqEsperadaTerciaPar = n * 0.0090;
            }

            if (terciaCount > 0) {
                switch (cantidadDecimales) {
                    case 3:
                        freqEsperadaTercia = n * 0.01;
                        break;
                    case 4:
                        freqEsperadaTercia = n * 0.0360;
                        break;
                    case 5:
                        freqEsperadaTercia = n * 0.0720;
                        break;
                }
            }

            if (pokerCount > 0) {
                switch (cantidadDecimales) {
                    case 4:
                        freqEsperadaPoker = n * 0.0010;
                        break;
                    case 5:
                        freqEsperadaPoker = n * 0.0045;
                        break;
                }
            }

            if (quintillaCount > 0) {
                freqEsperadaQuintilla = n * 0.0001;
            }

            // Actualizar la tabla con los conteos y las frecuencias esperadas
            DefaultTableModel model = (DefaultTableModel) resultadosTable.getModel();
            model.setValueAt(todosDiferentesCount, 0, 1);
            model.setValueAt(unParCount, 1, 1);
            model.setValueAt(dosParesCount, 2, 1);
            model.setValueAt(terciaParCount, 3, 1);
            model.setValueAt(terciaCount, 4, 1);
            model.setValueAt(pokerCount, 5, 1);
            model.setValueAt(quintillaCount, 6, 1);

            model.setValueAt(freqEsperadaTodosDiferentes, 0, 2);
            model.setValueAt(freqEsperadaUnPar, 1, 2);
            model.setValueAt(freqEsperadaDosPares, 2, 2);
            model.setValueAt(freqEsperadaTerciaPar, 3, 2);
            model.setValueAt(freqEsperadaTercia, 4, 2);
            model.setValueAt(freqEsperadaPoker, 5, 2);
            model.setValueAt(freqEsperadaQuintilla, 6, 2);

           
              // Calcular y actualizar (Ei-Oi)^2/Ei
       double sumaColumna = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            int oi = (int) model.getValueAt(i, 1);
            double ei = (double) model.getValueAt(i, 2);
            double eiMinusOiSquaredOverEi = (ei - oi) * (ei - oi) / ei;
            if (!Double.isNaN(eiMinusOiSquaredOverEi)) {
                model.setValueAt(eiMinusOiSquaredOverEi, i, 3);
                sumaColumna += eiMinusOiSquaredOverEi;
            } else {
                model.setValueAt(0, i, 3); 
            }
        }

        // Agregar la fila "X^2" con la suma de la cuarta columna
        model.addRow(new Object[]{"X^2", "", "", sumaColumna});
    }
}
 


// Función para verificar si tiene tercia y par
    private boolean tieneTerciaPar(String parteDecimal) {
        Map<Character, Integer> frecuenciaDigitos = new HashMap<>();
        for (char c : parteDecimal.toCharArray()) {
            if (Character.isDigit(c)) {
                frecuenciaDigitos.put(c, frecuenciaDigitos.getOrDefault(c, 0) + 1);
            }
        }

        int tercias = 0;
        int pares = 0;

        for (int frecuencia : frecuenciaDigitos.values()) {
            if (frecuencia == 3) {
                tercias++;
            } else if (frecuencia == 2) {
                pares++;
            }
        }

        // Verifica que haya exactamente 1 tercia y 1 par
        return tercias == 1 && pares == 1;
    }

    // Función para verificar si tiene quintilla
    private boolean tieneQuintilla(String parteDecimal) {
        Map<Character, Integer> frecuenciaDigitos = new HashMap<>();
        for (char c : parteDecimal.toCharArray()) {
            if (Character.isDigit(c)) {
                frecuenciaDigitos.put(c, frecuenciaDigitos.getOrDefault(c, 0) + 1);
            }
        }

        int quintillas = 0;

        for (int frecuencia : frecuenciaDigitos.values()) {
            if (frecuencia == 5) {
                quintillas++;
            }
        }

        // Verifica que haya exactamente 5 decimales iguales
        return quintillas == 1;
    }

    // Función para verificar si todos los dígitos son diferentes
    private boolean esTodosDiferentes(String parteDecimal) {
        Set<Character> digitos = new HashSet<>();
        for (char c : parteDecimal.toCharArray()) {
            digitos.add(c);
        }
        return digitos.size() == parteDecimal.length();
    }

    // Función para verificar si tiene exactamente un par
    private boolean tieneUnPar(String parteDecimal) {
        Map<Character, Integer> frecuenciaDigitos = new HashMap<>();
        for (char c : parteDecimal.toCharArray()) {
            if (Character.isDigit(c)) {
                frecuenciaDigitos.put(c, frecuenciaDigitos.getOrDefault(c, 0) + 1);
            }
        }

        int pares = 0;

        for (int frecuencia : frecuenciaDigitos.values()) {
            if (frecuencia == 2) {
                pares++;
            }
        }

        // Verifica que haya exactamente 1 par
        return pares == 1;
    }

    // Función para verificar si tiene dos pares
    private boolean tieneDosPares(String parteDecimal) {
        Map<Character, Integer> frecuenciaDigitos = new HashMap<>();
        for (char c : parteDecimal.toCharArray()) {
            if (Character.isDigit(c)) {
                frecuenciaDigitos.put(c, frecuenciaDigitos.getOrDefault(c, 0) + 1);
            }
        }

        int pares = 0;

        for (int frecuencia : frecuenciaDigitos.values()) {
            if (frecuencia == 2) {
                pares++;
            }
        }

        // Verifica que haya exactamente 2 pares
        return pares == 2;
    }

    // Función para verificar si tiene tercia
    private boolean tieneTercia(String parteDecimal) {
        Map<Character, Integer> frecuenciaDigitos = new HashMap<>();
        for (char c : parteDecimal.toCharArray()) {
            if (Character.isDigit(c)) {
                frecuenciaDigitos.put(c, frecuenciaDigitos.getOrDefault(c, 0) + 1);
            }
        }

        int tercias = 0;

        for (int frecuencia : frecuenciaDigitos.values()) {
            if (frecuencia == 3) {
                tercias++;
            }
        }

        // Verifica que haya exactamente 1 tercia
        return tercias == 1;
    }

    // Función para verificar si tiene poker
    private boolean tienePoker(String parteDecimal) {
        Map<Character, Integer> frecuenciaDigitos = new HashMap<>();
        for (char c : parteDecimal.toCharArray()) {
            if (Character.isDigit(c)) {
                frecuenciaDigitos.put(c, frecuenciaDigitos.getOrDefault(c, 0) + 1);
            }
        }

        int cuatros = 0;

        for (int frecuencia : frecuenciaDigitos.values()) {
            if (frecuencia == 4) {
                cuatros++;
            }
        }

        // Verifica que haya exactamente 4 dígitos iguales
        return cuatros == 1;
    }

    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Poker ventana = new Poker();
            ventana.setVisible(true);
        });
    }
}