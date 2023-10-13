import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PruebaMedias extends JFrame {
    private JTextField valorTextField;
    private JTextArea valoresTextArea;
    private ArrayList<Double> valoresList;
    private double porcentajeError; // Variable para almacenar el porcentaje de error (PE)

    public PruebaMedias() {
        valoresList = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        valorTextField = new JTextField(10);
        JButton agregarButton = new JButton("Agregar");
        JButton calcularMediaButton = new JButton("Calcular Media");
        JButton eliminarUltimoButton = new JButton("Eliminar Último");
        JButton calcularLimitesButton = new JButton("Calcular Límites");
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
        panel.add(eliminarUltimoButton);
        panel.add(calcularLimitesButton);

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

        double media = suma / valoresList.size();

        JOptionPane.showMessageDialog(this, "La media de los valores ingresados es: " + media, "Resultado", JOptionPane.INFORMATION_MESSAGE);
    }

    private void calcularLimites() {
        int n = valoresList.size();

        double Li = 0.5 - (porcentajeError * (1 / (Math.sqrt(12 * n))));
        double Ls = 0.5 + (porcentajeError * (1 / (Math.sqrt(12 * n))));

        String resultado = "Límite Inferior (Li): " + Li + "\nLímite Superior (Ls): " + Ls;
        JOptionPane.showMessageDialog(this, resultado, "Resultados de Límites", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PruebaMedias().setVisible(true);
            }
        });
    }
}
