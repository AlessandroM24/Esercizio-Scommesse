// MACALUSO ALESSANDRO 4^C INF. 11/03/2024

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class FinestraPrincipale extends JFrame {

    // Font per i vari componenti.
    private final Font FONT = new Font("Arial", Font.PLAIN, 16);

    private final JComboBox<String> jcbClienti; // JComboBox contenente i clienti.
    HashMap<String, Integer> hmScommesseTipo; // HashMap contenente chiave valore di ciascuno sport (calcio=0 ecc..).
    private final JComboBox<String> jcbSports; // JComboBox contenente gli sport.
    private final JPanel jpPannelloSud; // JPanel contenente i bottoni e i JComboBox (posto in basso).
    private final JPanel jpPannelloImmagini; // JPanel a cui vengono aggiunte le immagini degli sport (bottoni).
    private final JButton btnAggiungiCliente; // JButton per aggiungere un cliente.
    private final JButton btnScommettere; // JButton per scommettere (se presente almeno un cliente).
    private final JButton btnClienteMaggiorDenaro; // JButton per ottenere il cliente che ha vinto di più.
    private final JButton btnNumTotScommesseTipo; // JButton per ottenere l'HashMap di tutti gli sport.
    private final ArrayList<Scommessa> scommesse; // ArrayList contenente tutte le scommesse (oggetti Scommessa).
    private final ArrayList<Cliente> clienti; // ArrayList contenente tutti i clienti (oggetti Cliente).
    private final JButton btnCifraTotale; // JButton per ottenere la cifra totale puntata dal cliente.
    private final String[] SPORTS = {"Calcio", "Basket", "Ciclismo", "Nuoto", "Atletica"};
    // Array contenente tutti gli sport. (utilizzato per "jcbSports" e "hmScommesseTipo").

    public FinestraPrincipale() {
        this.setSize(1100, 750);
        String percorsoImmagine = "/img/FinestraPrincipale.jpg";
        this.setIconImage(new ImageIcon(Objects.requireNonNull(FinestraPrincipale.class.getResource(percorsoImmagine))).getImage());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.btnScommettere = new JButton("Scommetti");
        this.jcbClienti = new JComboBox<>();
        this.clienti = new ArrayList<>();
        this.hmScommesseTipo = new HashMap<>();
        formaHashMap(hmScommesseTipo); // Inserisce le coppie chiave-valore per ciascuno sport.

        this.btnCifraTotale = new JButton("Scommesse per tipo (GIOCATORE SELEZ.)");
        this.btnNumTotScommesseTipo = new JButton("Scommesse per tipo (TUTTO)");
        this.btnClienteMaggiorDenaro = new JButton("Cliente che ha vinto di più");
        this.jpPannelloImmagini = new JPanel(new FlowLayout(FlowLayout.LEFT));
        this.jcbSports = new JComboBox<>(SPORTS);
        this.scommesse = new ArrayList<>();
        this.btnAggiungiCliente = new JButton("Aggiungi cliente");
        this.jpPannelloSud = new JPanel(new GridLayout(3, 2, 1, 1));
        creaGUI();
        this.setVisible(true);
    }

    /**
     * Crea la GUI impostando i componenti, le interfacce e i font.
     */
    private void creaGUI() {
        this.setTitle("Scommesse");
        this.add(jpPannelloImmagini, BorderLayout.CENTER);
        this.add(jpPannelloSud, BorderLayout.SOUTH);
        jpPannelloSud.add(jcbClienti);
        jpPannelloSud.add(jcbSports);
        jpPannelloSud.add(btnAggiungiCliente);
        jpPannelloSud.add(btnScommettere);
        jpPannelloSud.add(btnClienteMaggiorDenaro);
        jpPannelloSud.add(btnNumTotScommesseTipo);
        jpPannelloSud.add(btnCifraTotale, BorderLayout.CENTER);


        // Imposto il font dei vari componenti.
        jcbClienti.setFont(FONT);
        jcbSports.setFont(FONT);
        btnScommettere.setFont(FONT);
        btnAggiungiCliente.setFont(FONT);
        btnCifraTotale.setFont(FONT);
        btnNumTotScommesseTipo.setFont(FONT);
        btnClienteMaggiorDenaro.setFont(FONT);


        btnClienteMaggiorDenaro.addActionListener(new ClienteMaggiorDenaro());
        btnAggiungiCliente.addActionListener(new AggiungiCliente());
        btnScommettere.addActionListener(new Scommetti());
        btnNumTotScommesseTipo.addActionListener(new NumTotScommesseTipo());
        btnCifraTotale.addActionListener(new NumScommesseTipoCliente());

    }


    /**
     * Classe utilizzata per stampare l'HashMap delle frequenze degli sport del giocatore selezionato.
     */
    public class NumScommesseTipoCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (clienti.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Aggiungere almeno un cliente.");
            } else {
                HashMap<String, Integer> hmScommesseClienteSelezionato = new HashMap<>();
                formaHashMap(hmScommesseClienteSelezionato);

                int indiceClienteSelezionato = jcbClienti.getSelectedIndex();
                Cliente clienteSelezionato = clienti.get(indiceClienteSelezionato);

                for (Scommessa scommessa : scommesse) {
                    if (scommessa.getCliente().getCodiceUnivoco().equals(clienteSelezionato.getCodiceUnivoco())) {
                        int valore = hmScommesseClienteSelezionato.get(scommessa.getSport());
                        hmScommesseClienteSelezionato.put(scommessa.getSport(), valore + 1);
                    }
                }
                JOptionPane.showMessageDialog(null, hmScommesseClienteSelezionato);
            }
        }
    }

    /**
     * Forma l'HashMap con i valori sport-numeroScommessa ("calcio"=0, "basket"=0, ecc...)
     *
     * @param hmScommesseTipo HashMap da formare.
     */
    private void formaHashMap(HashMap<String, Integer> hmScommesseTipo) {
        for (String sport : SPORTS) {
            hmScommesseTipo.put(sport, 0);
        }
    }

    /**
     * Classe utilizzata per stampare l'HashMap delle frequenze degli sport di tutti i giocatori.
     */
    public class NumTotScommesseTipo implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, hmScommesseTipo);
        }
    }

    /**
     * Classe utilizzata per stampare il cliente che ha vinto più soldi.
     */
    public class ClienteMaggiorDenaro implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Se non ci sono scommesse, avvisa di scommettere almeno una volta.
            if (scommesse.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Scommettere almeno una volta.");
            } else { // Se è presente almeno una scommessa, stampa il cliente che ha vinto più soldi.
                Cliente clienteGuadagnoSuperiore = null;
                double guadagnoMaggiore = Double.MIN_VALUE;

                for (Scommessa scommessa : scommesse) {
                    Cliente cliente = scommessa.getCliente();
                    if (cliente.getGuadagno() > guadagnoMaggiore) {
                        guadagnoMaggiore = cliente.getGuadagno();
                        clienteGuadagnoSuperiore = cliente;
                    }
                }
                JOptionPane.showMessageDialog(null, clienteGuadagnoSuperiore);
            }
        }
    }


    /**
     * Classe utilizzata per far comparire la finestra per inserire il nome del giocatore.
     */
    public class AggiungiCliente implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            new FinestraInputCliente(jcbClienti, clienti);
        }
    }

    /**
     * Classe utilizzata per far comparire la finestra di input della scommessa.
     * SOLO SE è presente almeno un cliente.
     */
    public class Scommetti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (jcbClienti.getItemCount() > 0) {
                new FinestraScommessa(Objects.requireNonNull(jcbSports.getSelectedItem()).toString(),
                        scommesse, jpPannelloImmagini, jcbClienti.getSelectedIndex(), clienti, hmScommesseTipo);
            } else {
                JOptionPane.showMessageDialog(null, "Inserire almeno un cliente per scommettere.");
            }
        }
    }
}