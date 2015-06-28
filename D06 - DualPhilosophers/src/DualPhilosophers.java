
public class DualPhilosophers {
	
	
	public static int findArragements(int[][] input)
	{
		int[] relations = relationify(input);

		if (hasCrossRelation(relations))
			return 0;
		
		return 2;
	}
	
	public static int[] relationify(int[][] input)
	{
		int[] relations = new int[input[0][0] + 1];
		
		for (int i = 1; i < input.length; i++)
			relations[input[i][0]] = input[i][1];
		
		return relations;
	}
	
	public static boolean hasCrossRelation(int[] relations)
	{
		boolean result = false;
	
		for (int i = 1; i < relations.length - 1; i++)
		{
			if (relations[i] != 0)
			{
				if (relations[i] == i + 1 && relations[i + 1] == i)
				{
					result = true;
				}
			}
		}
		return result;
	}

}
