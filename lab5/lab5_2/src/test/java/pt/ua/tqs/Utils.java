package pt.ua.tqs;

import java.time.LocalDate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

    public static LocalDate localDateFromDateParts(String year, String month, String day){
        return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
    }

    public static LocalDate isoTextToLocalDate(String isoText){
        return LocalDate.parse(isoText);
    }

    public static Date dateFromLocalDate(LocalDate local) {
        return Date.from(local.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}