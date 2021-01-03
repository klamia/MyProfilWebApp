package dz.com.cerist.myprofil.web.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


public class DateUtil {

	private static Format format;

	public static int getYearFromDate(Date date) {
		int year = 0;

		format = new SimpleDateFormat("yyyy");
		year = Integer.valueOf(format.format(date));
		return year;
	}

	public static int getMonthFromDate(Date date) {
		int month = 0;

		format = new SimpleDateFormat("MM");
		month = Integer.valueOf(format.format(date));
		return month;
	}

	public static int getDayFromDate(Date date) {
		int day = 0;

		format = new SimpleDateFormat("dd");
		day = Integer.valueOf(format.format(date));
		return day;
	}

	/**
	 * Methode renvoi le nombre d'annee entre deux date
	 * 
	 * @param date
	 * @return
	 */
	public static int nombreOfYearBetweenTwoDates(Date dateDebut, Date dateFin) {
		int nombreYear = 0;

		format = new SimpleDateFormat("yyyy");
		nombreYear = Integer.valueOf(format.format(dateFin)) - Integer.valueOf(format.format(dateDebut));
		return nombreYear;
	}

	public static int nombreOfMonthsBetweenTwoDates(Date dateDebut, Date dateFin) {

		GregorianCalendar gcDebut = new GregorianCalendar();

		gcDebut.setTime(dateDebut);

		GregorianCalendar gcFin = new GregorianCalendar();

		gcFin.setTime(dateFin);

		int gap = 0;

		gcDebut.add(Calendar.MONTH, 1);

		while (gcDebut.compareTo(gcFin) <= 0) {
			gap++;
			gcDebut.add(Calendar.MONTH, 1);
		}
		return gap;
	}

	/**
	 * Methode permet de formater une date donner
	 * 
	 * @param date
	 * @param pattern
	 *            (Date format )
	 * @return String
	 */
	public static String fromaterDate(Date date, String pattern) {

		format = new SimpleDateFormat(pattern);

		return format.format(date);
	}

	/**
	 * Methode permet de formater une date donner
	 * 
	 * @param date
	 * @param pattern
	 *            (Date format )
	 * @return String
	 */
	public static Date fromaterDate(String date, String pattern) {
		try {

			format = new SimpleDateFormat(pattern);

			SimpleDateFormat sdf = new SimpleDateFormat();

			return sdf.parse(format.format(date));

		} catch (Exception e) {

			return null;
		}
	}

	/**
	 * Methode permet de comparer deux date et return int
	 * 
	 * @param d1
	 * @param d2
	 * @return if d1>d2 return 1 , if d1<d2 return -1, if d1=d2 return 0
	 */
	public static int compareTowDates(Date d1, Date d2) {

		SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		System.out.print(fm.format(c1.getTime()) + " is ");
		if (c1.before(c2)) {
			System.out.println("less than " + fm.format(c2.getTime()));
			return -1;
		} else if (c1.after(c2)) {
			System.out.println("greater than " + fm.format(c2.getTime()));
			return 1;
		} else if (c1.equals(c2)) {
			System.out.println("is equal to " + fm.format(c2.getTime()));
			return 0;
		}
		return 2;

	}
	
	/**
	 * the value 0 if the argument Date1 is equal to this Date2; a value less than
	 * 0 if this Date1 is before the Date2 argument; and a value greater than 0 if
	 * this Date1 is after the Date2 argument.
	 * @param d1
	 * @param d2
	 * @return if d1 > d2 return >0 , if d1<d2 return < 0 , if d1=d2 return 0
	 */

	public static int compareTowDate(Date d1, Date d2) {
		try {

			return d1.compareTo(d2);

		} catch (NullPointerException e) {
			return -1;
		}

	}

	/**
	 * Verifier si lann�e bissextile ou non
	 * 
	 * @param Year
	 * @return
	 */
	public static boolean IsLeapYear(int Year) {
		return (((Year & 3) == 0) && ((Year % 100 != 0) || (Year % 400 == 0)));
	}

	/*
	 * Add Day/Month/Year to a Date add() is used to add values to a Calendar
	 * object. You specify which Calendar field is to be affected by the
	 * operation (Calendar.YEAR, Calendar.MONTH, Calendar.DATE).
	 */
	
	public static Date nextDate(Date date){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DATE, 1);
		return c1.getTime();
	}
	
	public static Date previousDate(Date date){
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DATE, -1);
		return c1.getTime();
	}

	public static final String DATE_FORMAT = "dd-MM-yyyy";

	// See Java DOCS for different date formats
	// public static final String DATE_FORMAT = "yyyy-MM-dd";

	public static void addToDate() {
		System.out.println("1. Add to a Date Operation\n");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		// Gets a calendar using the default time zone and locale.
		Calendar c1 = Calendar.getInstance();
		Date d1 = new Date();
		// System.out.println("Todays date in Calendar Format : "+c1);

		System.out.println("c1.getTime() : " + c1.getTime());
		System.out.println("c1.get(Calendar.YEAR): " + c1.get(Calendar.YEAR));
		System.out.println("Todays date in Date Format : " + d1);
		c1.set(1999, 0, 20); // (year,month,date)
		System.out.println("c1.set(1999,0 ,20) : " + c1.getTime());
		c1.add(Calendar.DATE, 20);
		System.out.println("Date + 20 days is : " + sdf.format(c1.getTime()));
		System.out.println();
		System.out.println("-------------------------------------");
	}

	/*
	 * Substract Day/Month/Year to a Date
	 * 
	 * roll() is used to substract values to a Calendar object. You specify
	 * which Calendar field is to be affected by the operation (Calendar.YEAR,
	 * Calendar.MONTH, Calendar.DATE).
	 * 
	 * Note: To substract, simply use a negative argument. roll() does the same
	 * thing except you specify if you want to roll up (add 1) or roll down
	 * (substract 1) to the specified Calendar field. The operation only affects
	 * the specified field while add() adjusts other Calendar fields. See the
	 * following example, roll() makes january rolls to december in the same
	 * year while add() substract the YEAR field for the correct result. Hence
	 * add() is preferred even for subtraction by using a negative element.
	 */

	public static void subToDate() {

		System.out.println("2. Subtract to a date Operation\n");
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		Calendar c1 = Calendar.getInstance();
		c1.set(1999, 0, 20);
		System.out.println("Date is : " + sdf.format(c1.getTime()));
		// roll down, substract 1 month
		c1.roll(Calendar.MONTH, false);
		System.out.println("Date roll down 1 month : " + sdf.format(c1.getTime()));
		c1.set(1999, 0, 20);
		System.out.println("Date is : " + sdf.format(c1.getTime()));
		c1.add(Calendar.MONTH, -1);
		// substract 1 month
		System.out.println("Date minus 1 month : " + sdf.format(c1.getTime()));
		System.out.println();
		System.out.println("-------------------------------------");
	}

	public static void daysBetween2Dates() {

		System.out.println("3. No of Days between 2 dates\n");
		Calendar c1 = Calendar.getInstance(); // new GregorianCalendar();
		Calendar c2 = Calendar.getInstance(); // new GregorianCalendar();
		c1.set(1999, 0, 20);
		c2.set(1999, 0, 22);
		System.out.println("Days Between " + c1.getTime() + " and " + c2.getTime() + " is");
		System.out.println((c2.getTime().getTime() - c1.getTime().getTime()) / (24 * 3600 * 1000));
		System.out.println();
		System.out.println("-------------------------------------");
	}

	public static int daysInMonth(Date date) {

		System.out.println("4. No of Days in a month for a given date\n");

		Calendar c1 = Calendar.getInstance(); // new GregorianCalendar();

		c1.setTime(date);

		int year = c1.get(Calendar.YEAR);

		// int month = c1.get(Calendar.MONTH);

		// int days = c1.get(Calendar.DATE);

		int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

		return daysInMonths[1] += DateUtil.isLeapYear(year) ? 1 : 0;

		// System.out.println("Days in " + month + "th month for year" + year +
		// "is " + daysInMonths[c1.get(Calendar.MONTH)]);

		// System.out.println();

		// System.out.println("-------------------------------------");

	}

	public static boolean validateAGivenDate(String date, String dateformat) {

		System.out.println("Validate a given date\n");

		Date dt1 = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
			sdf.setLenient(false);
			dt1 = sdf.parse(date);
			System.out.println("Date is ok = " + dt1 + "(" + date + ")");

			return true;
		} catch (ParseException e) {
			System.out.println(e.getMessage());
			return false;
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid date");
			return false;
		}

	}

	public static void compare2Dates() {

		System.out.println("6. Comparision of 2 dates\n");
		SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy");
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.set(2000, 02, 15);
		c2.set(2001, 02, 15);
		System.out.print(fm.format(c1.getTime()) + " is ");
		if (c1.before(c2)) {
			System.out.println("less than " + fm.format(c2.getTime()));
		} else if (c1.after(c2)) {
			System.out.println("greater than " + fm.format(c2.getTime()));
		} else if (c1.equals(c2)) {
			System.out.println("is equal to " + fm.format(c2.getTime()));
		}
		System.out.println();
		System.out.println("-------------------------------------");
	}

	public static void getDayofTheDate() {

		System.out.println("7. Get the day for a given date\n");
		Date d1 = new Date();
		String day = null;
		DateFormat f = new SimpleDateFormat("EEEE");
		try {
			day = f.format(d1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("The day for " + d1 + " is " + day);
		System.out.println();
		System.out.println("-------------------------------------");
	}

	// Utility Method to find whether an Year is a Leap year or Not
	public static boolean isLeapYear(int year) {

		if ((year % 100 != 0) || (year % 400 == 0)) {
			return true;
		}
		return false;
	}


	
	public static int getNbJoursWeekend(Calendar from, Calendar to) {
		   int nbJoursWeekend = 0;
		 
		   for (Calendar c = (Calendar) from.clone(); c.compareTo(to) <= 0; c.add(Calendar.DATE, +1)) {
		      int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		    
		      if (dayOfWeek  == Calendar.SATURDAY || dayOfWeek == Calendar.FRIDAY) {
		        		    	 
		    	  nbJoursWeekend++;
		      }
		   }
		   return nbJoursWeekend;
		}
	
	public static int getNbJourEntreDeuxDates (Calendar from, Calendar to) {
		   int nbJours = 0;
		
		   nbJours =(int)  ((to.getTimeInMillis()-from.getTimeInMillis()) / (24*3600*1000) );
		   
		   return nbJours+1 ;
		}
	 
	 

	  /**
     * Calculate the difference between two dates, ignoring weekends.
     *
     * @param d1 the first day of the interval
     * @param d2 the day after the end of the interval
     * @return the number of days in the interval, excluding weekends
     */
    public static long diff(Date d1, Date d2) {
   	   	
	return wdnum(d2) - wdnum(d1);
    }
    
    /**
     * Return the number of week days between Monday, 29 December, 1969 and the
     * given date.
     *
     * @param d a date
     * @return the number of week days since Monday, 29 December, 1969
     */
    public static long wdnum(Date date) {
    	
    	
	long l = div(date.getTime(), 1000 * 60 * 60 * 24)[0] + 3;
	long d[] = div(l, 7);
	return l - 2 * d[0] - Math.max(d[1] - 5, 0);
    }
    
    /**
     * Divide two integers, rounding towards -Inf and returning quotient and
     * remainder.
     *
     * @param n the numerator
     * @param d the denominator
     * @return the quotient and remainder
     * @throws ArithmeticException if <code>d == 0</code>
     */
    public static long[] div(long n, long d) {
	long q = n / d;
	long r = n % d;
	
	if (r < 0) {
	    q--;
	    r += d;
	}
	return new long[] {
	    q, r,
	};
    }
	 

}
