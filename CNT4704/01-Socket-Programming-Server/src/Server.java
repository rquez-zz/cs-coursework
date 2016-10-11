import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {

	private static final HashMap<String, String> questionAnswer = new HashMap<String, String>(){{
		put("What is your name?", "Ricardo Vasquez");
		put("What course is this project for?", "This project is for CNT4704, Fall 2016.");
		put("How many programming assignments do we have?", "I think there are two.");
		put("Do you think the second part is more challenging?", "Of course, the first part (socket programming) has been done for you.");
		put("How do you know to respond to these questions?", "You programmed me, didn’t you?");
		put("Can I ask you a personal question?", "I don’t think you’d like the answer");
		put("Can you answer an arbitrary question?", "Yes, I can, but I won’t.");
		put("Thank you.", "You are welcome. Have a nice day!");
	}};

	public static void main(String[] args) {
		// Let port be the first argument
		Integer port = Integer.parseInt(args[0]);
		System.out.format("Server running on port %d.\n", port);

		// Try with resources
		try (
			ServerSocket server = new ServerSocket(port);
			Socket client = server.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			PrintStream out = new PrintStream(client.getOutputStream(), true)
		) {
			out.println("You are connected to the ServerSocket running on port " + port);

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println(inputLine);
				if (questionAnswer.containsKey(inputLine)) {
					// Reply with answer
					out.println(questionAnswer.get(inputLine));

					// Check if terminating
					if (inputLine.equals("Thank you.")) {
						break;
					}
				} else {
					// No answer found for the question
					out.println("I do not understand your question. Please reenter your question.");
				}
			}
		} catch (IOException e) {
			System.out.format("Error accepting socket connection from client: %s\n", e.getMessage());
		}
	}
}
