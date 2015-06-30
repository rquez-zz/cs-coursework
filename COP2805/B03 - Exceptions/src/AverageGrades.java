public class AverageGrades {

	public static void main(String[] args) {

		System.out.println("PART III\n");

		int[] gradeSet1 = { 100, 100, 90, 90, 80, 80, 70 };
		int[] gradeSet2 = { 55, 55, 55, 55, 101 };
		int[] gradeSet3 = { 44, 600 };

		try {

			System.out.print("Grades before average: ");
			for (int i = 0; i < gradeSet1.length; i++) {
				System.out.print(gradeSet1[i] + " ");
			}
			System.out.print("\n");

			System.out.println("Average is " + average(gradeSet1));

		} catch (InvalidGradeException ige) {

			System.out.println(ige.getMessage());

		} finally {

			System.out.print("Grades after average: ");
			for (int i = 0; i < gradeSet1.length; i++) {
				System.out.print(gradeSet1[i] + " ");
			}
			System.out.println("\n");

		}

		try {

			System.out.print("Grades before average: ");
			for (int i = 0; i < gradeSet2.length; i++) {
				System.out.print(gradeSet2[i] + " ");
			}
			System.out.print("\n");

			System.out.println("Average is " + average(gradeSet2));

		} catch (InvalidGradeException ige) {

			System.out.println(ige.getMessage());

		} finally {

			System.out.print("Grades after average: ");
			for (int i = 0; i < gradeSet2.length; i++) {
				System.out.print(gradeSet2[i] + " ");
			}
			System.out.println("\n");

		}

		try {

			System.out.print("Grades before average: ");
			for (int i = 0; i < gradeSet3.length; i++) {
				System.out.print(gradeSet3[i] + " ");
			}
			System.out.print("\n");

			System.out.println("Average is " + average(gradeSet3));

		} catch (InvalidGradeException ige) {

			System.out.println(ige.getMessage());

		} finally {

			System.out.print("Grades after average: ");
			for (int i = 0; i < gradeSet3.length; i++) {
				System.out.print(gradeSet3[i] + " ");
			}
			System.out.println("\n");

		}

	}

	public static int average(int[] grades) throws InvalidGradeException {

		int average = 0;

		try {
			for (int i = 0; i < grades.length; i++) {

				if (grades[i] > 100 || grades[i] < 0) {
					throw new InvalidGradeException(
							"InvalidGradeException has been thrown! "
									+ grades[i] + " is an invalid grade.");
				}

				average += grades[i];

			}

			average /= grades.length;

		} finally {

			for (int i = 0; i < grades.length; i++) {
				if (grades[i] < 60) {
					grades[i] = 60;
				}
			}
		}

		return average;

	}

}
