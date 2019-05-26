import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) throws IOException {

        byte[] receive = new byte[65535];

        Scanner sc = new Scanner(System.in);

        DatagramSocket ds = new DatagramSocket();

        InetAddress ip = InetAddress.getLocalHost();

        System.out.println(ds.getLocalPort());

        byte buf[] = null;

        // loop processor
        while (true) {
            String inp = sc.nextLine();

            buf = inp.getBytes();

            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, 1234);

            ds.send(DpSend);

            //Exit from loop processor
            if (inp.equals("bye"))
                break;


            /** received block*/

            DatagramPacket DpReceive = new DatagramPacket(receive, receive.length);

            ds.receive(DpReceive);

            System.out.println("Server:-" + data(receive));

            //Exit by "bye" from server
            if (data(receive).toString().equals("bye")) {
                System.out.println("Server sent bye.....EXITING");
                break;
            }
            receive = new byte[65535];

        }
    }
    //convertor toString from byte

    public static StringBuilder data(byte[] a) {
        if (a == null)
            return null;
        StringBuilder ret = new StringBuilder();
        int i = 0;
        while (a[i] != 0) {
            ret.append((char) a[i]);
            i++;
        }
        return ret;
    }
}