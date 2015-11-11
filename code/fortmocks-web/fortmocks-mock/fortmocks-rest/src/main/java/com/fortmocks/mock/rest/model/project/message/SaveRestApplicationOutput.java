package com.fortmocks.mock.rest.model.project.message;

import com.fortmocks.core.model.Output;
import com.fortmocks.mock.rest.model.project.dto.RestApplicationDto;
import com.fortmocks.mock.rest.model.project.dto.RestProjectDto;

/**
 * @author Karl Dahlgren
 * @since 1.0
 */
public class SaveRestApplicationOutput implements Output {

    private RestApplicationDto savedRestApplication;

    public RestApplicationDto getSavedRestApplication() {
        return savedRestApplication;
    }

    public void setSavedRestApplication(RestApplicationDto savedRestApplication) {
        this.savedRestApplication = savedRestApplication;
    }
}
