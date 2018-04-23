package uk.gov.ons.fwmt.gateway.debug;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloWorldWebPage {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
