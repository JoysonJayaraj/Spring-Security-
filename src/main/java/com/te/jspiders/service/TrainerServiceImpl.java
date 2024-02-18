package com.te.jspiders.service;

import com.te.jspiders.dto.TrainerDTO;
import com.te.jspiders.entity.AppUser;
import com.te.jspiders.entity.Role;
import com.te.jspiders.entity.Trainer;
import com.te.jspiders.repository.AppUserRepository;
import com.te.jspiders.repository.RoleRepository;
import com.te.jspiders.repository.TrainerRepository;
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
public class TrainerServiceImpl implements TrainerService {
    private final TrainerRepository trainerRepository;
    private final RoleRepository roleRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder; //org.springframework.security.crypto.password.PasswordEncoder

    @Override
    public Optional<String> registerTrainer(TrainerDTO trainerDto) {
        log.info("TrainerServiceImpl:registerTrainer execution start, {}", trainerDto);
        Trainer trainer = new Trainer();
        BeanUtils.copyProperties(trainerDto, trainer);
        log.debug("TrainerServiceImpl:registerTrainer, student entity object created {}", trainer);

        Optional<Role> employeeRole = roleRepository.findByRoleName("ROLE_TRAINER");
        if (employeeRole.isPresent()) {
            Role roles = employeeRole.get();
            AppUser appUser = AppUser.builder()
                    .username(trainer.getTrainerId())
                    .password(passwordEncoder.encode(trainerDto.getPassword()))
                    .roles(new ArrayList()).build();
            roles.getAppUsers().add(appUser);
            appUser.getRoles().add(roles);
            appUserRepository.save(appUser);
        }
        return Optional.ofNullable(trainerRepository.save(trainer).getTrainerId());
    }
}