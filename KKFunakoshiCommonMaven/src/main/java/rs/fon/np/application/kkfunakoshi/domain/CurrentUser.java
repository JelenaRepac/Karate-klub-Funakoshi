/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

/**
 * Predstavlja trenutno prijavljenog korisnika na sistem.
 * 
 * @author Jelena Repac
 */

public class CurrentUser {
    
    private static Trainer currentUser;

    /**
     * Vraca trenutno prijavljenog korisnika na sistem.
     * @return objekat Trainer koji je trenutno prijavljen na sistem
     */
    public static Trainer getCurrentUser() {
        return currentUser;
    }
    /**
     * Postavlja trenutno prijavljenog korisnika.
     * @param currentUser trenutno prijavljeni korisnik
     */
    public static void setCurrentUser(Trainer currentUser) {
        CurrentUser.currentUser = currentUser;
    }
    
    
    
}
