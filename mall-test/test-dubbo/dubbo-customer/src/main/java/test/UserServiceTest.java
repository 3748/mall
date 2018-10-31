package test;

import bean.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

import java.util.List;

public class UserServiceTest {
    private UserService userService;

    @Before
    public void setUp() throws Exception {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "classpath:dubbo/*.xml");
        this.userService = applicationContext.getBean(UserService.class);
    }

    @Test
    public void testQueryAll() {
        for (int i = 0;i<500;i++) {
            List<User> users = this.userService.queryAll();
            for (User user : users) {
                System.out.println(user);
            }
            try {
                Thread.sleep(i*10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
