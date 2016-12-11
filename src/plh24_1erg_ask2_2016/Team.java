/*
 * Ελληνικό Ανοικτό Πανεπιστήμιο
 * Στο πλαίσιο των Γραπτών Εργασιών της Θεματικής Ενότητας
 * ΠΛΗ24: Σχεδιασμός Λογισμικού
 * Γλώσσες Προγραμματισμού ΙΙ – Αντικειμενοστρεφής Προγραμματισμός
 */
package plh24_1erg_ask2_2016;

/**
 *
 * @author 
 *
 * Το πλήθος των ομάδων στο πρωτάθλημα ποδοσφαίρου καθώς και στο πρωτάθλημα
 * καλαθοσφαίρισης είναι 3. Μια ομάδα μπορεί να αγωνιστεί το πολύ δύο φορές με
 * οποιαδήποτε από τις άλλες ομάδες, μία στην έδρα της και μία στην έδρα της
 * άλλης ομάδας.
 *
 * Το σκορ οποιασδήποτε ομάδας πρέπει να είναι ένας θετικός αριθμός ή μηδέν. Στα
 * παιχνίδια μιας ομάδας (teamGames), η ομάδα θα πρέπει υποχρεωτικά να
 * συμμετέχει, είτε ως φιλοξενούμενη (teamGuest), είτε ως γηπεδούχος (teamHome).
 * Το σύνολο των αγώνων μιας ομάδας δεν μπορεί να υπερβαίνει το μέγιστο
 * επιτρεπόμενο πλήθος αγώνων ανά πρωτάθλημα.
 */
public abstract class Team {

    private String name; //όνομα της ομάδας
    /* private int maxNumOfGames; //μέγιστο πλήθος αγώνων,
    το παραπάνω έχει περισσότερο νόημα να οριστεί ως final
    αφού ουσιαστικά αφορά σταθερά */
    final int maxNumOfGames;
    protected int curNumOfGames; //τρέχον πλήθος αγώνων
    protected Game[] teamGames; //οι αγώνες της ομάδας    

    //constructor με τον οποίο εισάγονται το όνομα της ομάδας
    //και ο μέγιστος αριθμός παιχνιδιών
    public Team(String name, int maxNumOfGames) {
        this.name = name;
        this.maxNumOfGames = maxNumOfGames;

        // αρχικοποίηση του πλήθους των παιχνιδιών της ομάδας
        this.curNumOfGames = 0;

        /* αρχικοποίηση του αντικειμένου
        στο οποίο θα αποθηκεύονται τα παιχνίδια της ομάδας*/
        teamGames = new Game[maxNumOfGames];
    }

    // Μέθοδος Setter για το όνομα της ομάδας
    public void setName(String name) {
        this.name = name;
    }

    // Μέθοδος getter για το όνομα της ομάδας
    public String getName() {
        return name;
    }

    /**
     * Μέθοδος η οποία χρησιμοποιείται για την καταγραφή/αποθήκευση των
     * παιχνιδιών της ομάδας. Πριν από την εισαγωγή ενός παιχνιδιού ελέγχονται
     * με την σειρά οι παρακάτω συνθήκες*: 1) Η ομάδα δεν έχει ξεπεράσει το
     * ανώτατο όριο παιχνιδιών 2) Το score του γηπεδούχου δεν είναι αρνητικό 3)
     * Το score του φιλοξενούμενου δεν είναι αρνητικό 4) Η ομάδα δεν παίζει με
     * το εαυτό της 5) Και οι δύο ομάδες ανήκουν στο ίδιο άθλημα. 6) Αν η ομάδα
     * όντως αντιστοιχεί σε μια από τις ομάδες του παιχνιδιου (γηπεδούχος ή
     * φιλοξενούμενη). 7) Δύο ομάδες έχουν παίξει μεταξύ τους δύο φορές (μια ως
     * γηπεδούχες και μια ως φιλοξενουμενες)
     *
     * Οι ίδιοι (εκτός του 7) έλεγχοι γίνονται και κατά την δημιουργία ενός
     * παιχνιδιού στην κλάση Game. Εκεί μάλιστα δίδεται ανατροφοδότηση στον
     * χρήστη για τυχόν λάθος κατά την καταχώρηση των απαραίτητων στοιχείων.
     *
     * @param g αντικείμενο της κλάσης παιχνίδι.
     */
    public void addGame(Game g) {
        boolean flag = false;
        if (curNumOfGames <= maxNumOfGames // *1
                && g.getScoreHome() >= 0 // *2
                && g.getScoreGuest() >= 0 // *3
                && !g.getTeamHome().getName() // *4
                .equals(g.getTeamGuest().getName())
                && g.getTeamHome().getClass().getSimpleName() // *5
                .equals(g.getTeamGuest().getClass().getSimpleName())
                && (this.getName().equals(g.getTeamHome().getName()) // *6
                || this.getName().equals(g.getTeamGuest().getName()))) {

            // *7. Ελέγχεται αν το παιχνίδι προς εισαγωγή έχει ήδη εισαχθεί.
            for (int i = 0; i < curNumOfGames; i++) {
                if (teamGames[i].getTeamHome().getName()
                        .equals(g.getTeamHome().getName())
                        && teamGames[i].getTeamGuest().getName()
                                .equals(g.getTeamGuest().getName())) {
                    flag = true; // αν ναι τότε σηκώνεται σημαία
                }
            }

            /* Αν σηκωθεί σημαία (δηλαδή βρεθεί διπλεγραφη, 
            εμφανίζεται σχετικό μήνυμα με τα λάθη που βρέθηκαν. 
            Η εφαρμογή τερματίζεται. 
            Ο χρήστης πρέπει να διορθώσει τα λάθη,
            και να ξανατρέξει την εφαρμογή*/
            if (flag) {
                System.out.println("-> Error: Game "
                        + g.getTeamHome().getName() + " - "
                        + g.getTeamGuest().getName());
                System.out.println("--> At least one "
                        + "duplicate game detected.");
                System.out.println("\nCorrect all errors and try again.\n"
                        + "Terminating...");
                System.exit(0);

                // Αν δε βρεθεί κάποιο λάθος:    
            } else {
                // Προσθήκη του παιχνιδιού στον πίνακα παιχνιδιών της ομάδας
                teamGames[curNumOfGames] = g;
                // και αύξηση του μετρητη του πλήθους των παιχνιδιών της
                curNumOfGames++;

            }

        }
    }   
}


