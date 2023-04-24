/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.domain;

/**
 *
 * @author Jeks
 */
public class CurrentUser {
    
    private static Trainer currentUser;

    public static Trainer getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(Trainer currentUser) {
        CurrentUser.currentUser = currentUser;
    }
    
    
    
}
