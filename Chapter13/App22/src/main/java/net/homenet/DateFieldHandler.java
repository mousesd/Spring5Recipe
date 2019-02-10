package net.homenet;

import org.exolab.castor.mapping.GeneralizedFieldHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFieldHandler extends GeneralizedFieldHandler {
    private final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public Object convertUponGet(Object value) {
        return format.format((Date) value);
    }

    @Override
    public Object convertUponSet(Object value) {
        try {
            return format.parse((String) value);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Class getFieldType() {
        return Date.class;
    }
}
