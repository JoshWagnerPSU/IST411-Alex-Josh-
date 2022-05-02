package View;

/**
 * IST 411 Final Project
 * File: DateLabelFormatter.java
 * 
 * Purpose: This class helps to format and instantiate JDatePicker elements.
 * 
 * Last Edited On:4/24/2022
 * Last Edited By: Josh Wagner
 * 
 * @version 1.0
 * @author Alex Koontz and Josh Wagner
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar; 
import javax.swing.JFormattedTextField.AbstractFormatter;

public class DateLabelFormatter extends AbstractFormatter  {
    // Private Attributes
    private String datePattern = "MM/dd/yyyy";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
     
    /**
     * Allows calendar to convert string to JDatePicker value.
     * 
     * @param text Text format of date.
     * @return Formatted date.
     * @throws ParseException 
     */
    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }
 
    /**
     * Allows calendar to convert JDatePicker to string.
     * 
     * @param value The calendar date.
     * @return Formatted date.
     * @throws ParseException 
     */
    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
