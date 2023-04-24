package rs.fon.np.application.kkfunakoshi.client;

import java.io.IOException;
import java.net.Socket;

import javax.swing.JOptionPane;

import rs.fon.np.application.kkfunakoshi.client.Client;
import rs.fon.np.application.kkfunakoshi.client.Client.ThreadListener;
import rs.fon.np.application.kkfunakoshi.communication.Communication;
import rs.fon.np.application.kkfunakoshi.communication.Operations;
import rs.fon.np.application.kkfunakoshi.communication.Receiver;
import rs.fon.np.application.kkfunakoshi.communication.Request;
import rs.fon.np.application.kkfunakoshi.communication.Response;
import rs.fon.np.application.kkfunakoshi.communication.Sender;
import rs.fon.np.application.kkfunakoshi.controller.ControllerUI;
import rs.fon.np.application.kkfunakoshi.view.form.FrmLogin;

public class Client {


    private FrmLogin frm;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.connect();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "The server is not running.");
        }

    }

    public void connect() throws IOException {
        
        Socket soket = new Socket("localhost", 9000);
        
        Receiver receiver = new Receiver(soket);
        Sender sender = new Sender(soket);
        Communication.getInstance().setReceiver(receiver);
        Communication.getInstance().setSender(sender);
        ThreadListener listener = new ThreadListener(receiver, sender);

        frm = new FrmLogin();
        frm.setVisible(true);

    }

    class ThreadListener extends Thread {

        private Receiver receiver;
        private Sender sender;

        public ThreadListener(Receiver receiver, Sender sender) {
            this.receiver = receiver;
            this.sender = sender;
            start();
        }

        @Override
        public void run() {
            try {
                while (!isInterrupted()) {
                    sleep(100000);

                    Request request = new Request(Operations.LOGIN, null);
                    sender.send(request);

                    Response response = (Response) receiver.receive();
                    handleResponse(response);
                }

            } catch (Exception ex) {
                System.out.println("Prekinuta konekcija.");
                //ex.printStackTrace();
                System.exit(0);

            }
        }

        private void handleResponse(Response response) {
            try {
                if (response != null && response.getOperation() == Operations.SERVER_STOPPED) {
                    System.out.println(response.getResult());
                    Request request = new Request(Operations.STOP, null);
                    sender.send(request);

                    ControllerUI.getInstance().finish();
                    
                    this.interrupt();
                    frm.dispose();
                } else {

                }
            } catch (Exception ex) {
                System.out.println("Konekcija prekinuta>> " + ex.getMessage());
            }
        }

    }
}
