/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.validation;

/**
 * Klasa namenjena za upravljanje greskama, baca se greska ukoliko validacija nije uspesna.
 * @author Jelena Repac
 */
public class ValidationException extends Exception{
    
    /**
     * Kreira ValidationException sa specificnom porukom
     * @param message prosledjena poruka
     */
    public ValidationException(String message) {
        super(message);
    }
    
}
