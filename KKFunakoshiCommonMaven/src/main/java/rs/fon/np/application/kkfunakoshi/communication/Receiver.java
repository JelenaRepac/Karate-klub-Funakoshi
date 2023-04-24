/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *	Klasa koja sluzi za prijem podataka putem soket konekcije.
 *	I klijent i server mogu da koriste ovu klasu za prijem podataka.
 * @author Jelena Repac
 */
public class Receiver {
    
    private Socket socket;

   /**
    * Konstruktor
    * @param socket soket koji se koristi za prijem podataka
    */
    public Receiver(Socket socket) {
        this.socket = socket;
    }
    /**
     * Metoda koja prima podatke.
     * Metodu koristi i klijent i server.
     * Ukoliko dodje do greske, baca se opsti izuzetak sa porukom o gresci.
     * @return Object podatak koji primamo kao Objekat
     * @throws Exception ukoliko nastane greska tokom prijema podataka
     */
    public Object receive() throws  Exception{
        try {
            
            ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        } catch (IOException ex) {
           ex.printStackTrace();
           throw new Exception("Error receiving object.."+ex);
        }
    }
}
