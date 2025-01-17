/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Messages.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public ServerSocket socket;
    public int port;
    public ListenConnectionRequestThread listenConnectionRequestThread;
    public ClientRemovingControlThread removingControlThread;
    public static ArrayList<SClient> clients;

    public static Semaphore pairingLockForTwoPair = new Semaphore(1, true);

    public Server(int port) {
        try {
            this.port = port;
            this.socket = new ServerSocket(this.port);
            this.listenConnectionRequestThread = new ListenConnectionRequestThread(this);
            removingControlThread = new ClientRemovingControlThread(this);
            this.clients = new ArrayList<SClient>();
            
        } catch (IOException ex) {
            System.out.println("There is an error occured when opening the server on port:" + this.port);

        }
    }

    // starts the acceptance
    public void ListenClientConnectionRequests() {
        this.listenConnectionRequestThread.start();
    }

    public static void SendMessage(SClient client, Message message) {
        try {
            client.cOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void SendMessage(SClient client, String message) {
        try {
            client.cOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public static void SendMessage(SClient client, Object object) {
        try {
            client.cOutput.writeObject(object);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
