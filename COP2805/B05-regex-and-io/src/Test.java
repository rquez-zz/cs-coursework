import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
@SuppressWarnings("all")

public class Test {

    public static void main(String[] args){
    	
        Scanner console = new Scanner(System.in);
 
        while (true) {
        	System.out.println("\nEnter your regex:  ");
        	String myRegex = console.nextLine();
        	
            Pattern pattern = Pattern.compile(myRegex);
            
            System.out.println("\nEnter the String to search:  ");
            String searchString = console.nextLine();

            Matcher matcher = pattern.matcher(searchString);

            boolean found = false;
            while (matcher.find()) {
                System.out.format("I found the text \"%s\" starting at " +
                   "index %d and ending at index %d.%n",
                    matcher.group(), matcher.start(), matcher.end());
                found = true;
            }
            
            if(!found){
                System.out.println("No match found.\n");
            }
        }
    }
}