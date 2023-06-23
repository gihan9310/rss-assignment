package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeUtils {

    public static LocalDateTime convertStringToLocalDate(String dateText)throws Exception{
        SimpleDateFormat formatter = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
        String dateInString = dateText.split("\\+")[0];
        Date date = formatter.parse(dateInString);
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
