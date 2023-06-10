package org.jfree.data.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimePeriodFormatException;
import org.jfree.data.time.Year;
import org.junit.Test;

public class QuarterClassTest {
    Quarter quarter;
    TimeZone timeZone = TimeZone.getTimeZone("GMT+0");
    Calendar calendar = Calendar.getInstance(timeZone);

    private void arrange(Integer quart, Integer year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange() {
        quarter = new Quarter();
    }

    private void arrange(Integer quart, Year year) {
        quarter = new Quarter(quart, year);
    }

    private void arrange(Date time) {
        quarter = new Quarter(time);
    }

    private void arrange(Date time, TimeZone zone) {
        quarter = new Quarter(time, zone);
    }

    @Test
    public void testQuarterDefaultCtor() {
        arrange();
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithQuarterAndYear() {
        arrange(1, 1900);
        assertEquals(1900, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithQuarterAndYear1() {
        arrange(4, 9999);
        assertEquals(9999, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithQuarterAndYear2() {
        arrange(2, 2023);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    // code doesn't check boundaries of quarters
    @Test
    public void testQuarterCtorWithQuarterAndYear3() {
        arrange(5, 2023);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(5, quarter.getQuarter());
    }

    // insufficient documentation
    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithQuarterAndYear4() {
        arrange(1, 1899);
        assertEquals(1899, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    // insufficient documentation
    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithQuarterAndYear5() {
        arrange(1, 10000);
        assertEquals(10000, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithQuarterAndYearObject() {
        Year y = new Year(1900);
        arrange(1, y);
        assertEquals(1900, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithQuarterAndYearObject1() {
        Year y = new Year(9999);
        arrange(4, y);
        assertEquals(9999, quarter.getYear().getYear());
        assertEquals(4, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithQuarterAndYearObject2() {
        Year y = new Year(2023);
        arrange(2, y);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    // code doesn't check boundaries of quarters
    @Test
    public void testQuarterCtorWithQuarterAndYearObject3() {
        Year y = new Year(2023);
        arrange(5, y);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(5, quarter.getQuarter());
    }

    // insufficient documentation
    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithQuarterAndYearObject4() {
        Year y = new Year(1899);
        arrange(1, y);
        assertEquals(1899, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    // insufficient documentation
    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithQuarterAndYearObject5() {
        Year y = new Year(10000);
        arrange(1, y);
        assertEquals(10000, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithDate() throws ParseException {
        // Create a Date object representing March 15, 2022 at 12:00pm UTC
        String dateString = "2022-03-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        arrange(specificDate);
        // Verify that the Quarter object was created with the correct values
        assertEquals(2022, quarter.getYear().getYear());
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithDate1() throws ParseException {
        String dateString = "2023-05-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        arrange(specificDate);
        assertEquals(2023, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    // insufficient requirement , documentation doesn't say it throws exception

    @Test(expected = IllegalArgumentException.class)
    public void testQuarterCtorWithDate2() throws ParseException {
        String dateString = "1100-05-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        arrange(specificDate);
        assertEquals(1100, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    @Test
    public void testQuarterCtorWithDateAndTimeZone() throws ParseException {
        String dateString = "1950-05-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        TimeZone timezone = TimeZone.getTimeZone("GMT+2");
        arrange(specificDate, timezone);
        assertEquals(1950, quarter.getYear().getYear());
        assertEquals(2, quarter.getQuarter());
    }

    ///////////////////////////////////////////////////////////////////
    @Test
    public void testGetQuartor() {
        arrange(1, 1900);
        assertEquals(1, quarter.getQuarter());
    }

    @Test
    public void testGetYear() {
        arrange(1, 1900);
        assertEquals(1900, quarter.getYear().getYear());
    }

    ///////////////////////////////////////////////////////////////////
    @Test
    public void testPrevious() {
        arrange(1, 1900);
        RegularTimePeriod previousQuarter = quarter.previous();
        assertNull(previousQuarter);
    }

    @Test
    public void testPrevious1() {
        arrange(2, 2022);
        RegularTimePeriod previousQuarter = quarter.previous();
        assertEquals(new Quarter(1, 2022), previousQuarter);
    }

    @Test
    public void testPrevious2() {
        arrange(1, 2022);
        RegularTimePeriod previousQuarter = quarter.previous();
        assertEquals(new Quarter(4, 2021), previousQuarter);
    }

    ///////////////////////////////////////////////////////////////////

    @Test
    public void testNext() {
        arrange(4, 9999);
        RegularTimePeriod nextQuarter = quarter.next();
        assertNull(nextQuarter);
    }

    @Test
    public void testNext1() {
        arrange(3, 2022);
        RegularTimePeriod nextQuarter = quarter.next();
        assertEquals(new Quarter(4, 2022), nextQuarter);
    }

    @Test
    public void testNext2() {
        arrange(4, 2022);
        RegularTimePeriod nextQuarter = quarter.next();
        assertEquals(new Quarter(1, 2023), nextQuarter);
    }

    ///////////////////////////////////////////////////////////////////

    @Test
    public void testGetSerialIndex() {
        arrange(1, 2023);
        long serialIndex = quarter.getSerialIndex();
        System.out.println(serialIndex);
        assertEquals(2023 * 4 + 1, serialIndex);
    }

    @Test
    public void testGetSerialIndex1() {
        arrange(4, 9999);
        long serialIndex = quarter.getSerialIndex();
        System.out.println(serialIndex);
        assertEquals(9999 * 4 + 4, serialIndex);
    }

    @Test
    public void testGetSerialIndex2() {
        arrange(1, 1900);
        long serialIndex = quarter.getSerialIndex();
        System.out.println(serialIndex);
        assertEquals(1900 * 4 + 1, serialIndex);
    }

    ///////////////////////////////////////////////////////////////////
    @Test
    public void testEquals() {
        arrange(1, 1900);
        Quarter quarter1 = new Quarter(1, 1900);
        assertTrue(quarter.equals(quarter1));
    }

    @Test
    public void testEquals1() throws ParseException {
        arrange(4, 9999);
        String dateString = "9999-12-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        Quarter quarter1 = new Quarter(specificDate);
        assertTrue(quarter.equals(quarter1));
    }

    @Test
    public void testEquals2() {
        arrange(1, 1900);
        Quarter quarter1 = new Quarter(4, 1900);
        assertFalse(quarter.equals(quarter1));
    }

    ///////////////////////////////////////////////////////////////////
    @Test
    public void testCompareTo() {
        arrange(1, 1900);
        Quarter quarter1 = new Quarter(1, 1900);
        assertTrue(quarter.compareTo(quarter1) == 0);
    }

    @Test
    public void testCompareTo1() throws ParseException {
        arrange(4, 9999);
        String dateString = "9999-12-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        Quarter quarter1 = new Quarter(specificDate);
        assertTrue(quarter.compareTo(quarter1) == 0);
    }

    @Test
    public void testCompareTo2() {
        arrange(1, 1900);
        Quarter quarter1 = new Quarter(4, 1900);
        assertTrue(quarter.compareTo(quarter1) < 0);
    }

    @Test
    public void testCompareTo3() {
        arrange(1, 1900);
        Quarter quarter1 = new Quarter(4, 1900);
        assertTrue(quarter1.compareTo(quarter) > 0);
    }

    ///////////////////////////////////////////////////////////////////
    @Test
    public void testToString() {
        arrange(1, 1900);
        assertEquals("Q1/1900", quarter.toString());
    }

    @Test
    public void testToString1() throws ParseException {
        String dateString = "9999-12-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        arrange(specificDate);
        assertEquals("Q4/9999", quarter.toString());
    }

    @Test
    public void testToString2() throws ParseException {
        String dateString = "9999-12-15 12:00:00";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date specificDate = dateFormat.parse(dateString);
        arrange(specificDate);
        assertNotEquals("Q2/9999", quarter.toString());
    }
    ///////////////////////////////////////////////////////////////////

    @Test
    public void testGetFirstMillisecond() {
        arrange(1, 1900);
        long firstMillisecond = quarter.getFirstMillisecond(calendar);
        assertEquals(-2208988800000L, firstMillisecond);
    }

    @Test
    public void testGetFirstMillisecond1() {
        arrange(1, 1970);
        long firstMillisecond = quarter.getFirstMillisecond(calendar);
        assertEquals(0L, firstMillisecond);
    }

    @Test
    public void testGetFirstMillisecond2() {
        arrange(1, 2038);
        long firstMillisecond = quarter.getFirstMillisecond(calendar);
        assertEquals(2145916800000L, firstMillisecond);
    }

    @Test
    public void testGetFirstMillisecond3() {
        arrange(4, 9999);
        long firstMillisecond = quarter.getFirstMillisecond(calendar);
        assertEquals(253394352000000L, firstMillisecond);
    }

    ///////////////////////////////////////////////////////////////////

    @Test
    public void testGetLastMillisecond() {
        arrange(1, 1900);
        long lastMillisecond = quarter.getLastMillisecond(calendar);
        assertEquals(-2201212800001L, lastMillisecond);
    }

    @Test
    public void testGetLastMillisecond1() {
        arrange(4, 1970);
        long lastMillisecond = quarter.getLastMillisecond(calendar);
        assertEquals(31535999999L, lastMillisecond);
    }

    @Test
    public void testGetLastMillisecond2() {
        arrange(3, 2038);
        long lastMillisecond = quarter.getLastMillisecond(calendar);
        assertEquals(2169503999999L, lastMillisecond);
    }

    @Test
    public void testGetLastMillisecond3() {
        arrange(4, 9999);
        long lastMillisecond = quarter.getLastMillisecond(calendar);
        assertEquals(253402300799999L, lastMillisecond);
    }

    ///////////////////////////////////////////////////////////////////

    @Test
    public void testParseQuarter() {
        arrange(1, 2022);
        Quarter quarter1 = Quarter.parseQuarter("2022-Q1");
        assertEquals(quarter, quarter1);
    }


    @Test
    public void testParseQuarter1() {
        arrange(2, 2022);
        // Test a valid input string in the format "QN-YYYY"
        Quarter quarter1 = Quarter.parseQuarter("Q2-2022");
        assertEquals(quarter, quarter1);
    }

    @Test
    public void testParseQuarter2() {
        arrange(3, 2022);
        // Test a valid input string with a space separator
        Quarter quarter1 = Quarter.parseQuarter("2022 Q3");
        assertEquals(quarter, quarter1);
    }

    @Test
    public void testParseQuarter22() {
        arrange(3, 2022);
        // Test a valid input string with a space separator
        Quarter quarter1 = Quarter.parseQuarter("Q3 2022");
        assertEquals(quarter, quarter1);
    }

    @Test
    public void testParseQuarter3() {
        arrange(4, 2022);
        // Test a valid input string with a forward-slash separator
        Quarter quarter1 = Quarter.parseQuarter("2022/Q4");
        assertEquals(quarter, quarter1);
    }

    @Test
    public void testParseQuarter33() {
        arrange(4, 2022);
        // Test a valid input string with a forward-slash separator
        Quarter quarter1 = Quarter.parseQuarter("Q4/2022");
        assertEquals(quarter, quarter1);
    }

    @Test
    public void testParseQuarter4() {
        arrange(1, 2030);
        // Test a valid input string with a comma separator
        Quarter quarter1 = Quarter.parseQuarter("2030,Q1");
        assertEquals(quarter, quarter1);
    }

    @Test
    public void testParseQuarter44() {
        arrange(1, 2030);
        // Test a valid input string with a comma separator
        Quarter quarter1 = Quarter.parseQuarter("Q1,2030");
        assertEquals(quarter, quarter1);
    }


    //insufficient documnetation
    @Test (expected = TimePeriodFormatException.class)
    public void testParseQuarter5() {
        arrange(1, 2030);
        // Test a valid input string with a comma separator
        Quarter quarter1 = Quarter.parseQuarter("2030_Q1");
        assertEquals(quarter, quarter1);
    }


    //this code doesn't throw exeption but it should
    @Test
    public void testParseQuarter6() {
        // Test an invalid input string with an incorrect quarter number
        assertThrows(IllegalArgumentException.class, () -> {
            Quarter.parseQuarter("2022-Q5");
        });
    }
}