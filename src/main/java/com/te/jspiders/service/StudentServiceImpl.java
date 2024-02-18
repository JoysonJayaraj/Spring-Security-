package com.te.jspiders.service;

import com.te.jspiders.dto.StudentDTO;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Role;
import com.te.jspiders.entity.Student;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RoleRepository;
import com.te.jspiders.repository.StudentRepository;
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
public class StudentServiceImpl implements  StudentService{

    private final StudentRepository studentRepository;
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<String> registerStudent(StudentDTO studentDto) {
        log.info("StudentServiceImpl:registerStudent execution start, {}", studentDto);
        Student student = new Student();
        BeanUtils.copyProperties(studentDto, student);
        log.debug("StudentServiceImpl:registerStudent, employee entity object created {}", student);

        Optional<Role> employeeRole = roleRepository.findByRoleName("ROLE_STUDENT");
        if (employeeRole.isPresent()) {
            Role roles = employeeRole.get();
            AppUser appUser = AppUser.builder().username(student.getStudentId())
                    .password(passwordEncoder.encode(studentDto.getPassword())).roles(new ArrayList()).build();
            roles.getAppUsers().add(appUser);
            appUser.getRoles().add(roles);
            appUserRepository.save(appUser);
        }
        return Optional.ofNullable(studentRepository.save(student).getStudentId());
    }
}
