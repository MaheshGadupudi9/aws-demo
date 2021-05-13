package co.za.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

@SpringBootApplication
public class AwsDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AwsDemoApplication.class, args);
	}

	/*public static void main(String[] args) {

		Date date = new Date(121,03,05);
//		Date day0 = workoutStartDateForSLACalculationTest(date, 0L);
//		System.out.println("day0 = " + day0);

		date = new Date(121,03,05);
		Date day1 = workoutStartDateForSLACalculationTest(date, 1L);
		System.out.println("day1 = " + day1);

		Date day2 = workoutStartDateForSLACalculationTest(date, 2L);
		System.out.println("day2 = " + day2);
	}*/

	private static Date workoutStartDateForSLACalculationTest(Date fileDate, Long daysDiff) {
		Date startDate = new Date(fileDate.getTime());
		boolean moveForwardInTime = (daysDiff == 0);
		boolean isHolidayOrWeekend = true; //5th April 2021 (holiday) diff(0)

		int counter  = daysDiff.intValue();

		//WM:  Wee need to know if we started on a workinday
		boolean startedOnWorkingDay = !isHolidayOrWeekend;

		//WM: Decision on how to move forward or backward
		boolean canMoveBackWard = !moveForwardInTime && counter >= 0;
		boolean canMoveForward = moveForwardInTime && isHolidayOrWeekend;

		//WM: Keep looping when  move backward or move forward is true
		while ((canMoveBackWard ) ||  (canMoveForward)) {

			// When the daysDiff from the file is (0) then move forward to get the first working day.
			// When the daysDiff from the file is (1; 2 ...) then move backwards to get the first working day.
			startDate = (moveForwardInTime)
					? add( startDate,5, 1)
					: add( startDate,5, -1);

 			isHolidayOrWeekend = false ;

			//decrement if it is a working days
			if(!isHolidayOrWeekend) {
				counter--;
			}
			//WM: if file date was not a working day then counter needs to be >=0 (counter-- will stop at -1)
			//WM:  if file date was on a working day then counter needs to be > 0 ( counter-- will stop at 0 )
			canMoveBackWard = !moveForwardInTime && ((!startedOnWorkingDay  && counter >= 0) || (startedOnWorkingDay  && counter > 0));

			// move forwared only if daysdiff = 0 and is a holiday or weekend
			canMoveForward = moveForwardInTime && isHolidayOrWeekend;
		}

		//WM: return the start date when move backward and move forward are false
		return startDate;
	}
	private static Date add(Date date, int calendarField, int amount) {
		validateDateNotNull(date != null, "The date must not be null", new Object[0]);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(calendarField, amount);
		return c.getTime();
	}

	public static void validateDateNotNull(boolean expression, String message, Object... values) {
		if (!expression) {
			throw new IllegalArgumentException(String.format(message, values));
		}
	}
}
