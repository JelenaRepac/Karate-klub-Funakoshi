/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.io.Serializable;


/**
 * Klasa koja predstavlja zahtev koji klijent šalje serveru.
 * Objekat klase Request se sastoji od tipa operacije koju klijent zahteva i argumenta za tu operaciju.
 * <p>Klasa implementira interfejs Serializable, što omogućava da se objekti klase Request serijalizuju i deserijalizuju.
 * Što je neophodno, jer se koristi prenos podataka između procesa, putem soketa. </p>
 * 
 *
 * @author Jelena Repac
 */
public class Request implements Serializable {
    /**
     * Tip operacije
     */
	private int operation; //tip operacije
	/**
	 * Argument za operaciju
	 */
    private Object argument;

    /**
     * Konstruktor bez argumenata.
     */
    public Request() {
    }

    /**
     * Konstruktor koji kreira novi objekat klase Request sa datim tipom operacije i argumentom.
     * @param operacija tio operacije koju klijent zahteva
     * @param argument argument za operaciju
     */
    public Request(int operacija, Object argument) {
        this.operation = operacija;
        this.argument = argument;
    }

    /**
     * Vraca vrednost argumenta za operaciju.
     * @return argument za operaciju
     */
    public Object getArgument() {
        return argument;
    }

    /**
     * Vraca vrednost tipa operacije.
     * @return tip operacije
     */
    public int getOperation() {
        return operation;
    }

    /**
     * Postavlja vrednost argumenta.
     * @param argument novi argument za operaciju
     */
    public void setArgument(Object argument) {
        this.argument = argument;
    }

    /**
     * Postavlja novi tip operacije. 
     * @param operation novi tip operacije
     */
    public void setOperation(int operation) {
        this.operation = operation;
    }
    
    
    
}
