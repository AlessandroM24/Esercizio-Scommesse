// MACALUSO ALESSANDRO 4^C INF. 11/03/2024

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class FinestraScommessa extends JFrame {

    private final JPanel jpInputDatiScommessa; // Pannello che contiene i campi di input della scommessa.
    private final String sport; // Sport selezionato dal JComboBox di FinestraPrincipale.
    Random random = new Random(); // Random utilizzato per decidere se il cliente ha vinto o ha perso la scommessa.
    private final JPanel jpPannelloImmagini;
    private final Cliente cliente;
    HashMap<String, Integer> hmScommesseTipo;

    JButton bottoneImmagine = new JButton();
    private static int indiceImmagine = 0; // Variabile utilizzata per essere assegnata come nome al bottone (con immagine)
    // così da poter trovare la Scommessa in "scommesse" (ArrayList)

    // Campi di input per tutte le scommesse:
    private final JTextField jtfData;
    private final JTextField jtfPuntata;
    private final JTextField jtfQuota;
    private final JButton jbCaricaScommessa;
    private final ArrayList<Scommessa> scommesse;

    // SOLO per calcio e basket:
    private final JTextField jtfSquadra1;
    private final JTextField jtfSquadra2;
    private final JTextField jtfRisultato;

    // SOLO per ciclismo:
    private final JTextField jtfCiclista1;
    private final JTextField jtfCiclista2;
    private final JTextField jtfCiclista3;

    // SOLO per atletica:
    private final JTextField jtfSpecialita;
    private final JTextField jtfVincitoreAtletica;

    // SOLO per nuoto:
    private final JTextField jtfStile;
    private final JTextField jtfDistanza;
    private final JTextField jtfVincitoreNuoto;

    public FinestraScommessa(String sport, ArrayList<Scommessa> scommesse, JPanel jpPannelloImmagini, int clienteSelezionato,
                             ArrayList<Cliente> clienti, HashMap<String, Integer> hmScommesseTipo) {
        this.setSize(750, 650);
        this.setLocationRelativeTo(null);
        this.sport = sport;
        this.scommesse = scommesse;
        this.jpPannelloImmagini = jpPannelloImmagini;
        this.jpInputDatiScommessa = new JPanel(new GridLayout(3, 2, 1, 1));
        this.jtfData = new JTextField();
        this.jbCaricaScommessa = new JButton("Carica scommessa");
        this.jtfPuntata = new JTextField();
        this.jtfQuota = new JTextField();

        this.hmScommesseTipo = hmScommesseTipo;

        this.jtfSquadra1 = new JTextField();
        this.jtfSquadra2 = new JTextField();
        this.jtfRisultato = new JTextField();
        this.cliente = clienti.get(clienteSelezionato);
        this.jtfCiclista1 = new JTextField();
        this.jtfCiclista2 = new JTextField();
        this.jtfCiclista3 = new JTextField();
        this.jtfSpecialita = new JTextField();
        this.jtfVincitoreAtletica = new JTextField();
        this.jtfStile = new JTextField();
        this.jtfDistanza = new JTextField();
        this.jtfVincitoreNuoto = new JTextField();

        creaGUI();
        this.setVisible(true);
    }

    /**
     * Crea la GUI impostando i clienti e le interfacce.
     */
    private void creaGUI() {
        this.setTitle("Scommessa " + sport);
        this.add(jpInputDatiScommessa);

        // Aggiunge i campi di input di default (utilizzati da tutti i tipi di sport).
        jpInputDatiScommessa.add(new JLabel("Data:"));
        jpInputDatiScommessa.add(jtfData);
        jpInputDatiScommessa.add(new JLabel("Puntata:"));
        jpInputDatiScommessa.add(jtfPuntata);
        jpInputDatiScommessa.add(new JLabel("Quota:"));
        jpInputDatiScommessa.add(jtfQuota);

        // Aggiunge i campi di input a seconda dello sport selezionato nel JComboBox.
        if (sport.equalsIgnoreCase("Calcio") || sport.equalsIgnoreCase("Basket")) {
            aggiungiCampiCalcioBasket();
        } else if (sport.equalsIgnoreCase("Ciclismo")) {
            aggiungiCampiCiclismo();
        } else if (sport.equalsIgnoreCase("Atletica")) {
            aggiungiCampiAtletica();
        } else {
            aggiungiCampiNuoto();
        }

        this.add(jbCaricaScommessa, BorderLayout.SOUTH);
        jbCaricaScommessa.addActionListener(new InviaScommessa());

    }

    /**
     * Aggiunge i campi di input relativi allo sport CALCIO e allo sport BASKET
     */
    private void aggiungiCampiCalcioBasket() {
        jpInputDatiScommessa.setLayout(new GridLayout(6, 2, 1, 1));

        jpInputDatiScommessa.add(new JLabel("Squadra 1:"));
        jpInputDatiScommessa.add(jtfSquadra1);
        jpInputDatiScommessa.add(new JLabel("Squadra 2:"));
        jpInputDatiScommessa.add(jtfSquadra2);
        jpInputDatiScommessa.add(new JLabel("Risultato:"));
        jpInputDatiScommessa.add(jtfRisultato);

    }

    /**
     * Aggiunge i campi di input relativi allo sport CICLISMO.
     */
    private void aggiungiCampiCiclismo() {
        jpInputDatiScommessa.setLayout(new GridLayout(6, 2, 1, 1));

        jpInputDatiScommessa.add(new JLabel("Ciclista 1:"));
        jpInputDatiScommessa.add(jtfCiclista1);
        jpInputDatiScommessa.add(new JLabel("Ciclista 2:"));
        jpInputDatiScommessa.add(jtfCiclista2);
        jpInputDatiScommessa.add(new JLabel("Ciclista 3:"));
        jpInputDatiScommessa.add(jtfCiclista3);
    }

    /**
     * Aggiunge i campi di input relativi allo sport ATLETICA.
     */
    private void aggiungiCampiAtletica() {
        jpInputDatiScommessa.setLayout(new GridLayout(5, 2, 1, 1));

        jpInputDatiScommessa.add(new JLabel("Specialità:"));
        jpInputDatiScommessa.add(jtfSpecialita);
        jpInputDatiScommessa.add(new JLabel("Vincitore:"));
        jpInputDatiScommessa.add(jtfVincitoreAtletica);
    }

    /**
     * Aggiunge i campi di input relativi allo sport NUOTO.
     */
    private void aggiungiCampiNuoto() {
        jpInputDatiScommessa.setLayout(new GridLayout(6, 2, 1, 1));

        jpInputDatiScommessa.add(new JLabel("Stile:"));
        jpInputDatiScommessa.add(jtfStile);
        jpInputDatiScommessa.add(new JLabel("Distanza (>=50):"));
        jpInputDatiScommessa.add(jtfDistanza);
        jpInputDatiScommessa.add(new JLabel("Vincitore:"));
        jpInputDatiScommessa.add(jtfVincitoreNuoto);
    }


    /**
     * Classe utilizzata per verificare che i campi di input (a seconda dello sport) siano corretti
     */
    public class InviaScommessa implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean inputCorretto = true; // Flag per verificare che l'input sia corretto. (di tutti i campi)
            boolean bloccaImmagineNuoto = false; // Flag utilizzato per non far comparire l'immagine del nuoto
            // in caso in cui i campi non siano corretti.
            boolean TUTTO_CORRETTO = false;
            String data = jtfData.getText();
            int puntata = 0;
            double quota = 0;
            // Try e Catch utilizzato per controllare che sia possibile eseguire il parse da String a int.
            try {
                puntata = Integer.parseInt(jtfPuntata.getText());
                quota = Double.parseDouble(jtfQuota.getText());
            } catch (NumberFormatException ex) {
                System.out.println(ex.getLocalizedMessage());
                inputCorretto = false;
            }

            // Verifica che non ci siano stati problemi con il parseDouble, che la data non sia vuota o
            // composta da soli spazi e che la puntata e la quota siano maggiori 0.
            // Se tutto è andato bene, controlla anche i campi del singolo sport.
            if (inputCorretto && !data.isBlank() && !data.isEmpty() && puntata > 0 && quota > 0) {
                boolean vittoria = random.nextBoolean(); // Generazione casuale della vittoria o meno.
                if (sport.equalsIgnoreCase("Calcio") || sport.equalsIgnoreCase("Basket")) {
                    String squadra1 = jtfSquadra1.getText();
                    String squadra2 = jtfSquadra2.getText();
                    String risultato = jtfRisultato.getText();
                    if (inputCorrettoCalcioBasket(squadra1, squadra2, risultato)) {
                        if (vittoria) {
                            cliente.setGuadagno(cliente.getGuadagno() + (puntata * quota));
                        }
                        TUTTO_CORRETTO = true;
                        scommesse.add(new ScommessaCalcioBasket(data, puntata, quota, vittoria, cliente, sport, squadra1, squadra2, risultato));
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati.");
                    }
                } else if (sport.equalsIgnoreCase("Ciclismo")) {
                    String ciclista1 = jtfCiclista1.getText();
                    String ciclista2 = jtfCiclista2.getText();
                    String ciclista3 = jtfCiclista3.getText();
                    if (inputCorrettoCiclismo(ciclista1, ciclista2, ciclista3)) {
                        if (vittoria) {
                            cliente.setGuadagno(cliente.getGuadagno() + (puntata * quota));
                        }
                        TUTTO_CORRETTO = true;
                        scommesse.add(new ScommessaCiclismo(data, puntata, quota, vittoria, cliente, sport, ciclista1, ciclista2, ciclista3));
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati.");
                    }
                } else if (sport.equalsIgnoreCase("Atletica")) {
                    String specialita = jtfSpecialita.getText();
                    String vincitoreAtletica = jtfVincitoreAtletica.getText();
                    if (inputCorrettoAtletica(specialita, vincitoreAtletica)) {
                        if (vittoria) {
                            cliente.setGuadagno(cliente.getGuadagno() + (puntata * quota));
                        }
                        TUTTO_CORRETTO = true;
                        scommesse.add(new ScommessaAtletica(data, puntata, quota, vittoria, cliente, sport, specialita, vincitoreAtletica));
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati.");
                    }
                } else {
                    String stile = jtfStile.getText();
                    boolean erroreDistanza = false;
                    int distanza = 0;

                    try {
                        distanza = Integer.parseInt(jtfDistanza.getText());
                    } catch (NumberFormatException ex) {
                        System.out.println(ex.getLocalizedMessage());
                        erroreDistanza = true;
                    }
                    String vincitore = jtfVincitoreNuoto.getText();
                    if (inputCorrettoNuoto(stile, erroreDistanza, distanza, vincitore)) {
                        if (vittoria) {
                            cliente.setGuadagno(cliente.getGuadagno() + (puntata * quota));
                        }
                        TUTTO_CORRETTO = true;
                        scommesse.add(new ScommessaNuoto(data, puntata, quota, vittoria, cliente, sport, stile, distanza, vincitore));
                    } else {
                        JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati.");
                        bloccaImmagineNuoto = true;
                    }
                }
                // Se tutto è andato a buon fine, stampa l'immagine relativa allo sport e incrementa il valore della
                // chiave relativa allo sport.
                if ((!bloccaImmagineNuoto || !sport.equalsIgnoreCase("Nuoto")) && TUTTO_CORRETTO) {
                    stampaImmagineScommessa();
                    int valore = hmScommesseTipo.get(sport);
                    hmScommesseTipo.put(sport, valore + 1);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Errore nell'inserimento dei dati.");
            }
        }

        // QUESTI 4 METODI SERVONO PER VERIFICARE CHE I CAMPI DI INPUT SIANO CORRETTI (a seconda dello sport).
        private boolean inputCorrettoCalcioBasket(String squadra1, String squadra2, String risultato) {

            return !squadra1.isBlank() && !squadra1.isEmpty() && !squadra2.isBlank()
                    && !squadra2.isEmpty() && !risultato.isBlank() && !risultato.isEmpty();
        }

        private boolean inputCorrettoCiclismo(String ciclista1, String ciclista2, String ciclista3) {
            return !ciclista1.isBlank() && !ciclista1.isEmpty() && !ciclista2.isBlank()
                    && !ciclista2.isEmpty() && !ciclista3.isBlank() && !ciclista3.isEmpty();
        }

        private boolean inputCorrettoAtletica(String specialita, String vincitoreAtletica) {
            return !specialita.isBlank() && !specialita.isEmpty() && !vincitoreAtletica.isBlank()
                    && !vincitoreAtletica.isEmpty();
        }

        private boolean inputCorrettoNuoto(String stile, boolean erroreDistanza, int distanza, String vincitore) {
            return !erroreDistanza && !stile.isBlank() && !stile.isEmpty() && !vincitore.isBlank() &&
                    !vincitore.isEmpty() && distanza >= 50;
        }

        /**
         * Metodo per far apparire il bottone (con immagine impostata a seconda dello sport) a schermo.
         * È stato utilizzato un bottone con immagine e non direttamente l'immagine, così da permettere il click
         * e far apparire le informazioni relative alla scommessa.
         */
        private void stampaImmagineScommessa() {
            String percorsoImmagine = "/img/" + sport.toLowerCase() + ".jpg";
            bottoneImmagine.setIcon(new ImageIcon(Objects.requireNonNull(FinestraScommessa.class.getResource(percorsoImmagine))));

            // Rendono il bottone trasparente così da far vedere solo l'immagine.
            // (dare l'effetto di click dell'immagine).
            bottoneImmagine.setOpaque(false);
            bottoneImmagine.setContentAreaFilled(false);
            bottoneImmagine.setBorderPainted(false);

            bottoneImmagine.addActionListener(new ClickImmagine()); // Impostato l'ascoltatore del click del bottone.

            // indiceImmagine usato per assegnare un nome (come se fosse un id identificativo) per poi ritrovare
            // la scommessa all'interno dell'ArrayList "scommesse".
            bottoneImmagine.setName(String.valueOf(indiceImmagine));
            indiceImmagine++;

            // Aggiunge il bottone (con immagine impostata dello sport selezionato), esegui il revalidate e il repaint.
            jpPannelloImmagini.add(bottoneImmagine);
            jpPannelloImmagini.revalidate();
            jpPannelloImmagini.repaint();
        }

    }

    /**
     * Classe utilizzata per far apparire a schermo le informazioni relative alla scommessa cliccata.
     */
    public class ClickImmagine implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(null, scommesse.get(Integer.parseInt(bottoneImmagine.getName())).toString());
        }
    }
}
