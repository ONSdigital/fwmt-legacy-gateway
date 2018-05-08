package uk.gov.ons.fwmt.gateway.utility.csvconverter;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobHeaderType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobIdentityType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.ResourceIdentityType;

public class LegacyUpdateJobHeaderRequestFactory {

    private static UpdateJobHeaderRequest buildRequest() {
        UpdateJobHeaderRequest request = new UpdateJobHeaderRequest();
        JobHeaderType jobHeaderType = new JobHeaderType();
        request.setJobHeader(jobHeaderType);
        return request;
    }
    
    public static UpdateJobHeaderRequest reallocate(String tmJobId, String username) {
        UpdateJobHeaderRequest update = buildRequest();
        
        ResourceIdentityType resourceIdentityType = new ResourceIdentityType();
        resourceIdentityType.setUsername(username);
        update.getJobHeader().setAllocatedTo(resourceIdentityType);
        
        JobIdentityType jobIdentityType = new JobIdentityType();
        jobIdentityType.setReference(tmJobId);
        update.getJobHeader().setJobIdentity(jobIdentityType);
        
        return update;
    }
}
