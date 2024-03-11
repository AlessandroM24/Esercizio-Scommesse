// MACALUSO ALESSANDRO 4^C INF. 11/03/2024

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class FinestraInputCliente extends JFrame {
    private final JTextField jtfNomeCliente;
    private final JPanel jpPannello;
    private final JButton btnInvioDatiClienti;
    private final JComboBox<String> jcbClienti;
    private final ArrayList<Cliente> clienti;

    public FinestraInputCliente(JComboBox<String> jcbClienti, ArrayList<Cliente> clienti) {
        this.setName("Input cliente");
        this.setSize(500, 400);
        this.setLocationRelativeTo(null);
        this.jtfNomeCliente = new JTextField();
        this.jcbClienti = jcbClienti;
        this.clienti = clienti;
        this.jpPannello = new JPanel(new GridLayout(1, 2, 1, 1));
        this.btnInvioDatiClienti = new JButton("Registra cliente");
        creaGUI();
        this.setVisible(true);
    }

    /**
     * Crea la GUI impostando i componenti e le interfacce.
     */
    private void creaGUI() {
        this.setIconImage(new ImageIcon(Objects.requireNonNull(FinestraInputCliente.class.getResource("/img/logoInput.jpg"))).getImage());
        this.setTitle("Input cliente");
        this.add(jpPannello);
        jpPannello.add(new JLabel("Nome cliente:"));
        jpPannello.add(jtfNomeCliente);
        this.add(btnInvioDatiClienti, BorderLayout.SOUTH);

        // Impostando l'ascoltatore, è possibile inviare i dati del cliente premendo il bottone o premendo INVIO
        // sul campo di input.
        jtfNomeCliente.addActionListener(new InviaDatiCliente());
        btnInvioDatiClienti.addActionListener(new InviaDatiCliente());
    }


    /**
     * Dopo aver controllato il nome, aggiunge al JComboBox dei clienti il cliente e il suo rispettivo codice univoco.
     * Aggiunge all'ArrayList contenente i clienti, il cliente appena creato.
     */
    public class InviaDatiCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nomeCliente = jtfNomeCliente.getText();

            // Aggiunge il cliente solo se il suo nome non è vuoto e non è composto da soli spazi bianchi.
            if (!nomeCliente.isBlank() && !nomeCliente.isEmpty()) {
                Cliente cliente = new Cliente(nomeCliente, codiceUnivoco());
                clienti.add(cliente);
                jcbClienti.addItem(cliente.getNome() + " - " + cliente.getCodiceUnivoco()); // Aggiunge il cliente come voce del JComboBox.
            } else {
                JOptionPane.showMessageDialog(null, "Formato nome errato.");
            }
            jtfNomeCliente.setText("");
        }

        /**
         * Ottenere un codice univoco da assegnare oltre al nome del cliente.
         *
         * @return il codice univoco.
         */
        private String codiceUnivoco() {
            final String lettere = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            final String cifre = "0123456789";
            StringBuilder stringBuilder = new StringBuilder();
            Random random = new Random();

            for (int i = 0; i < 6; i++) {
                int indiceLetteraCasuale = random.nextInt(lettere.length());
                stringBuilder.append(lettere.charAt(indiceLetteraCasuale));
                int indiceCifraCasuale = random.nextInt(cifre.length());
                stringBuilder.append(cifre.charAt(indiceCifraCasuale));
            }
            return stringBuilder.toString();
        }
    }
}