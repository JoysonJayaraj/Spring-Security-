package com.te.jspiders.service;

import com.te.jspiders.dto.EmployeeDTO;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Employee;
import com.te.jspiders.entity.Role;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.EmployeeRepository;
import com.te.jspiders.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<String> registerEmployee(EmployeeDTO employeeDto) {
        log.info("EmployeeServiceImpl:registerEmployee execution start, {}", employeeDto);
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDto, employee);
        log.debug("EmployeeServiceImpl:registerEmployee, employee entity object created {}", employee);
        Optional<Role> employeeRole = roleRepository.findByRoleName("ROLE_EMPLOYEE");
        if (employeeRole.isPresent()) {
            log.debug("EmployeeServiceImpl:registerEmployee, role found in database");
            Role roles = employeeRole.get();
            AppUser appUser = AppUser.builder().username(employee.getEmployeeId())
                    .password(passwordEncoder.encode(employeeDto.getPassword())).roles(new ArrayList()).build();
            roles.getAppUsers().add(appUser);
            appUser.getRoles().add(roles);
            appUserRepository.save(appUser);
            log.info("EmployeeServiceImpl:registerEmployee, registraction done");
        }
        log.info("EmployeeServiceImpl:registerEmployee returning the data");
        return Optional.ofNullable(employeeRepository.save(employee).getEmployeeId());
    }
}
