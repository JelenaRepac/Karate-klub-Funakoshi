/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.server.thread;

import java.io.FileInputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.fon.np.application.kkfunakoshi.constants.ServerConstants;
import rs.fon.np.application.kkfunakoshi.controller.Controller;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;
import rs.fon.np.application.kkfunakoshi.form.TrainerForm;
import rs.fon.np.application.kkfunakoshi.form.tableModel.TableModelTrainer;

/**
 * Predstavlja serversku nit koja je pokrenuta uz pomoc serverskog soketa i sluzi za osluskivanje zahteva od strane klijenta.
 * @author Jelena Repac
 */
public class ServerThread  extends Thread{
    /**
     * Serverski soket
     */
    private ServerSocket serverSocket;
    /**
     * Lista korisnika
     */
    private List<HandleClientsRequest> clients;
    /**
     * Forma za prikaz trenera/korisnika
     */
    private TrainerForm usersForm;
    
    /**
     * Konstruktor 
     * @throws IOException Ukoliko dodje do I/O greske
     */
   public ServerThread() throws IOException{
       Properties properties= new Properties();
       properties.load(new FileInputStream(ServerConstants.SERVER_CONFIG_FILE_PATH));
       String port = properties.getProperty(ServerConstants.SERVER_CONFIG_PORT);
       serverSocket = new ServerSocket(Integer.parseInt(port));
       clients = new ArrayList<>();
   }

   /**
    * Metoda koja se pokrece nakon kreiranja objekta klase
    */
    @Override
    public void run() {
        try {
            while(true){
                Socket s= serverSocket.accept();
                connectClient(s);
                
            }
        } catch (Exception e) {
        }

    }

    /**
     * Metoda za povezivanje sa korisnikom
     * @param s soket
     */
    private void connectClient(Socket s) {
       HandleClientsRequest client= new HandleClientsRequest(this,s);
       clients.add(client);
       client.start();
    }
   /**
    * Metoda koja sluzi za prekidanje komunikacije i zatvaranje soketa
    * @throws IOException Ukoliko dodje do I/O greske
    */
   public void stopCommunication() throws IOException {
        for(HandleClientsRequest client: clients){
            client.stopCommunication();
        }
        try {
            sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Communication has been interrupted.");
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        serverSocket.close();
    }

   /**
    * Metoda za brisanje kliijenta, odnosno niti klijenta
    * @param ct HandleClientRequest
    */
    void removeClient(HandleClientsRequest ct) {
        clients.remove(ct);
        System.out.println("Client thread removed.");
    }

    /**
     * Metoda koja postavlja stanje korisnika
     * @param dbUser korisnik
     * @param loggedIn stanje
     */
    void setUserLoggedIn(Trainer dbUser, boolean loggedIn) {
       ((TableModelTrainer) usersForm.getTblUsers().getModel()).updateUser(dbUser,loggedIn);
       
    }

    /**
     * Metoda za odjavljivanje korisnika
     * @param ct Nit korisnika
     * @throws IOException Ukoliko dodje do I/O greske
     */
    void logout(HandleClientsRequest ct) throws IOException{
        ct.getSocket().close();
        removeClient(ct);
    }
   
    /**
     * Metoda za odjavljivanje svih krosnika sa sistema
     */
    public void logOutAllUsers(){
        for(HandleClientsRequest client:clients){
            Trainer u= client.getUser();
            
            if(u!=null){
                Controller.getInstance().logout(u);
                setUserLoggedIn(u, false);
                System.out.println("Korisnik izlogovan: "+ u.getFirstname());
            }
        }
    }

    /**
     * Postavljanje forme
     * @param u forma za prikaz svih trenera
     */
    public void setTrainersForm(TrainerForm u) {
        usersForm=u;
    }

    
    
}
