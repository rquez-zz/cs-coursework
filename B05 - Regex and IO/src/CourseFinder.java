import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CourseFinder {

	public static void main(String[] args) {

		FileReader fileReader;
		FileWriter fileWriter;
		String line;
		String myRegex = "[A-Z\\s\\d]+L\\s\\d\\s\\d\\s[3-9]";
		Pattern pattern = Pattern.compile(myRegex);
		int count = 0;

		try {

			fileReader = new FileReader("ValenciaCourses.txt");

			BufferedReader bufferReader = new BufferedReader(fileReader);

			fileWriter = new FileWriter("Courses.txt");

			while ((line = bufferReader.readLine()) != null) {

				Matcher matcher = pattern.matcher(line);

				if (matcher.find()) {
					count++;
					fileWriter.write(matcher.group() + "\n");
					System.out.println(matcher.group());
				}

			}

			System.out.println(count + " classes found.");
			System.out.println("Results written to 'Courses.txt'");
			fileWriter.close();
			bufferReader.close();
			fileReader.close();
			

		} catch (FileNotFoundException fnf) {
			System.out.println(fnf.getMessage());
		} catch (IOException ioe) {
			// TODO Auto-generated catch block
			System.out.println(ioe.getMessage());
		}

	}

}
