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
/**
 * Predstavlja klasu pomocu koje se klijent povezuje sa serverom.
 * @author Jelena Repac
 *
 */
public class Client {


    private FrmLogin frm;

    /** Glavna metoda koja pokrece klijentsku aplikaciju
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
    /**
     * Omogucava ostvarivanje konekcije sa serverom kreiranjem objekta soketa, prijemnika(receiver) i posiljaoca (sender), 
     * i postavlja ih u singleton objekat Communication.
     * Pokrece i ThreadListener objekat koji osluskuje odgovore sa servera.
     * @throws IOException ukoliko dodje do I/O greske prilikom kreiranja soketa
     */
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
	/**
	 *  Predstavlja ugnjezdenu klasu, koja nasledjuje klasu Thread, i osluskuje odgovore od servera.
	 *  
	 * @author Jelena Repac
	 *
	 */
    class ThreadListener extends Thread {
    	/**
    	 * Objekat za prijem podataka
    	 */
        private Receiver receiver;
        /**
         * Objekat za slanje podataka
         */
        private Sender sender;

        public ThreadListener(Receiver receiver, Sender sender) {
            this.receiver = receiver;
            this.sender = sender;
            start();
        }

        /**
         * Izvrsava se kada ThreadListener startuje. 
         * Osluskuje odgovore od servera konstantnim prijemom Response objekata od Receiver objekta. 
         * Ukoliko dodje do greske prilikom prijema odgovora, metoda ispisuje poruku o gresci i prekida izvrsavanje klijentske aplikacije.
         */
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
        /**
         * Metoda obradjuje odgovor od servera proverom poslate operacije u response objektu.
         * Ukoliko je operacije jednaka SERVER_STOPPED, metoda zaustavlja ThreadListener nit i klijentsku aplikaciju.
         * @param response odgovor dobijen od servera
         */
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
