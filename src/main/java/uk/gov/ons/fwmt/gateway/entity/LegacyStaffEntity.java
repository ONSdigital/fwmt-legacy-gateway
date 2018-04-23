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
@Table(name = "staff")
public class LegacyStaffEntity {
    @Id
    public String employeeno;
    @Column(unique=true, nullable=true)
    public String authno;
    public String forename;
    public String surname;
    public String jobtitle;
    public String email;
    public String phone;
    public String usertype;
}