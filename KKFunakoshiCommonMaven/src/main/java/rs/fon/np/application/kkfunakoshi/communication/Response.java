/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.io.Serializable;

/**
 * Klasa koja predstavlja odgovor koji server salje klijentu kao rezultat zahteva.
 * <p> Objekat klase Response se sastoji od rezultata izvrsavanja operacije, tipa odgovara i eventualne greske koja se desila tokom izvrsavanja operacije.</p>
 * <p> Klasa implementira interfejs Serializable, sto omogucava da se objekti klase Response serijalizuju i deserijlizuju. Sto je neophodno, ukoliko koristimo prenos podataka izmedju procesa, putem soketa.</p>
 * 
 * @author Jelena Repac
 */
public class Response implements Serializable {
    /**
     * Rezultat izvrsavanja operacije
     */
	private Object result;
	/**
	 * Tip odgovora
	 */
    private ResponseType responseType;
    /**
     * Greska tokom izvrsenja operacije
     */
    private Exception exception;
    /**
     * Broj operacije
     */
    private int operation;
  

    /**
     * Bezparametarski konstruktor
     */
    public Response() {
    }

    /**
     * 
     * Konstruktor
     * @param result rezultat zahteva
     * @param responseType tip odgovora
     * @param exception greska 
     */
    public Response(Object result, ResponseType responseType, Exception exception) {
        this.result = result;
        this.responseType = responseType;
        this.exception = exception;
    }

    /**
     * Postavlja rezultat
     * @param result rezultat
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * Postavlja tip odgovora
     * @param responseType tip odgovora
     */
    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    /**
     * Postavlja gresku
     * @param exception greska
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }

    /**
     * Vraca gresku
     * @return greska
     */
    public Exception getException() {
        return exception;
    }

    /**
     * Vraca tip odgovora
     * @return tip odgovora
     */
    public ResponseType getResponseType() {
        return responseType;
    }

    /**
     * Vraca rezultat
     * @return rezultat zahteva
     */
    public Object getResult() {
        return result;
    }

    /**
     * Metoda koja vraća broj operacije koja je izvršena na serveru.
     * @return Broj operacije kao int.
     */
    public int getOperation() {
        return operation;
    }
    /**
     * Metoda koja postavlja broj operacije koja je izvršena na serveru.
     * @param operation Broj operacije kao int.
     */
    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
