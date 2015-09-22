import java.util.Scanner;

public class DualPhilosophers {
	
	public static int findArrangements(int[][] input)
	{
		int[] relations = relationify(input);

		if (hasCrossRelation(relations) || input[0][0] == input[0][1])
			return 0;
	
		if (isOneToOne(relations) && input[0][0] - input[0][1] == 1)
			return 1;

		return 2;
	}
	
	private static int[] relationify(int[][] input)
	{
		int[] relations = new int[input[0][0] + 1];
		
		for (int i = 1; i < input.length; i++)
			relations[input[i][0]] = input[i][1];
		
		return relations;
	}
	
	private static boolean hasCrossRelation(int[] relations)
	{
		for (int i = 1; i < relations.length - 1; i++)
		{
            if (relations[i] != 0 &&relations[i] == i + 1 && relations[i + 1] == i)
                return true;
		}
		return false;
	}
	
	private static boolean isOneToOne(int[] relations)
	{
		for (int i = 2; i < relations.length; i++)
		{
			for (int j = 1; j < relations.length; j++)
			{
				if (relations[i] != 0 && i != j && relations[i] == relations[j])
					return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args)
	{

		Scanner scanner = new Scanner(System.in);
	
		int numEssays = 1;
		int numSharedTerms = 1;
		while (numEssays != 0 && numSharedTerms != 0)
		{
            System.out.println("Enter how many essays and how many shared terms between those essays (separated by a space)");
            numEssays = scanner.nextInt();
            numSharedTerms = scanner.nextInt();
           
            if (numEssays != 0 && numEssays > numSharedTerms)
            {
                int[][] input = new int[numSharedTerms + 1][2];
                input[0][0] = numEssays;
                input[0][1] = numSharedTerms;
                	
                System.out.println("Now enter " + numSharedTerms + " pairs essays share the same terms (separated by a space)");
                for (int i = 1; i <= numSharedTerms; i++)
                {
                    input[i][0] = scanner.nextInt();
                    input[i][1] = scanner.nextInt();
                }
                
                System.out.println(findArrangements(input) + " arrangements");
            }
            else
            {
            	System.out.println("Error, try again");
            }
		}
		scanner.close();
	}
}
