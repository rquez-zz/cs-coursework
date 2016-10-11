import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

	private static final String SERVER_END_MSG = "You are welcome. Have a nice day!";

	public static void main(String[] args) {
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);

		try (
				Socket socket = new Socket(hostname, port);
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		) {
			String fromServer, fromUser;
			while((fromServer = in.readLine()) != null) {
				// Read server response
				System.out.println(fromServer);
				if (fromServer.equals(SERVER_END_MSG)) {
					break;
				}
				// Send client message
				fromUser = stdin.readLine();
				if (fromUser != null) {
					out.println(fromUser);
				}
			}
		} catch (IOException e) {
			System.out.format("Error opening socket connection to server: %s\n", e.getMessage());
		}
	}
}
