import java.util.*;

public class HelloWorld {
	public static void main(String[] args) {
		System.out.println(days(2020, 1));
		System.out.println(days2(2020, 1));
	}
	
	public static String days(int year, int month ) {
		int[][] days = {
				{ 31, 31 },
				{ 28, 29 },
				{ 31, 31 },
				{ 30, 30 },
				{ 31, 31 },
				{ 30, 30 },
				{ 31, 31 },
				{ 31, 31 },
				{ 30, 30 },
				{ 31, 31 },
				{ 30, 30 },
				{ 31, 31 }
		};
		int ifLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))? 1 : 0;
		return days[month][ifLeapYear] + "days for " + year + "-" + (month + 1);
	}
	
	public static String days2(int year, int month ) {
		HashMap<Integer, ArrayList<Integer>> days = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<Integer> thirtyOne = new ArrayList<>(Arrays.asList(31, 31));
		ArrayList<Integer> thirty = new ArrayList<>(Arrays.asList(30, 30));
		ArrayList<Integer> feb = new ArrayList<>(Arrays.asList(28, 29));
		
		days.put(0, thirtyOne);
		days.put(1, feb);
		days.put(2, thirtyOne);
		days.put(3, thirty);
		days.put(4, thirtyOne);
		days.put(5, thirty);
		days.put(6, thirtyOne);
		days.put(7, thirtyOne);
		days.put(8, thirty);
		days.put(9, thirtyOne);
		days.put(10, thirty);
		days.put(11, thirtyOne);
		
		int ifLeapYear = ((year % 4 == 0) && (year % 100 != 0) || (year % 400 == 0))? 1 : 0;
		return days.get(month).get(ifLeapYear) + "days for " + year + "-" + (month + 1);
	}
}
