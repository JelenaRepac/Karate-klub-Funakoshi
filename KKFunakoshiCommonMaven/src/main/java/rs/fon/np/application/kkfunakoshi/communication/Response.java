/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.io.Serializable;

/**
 * Klasa koja predstavlja odgovor koji server šalje klijentu kao rezultat zahteva.
 * <p> Objekat klase Response se sastoji od rezultata izvršavanja operacije, tipa odgovara i eventualne greške koja se desila tokom izvršavanja operacije.</p>
 * <p> Klasa implementira interfejs Serializable, što omogućava da se objekti klase Response serijalizuju i deserijlizuju. Što je neophodno, ukoliko koristimo prenos podataka između procesa, putem soketa.</p>
 * 
 * @author Jelena Repac
 */
public class Response implements Serializable {
    
  private Object result;
    private ResponseType responseType;
    private Exception exception;
    private int operation;
  

    public Response() {
    }

    public Response(Object result, ResponseType responseType, Exception exception) {
        this.result = result;
        this.responseType = responseType;
        this.exception = exception;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Exception getException() {
        return exception;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public Object getResult() {
        return result;
    }

    ////////////////////
    public int getOperation() {
        return operation;
    }

    public void setOperation(int operation) {
        this.operation = operation;
    }
    
}
