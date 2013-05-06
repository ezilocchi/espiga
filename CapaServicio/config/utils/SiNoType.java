package utils;

import org.hibernate.type.CharBooleanType;

public class SiNoType extends CharBooleanType {
	
    protected final String getTrueString() {
        return "S";
    }

    protected final String getFalseString() {
        return "N";
    }

    public String getName() {
        return "si_no";
    }

}
