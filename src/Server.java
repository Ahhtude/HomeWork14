import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        InetAddress ip = InetAddress.getLocalHost();

        DatagramSocket ds = new DatagramSocket(1234);

        byte[] receive = new byte[65535];

        byte[] send = new byte[65535];

        while (true)
        {

            DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);

            ds.receive(DpReceive);

            System.out.println("Client:-" + data(receive));

            //Exit from loop processor
            if (data(receive).toString().equals("bye"))
            {
                System.out.println("Client sent bye.....EXITING");
                break;
            }

            receive = new byte[65535];

            /** Sending block*/

            String message = sc.nextLine();

            send=message.getBytes();

            DatagramPacket DpSend =
                    new DatagramPacket(send, send.length, ip, 50613);

            ds.send(DpSend);

            //Exit from loop processor by "bye"
            if (send.equals("bye"))
                break;
            
            send = new byte[65535];



        }
    }


    public static StringBuilder data(byte[] a)
    {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0)
        {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}

