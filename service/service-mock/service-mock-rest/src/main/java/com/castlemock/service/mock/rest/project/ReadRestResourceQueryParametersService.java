/*
 * Copyright 2018 Karl Dahlgren
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.castlemock.service.mock.rest.project;

import com.castlemock.model.core.Service;
import com.castlemock.model.core.ServiceResult;
import com.castlemock.model.core.ServiceTask;
import com.castlemock.model.mock.rest.domain.RestParameterQuery;
import com.castlemock.model.mock.rest.domain.RestResource;
import com.castlemock.service.core.utility.UrlUtility;
import com.castlemock.service.mock.rest.project.input.ReadRestResourceQueryParametersInput;
import com.castlemock.service.mock.rest.project.output.ReadRestResourceQueryParametersOutput;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Karl Dahlgren
 * @since 1.21
 */
@org.springframework.stereotype.Service
public class ReadRestResourceQueryParametersService extends AbstractRestProjectService
        implements Service<ReadRestResourceQueryParametersInput, ReadRestResourceQueryParametersOutput> {
    /**
     * The process message is responsible for processing an incoming serviceTask and generate
     * a response based on the incoming serviceTask input
     *
     * @param serviceTask The serviceTask that will be processed by the service
     * @return A result based on the processed incoming serviceTask
     * @see ServiceTask
     * @see ServiceResult
     */
    @Override
    public ServiceResult<ReadRestResourceQueryParametersOutput> process(
            final ServiceTask<ReadRestResourceQueryParametersInput> serviceTask) {
        final ReadRestResourceQueryParametersInput input = serviceTask.getInput();
        final RestResource resource = super.resourceRepository.findOne(input.getResourceId());
        final Set<String> pathParameters = UrlUtility.getPathParameters(resource.getUri());
        final Set<RestParameterQuery> parameterQueries = pathParameters.stream()
                .map(pathParameter -> {
                    RestParameterQuery query = new RestParameterQuery();
                    query.setQuery(pathParameter);
                    return query;
                })
                .collect(Collectors.toSet());

        return createServiceResult(ReadRestResourceQueryParametersOutput.builder()
                .queries(parameterQueries)
                .build());
    }
}
