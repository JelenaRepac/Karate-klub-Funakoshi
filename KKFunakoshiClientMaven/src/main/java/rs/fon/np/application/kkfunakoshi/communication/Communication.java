/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.net.Socket;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

/**
 * Singleton klasa koja obuhvata komunikaciju klijenta sa serverom.
 * Omogucava korisniku da se prijavi i odjavi sa servera.
 * @author Jelena Repac
 */
public class Communication {

	private static Communication instance;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;
    /**
     * Postavlja instancu prijemnika
     * @param receiver objekat klase Receiver
     */
    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }
    /**
     * Bezparametarski konstruktor
     */
    private Communication() {
    }

    /**
     * Metoda koja poziva konstruktor, predstavlja osobinu Singleton klase
     * @return singleton instancu Communication klase
     */
    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;

    }
    /**
     * Salje login zahtev serveru i vraca odgovor
     * @param request login zahtev poslat serveru
     * @return odgovor servera
     * @throws Exception ukoliko dodje do greske prilikom slanja ili primanja zahteva
     */
    public Response login(Request request) throws Exception {
        sender.send(request);
        return (Response) receiver.receive();

    }
    /**
     * Salje zahtev serveru i vraca odgovor
     * @param request zahtev poslat serveru
     * @return odgovor servera
     * @throws Exception ukoliko dodje do greske prilikom slanja ili primanja zahteva
     */
    public Response sendRequest(Request request) throws Exception {
        sender.send(request);
        return (Response) receiver.receive();

    }
    /**
     * Prima odgovor od servera
     * @return odgovor servera
     * @throws Exception ukoliko dodje do greske prilikom primanja odgovora
     */
    public Response receiveResponse() throws Exception {
        return (Response) receiver.receive();

    }
    /**
     * Postavlja instancu posiljaoca
     * @param sender objekat klase Sender
     */
    public void setSender(Sender sender) {
        this.sender = sender;
    }    
    /**
     * Salje zahtev za odjavljivanje sa servera
     * @param user korisnik koji zeli da se odjavi sa servera
     * @throws Exception ukoliko dodje do greske prilikom slanja zahteva za odjavu sa sistema
     */
    public void logout(Trainer user) throws Exception {
        try {
            Request request= new Request(Operations.LOGOUT,user);
            sender.send(request);
        } catch (Exception e) {
            throw new Exception("Error in getting logging out: " + e.getMessage());
        }
    }
}

