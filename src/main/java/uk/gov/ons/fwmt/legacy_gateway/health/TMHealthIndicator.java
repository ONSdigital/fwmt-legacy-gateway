package uk.gov.ons.fwmt.legacy_gateway.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

@Component
public class TMHealthIndicator extends AbstractHealthIndicator {
    TMPollTask tmPollTask;

    @Autowired
    TMHealthIndicator(TMPollTask tmPollTask) {
        this.tmPollTask = tmPollTask;
    }

    @Override
    protected void doHealthCheck(Health.Builder bldr) {
        bldr.withDetail("poll count", tmPollTask.pollCount);
        if (tmPollTask.statusCode == null) {
            bldr.down();
            return;
        }
        bldr.withDetail("status code", tmPollTask.statusCode);
        if (tmPollTask.tmStatus) {
            bldr.up();
        } else {
            bldr.down();
        }
    }
}
