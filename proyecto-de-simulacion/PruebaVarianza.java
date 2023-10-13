import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Component;
import java.util.ArrayList;

public class PruebaVarianza extends JFrame {
    private JTextField valorTextField;
    private JTextArea valoresTextArea;
    private double[][] poracep;
    private double[][] porerr;
    private ArrayList<Double> valoresList;
    private double porcentajeError; // Variable para almacenar el porcentaje de error (PE)
    private double media;

    public PruebaVarianza() {
        valoresList = new ArrayList<>();
        // Inicializa las matrices poracep y porerr con los valores proporcionados
        poracep = new double[][] {
            {0.90, 0.95, 0.975, 0.99, 0.995, 0.999},
            {1, 0.016, 0.004, 0.001, 0.000, 0.000, 0.000},
            {2, 0.211, 0.103, 0.051, 0.020, 0.010, 0.002},
            {3, 0.584, 0.352, 0.216, 0.115, 0.072, 0.024},
            {4, 1.064, 0.711, 0.484, 0.297, 0.207, 0.091},
            {5, 1.610, 1.145, 0.831, 0.554, 0.412, 0.210},
            {6, 2.204, 1.635, 1.237, 0.872, 0.676, 0.381},
            {7, 2.833, 2.167, 1.690, 1.239, 0.989, 0.598},
            {8, 3.490, 2.733, 2.180, 1.646, 1.344, 0.857},
            {9, 4.168, 3.325, 2.700, 2.088, 1.735, 1.152},
            {10, 4.865, 3.940, 3.247, 2.558, 2.156, 1.479},
            {11, 5.578, 4.575, 3.816, 3.053, 2.603, 1.834},
            {12, 6.304, 5.226, 4.404, 3.571, 3.074, 2.214},
            {13, 7.042, 5.892, 5.009, 4.107, 3.565, 2.617},
            {14, 7.790, 6.571, 5.629, 4.660, 4.075, 3.041},
            {15, 8.547, 7.261, 6.262, 5.229, 4.601, 3.483},
            {16, 9.312, 7.962, 6.908, 5.812, 5.142, 3.942},
            {17, 10.085, 8.672, 7.564, 6.408, 5.697, 4.416},
            {18, 10.865, 9.390, 8.231, 7.015, 6.265, 4.905},
            {19, 11.651, 10.117, 8.907, 7.633, 6.844, 5.407},
            {20, 12.443, 10.851, 9.591, 8.260, 7.434, 5.921},
            {21, 13.240, 11.591, 10.283, 8.897, 8.034, 6.447},
            {22, 14.041, 12.338, 10.982, 9.542, 8.643, 6.983},
            {23, 14.848, 13.091, 11.689, 10.196, 9.260, 7.529},
            {24, 15.659, 13.848, 12.401, 10.856, 9.886, 8.085},
            {25, 16.473, 14.611, 13.120, 11.524, 10.520, 8.649},
            {26, 17.292, 15.379, 13.844, 12.198, 11.160, 9.222},
            {27, 18.114, 16.151, 14.573, 12.879, 11.808, 9.803},
            {28, 18.939, 16.928, 15.308, 13.565, 12.461, 10.391},
            {29, 19.768, 17.708, 16.047, 14.256, 13.121, 10.986},
            {30, 20.599, 18.493, 16.791, 14.953, 13.787, 11.588},
            {40, 29.051, 26.509, 24.433, 22.164, 20.707, 17.916},
            {50, 37.689, 34.764, 32.357, 29.707, 27.991, 24.674},
            {60, 46.459, 43.188, 40.482, 37.485, 35.534, 31.738},
            {70, 55.329, 51.739, 48.758, 45.442, 43.275, 39.036},
            {80, 64.278, 60.391, 57.153, 53.540, 51.172, 46.520},
            {90, 73.291, 69.126, 65.647, 61.754, 59.196, 54.155},
            {100, 82.358, 77.929, 74.222, 70.065, 67.328, 61.918}
        };
        porerr = new double[][] {
            {0.10, 0.95, 0.975, 0.99, 0.995, 0.999},
            {1, 2.706, 3.841, 5.024, 6.635, 7.879, 10.828},
            {2, 4.605, 5.991, 7.378, 9.210, 10.597, 13.816},
            {3, 6.251, 7.815, 9.348, 11.345, 12.838, 16.266},
            {4, 7.779, 9.488, 11.143, 13.277, 14.860, 18.467},
            {5, 9.236, 11.070, 12.833, 15.086, 16.750, 20.515},
            {6, 10.645, 12.592, 14.449, 16.812, 18.548, 22.458},
            {7, 12.017, 14.067, 16.013, 18.475, 20.278, 24.322},
            {8, 13.362, 15.507, 17.535, 20.090, 21.955, 26.124},
            {9, 14.684, 16.919, 19.023, 21.666, 23.589, 27.877},
            {10, 15.987, 18.307, 20.483, 23.209, 25.188, 29.588},
            {11, 17.275, 19.675, 21.920, 24.725, 26.757, 31.264},
            {12, 18.549, 21.026, 23.337, 26.217, 28.300, 32.909},
            {13, 19.812, 22.362, 24.736, 27.688, 29.819, 34.528},
            {14, 21.064, 23.685, 26.119, 29.141, 31.319, 36.123},
            {15, 22.307, 24.996, 27.488, 30.578, 32.801, 37.697},
            {16, 23.542, 26.296, 28.845, 32.000, 34.267, 39.252},
            {17, 24.769, 27.587, 30.191, 33.409, 35.718, 40.790},
            {18, 25.989, 28.869, 31.526, 34.805, 37.156, 42.312},
            {19, 27.204, 30.144, 32.852, 36.191, 38.582, 43.820},
            {20, 28.412, 31.410, 34.170, 37.566, 39.997, 45.315},
            {21, 29.615, 32.671, 35.479, 38.932, 41.401, 46.797},
            {22, 30.813, 33.924, 36.781, 40.289, 42.796, 48.268},
            {23, 32.007, 35.172, 38.076, 41.638, 44.181, 49.728},
            {24, 33.196, 36.415, 39.364, 42.980, 45.559, 51.179},
            {25, 34.382, 37.652, 40.646, 44.314, 46.928, 52.620},
            {26, 35.563, 38.885, 41.923, 45.642, 48.290, 54.052},
            {27, 36.741, 40.113, 43.195, 46.963, 49.645, 55.476},
            {28, 37.916, 41.337, 44.461, 48.278, 50.993, 56.892},
            {29, 39.087, 42.557, 45.722, 49.588, 52.336, 58.301},
            {30, 40.256, 43.773, 46.979, 50.892, 53.672, 59.703},
            {40, 51.805, 55.758, 59.342, 63.691, 66.766, 73.402},
            {50, 63.167, 67.505, 71.420, 76.154, 79.490, 86.661},
            {60, 74.397, 79.082, 83.298, 88.379, 91.952, 99.607},
            {70, 85.527, 90.531, 95.023, 100.425, 104.215, 112.317},
            {80, 96.578, 101.879, 106.629, 112329, 116.321, 124.839},
            {90, 107.565, 113.145, 118.136, 124.116, 128.299, 137.208},
            {100, 118.498, 124.342, 129.561, 135.807, 140.169, 149.449}
        };
        initComponents();
    }

    private void initComponents() {
        valorTextField = new JTextField(10);
        JButton agregarButton = new JButton("Agregar");
        JButton calcularMediaButton = new JButton("Calcular Media");
        JButton calcularVarianzaButton = new JButton("Calcular Varianza");
        JButton eliminarUltimoButton = new JButton("Eliminar Último");
        JButton calcularLimitesButton = new JButton("Calcular Límites M");
        JButton calcularLimitesVButton = new JButton("Calcular Límites V"); // Nuevo botón

        valoresTextArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(valoresTextArea);

        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                agregarValor();
            }
        });

        calcularMediaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                calcularMedia();
            }
        });

        calcularVarianzaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                calcularVarianza();
            }
        });

        eliminarUltimoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                eliminarUltimoValor();
            }
        });

        calcularLimitesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                calcularLimites();
            }
        });

        calcularLimitesVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                calcularLimitesV();
            }
        });

        // Configura la disposición de los componentes en un panel
        JPanel panel = new JPanel();
        panel.add(new JLabel("Porcentaje de Aceptación (PA):"));

        // Agrega botones para diferentes valores de PA
        panel.add(crearBotonPorcentajeAceptacion("90", 1.645));
        panel.add(crearBotonPorcentajeAceptacion("95", 1.960));
        panel.add(crearBotonPorcentajeAceptacion("98", 2.326));
        panel.add(crearBotonPorcentajeAceptacion("99", 2.576));

        panel.add(new JLabel("Valor:"));
        panel.add(valorTextField);
        panel.add(agregarButton);
        panel.add(calcularMediaButton);
        panel.add(calcularVarianzaButton);
        panel.add(eliminarUltimoButton);
        panel.add(calcularLimitesButton);
        panel.add(calcularLimitesVButton);

        // Configura la ventana
        setTitle("Prueba de Medias");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(panel, "North");
        getContentPane().add(scrollPane);

        pack();
    }

    private JButton crearBotonPorcentajeAceptacion(final String pa, final double pe) {
        JButton boton = new JButton(pa + "%");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                porcentajeError = pe;
                JOptionPane.showMessageDialog(null, "Porcentaje de Aceptación seleccionado: " + pa + "%", "Selección de PA", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        return boton;
    }

    private void agregarValor() {
        try {
            double valor = Double.parseDouble(valorTextField.getText());
            valoresList.add(valor);
            actualizarValoresTextArea();
            valorTextField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void eliminarUltimoValor() {
        if (!valoresList.isEmpty()) {
            valoresList.remove(valoresList.size() - 1);
            actualizarValoresTextArea();
        }
    }

    private void actualizarValoresTextArea() {
        valoresTextArea.setText("");
        for (double valor : valoresList) {
            valoresTextArea.append(Double.toString(valor) + "\n");
        }
    }

    private void calcularMedia() {
        double suma = 0;
        for (double valor : valoresList) {
            suma += valor;
        }

        media = suma / valoresList.size();

        JOptionPane.showMessageDialog(this, "La media de los valores ingresados es: " + media, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void calcularLimites() {
        int n = valoresList.size();

        double Li = 0.5 - (porcentajeError * (1 / Math.sqrt(12 * n)));
        double Ls = 0.5 + (porcentajeError * (1 / Math.sqrt(12 * n)));

        String resultado = "Límite Inferior (Li): " + Li + "\nLímite Superior (Ls): " + Ls;
        JOptionPane.showMessageDialog(this, resultado, "Resultados de Límites", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void calcularVarianza() {
        double suma = 0;
        int n = valoresList.size();

        for (double valor : valoresList) {
            double resta = valor - media;
            double cuadradoResta = resta * resta;
            suma += cuadradoResta;
        }

        double varianza = suma / (n - 1); 

        JOptionPane.showMessageDialog(this, "La Varianza de los valores ingresados es: " + varianza, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void calcularLimitesV() {
        int gradosLibertad = valoresList.size() - 1;

        if (gradosLibertad < 1 || gradosLibertad >= poracep.length) {
            JOptionPane.showMessageDialog(this, "El número de grados de libertad no está en el rango válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double alpha = Double.parseDouble(getAlphaValue()); // Obtiene el valor de α del botón
        int porcentajeAceptacion = buscarPorcentajeAceptacion(porerr[0], alpha); // Busca el porcentaje en la fila 0 de porerr
        double lsv = (porerr[gradosLibertad][porcentajeAceptacion] / 12.0) / (gradosLibertad);
        int porcentajeAceptacionAce = buscarPorcentajeAceptacion(poracep[0], alpha); // Busca el porcentaje en la fila 0 de poracep
        double liv = (1 - poracep[gradosLibertad][porcentajeAceptacionAce] / 12.0) / (gradosLibertad);

        JOptionPane.showMessageDialog(this, "Valor de LSV: " + lsv + "\nValor de LIV: " + liv, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private String getAlphaValue() {
        // Obtiene el valor de α del botón que fue presionado
        for (Component component : getContentPane().getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
                if (button.isEnabled() && button.getModel().isArmed()) {
                    return button.getText().replace("%", "");
                }
            }
        }
        return "0"; // Valor por defecto si no se encuentra un botón presionado
    }

    private int buscarPorcentajeAceptacion(double[] fila, double alpha) {
        // Busca el valor de α en la fila y devuelve el índice correspondiente
        for (int i = 1; i < fila.length; i++) {
            if (fila[i] == alpha) {
                return i;
            }
        }
        return -1; // Valor por defecto si no se encuentra en la fila
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PruebaVarianza().setVisible(true);
            }
        });
    }
}
