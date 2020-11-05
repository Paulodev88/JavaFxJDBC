package model.exceptions;

import java.util.HashMap;
import java.util.Map;

public class ValidationException extends RuntimeException{

    private Map<String, String> errors = new HashMap<>();

    public ValidationException(String stg){
        super(stg);
    }


    public Map<String, String> getErrors(){
        return errors;
    }

    public void addError(String fieldName, String errorMessage){
        errors.put(fieldName, errorMessage);
    }
}
