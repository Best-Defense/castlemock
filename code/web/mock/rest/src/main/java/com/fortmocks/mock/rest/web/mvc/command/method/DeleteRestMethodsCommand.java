/*
 * Copyright 2015 Karl Dahlgren
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

package com.fortmocks.mock.rest.web.mvc.command.method;

import com.fortmocks.core.mock.rest.model.project.dto.RestMethodDto;

import java.util.List;

/**
 * The DeleteRestMethodCommand is a command class and is used to carry information on
 * which resource should be deleted from the database
 * @author Karl Dahlgren
 * @since 1.0
 */
public class DeleteRestMethodsCommand {

    private List<RestMethodDto> restMethods;

    public List<RestMethodDto> getRestMethods() {
        return restMethods;
    }

    public void setRestMethods(List<RestMethodDto> restMethods) {
        this.restMethods = restMethods;
    }
}

