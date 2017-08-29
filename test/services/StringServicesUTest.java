package services;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringServicesUTest {

    @Test
    public void checkValidity_matches_an_input_on_regex () {
        assertEquals(StringServices.checkValidity(null, "e*"), false);
        assertEquals(StringServices.checkValidity("eee", "e*"), true);
        assertEquals(StringServices.checkValidity("aaa", "e*"), false);
    }
}
