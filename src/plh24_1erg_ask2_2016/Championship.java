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
 */
public class Championship {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        final int NumOfTeams = 3;
        final int maxNumOfGames = (NumOfTeams * 2) - 1;

        /* Ερώτημα 1 - Δημιουργία ομάδων.
        Για τη διευκόλυνση της διαδικασίας εισαγωγής,
        οι ομάδες καταχωρούνται σε πίνακα*/
        // Δήλωση και δημιουργία ομάδων ποδοσφαίρου
        FootballTeam[] fTeams = new FootballTeam[NumOfTeams];
        fTeams[0] = new FootballTeam("OLYMPIAKOS FC", maxNumOfGames);
        fTeams[1] = new FootballTeam("AEK FC", maxNumOfGames);
        fTeams[2] = new FootballTeam("PAO FC", maxNumOfGames);

        // Δήλωση και δημιουργία ομάδων μπάσκετ
        BasketballTeam[] bTeams = new BasketballTeam[NumOfTeams];
        bTeams[0] = new BasketballTeam("PAO BC", maxNumOfGames);
        bTeams[1] = new BasketballTeam("PAOK BC", maxNumOfGames);
        bTeams[2] = new BasketballTeam("ARIS BC", maxNumOfGames);

        /* Ερώτημα 2 - Δημιουργία παιχνιδιών/αποτελεσμάτων.
        Για τη διευκόλυνση της διαδικασίας εισαγωγής,
        τα παιχνίδια καταχωρούνται σε πίνακα*/
        // Δήλωση παιχνιδιών (ποδοσφαίρου και μπάσκετ)
        Game[] fgames, bgames;
        fgames = new Game[2 * NumOfTeams - 1];
        bgames = new Game[2 * NumOfTeams - 1];

        // Δημιουργία παιχνιδιών ποδοσφαίρου
        fgames[0] = new Game(fTeams[0], fTeams[1], 2, 1);
        fgames[1] = new Game(fTeams[1], fTeams[2], 2, 1);
        fgames[2] = new Game(fTeams[2], fTeams[1], 1, 1);

        // Δημιουργία παιχνιδιών μπάσκετ
        bgames[0] = new Game(bTeams[0], bTeams[1], 43, 45);
        bgames[1] = new Game(bTeams[1], bTeams[2], 74, 34);
        bgames[2] = new Game(bTeams[1], bTeams[0], 54, 65);

        /* Καταχώρηση των παιχνιδιών σε κάθε ομάδα
        Σε κάθε ομάδα προστίθενται τα παιχνίδια που την αφορούν ...*/
        // ... για την καλαθοσφαίριση
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                fTeams[i].addGame(fgames[j]);
            }
        }

        // ... για το ποδόσφαιρο
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                bTeams[i].addGame(bgames[j]);
            }
        }

        /* Ερώτημα 4 - Εκτύπωση των στοιχείων όλων των ομάδων 
        ποδοσφαίρου και καλαθοσφαίρισης αντίστοιχα.*/
        printFootballTeams(fTeams);
        printBasketballTeams(bTeams);

        /* Ερώτημα 5 - Εκτύπωση των πρωτοπόρων ομάδων 
        ποδοσφαίρου και καλαθοσφαίρισης (αντίστοιχα), 
        λαμβάνοντας υπόψη όλους τους αγώνες.*/
        printFootballStandings(fTeams);
        printBasketballStandings(bTeams);

        /* Ερώτημα 3 -  Εκτύπωση πρωταθλητών.
        Εκτύπωση του ονοματοςτης ομάδας με τους περισσότερους βαθμούς */
        calcFootballCampion(fTeams);    // Ομάδα ποδοσφαίρου
        calcBasektballCampion(bTeams);  // Ομάδα μπάσκετ

    }

    // Ερώτημα 5 - Εκτύπωση κατάταξης ομάδων ποδοσφαίρου
    /**
     * Εκτύπωση των πρωτοπόρων ομάδων ποδοσφαίρου λαμβάνοντας υπόψη όλους τους
     * αγώνες. Εκτυπώνονται όλες οι ομάδες ταξινομημένες σε φθίνουσα σειρά
     * βαθμολογίας (από την 1η βαθμολογικά ομάδας προς την τελευταία).
     *
     * @param fTeams ομάδες ποδοσφαίρου
     */
    public static void printFootballStandings(FootballTeam[] fTeams) {

        // Εκτυπωση διαχωριστικής επικεφαλίδας για το προτάθλημα ποδοσφαίρου
        System.out.println("\n\n");
        System.out.println("Football Championship Standings:");
        System.out.println("-------------------------------------");

        // Ταξινόμηση
        fTeams = bsort(fTeams);

        /* Εκτύπωση αποτελεσμάτων. 
        Γίνεται χρήση του printf ώστε να επιτευχθεί 
        στοίχιση και πινακοειδής μορφή*/
        System.out.printf("%-4s  %-13s  %-6s  %-6s\n", "Pos.", "Team", "Points", "Games");
        for (int i = 0; i < fTeams.length; i++) {
            System.out.printf("%-4s  %-13s  %-6s  %-6s\n", i + 1, fTeams[i].getName(), fTeams[i].calcPoints(), fTeams[i].curNumOfGames);
        }
    }

    // Ερώτημα 5 - Εκτύπωση κατάταξης ομάδων καλαθοσφαίρισης
    /**
     * Εκτύπωση των πρωτοπόρων ομάδων καλαθοσφαίρισης, λαμβάνοντας υπόψη όλους
     * τους αγώνες. Εκτυπώνονται όλες οι ομάδες ταξινομημένες σε φθίνουσα σειρά
     * βαθμολογίας (από την 1η βαθμολογικά ομάδας προς την τελευταία).
     *
     * @param bTeams ομάδες μπάσκετ
     */
    public static void printBasketballStandings(BasketballTeam[] bTeams) {

        // Εκτυπωση διαχωριστικής επικεφαλίδας για το προτάθλημα μπάσκετ
        System.out.println("\n\n");
        System.out.println("Basketball Championship Standings:");
        System.out.println("-------------------------------------");

        // Ταξινόμηση
        bTeams = bsort(bTeams);

        /* Εκτύπωση αποτελεσμάτων. 
        Γίνεται χρήση του printf ώστε να επιτευχθεί 
        στοιχιση και πινακοειδής μορφή*/
        System.out.printf("%-4s  %-13s  %-6s  %-6s\n", "Pos.", "Team", "Points", "Games");
        for (int i = 0; i < bTeams.length; i++) {
            System.out.printf("%-4s  %-13s  %-6s  %-6s\n", i + 1, bTeams[i].getName(), bTeams[i].calcPoints(), bTeams[i].curNumOfGames);
        }

    }

    // Ερώτημα 4
    /**
     * Εκτύπωση όλων των ομάδων μπάσκετ. Τυπώνονται τα στοιχεία -Team Name, No.
     * of games, Points.
     *
     * @param fTeams ομάδες μπάσκετ
     */
    public static void printFootballTeams(FootballTeam[] fTeams) {

        // Εκτυπωση διαχωριστικής επικεφαλίδας για το προτάθλημα μπάσκετ
        System.out.println("\n\n");
        System.out.println("Current status of Football Teams:");
        System.out.println("-------------------------------------");

        // Εκτυπωση των στοιχείων των ομάδων σε μορφή πίνακα
        System.out.printf("%-4s  %-13s  %-6s  %-6s\n", "A/N", "Team", 
                "Points", "Games");
        for (int i = 0; i < fTeams.length; i++) {
            System.out.printf("%-4s  %-13s  %-6s  %-6s\n", i + 1,
                    fTeams[i].getName(), fTeams[i].calcPoints(), 
                    fTeams[i].curNumOfGames);
        }
    }

    /**
     * Εκτύπωση όλων των ομάδων ποδοσφαίρου. Τυπώνονται τα στοιχεία -Team Name,
     * No. of games, Points.
     *
     * @param bTeams Ομάδες Μπασκετ
     */
    public static void printBasketballTeams(BasketballTeam[] bTeams) {
        System.out.println("\n\n");
        System.out.println("Current status of Basketball Teams:");
        System.out.println("---------------------------------------");

        System.out.printf("%-4s  %-13s  %-6s  %-6s\n", "A/N", "Team", "Points", "Games");
        for (int i = 0; i < bTeams.length; i++) {
            System.out.printf("%-4s  %-13s  %-6s  %-6s\n", i + 1, bTeams[i].getName(), bTeams[i].calcPoints(), bTeams[i].curNumOfGames);
        }
    }

    // Ερώτημα 3 - 
    /**
     * Εκτύπωση του ονόματος της ομάδας ποδοσφαίρου με τους περισσότερους
     * βαθμούς.
     *
     * @param fTeams Ομάδες ποδοσφαίρου
     */
    public static void calcFootballCampion(FootballTeam[] fTeams) {
        fTeams = bsort(fTeams); // σε περίπτωση που δεν έχει γίνει ταξινόμιση νωρίτερα
        System.out.println("\n* Football Champion: " + fTeams[0].getName() + " !");
    }

    /**
     * Εκτύπωση του ονόματος της ομάδας καλαθοσφαίρισης τους περισσότερους
     * βαθμούς.
     *
     * @param bTeams Ομάδες καλαθοσφαίρισης
     */
    public static void calcBasektballCampion(BasketballTeam[] bTeams) {
        bTeams = bsort(bTeams); // σε περίπτωση που δεν έχει γίνει ταξινόμιση νωρίτερα
        System.out.println("\n* Basketball Champion: " + bTeams[0].getName() + " !");
    }

    /**
     * Ταξινόμηση bubble sort (descending).
     * Επιστρέφει τον πίνακα των ομάδων καλαθοσφαίρισης ταξινομημένο κατά
     * φθίνουσα σειρά.
     *
     * @param bTeams Ομάδες μπάσκετ
     * @return bTeams Ομάδες μπάσκετ ταξινομημένες σε φθίνουσα σειρά
     */
    public static BasketballTeam[] bsort(BasketballTeam[] bTeams) {
        // Αλγόριθμός ταξινόμισης bubble sort (descending)
        for (int i = 0; i < bTeams.length; i++) {
            for (int j = 1; j < (bTeams.length - i); j++) {
                if (bTeams[j - 1].calcPoints() < bTeams[j].calcPoints()) {
                    BasketballTeam temp = bTeams[j - 1];
                    bTeams[j - 1] = bTeams[j];
                    bTeams[j] = temp;
                }
            }
        }

        return bTeams;
    }

    /**
     * Ταξινόμηση bubble sort (descending).
     * Επιστρέφει τον πίνακα των ομάδων καλαθοσφαίρισης ταξινομημένο κατά
     * φθίνουσα σειρά.
     *
     * @param fTeams Ομάδες μπάσκετ
     * @return fTeams Ομάδες μπάσκετ ταξινομημένες σε φθίνουσα σειρά
     */
    public static FootballTeam[] bsort(FootballTeam[] fTeams) {
        // Αλγόριθμός ταξινόμισης bubble sort (descending)
        for (int i = 0; i < fTeams.length; i++) {
            for (int j = 1; j < (fTeams.length - i); j++) {
                if (fTeams[j - 1].calcPoints() < fTeams[j].calcPoints()) {
                    FootballTeam temp = fTeams[j - 1];
                    fTeams[j - 1] = fTeams[j];
                    fTeams[j] = temp;
                }
            }
        }
        return fTeams;
    }
}
