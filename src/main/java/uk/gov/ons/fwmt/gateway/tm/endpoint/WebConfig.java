package uk.gov.ons.fwmt.gateway.tm.endpoint;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;
import org.springframework.ws.wsdl.wsdl11.Wsdl11Definition;

@EnableWs
@Configuration
public class WebConfig extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet, "/tm/*");
    }

    // creates a /tm/GenericOutgoingWs.wsdl
    @Bean(name = "GenericOutgoingWs")
    public Wsdl11Definition defaultGenericOutgoingWsWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/Messaging_GenericOutgoingWs.wsdl"));
        return wsdl11Definition;
    }

    // creates a /tm/MessageQueueWs.wsdl
    @Bean(name = "MessageQueueWs")
    public Wsdl11Definition defaultMessageQueueWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/Messaging_MessageQueueWs.wsdl"));
        return wsdl11Definition;
    }

    // creates a /tm/OutgoingWs.wsdl
    @Bean(name = "OutgoingWs")
    public Wsdl11Definition defaultOutgoingWsWsdl11Definition() {
        SimpleWsdl11Definition wsdl11Definition = new SimpleWsdl11Definition();
        wsdl11Definition.setWsdl(new ClassPathResource("/wsdl/Messaging_OutgoingWs.wsdl"));
        return wsdl11Definition;
    }
}
