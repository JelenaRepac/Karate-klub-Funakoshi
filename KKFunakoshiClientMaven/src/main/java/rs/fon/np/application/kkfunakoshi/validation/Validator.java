/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rs.fon.np.application.kkfunakoshi.validation;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Jeks
 */
public class Validator {
    
    private final List<String> validationErrors;

    public Validator() {
        this.validationErrors = new ArrayList<>();
    }
    
    
    public static Validator startValidation() {
        return new Validator();
    }

    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }

    public Validator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    
    public Validator validateFormat(String value, String format, String errorMessage) throws ValidationException {
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(value);
        
        boolean b = m.matches();
        if (value == null || !b) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
 
    public Validator validateValueIsNumber(String value, String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                new BigDecimal(value);
            } else {
                this.validationErrors.add(errorMessage);
            }
        } catch (NumberFormatException nfe) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    
    public Validator validateValueIsDate(String value,String pattern , String errorMessage) throws ValidationException {
        try {
            if (value != null) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                sdf.parse(value);
            } else {
                this.validationErrors.add(errorMessage);
            }
        } catch (ParseException ex) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    
    public Validator validateContainsCharacter(String value, String character, String errorMessage) throws ValidationException {
        if (value.contains(character)) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    public Validator validateListIsNotEmpty(List list , String errorMessage) throws ValidationException {
            if (list == null || list.isEmpty()) {
                this.validationErrors.add(errorMessage);
            }
        return this;
    }

    public void throwIfInvalide() throws ValidationException {
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(this.validationErrors.stream().collect(Collectors.joining("\n")));
        }
    }

    public Validator validateNumberNotNegative(String value, String errorMessage) throws ValidationException{
          if ((Double.parseDouble(value))<0) {
                this.validationErrors.add(errorMessage);
            }
        return this; }

  

    
}
