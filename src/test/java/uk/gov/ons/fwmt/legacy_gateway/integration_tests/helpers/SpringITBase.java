package uk.gov.ons.fwmt.legacy_gateway.integration_tests.helpers;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.service.impl.TMServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringITBase {
  @MockBean
  private TMServiceImpl tmServiceImpl;
}
