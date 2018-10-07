package net.homenet.s2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("SameParameterValue")
@DatePrefixAnnotation
public class DatePrefixGenerator implements PrefixGenerator {

    private DateFormat formatter;

    void setPattern(String pattern) {
        formatter = new SimpleDateFormat(pattern);
    }

    @Override
    public String getPrefix() {
        return formatter.format(new Date());
    }
}
