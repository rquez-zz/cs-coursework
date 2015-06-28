
public class DualPhilosophers {
	
	public static int findArragements(int[][] input)
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
			for (int j = 1; j < relations.length; i++)
			{
				if (relations[i] != 0 && relations[i] == relations[j])
					return false;
			}
		}
		
		return true;
	}
}
