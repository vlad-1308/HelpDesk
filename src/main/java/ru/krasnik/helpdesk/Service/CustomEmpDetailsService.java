package ru.krasnik.helpdesk.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.krasnik.helpdesk.Dao.EmployeeRepository;

@Service
@RequiredArgsConstructor
public class CustomEmpDetailsService implements UserDetailsService {

    private final EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return employeeRepository.findByLogin(login);
    }
}
