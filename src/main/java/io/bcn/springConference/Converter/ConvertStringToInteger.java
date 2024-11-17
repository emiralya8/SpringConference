package io.bcn.springConference.Converter;

import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

public class ConvertStringToInteger implements Converter<String, Integer>
{

    @Override
    public Result<Integer> convertToModel(String s, ValueContext valueContext) {
        // ok is a static helper method that
        // creates a Result
        try {
            // ok is a static helper method that
            // creates a Result
            return Result.ok(Integer.valueOf(
                    s));
        } catch (NumberFormatException e) {
            // error is a static helper method
            // that creates a Result
            return Result.error("Enter a number");
        }
    }

    @Override
    public String convertToPresentation(Integer integer, ValueContext valueContext) {
        return String.valueOf(integer);
    }
}
