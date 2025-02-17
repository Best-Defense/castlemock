/*
 * Copyright 2019 Karl Dahlgren
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

package com.castlemock.web.core.controller.rest;

import com.castlemock.model.core.ServiceProcessor;
import com.castlemock.model.core.user.User;
import com.castlemock.service.core.user.input.CreateUserInput;
import com.castlemock.service.core.user.input.DeleteUserInput;
import com.castlemock.service.core.user.input.ReadAllUsersInput;
import com.castlemock.service.core.user.input.ReadUserInput;
import com.castlemock.service.core.user.input.UpdateUserInput;
import com.castlemock.service.core.user.output.CreateUserOutput;
import com.castlemock.service.core.user.output.ReadAllUsersOutput;
import com.castlemock.service.core.user.output.ReadUserOutput;
import com.castlemock.service.core.user.output.UpdateUserOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/rest/core")
@Tag(name="Core - User", description="REST Operations for Castle Mock Core")
@ConditionalOnExpression("${server.mode.demo} == false")
public class UserCoreRestController extends AbstractRestController {

    public UserCoreRestController(final ServiceProcessor serviceProcessor){
        super(serviceProcessor);
    }

    @Operation(summary =  "Create user",
            description = "Create user. Required authorization: Admin.")
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    ResponseEntity<User> createUser(@RequestBody final User user) {
        final CreateUserOutput output = serviceProcessor.process(CreateUserInput.builder()
                .user(user)
                .build());
        final User createdUser = output.getSavedUser();
        createdUser.setPassword(EMPTY);
        return ResponseEntity.ok(createdUser);
    }

    @Operation(summary =  "Update user",
            description = "Update user. Required authorization: Admin.")
    @RequestMapping(method = RequestMethod.PUT, value = "/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    ResponseEntity<User> updateUser(@PathVariable("userId") final String userId,
                    @RequestBody final User user) {
        final UpdateUserOutput output = serviceProcessor.process(UpdateUserInput.builder()
                .user(user)
                .userId(userId)
                .build());
        final User updatedUser = output.getUpdatedUser();
        updatedUser.setPassword(EMPTY);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary =  "Get all users",
            description = "Get all users. Required authorization: Admin.")
    @RequestMapping(method = RequestMethod.GET, value = "/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    ResponseEntity<List<User>> getUsers() {
        final ReadAllUsersOutput output = serviceProcessor.process(new ReadAllUsersInput());
        final List<User> users = output.getUsers();
        users.forEach(user -> user.setPassword(EMPTY));
        return ResponseEntity.ok(users);
    }

    @Operation(summary =  "Get user",
            description = "Get user. Required authorization: Admin.")
    @RequestMapping(method = RequestMethod.GET, value = "/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    ResponseEntity<User> getUser(@PathVariable("userId") final String userId) {
        final ReadUserOutput output = serviceProcessor.process(ReadUserInput.builder()
                .userId(userId)
                .build());
        final User user = output.getUser();
        user.setPassword(EMPTY);
        return ResponseEntity.ok(user);
    }

    @Operation(summary =  "Delete user", description = "Delete user. Required authorization: Admin.")
    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public @ResponseBody
    void deleteUser(@PathVariable("userId") final String userId) {
        serviceProcessor.process(DeleteUserInput.builder()
                .userId(userId)
                .build());
    }

}
