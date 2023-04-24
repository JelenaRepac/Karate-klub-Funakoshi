/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.communication;

import java.net.Socket;
import rs.fon.np.application.kkfunakoshi.domain.Trainer;

/**
 *
 * @author Jeks
 */
public class Communication {

  private static Communication instance;
    private Socket socket;
    private Receiver receiver;
    private Sender sender;

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    private Communication() {
    }

    public static Communication getInstance() {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;

    }

    public Response login(Request request) throws Exception {
        //posalji zahtev
        sender.send(request);
       // System.out.println("Zahtev za prijavom na sistem je poslat.");
        return (Response) receiver.receive();

    }

    public Response sendRequest(Request request) throws Exception {
        sender.send(request);
        return (Response) receiver.receive();

    }

    public Response receiveResponse() throws Exception {
        return (Response) receiver.receive();

    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }    

    public void logout(Trainer user) throws Exception {
        try {
            Request request= new Request(Operations.LOGOUT,user);
            sender.send(request);
        } catch (Exception e) {
            throw new Exception("Error in getting logging out: " + e.getMessage());
        }
    }
}

