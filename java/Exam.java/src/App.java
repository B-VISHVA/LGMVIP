import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class App extends JFrame implements ActionListener {

    private JLabel amountLabel, fromLabel, toLabel, resultLabel;
    private JTextField amountTextField, resultTextField;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton jButton;

    private final String[] CURRENCIES = { "INR", "AFN", "AZN", "BTD", "KHR", "CNY", "HDK", "JPY", "MYR", "LRK", "PKR", 
             "THB", "SAR", "OMR", "ILS", "EUR", "RUB", "CHF", "GBP", "USD", "MXN", "NZD"}; 
     private final double[] EXCHANGE_RATES = { 1, 0.95, 48.67, 0.79, 0.020, 11.86, 10.63, 0.61, 18.70, 0.22, 0.37, 
             2.32, 22.01, 214.45, 23.89, 87.65, 1.28, 88.79, 100.47, 82.73, 4.18, 0.73 }; 

    Border Line = BorderFactory.createLineBorder(Color.white);

    public App() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout()); // Use GridBagLayout
        panel.setBorder(Line);
        panel.setBackground(new Color(124, 160, 200));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        amountLabel = new JLabel("Enter Amt");
        fromLabel = new JLabel("Amt From");
        toLabel = new JLabel("Change To");
        resultLabel = new JLabel("Output");

        amountTextField = new JTextField();
        resultTextField = new JTextField();
        resultTextField.setEditable(false);

        fromComboBox = new JComboBox<>(CURRENCIES);
        toComboBox = new JComboBox<>(CURRENCIES);

        jButton = new JButton("Calculate");
        jButton.addActionListener(this);

        // Add components to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(amountLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(amountTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(fromLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(fromComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(toLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(toComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(resultLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        panel.add(resultTextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(jButton, gbc);

        add(panel, BorderLayout.CENTER);
        amountTextField.setText("100");
        resultTextField.setText("0,00,000");

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jButton) {
            try {

                double amount = Double.parseDouble(amountTextField.getText());
                int fromIndex = fromComboBox.getSelectedIndex();
                int toIndex = toComboBox.getSelectedIndex();

                double result = amount * EXCHANGE_RATES[fromIndex] / EXCHANGE_RATES[toIndex];
                DecimalFormat df = new DecimalFormat("#.##");

                resultTextField.setText(df.format(result));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please Enter Valid Amount");
            }
        }
    }

    public static void main(String[] args) {

        try {

            App currencyConverter = new App();

            currencyConverter.setTitle("---Currency_Converter_App---");
            currencyConverter.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            currencyConverter.setSize(300, 450);
            currencyConverter.setLocationRelativeTo(null);
            currencyConverter.setVisible(true);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }
}

