package fall2018.csc2017.slidingtiles;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class UserAccountAndManagerTest {
    @Test
    public void testCreateNewUser() {
        Factory f = new Factory();
        UserAccount newUser = f.createUserAccount("aaa", "b");
        assertEquals("aaa",newUser.getName());
        assertEquals("b",newUser.getPassword());
        newUser.setPassword("1a2c");
        assertEquals("1a2c",newUser.getPassword());
        newUser.setName("abcabc");
        assertEquals("abcabc",newUser.getName());
        newUser.setEmail("abc@gmail.com");
        assertEquals("abc@gmail.com",newUser.getEmail());
        newUser.setAge(3);
        assertEquals(3,(int) newUser.getAge());
    }

    @Test
    public void testUserAccountManager(){
        Factory f = new Factory();
        UserAccountManager m = f.createUserManager();
        assertEquals(5 ,m.getGlobalScoreBoard().size());
        m.addUser("aaa");
        assertEquals(1, m.getUserList().size());
    }
}