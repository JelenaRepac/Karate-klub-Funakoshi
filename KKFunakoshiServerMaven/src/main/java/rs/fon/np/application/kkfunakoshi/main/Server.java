/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.main;

import  rs.fon.np.application.kkfunakoshi.form.ServerForm;

/**
 *
 * @author Jeks
 */
public class Server {

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.startServer();
        } catch (Exception e) {
            System.out.println("Error while starting server: " + e.getMessage());
        }

    }

    private void startServer() throws Exception {
        new ServerForm().setVisible(true);
    }
}
