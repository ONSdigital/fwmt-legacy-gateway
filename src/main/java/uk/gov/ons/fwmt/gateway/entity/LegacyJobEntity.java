package uk.gov.ons.fwmt.gateway.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jobs")
public class LegacyJobEntity {
    @Id
    public String tmjobid;
    @Column(unique=true, nullable=true)
    public String serno;
    public String state;
    public String initaltimestamp;
    public String senttimestamp;
    public String processedtimestamp;
    public String erroredtimestamp;
}
