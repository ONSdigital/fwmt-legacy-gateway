package uk.gov.ons.fwmt.gateway.entity;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class LegacyJobEntity {
  public static final String INITIAL_STATE = "INITIAL";
  public static final String SENT_STATE = "SENT";
  public static final String PROCESSED_STATE = "PROCESSED";
  public static final String ERRORED_STATE = "ERROR";

  @Id
  public String tmJobId;
  @Column(unique = true, nullable = true)
  public String legacyJobId;
  public String state;
  public String initialTimeStamp;
  public String sentTimeStamp;
  public String processedTimeStamp;
  public String erroredTimeStamp;

  public LegacyJobEntity(CreateJobRequest createJobRequest) {
    JobType job = createJobRequest.getJob();

    this.setTmJobId(job.getIdentity().getReference());
    this.setLegacyJobId(job.getLocation().getReference());
    this.setState(INITIAL_STATE);
    this.setInitialTimeStamp(LocalDateTime.now().toString());
    this.setSentTimeStamp(null);
    this.setProcessedTimeStamp(null);
    this.setErroredTimeStamp(null);
  }

  public void setStateSent() {
    this.setState(SENT_STATE);
    this.setSentTimeStamp(LocalDateTime.now().toString());
  }

  public void setStateProcessed() {
    this.setState(PROCESSED_STATE);
    this.setSentTimeStamp(LocalDateTime.now().toString());
  }

  public void setStateErrored() {
    this.setState(ERRORED_STATE);
    this.setSentTimeStamp(LocalDateTime.now().toString());
  }
}
