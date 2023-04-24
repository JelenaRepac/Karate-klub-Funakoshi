/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Klasa koja sluzi za slanje podataka preko soketa.
 * @author Jelena Repac
 */
public class Sender {
    
    private Socket socket;

  /**
   * Za kreiranje nove instance klase Sender.
   * @param socket soket koji je koriscen za uspostavljanje konekcije
   */
    public Sender(Socket socket) {
        this.socket = socket;
    }
    /**
     * Metoda koja salje podatke.
     * @param object objekat koji se salje
     * @throws Exception ukoliko se desi greska prilikom slanja objekta
     */
    public void send(Object object) throws Exception{
        
        try {
            ObjectOutputStream out= new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(object);
            out.flush();
        } catch (Exception ex) {
           ex.printStackTrace();
           throw new Exception("Error sending object.."+ex);
        }
        
    }
    
}
