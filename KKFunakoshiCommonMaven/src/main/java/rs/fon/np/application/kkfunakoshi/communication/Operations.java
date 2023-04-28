/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

/**
 * Klasa koja predstavlja listu operacija.
 * Svaka operacija je predstavljena tipom integer.
 * Konstante se koriste kako bi se olaksala komunikacija izmedju razlicitih delova programa koji se bave razlicitim funkcijama.
 * @author Jelena Repac
 */
public class Operations {
	/**
	 * Prijava na sistem.
	 */
    public static final int LOGIN=1;
    /**
     * Vraca sve clanove kluba.
     */
    public static final int GET_ALL_MEMBERS=2;
    /**
     * Vraca sve clanarine clanova.
     */
    public static final int GET_ALL_MEMBERSHIPFEES=3;
    /**
     * Dodaje clana.
     */
    public static final int ADD_MEMBER=4;
    /**
     * Vraca listu gradova.
     */
    public static final int GET_CITIES=5;
    /**
     * Odjava sa sistema.
     */
    public static final int LOGOUT=6;
    /**
     * Server zaustavljen.
     */
    public static final int SERVER_STOPPED=7;
    /**
     * Za prekidanje veze sa serverom.
     */
    public static final int STOP=8;
    /**
     * Vraca clanove na osnovu upita.
     */
    public static final int GET_MEMBERS_BY_QUERY=9;
    /**
     * Brise clana.
     */
    public static final int DELETE_MEMBER=10;
    /**
     * Azurira podatke clana.
     */
    public static final int UPDATE_MEMBER=11;
    /**
     * Dodaje takmicenje.
     */
    public static final int ADD_COMPETITION=12;
    /**
     * Vraca listu svih takmicenja.
     */
    public static final int GET_ALL_COMPETITIONS=13;
    /**
     * Dodaje tim.
     */
    public static final int ADD_TEAM=14;
    /**
     * Vraca listu svih timova.
     */
    public static final int GET_ALL_TEAMS=15;
    /**
     * Dodaje rezultat ostvaren na takmicenju.
     */
    public static final int ADD_RESULT=16;
    /**
     * Vraca sve rezultate na osnovu upita.
     */
    public static final int GET_RESULTS_BY_QUERY=17;
    /**
     * Azurira medalje clana.
     */
    public static final int UPDATE_MEMBER_MEDALS=18;
    /**
     * Azurira rezultat.
     */
    public static final int UPDATE_RESULT=19;
    /**
     * Azurira rezultat tima.
     */
    public static final int UPDATE_RESULT_TEAM=20;
    /**
     * Vraca sva takmicanja na osnovu upita.
     */
    public static final int GET_COMPETITIONS_BY_QUERY=21;
    
}
