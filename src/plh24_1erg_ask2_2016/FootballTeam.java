/*
 * Ελληνικό Ανοικτό Πανεπιστήμιο
 * Στο πλαίσιο των Γραπτών Εργασιών της Θεματικής Ενότητας
 * ΠΛΗ24: Σχεδιασμός Λογισμικού
 * Γλώσσες Προγραμματισμού ΙΙ – Αντικειμενοστρεφής Προγραμματισμός
 */
package plh24_1erg_ask2_2016;

/**
 * Μια ομάδα (Team) μπορεί να είναι είτε ομάδα ποδοσφαίρου (FootballTeam), είτε
 * ομάδα καλαθοσφαίρισης (BasketballTeam).
 *
 * @author 
 */
public class FootballTeam extends Team {

    //constructor με τον οποίο εισάγονται το όνομα της ομάδας
    //και ο μέγιστος αριθμός παιχνιδιών στην υπερκλάση
    public FootballTeam(String name, int maxNumOfGames) {
        super(name, maxNumOfGames);
    }

    /**
     * Η μέθοδος calcPoints() υπολογίζει τους συνολικούς βαθμούς που έχει
     * συγκεντρώσει μια ομάδα με βάση τους αγώνες που έχει δώσει. Σ’ έναν αγώνα
     * (Game) ποδοσφαίρου, ο νικητής λαμβάνει 3 βαθμούς και ο ηττημένος 0
     * βαθμούς. Σε περίπτωση ισοπαλίας, η κάθε ομάδα λαμβάνει από 1 βαθμό.
     *
     * @return Επιστρέφει τους συνολικούς πόντους της ομάδας ποδοσφαίρου.
     */
    public int calcPoints() {
        // αρχικοποίση της μεταβλητής η οποία θα μετρά τις νίκες της ομάδας
        int wins = 0, ties = 0;

        //Για κάθε παιχνίδι της ομάδας:
        /* Έλεγος Νίκης: Αν το score της ομάδας ως γηπεδούχου είναι μεγαλυτερο
        από το score της φιλοξενούμενης ή το score της ομάδας ως φιλοξενούμενη
        είναι μεγαλυτερο από το score της γηπεδούχου τότε
        αύξησε κατα ένα τον μετρητή των νικών.*/
        for (int i = 0; i < curNumOfGames; i++) {
            if ((teamGames[i].getTeamHome().getName().equals(this.getName())
                    && teamGames[i].getScoreHome()
                    > teamGames[i].getScoreGuest())
                    || (teamGames[i].getTeamGuest().getName()
                            .equals(this.getName())
                    && teamGames[i].getScoreHome()
                    < teamGames[i].getScoreGuest())) {
                wins++;

                /* Έλεγος Ισοπαλίας: Διαφορετικά, αν το score της ομάδας 
                ως γηπεδούχου είναι ίσο από το score της φιλοξενούμενης 
                ή το score της ομάδας ως φιλοξενούμενη είναι ίσο από το score 
                της γηπεδούχου τότε αύξησε κατα ένα τον μετρητή των ισοπαλιών.*/
            } else if ((teamGames[i].getTeamHome().getName()
                    .equals(this.getName())
                    && teamGames[i].getScoreHome()
                    == teamGames[i].getScoreGuest())
                    || (teamGames[i].getTeamGuest().getName()
                    .equals(this.getName())
                    && teamGames[i].getScoreHome()
                    == teamGames[i].getScoreGuest())) {
                ties++;
            }
        }
        /* Επέστρεψε τρεις βαθμούς για κάθε νίκη της ομάδας και έναν για κάθε
        ισοπαλία αφού πρόκειται για ομάδα ποδοσφαίρου*/
        int points = wins * 3 + ties * 1;
        return points;
    }

    /**
     * Υπερκάλυψη της μεθόδου toString() της κλάσης Team, έτσι ώστε να τυπώνει
     * (και να επιστρέφει) τα στοιχεία της ομάδας: όνομα, πλήθος αγώνων, βαθμοί.
     *
     * @return Επιστρέφει τα στοιχεία της ομάδας: όνομα, πλήθος αγώνων, βαθμοί.
     */
    @Override
    public String toString() {
        String output = "\nTeam Info:\n"
                + "-Team Name: " + getName()
                + "\n-No. of games: " + curNumOfGames
                + "\n-Points: " + calcPoints();

        return output;
    }


}
