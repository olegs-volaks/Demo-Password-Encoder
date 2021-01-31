import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordEncoderTest {

    private final PasswordEncoder subject = new PasswordEncoder();

    @Test
    void success() {
        for (int i = 0; i < 15; i++) {
            String password = "demo password";
            String encodedResult = subject.encode(password);
            assertTrue(subject.matches(encodedResult, password));
        }
    }

    @Test
    void fail() {
        for (int i = 0; i < 15; i++) {
            String password = "demo password";
            String encodedResult = subject.encode(password);
            assertFalse(subject.matches(encodedResult, "other password"));
        }
    }

    @Test
    void exception() {
        String password = "demo password";
        boolean result = false;
        try {
            subject.matches(password, password);
        } catch (IncorrectPasswordFormException ex) {
            assertEquals(ex.getMessage(), "Incorrect form of encoded password");
            result = true;
        }
        assertTrue(result);
    }
}