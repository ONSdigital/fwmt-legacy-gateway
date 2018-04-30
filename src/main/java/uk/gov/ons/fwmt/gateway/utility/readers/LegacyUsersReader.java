package uk.gov.ons.fwmt.gateway.utility.readers;

import com.opencsv.bean.CsvToBean;

import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyUserEntity;

public class LegacyUsersReader  extends CsvToBean<LegacyUserEntity>{

    public static LegacyUserEntity createUserEntity(LegacyStaffEntity staff, String tmUsername) {
        LegacyUserEntity legacyUserEntity = new LegacyUserEntity();
        
        legacyUserEntity.setAuthNo(staff.getAuthNo());
        legacyUserEntity.setTmUserName(tmUsername);
        
        return legacyUserEntity;
    }

}
