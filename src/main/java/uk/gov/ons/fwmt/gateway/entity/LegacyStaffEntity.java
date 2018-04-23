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
    @Column(unique=true)
    public String authno;
    public String title;
    public String forename;
    public String surname;
    public String sex;
    public String address1;
    public String address2;
    public String address3;
    public String address4;
    public String postcode;
    public String tel;
    public String tel2;
    public String mobiletel;
    public String emergencytel;
    public String email;
    public String gor;
    public String county;
    public String gridref;
    public String mainfieldforce;
    public String dateofbirth;
    public String inactiveno;
    public String easting;
    public String northing;
    public String fieldmanager;
    public String manageremployeeno;
    public String managerauthno;
    public String managerfirstname;
    public String managersurname;
    public String region;
    public String regionName;
    public String rmemployeeno;
    public String rmfirstname;
    public String rmsurname;
    public String phone;
    public String usertype;
}