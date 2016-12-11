/*
 * Ελληνικό Ανοικτό Πανεπιστήμιο
 * Στο πλαίσιο των Γραπτών Εργασιών της Θεματικής Ενότητας
 * ΠΛΗ24: Σχεδιασμός Λογισμικού
 * Γλώσσες Προγραμματισμού ΙΙ – Αντικειμενοστρεφής Προγραμματισμός
 */
package plh24_1erg_ask2_melachroinos_andreas;

/**
 * Μια ομάδα (Team) μπορεί να είναι είτε ομάδα ποδοσφαίρου (FootballTeam), είτε
 * ομάδα καλαθοσφαίρισης (BasketballTeam).
 *
 * @author Andreas Melachroinos <std084194@ac.eap.gr>
 */
public class BasketballTeam extends Team {

    //constructor με τον οποίο εισάγονται το όνομα της ομάδας
    //και ο μέγιστος αριθμός παιχνιδιών στην υπερκλάση
    public BasketballTeam(String name, int maxNumOfGames) {
        super(name, maxNumOfGames);
    }

    /**
     * Η μέθοδος calcPoints() υπολογίζει τους συνολικούς βαθμούς που έχει
     * συγκεντρώσει μια ομάδα με βάση τους αγώνες που έχει δώσει. Σ’ έναν αγώνα
     * (Game) καλαθοσφαίρισης, ο νικητής λαμβάνει 2 βαθμούς και ο ηττημένος 1
     * βαθμό. Δεν υπάρχει περίπτωση ισοπαλίας.
     *
     * @return Επιστρέφει τους συνολικούς πόντους της ομάδας μπάσκετ.
     */
    public int calcPoints() {
        int wins = 0, defeats = 0;

        //Για κάθε παιχνίδι της ομάδας:
        /* Αν το score της ομάδας ως γηπεδούχου είναι μεγαλύτερο από το 
            score της φιλοξενούμενης ή το score της ομάδας ως φιλοξενούμενη 
            είναι μεγαλύτερο από το score της γηπεδούχου τότε 
            αύξησε κατά ένα τον μετρητή των νικών.*/
        for (int i = 0; i < curNumOfGames; i++) {
            if ((teamGames[i].getTeamHome().getName().equals(this.getName())
                    && teamGames[i].getScoreHome()
                    > teamGames[i].getScoreGuest())
                    || (teamGames[i].getTeamGuest().getName().equals(this.getName())
                    && teamGames[i].getScoreHome()
                    < teamGames[i].getScoreGuest())) {
                wins++;

                /* Διαφορετικά, αν το score της ομάδας ως γηπεδούχου είναι μικρότερο 
                από το score της φιλοξενούμενης ή το score της ομάδας 
                ως φιλοξενούμενη είναι μικρότερο από το score 
                της γηπεδούχου τότε αύξησε κατα ένα τον μετρητή των ηττών.*/
            } else if ((teamGames[i].getTeamHome().getName().equals(this.getName())
                    && teamGames[i].getScoreHome()
                    < teamGames[i].getScoreGuest())
                    || (teamGames[i].getTeamGuest().getName().equals(this.getName())
                    && teamGames[i].getScoreHome()
                    > teamGames[i].getScoreGuest())) {
                defeats++;
            }
        }
        /* Επέστρεψε δύο βαθμούς για κάθε νίκη της ομάδας και έναν για κάθε 
        χαμένο αγώνα αφού πρόκειται για ομάδα καλαθοσφαίρισης*/
        return wins * 2 + defeats * 1;
    }

    /**
     * Υπερκάλυψη της μεθόδου toString() της κλάσης Team, έτσι ώστε να τυπώνει
     * (και να επιστρέφει) τα στοιχεία της ομάδας: όνομα, πλήθος αγώνων, βαθμοί.
     *
     * @return Επιστρέφει τα στοιχεία της ομάδας: όνομα, πλήθος αγώνων, βαθμοί.
     */
    @Override
    public String toString() {
        String output = "Team Info:\n"
                + "-Team Name: " + getName()
                + "\n-No. of games: " + curNumOfGames
                + "\n-Points: " + calcPoints();

        return output;
    }
}
