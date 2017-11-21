/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pc
 */
public class VAlidador {
    private Pattern pattern;
private Matcher matcher;

private static final String EMAIL_PATTERN = 
    "^[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}$";

public VAlidador() {
    pattern = Pattern.compile(EMAIL_PATTERN);
}


public boolean validate(final String hex) {

    matcher = pattern.matcher(hex);
    return matcher.matches();

}
}
