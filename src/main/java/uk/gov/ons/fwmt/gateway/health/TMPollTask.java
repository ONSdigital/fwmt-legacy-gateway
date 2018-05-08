package uk.gov.ons.fwmt.gateway.health;

import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.CriteriaType;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ParseAsType;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ArrayOfCriteriaType;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.QueryMessagesRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.QueryMessagesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import uk.gov.ons.fwmt.gateway.utility.TMMessageSubmitter;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBElement;
import java.util.Base64;

@Component
public class TMPollTask {
    public int pollCount = 0;
    public boolean tmStatus = false;
    public HttpStatus statusCode;

    @Value("${totalmobile.url}")
    private String urlStart;
    @Value("${totalmobile.message-queue-service}")
    private String urlEnd;
    @Value("${totalmobile.username}")
    private String username;
    @Value("${totalmobile.password}")
    private String password;

    private RestTemplate rest;
    private HttpHeaders headers;
    private TMMessageSubmitter submitter;

    @Autowired
    TMPollTask(TMMessageSubmitter submitter) {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        this.submitter = submitter;
    }

    @PostConstruct
    void postConstruct() {
        if (username != null && password != null) {
            String authorization = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
            headers.add("Authorization", "Basic " + authorization);
        }
    }

    public boolean get() {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        try {
            ResponseEntity<String> responseEntity = rest.exchange(urlStart + urlEnd, HttpMethod.GET, requestEntity, String.class);
            statusCode = responseEntity.getStatusCode();
        } catch (HttpStatusCodeException e) {
            statusCode = e.getStatusCode();
        }
        return statusCode.is2xxSuccessful();
    }

    public boolean checkErrors() throws Exception {
        com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory objectFactory =
                new com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory();
        // Query
        QueryMessagesRequest query = new QueryMessagesRequest();
        query.setCriteria(new ArrayOfCriteriaType());
        CriteriaType errorCriteria = new CriteriaType();
        errorCriteria.setProperty("Status");
        errorCriteria.setValue("Error");
        errorCriteria.setParseAs(ParseAsType.STRING);
        query.getCriteria().getCriterion().add(errorCriteria);
        // Send
        JAXBElement<QueryMessagesResponse> response = submitter.send(objectFactory.createQueryMessagesRequest(query));
        return response.getValue().getMessages().getMessage().size() == 0;
    }

    @Scheduled(fixedDelay = 60000)
    public void pollTM() throws Exception {
        pollCount += 1;
        tmStatus = get() && checkErrors();
    }
}
