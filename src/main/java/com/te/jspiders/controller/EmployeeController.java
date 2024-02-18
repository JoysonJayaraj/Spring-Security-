package com.te.jspiders.controller;

import com.te.jspiders.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping(path = "/api/employee")
@RestController
public class EmployeeController {

    @PutMapping(path = "/update")
    public ApiResponse<String> updateEmployee() {
        return new ApiResponse<String>("Employee update successfull!", null, "Update api being used");
    }

    @DeleteMapping(path = "/delete")
    public ApiResponse<String> deleteEmployee() {
        return new ApiResponse<String>("Employee delete successfull!", null, "Delete api being used");
    }

    @PutMapping(path = "/changePassword")
    public ApiResponse<String> changePassword() {
        return new ApiResponse<String>("Employee change password successfull!", null, "Change password api being used");
    }
}