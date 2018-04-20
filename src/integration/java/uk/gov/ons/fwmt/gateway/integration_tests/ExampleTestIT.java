package uk.gov.ons.fwmt.gateway.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.gateway.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class ExampleTestIT {
    @Value("${foo}")
    String foo;

    @Test
    public void test() {
        System.out.println(foo);
    }

}
