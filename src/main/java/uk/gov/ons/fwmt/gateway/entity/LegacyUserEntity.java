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
@Table(name = "users")
public class LegacyUserEntity {
    @Column(unique=true, nullable=true)
    public String authNo;
    @Id
    public String tmusername;
}