package io.bcn.springConference.Converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class ConvertStringToDate implements Converter<LocalDate, String> {

    private final DateTimeFormatter formatter;

    public ConvertStringToDate(String formatter) {
        this.formatter = DateTimeFormatter.ofPattern(formatter);
    }

    @Override
    public Result<String> convertToModel(LocalDate localDate, ValueContext valueContext) {
        try {
            return Result.ok(localDate.toString());
        } catch (DateTimeParseException e) {
            return Result.error("Invalid date format. Use " + formatter.toString());
        }
    }

    @Override
    public LocalDate convertToPresentation(String s, ValueContext valueContext) {
        if(s != null){
            return LocalDate.parse(s,this.formatter);
        }else{
            return LocalDate.now();
        }
    }
}
