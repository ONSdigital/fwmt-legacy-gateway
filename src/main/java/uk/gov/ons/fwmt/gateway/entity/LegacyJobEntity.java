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
import java.util.Calendar;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class LegacyJobEntity {
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
    this.setState("INITAL");
    this.setInitialTimeStamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
    this.setSentTimeStamp(null);
    this.setProcessedTimeStamp(null);
    this.setErroredTimeStamp(null);
  }

  public void setStateSent() {
    this.setState("SENT");
    this.setSentTimeStamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
  }

  public void setStateProcessed() {
    this.setState("PROCESSED");
    this.setSentTimeStamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
  }

  public void setStateErrored() {
    this.setState("ERRORED");
    this.setSentTimeStamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
  }
}
