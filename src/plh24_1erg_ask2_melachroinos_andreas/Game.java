/*
 * Ελληνικό Ανοικτό Πανεπιστήμιο
 * Στο πλαίσιο των Γραπτών Εργασιών της Θεματικής Ενότητας
 * ΠΛΗ24: Σχεδιασμός Λογισμικού
 * Γλώσσες Προγραμματισμού ΙΙ – Αντικειμενοστρεφής Προγραμματισμός
 */
package plh24_1erg_ask2_melachroinos_andreas;

/**
 *
 * @author Andreas Melachroinos <std084194@ac.eap.gr>
 *
 * Η κλάση του παιχνιδιού η οποία χρησιμοποιείται από τις ομάδες.
 */
public class Game {

    private Team teamHome;  //γηπεδούχος ομάδα
    private Team teamGuest; //φιλοξενούμενη ομάδα
    private int scoreHome;  //σκορ γηπεδούχου ομάδας
    private int scoreGuest; //σκορ φιλοξενούμενης ομάδας   

    // constructor με τον οποίο εισάγονται οι δύο ομάδες και το επιμέρους
    // αποτέλεσμα
    public Game(Team teamHome, Team teamGuest, int scoreHome, int scoreGuest) {

        /*
        Πριν από την εισαγωγή ενός παιχνιδιού ελέγχονται με την σειρά οι 
        παρακάτω συνθήκες*: 
        1) Η γηπεδούχος δεν έχει ξεπεράσει το ανώτατο όριο παιχνιδιών 
        2) Η φιλοξενούμενη δεν έχει ξεπεράσει το ανώτατο όριο παιχνιδιών 
        3) Το score της γηπεδούχου δεν είναι αρνητικό 
        4) Το score της φιλοξενούμενης δεν είναι αρνητικό 
        5) Μια ομάδα δεν παίζει με το εαυτό της 
        6) Και οι δύο ομάδες ανήκουν στο ίδιο άθλημα. 
        
        Αν κάποια συνθήκη δεν ισχύει, δίδεται ανατροφοδότηση στον χρήστη 
        για τυχόν λάθος κατά την καταχώρηση των απαραίτητων στοιχείων.
        
         */
        if (teamHome.curNumOfGames <= teamHome.maxNumOfGames // *1
                && teamGuest.curNumOfGames <= teamGuest.maxNumOfGames // *2
                && scoreHome >= 0 //3
                && scoreGuest >= 0 //4
                && !teamHome.getName().equals(teamGuest.getName()) // 5
                && teamHome.getClass().getSimpleName() // 6
                .equals(teamGuest.getClass().getSimpleName())) {

            // Μόνο για την περίπτωση του μπάσκετ, 
            // ενημερώνεται ο χρήστης όταν βρεθεί ισόπαλο παιχνίδι.
            if (teamHome.getClass().getSimpleName().equals("BasketballTeam")
                    && scoreHome == scoreGuest) {
                System.out.println("-> Warning: Game "
                        + teamHome.getName() + " - "
                        + teamGuest.getName());
                System.out.println("--> Zero points are given for ties"
                        + " in basketball league.");

            }

            // Αν δεν προκύψει κάποιο σφάλμα δημιουργείται το παιχνίδι
            this.teamHome = teamHome;
            this.teamGuest = teamGuest;
            this.scoreHome = scoreHome;
            this.scoreGuest = scoreGuest;

            /* Διαφορετικά τυπώνεται ενημερωτικό μήνυμα στο χρήστη 
            με πληροφορίες για το συγκεκριμένο σφάλμα που ανιχνεύτηκε.
            Η εφαρμογή τερματίζεται.
            Ο χρήστης πρέπει να διορθώσει τα λάθη,και να ξανατρέξει την εφαρμογή*/
        } else {
            System.out.println("-> Error: Game "
                    + teamHome.getName() + " - "
                    + teamGuest.getName());

            if (scoreHome < 0) {    // αρνητικό score γηπεδούχου
                System.out.println("--> "
                        + "Negative Score Given for "
                        + "Home Team " + teamHome.getName()
                        + " (score:" + scoreHome + ")");
            }

            if (scoreGuest < 0) {   // αρνητικό score φιλοξενούμενης
                System.out.println("--> "
                        + "Negative Score Given for "
                        + "Guest Team " + teamGuest.getName()
                        + " (score:" + scoreGuest + ")"
                );
            }

            // Υπέρβαση μέγιστου ορίου παιχνιδιών γηπεδούχου
            if (teamHome.curNumOfGames > teamHome.maxNumOfGames) {
                System.out.println("--> "
                        + "Home Team " + teamHome.getName()
                        + " has already maximum number of games.");
            }

            // Υπέρβαση μέγιστου ορίου παιχνιδιών φιλοξενούμενης
            if (teamGuest.curNumOfGames > teamGuest.maxNumOfGames) {
                System.out.println("--> "
                        + "Guest Team " + teamGuest.getName()
                        + " has already maximum number of games.");
            }

            // Ομάδες διαφορετικής κλάσης
            if (!teamHome.getClass().getSimpleName()
                    .equals(teamGuest.getClass().getSimpleName())) {
                System.out.println("--> "
                        + "A football team cannot match a basketball team."
                );
            }

            // Ίδια ομάδα ως φιλοξενούμενη και ως γηπεδούχος
            if (teamHome.getName().equals(teamGuest.getName())) {
                System.out.println("--> "
                        + "A team cannot match its self"
                );
            }

            // Τερματισμός
            System.out.println("\nCorrect all errors and try again.\n"
                    + "Terminating...");
            System.exit(0);
        }

    }

    // setter γηπεδούχου ομάδας
    public void setTeamHome(Team teamHome) {
        this.teamHome = teamHome;
    }

    // getter γηπεδούχου ομάδας
    public Team getTeamHome() {
        return teamHome;
    }

    // setter φιλοξενούμενης ομάδας
    public void setTeamGuest(Team teamGuest) {
        this.teamGuest = teamGuest;
    }

    // getter φιλοξενούμενης ομάδας
    public Team getTeamGuest() {
        return teamGuest;
    }

    // setter του score της γηπεδούχου
    public void setScoreHome(int scoreHome) {
        // έλεγχος του ότι το score δεν είναι αρνητικό
        if (scoreHome < 0) {
            System.out.println("Error: Negative Score Given for "
                    + "Home Team " + teamHome.getName()
                    + " (score:" + scoreHome + ") on game: "
                    + teamHome.getName() + " - "
                    + teamGuest.getName()
            );
        } else {
            this.scoreHome = scoreHome;
        }

    }

    // getter του score της γηπεδούχου
    public int getScoreHome() {
        return scoreHome;
    }

    // setter του score της φιλοξενούμενης
    public void setScoreGuest(int scoreGuest) {
        // έλεγχος του ότι το score δεν είναι αρνητικό
        if (scoreGuest < 0) {
            System.out.println("Error: Negative Score Given for "
                    + "Guest Team " + teamGuest.getName()
                    + " (score:" + scoreGuest
                    + ") on game: "
                    + teamHome.getName() + " - "
                    + teamGuest.getName()
            );
        } else {
            this.scoreGuest = scoreGuest;
        }
    }

    // getter του score της φιλοξενούμενης
    public int getScoreGuest() {
        return scoreGuest;
    }
}
