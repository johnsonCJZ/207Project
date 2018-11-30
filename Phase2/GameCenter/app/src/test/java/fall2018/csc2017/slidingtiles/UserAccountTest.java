package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getHistorySliding() {
        Factory f = new Factory();
        UserAccount newUser = f.createUserAccount("aaa", "b");
        assertEquals("aaa",newUser.getName());
        assertEquals("b",newUser.getPassword());

        newUser.setPassword("1a2c");
        assertEquals("1a2c",newUser.getPassword());

        newUser.setName("abcabc");
        assertEquals("abcabc",newUser.getName());

        assertNull(newUser.getSpecific2048History("resumeHistory2048"));
        assertNull(newUser.getSpecificSlideHistory("history3x3"));
        assertNull(newUser.getSpecificSlideHistory("history4x4"));
        assertNull(newUser.getSpecificSlideHistory("history5x5"));
        assertNull(newUser.getSpecificMineHistory("historyMine"));

        newUser.setEmail("abc@gmail.com");
        assertEquals("abc@gmail.com",newUser.getEmail());

        newUser.setAge(3);
        assertEquals(3,(int) newUser.getAge());
    }
}