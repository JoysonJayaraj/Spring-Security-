package com.te.jspiders;

import com.te.jspiders.entity.Admin;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Role;
import com.te.jspiders.repository.AdminRepository;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@RequiredArgsConstructor
@SpringBootApplication
public class JspidersApplication {

	private final AdminRepository adminRepository;
	private final RoleRepository roleRepository;
	private final AppUserRepository appUserRepository;
	private final PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(JspidersApplication.class, args);
	}

	/*
	 * CommandLineRunner is used to execute the code when the application started to
	 * execute. And here we are storing the roles so that they are readily available
	 * in the database. Also store admin data in Admin table and AppUser table.
	 */
	@Bean
	public CommandLineRunner runner() {
		return args -> {
			if(roleRepository.findByRoleName("ROLE_STUDENT").isEmpty()) {
				Role student = Role.builder().roleName("ROLE_STUDENT").build();
				Role employee = Role.builder().roleName("ROLE_EMPLOYEE").build();
				Role trainer = Role.builder().roleName("ROLE_TRAINER").build();
				Role admin = Role.builder().roleName("ROLE_ADMIN").appUsers(new ArrayList()).build();

				Admin admin01 = Admin.builder().adminId("ADMIN01").adminName("admin01").build();

				AppUser adminCredentials = AppUser.builder().username(admin01.getAdminId())
						.password(passwordEncoder.encode("qwerty")).roles(new ArrayList()).build();

				roleRepository.save(student);
				roleRepository.save(employee);
				roleRepository.save(trainer);

				adminRepository.save(admin01);

				adminCredentials.getRoles().add(admin);
				admin.getAppUsers().add(adminCredentials);

				roleRepository.save(admin);
				appUserRepository.save(adminCredentials);
			}
		};
	}
}
