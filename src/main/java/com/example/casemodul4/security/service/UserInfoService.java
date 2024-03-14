package com.example.casemodul4.security.service;
import com.example.casemodul4.model.User;
import com.example.casemodul4.repository.IRoleRepository;
import com.example.casemodul4.repository.IUserRepository;
import com.example.casemodul4.security.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private IRoleRepository roleRepository;
    @Autowired
    private  IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserInfoService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return UserPrinciple.build(userRepository.findByUsername(username));
    }
//    public void saveUser(UserPrinciple userPrinciple){
//        Users users = new Users();
//        String encodedPassword = passwordEncoder.encode(userPrinciple.getPassword());
//        users.setUsername(userPrinciple.getUsername());
//        users.setPassword(encodedPassword);
//        Role role = roleRepository.findByName("ROLE_CUSTOMER");
//        users.setRoles(Arrays.asList(role));
//        userRepository.save(users);
//    }
//    public void updateUser(UserPrinciple userPrinciple,Long id){
//        Users users = userRepository.findById(id).orElse(null);
//        String encodedPassword = passwordEncoder.encode(userPrinciple.getPassword());
//        users.setUsername(userPrinciple.getUsername());
//        users.setPassword(encodedPassword);
//        userRepository.save(users);
//    }

    public boolean isUsernameUnique(String username){
        User existingUsers = userRepository.findByUsername(username);
        return existingUsers == null;
    }

}
