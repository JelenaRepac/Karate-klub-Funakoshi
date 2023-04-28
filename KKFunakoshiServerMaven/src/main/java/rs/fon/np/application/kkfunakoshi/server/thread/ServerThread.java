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
 *
 * @author Jelena Repac
 */
public class ServerThread  extends Thread{
    
    private ServerSocket serverSocket;
    private List<HandleClientsRequest> clients;
    private TrainerForm usersForm;
    
   public ServerThread() throws IOException{
       Properties properties= new Properties();
       properties.load(new FileInputStream(ServerConstants.SERVER_CONFIG_FILE_PATH));
       String port = properties.getProperty(ServerConstants.SERVER_CONFIG_PORT);
       serverSocket = new ServerSocket(Integer.parseInt(port));
       clients = new ArrayList<>();
   }

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

    private void connectClient(Socket s) {
       HandleClientsRequest client= new HandleClientsRequest(this,s);
       clients.add(client);
       client.start();
    }
   
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

    void removeClient(HandleClientsRequest ct) {
        clients.remove(ct);
        System.out.println("Client thread removed.");
    }

    void setUserLoggedIn(Trainer dbUser, boolean loggedIn) {
       ((TableModelTrainer) usersForm.getTblUsers().getModel()).updateUser(dbUser,loggedIn);
       
    }

    void logout(HandleClientsRequest ct) throws IOException{
        ct.getSocket().close();
        removeClient(ct);
    }
   
    
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

    public void setTrainersForm(TrainerForm u) {
        usersForm=u;
    }

    
    
}
