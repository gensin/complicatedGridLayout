package es.gensin.tripslist;

import android.content.Context;
import android.support.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Created on 06/07/2017.
 */

public class DateUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    //Need to use this one for special format on profile birthday that comes from server
    public static final String DATE_FORMAT_BACKEND = "yyyy-MM-dd'T'HH:mm:ss.SS";

    public static final String DATE_FORMAT_MIN ="yyyy-MM-dd'T'HH:mm:ss";
    public static final String DATE_FORMAT_SHORT = "HH:mm:ss a";
    public static final String DATE_FORMAT_JUST_DAY = "dd/MM/yyyy";

    private static final String HOUR_FORMAT = "hh:mm a";
    private static final String HOUR_24H_FORMAT = "HH:mm";
    private static final String FULL_TIME_FORMAT = "HH:mm:ssZZZZZ";

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_MIN = new SimpleDateFormat(DATE_FORMAT_MIN, Locale.getDefault());
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());

    //Need to use this one for special format on profile birthday that comes from server
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_BACKEND = new SimpleDateFormat(DATE_FORMAT_BACKEND, Locale.getDefault());

    private static final SimpleDateFormat SIMPLE_HOUR_24H_FORMAT = new SimpleDateFormat(HOUR_24H_FORMAT, Locale.getDefault());
    private static final SimpleDateFormat SIMPLE_HOUR_FORMAT = new SimpleDateFormat(HOUR_FORMAT, Locale.getDefault());
    private static final SimpleDateFormat SIMPLE_FULL_TIME_FORMAT = new SimpleDateFormat(FULL_TIME_FORMAT, Locale.getDefault());
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT_JUST_DAY = new SimpleDateFormat(DATE_FORMAT_JUST_DAY, Locale.getDefault());

    private static final String BEGINNING_OF_THE_TIMES_SHORT = "1970-01-01T";
    private static final String BEGINNING_OF_THE_TIMES = "1970-01-01T00:00:00.000Z";
    private static final String BEGINNING_OF_THE_TIMES_MICROS = "1970-01-01T00:00:00.000000Z";

    private final static TimeZone utc = TimeZone.getTimeZone("UTC");

    private final static TimeZone LOCALE_TIMEZONE = Calendar.getInstance().getTimeZone();

    /*
        Parse methods
     */
    public static Date getDateMinFromString(String dateString) {
        return SIMPLE_DATE_FORMAT_MIN.parse(dateString, new ParsePosition(0));
    }

    public static Date getDateFromString(String dateString) {
        return SIMPLE_DATE_FORMAT.parse(dateString, new ParsePosition(0));
    }

    public static String getStringWithLocaleFormat(Date date, Locale locale) {
        return new SimpleDateFormat(DATE_FORMAT, locale).format(date);
    }

    public static Date getDateFrom24HString(String string) {
        try {
            return SIMPLE_HOUR_24H_FORMAT.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static Calendar getCalendarFromString(String string) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(SIMPLE_DATE_FORMAT.parse(string));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    //Need to use this one for special format on profile birthday that comes from server
    public static Calendar getCalendarFromBackendString(String string) {
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(SIMPLE_DATE_FORMAT_BACKEND.parse(string));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static @Nullable
    String getStringFromDate(Date date) {
        if (date == null) {
            return null;
        } else {
            DateFormat df = SIMPLE_DATE_FORMAT;
            df.setTimeZone(utc);
            return df.format(date);
        }
    }

    public static String getStringJustDay(Date date) {
        if (date == null) {
            return null;
        } else {
            DateFormat df = SIMPLE_DATE_FORMAT_JUST_DAY;
            df.setTimeZone(utc);
            return df.format(date);
        }
    }

    public static String getStringJustHours(Date date) {
        if (date == null) {
            return null;
        } else {
            DateFormat df = SIMPLE_HOUR_24H_FORMAT;
            df.setTimeZone(LOCALE_TIMEZONE);
            return df.format(date);
        }
    }

    public static @Nullable
    String getHourFormatStringFromDate(Date date) {
        if (date == null) {
            return null;
        } else {
            DateFormat df = SIMPLE_HOUR_FORMAT;
            df.setTimeZone(LOCALE_TIMEZONE);
            String dateString = df.format(date);
            dateString = dateString.replace("am", "AM")
                                    .replace("pm", "PM")
                                    .replace("a.m.", "AM")
                                    .replace("p.m.", "PM");
            return dateString;
        }
    }

    public static @Nullable
    Date stringToSimpleDate(String dateAsString) {
        try {
            DateFormat df = SIMPLE_DATE_FORMAT;
            df.setTimeZone(utc);
            return df.parse(dateAsString);
        } catch (ParseException e) {
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    /*
        Util methods
     */
    public static boolean equalsToBeginningOfTimes(Date source) {
        String date1 = SIMPLE_DATE_FORMAT.format(source);

        return date1.equals(BEGINNING_OF_THE_TIMES) ||
                date1.equals(BEGINNING_OF_THE_TIMES_MICROS) ||
                date1.startsWith(BEGINNING_OF_THE_TIMES_SHORT);
    }

    public static Date getMotherOfDates() {
        return SIMPLE_DATE_FORMAT.parse(BEGINNING_OF_THE_TIMES, new ParsePosition(0));
    }

    public static int getAge(Date birthday) {
        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar bday = new GregorianCalendar();
        GregorianCalendar bdayThisYear = new GregorianCalendar();

        bday.setTime(birthday);
        bdayThisYear.setTime(birthday);
        bdayThisYear.set(Calendar.YEAR, today.get(Calendar.YEAR));

        int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);

        if (today.getTimeInMillis() < bdayThisYear.getTimeInMillis())
            age--;

        return age;
    }

    public static boolean isToday(Calendar calendar) {
        return calendar.get(Calendar.DAY_OF_YEAR) == Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
    }

    public static boolean isYesterday(Calendar calendar) {
        Calendar yesteday = Calendar.getInstance();
        yesteday.add(Calendar.DATE, -1);
        return calendar.get(Calendar.DAY_OF_YEAR) == yesteday.get(Calendar.DAY_OF_YEAR);
    }

    public static int getDateWeek(int dayOfMonth) {
        if (dayOfMonth <= 7) {
            return 1;
        } else if (dayOfMonth <= 14) {
            return 2;
        } else if (dayOfMonth <= 21) {
            return 3;
        } else { //dayOfMonth <= 31
            return 4;
        }
    }

    public static boolean isCurrentWeek(Calendar currentCalendar) {
        Calendar today = Calendar.getInstance();
        return currentCalendar.get(Calendar.WEEK_OF_YEAR) == today.get(Calendar.WEEK_OF_YEAR) && currentCalendar.get(Calendar.YEAR) == today.get(Calendar.YEAR);
    }

    public static boolean isPastWeek(Calendar currentCalendar) {
        Calendar pastWeek = Calendar.getInstance();
        pastWeek.add(Calendar.DATE, -7);

        return currentCalendar.get(Calendar.WEEK_OF_YEAR) == pastWeek.get(Calendar.WEEK_OF_YEAR) && currentCalendar.get(Calendar.YEAR) == pastWeek.get(Calendar.YEAR);
    }

    public static boolean isCurrentMonth(Calendar currentCalendar) {
        Calendar today = Calendar.getInstance();
        return currentCalendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) && currentCalendar.get(Calendar.YEAR) == today.get(Calendar.YEAR);
    }

    public static boolean isPastMonth(Calendar currentCalendar) {
        Calendar pastMonth = Calendar.getInstance();
        pastMonth.add(Calendar.MONTH, -1);

        return currentCalendar.get(Calendar.MONTH) == pastMonth.get(Calendar.MONTH) && currentCalendar.get(Calendar.YEAR) == pastMonth.get(Calendar.YEAR);
    }

    public static Calendar dateInitMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

        return calendar;
    }

    public static Calendar dateFinishMonth(Calendar calendar) {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

        return calendar;
    }

    public static Calendar dateToCalendar(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /*
        Difference methods
     */
    public static int getDifferenceInDays(long timeAfter, long timeBefore) {
        Calendar calendarAfter = Calendar.getInstance();
        calendarAfter.setTime(new Date(timeAfter));

        Calendar calendarNewAfter = Calendar.getInstance();
        calendarNewAfter.set(calendarAfter.get(Calendar.YEAR), calendarAfter.get(Calendar.MONTH), calendarAfter.get(Calendar.DAY_OF_MONTH));

        Calendar calendarBefore = Calendar.getInstance();
        calendarBefore.setTime(new Date(timeBefore));

        Calendar calendarNewBefore = Calendar.getInstance();
        calendarNewBefore.set(calendarBefore.get(Calendar.YEAR), calendarBefore.get(Calendar.MONTH), calendarBefore.get(Calendar.DAY_OF_MONTH));

        return (int) ((calendarNewAfter.getTime().getTime() - calendarNewBefore.getTime().getTime()) / (24 * 60 * 60 * 1000));
    }

    public static long getCalendarDiff(Calendar date1, Calendar date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime().getTime() - date1.getTime().getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = date2.getTime() - date1.getTime();
        return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static long getTimeInMillis(String stringDate) {
        return getDateFromString(stringDate).getTime();
    }

    public static Date getDateFromTime(long tripStartTime) {
        return new Date(tripStartTime);
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    public static String getHourFormatFromSeconds(int seconds){
        return String.format("%02d:%02d", TimeUnit.SECONDS.toHours(seconds), TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.HOURS.toMinutes(TimeUnit.SECONDS.toHours(seconds)));
    }

    public static String getOnlyDaysFromSeconds(int seconds) {
        return String.format("%02d", TimeUnit.SECONDS.toDays(seconds));
    }

    public static String getOnlyHoursFromSeconds(int seconds) {
        return String.format("%02d", TimeUnit.SECONDS.toHours(seconds) - TimeUnit.HOURS.toHours(TimeUnit.SECONDS.toDays(seconds)));
    }

    public static int getCurrentMonth() {
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    public static String getDeviceTimeZone() {
        return TimeZone.getDefault().getID();
    }
}
