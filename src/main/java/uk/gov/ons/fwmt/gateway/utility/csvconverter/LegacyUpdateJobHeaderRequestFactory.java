package uk.gov.ons.fwmt.gateway.utility.csvconverter;

import java.util.List;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobHeaderType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobIdentityType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.ResourceIdentityType;

import uk.gov.ons.fwmt.gateway.entity.LegacyUserEntity;

public class LegacyUpdateJobHeaderRequestFactory {

    private static UpdateJobHeaderRequest buildRequest() {
        UpdateJobHeaderRequest request = new UpdateJobHeaderRequest();
        JobHeaderType jobHeaderType = new JobHeaderType();
        request.setJobHeader(jobHeaderType);
        return request;
    }
    
    public static UpdateJobHeaderRequest reallocate(String tmJobId, String staffId, List<LegacyUserEntity> users) {
        UpdateJobHeaderRequest update = buildRequest();
        
        ResourceIdentityType resourceIdentityType = new ResourceIdentityType();
        resourceIdentityType.setUsername(staffIdToTMUsername(users, staffId));
        update.getJobHeader().setAllocatedTo(resourceIdentityType);
        
        JobIdentityType jobIdentityType = new JobIdentityType();
        jobIdentityType.setReference(tmJobId);
        update.getJobHeader().setJobIdentity(jobIdentityType);
        
        return update;
    }
    
    private static String staffIdToTMUsername(List<LegacyUserEntity> users, String authNo) {
        for (LegacyUserEntity user : users) {
            if (user.getAuthno().equals(authNo)) {
                return user.getTmusername();
            }
        }
        return null;
    }
}
