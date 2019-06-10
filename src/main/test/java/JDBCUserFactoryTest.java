import model.entity.User;
import model.service.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class JDBCUserFactoryTest {
    @Test
    public void testFindByEmail() {
        UserService userService = new UserService();
        User user = userService.findByEmail("sergei_semenov@gmail.com");
        assertNotNull(user);
        assertEquals(user.getEmail(), "sergei_semenov@gmail.com");
    }
}