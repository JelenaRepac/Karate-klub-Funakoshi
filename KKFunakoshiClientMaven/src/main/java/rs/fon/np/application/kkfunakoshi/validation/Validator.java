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
 * Klasa koja sadrzi metode za validaciju prosledjenih vrednosti.
 * @author Jelena Repac
 */
public class Validator {
    
	/**
	 * Lista koja sadrzi sve greske koje su nastale prilikom neuspele validacije
	 */
    private final List<String> validationErrors;

    /**
     * Bezparametarski konstruktor
     */
    public Validator() {
        this.validationErrors = new ArrayList<>();
    }
    
    /**
     * 
     * @return instancu klase Validator
     */
    public static Validator startValidation() {
        return new Validator();
    }
    /**
     * Sluzi za proveru da li je prosledjeni string null ili prazan. 
     * Ukoliko jeste poruka o gresci se dodaje u listu svih poruka.
     * @param value String koji se provera
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
    public Validator validateNotNullOrEmpty(String value, String errorMessage) throws ValidationException {
        if (value == null || value.trim().isEmpty()) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    /**
     * Sluzi za proveru da li je prosledjeni objekat null. 
     * Ukoliko jeste poruka o gresci se dodaje u listu svih poruka.
     * @param value objekat koji se provera
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
    public Validator validateNotNull(Object value, String errorMessage) throws ValidationException {
        if (value == null) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    /**
     * Sluzi za proveru da li je prosledjeni string jednak prosledjenom regularnom izrazu. 
     * Ukoliko nije ili ukoliko je prosledjeni string jednak null poruka o gresci se dodaje u listu svih poruka.
     * @param value String koji se provera
     * @param format regularni izraz 
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
    public Validator validateFormat(String value, String format, String errorMessage) throws ValidationException {
        Pattern p = Pattern.compile(format);
        Matcher m = p.matcher(value);
        
        boolean b = m.matches();
        if (value == null || !b) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    /**
     * Sluzi za proveru da li je prosledjeni string numericka vrednost. 
     * Ukoliko nije ili je prosledjeni string jednak null poruka o gresci se dodaje u listu svih poruka.
     * @param value String koji se provera
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
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
    /**
     * Sluzi za proveru da li je prosledjeni string datum koji odgovara prosledjenom paternu. 
     * Ukoliko nije ili je prosledjeni string jednak null poruka o gresci se dodaje u listu svih poruka.
     * @param value String koji se provera
     * @param pattern patern za prikaz datuma
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
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
    /**
     * Sluzi za proveru da li prosledjeni string sadrzi prosledjeni karakter.
     * Ukoliko ne sadrzi poruka o gresci se dodaje u listu svih poruka.
     * @param value String koji se provera
     * @param character karakter koji prosledjeni string treba da sadrzi
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
    public Validator validateContainsCharacter(String value, String character, String errorMessage) throws ValidationException {
        if (value.contains(character)) {
            this.validationErrors.add(errorMessage);
        }
        return this;
    }
    /**
     * Sluzi za proveru da li je prosledjena lista jednaka null ili je prazna.
     * Ukoliko jeste poruka o gresci se dodaje u listu svih poruka.
     * @param list lista koja se proverava
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */
    public Validator validateListIsNotEmpty(List list , String errorMessage) throws ValidationException {
            if (list == null || list.isEmpty()) {
                this.validationErrors.add(errorMessage);
            }
        return this;
    }

    /**
     * Baca ValidationException ukoliko je prikupljena bar jedna poruka o gresci.
     * @throws ValidationException ukoliko su nastale greske validacije
     */
    public void throwIfInvalide() throws ValidationException {
        if (!validationErrors.isEmpty()) {
            throw new ValidationException(this.validationErrors.stream().collect(Collectors.joining("\n")));
        }
    }

    /**
     * Sluzi za proveru da li je prosledjeni string manji od nule.
     * Ukoliko jeste poruka o gresci se dodaje u listu svih poruka.
     * @param value String koji se proverava
     * @param errorMessage poruka o gresci
     * @return instancu klase 
     * @throws ValidationException ukoliko provera ne uspe
     */

    public Validator validateNumberNotNegative(String value, String errorMessage) throws ValidationException{
          if ((Double.parseDouble(value))<0) {
                this.validationErrors.add(errorMessage);
            }
        return this;
        
    }

  

    
}
