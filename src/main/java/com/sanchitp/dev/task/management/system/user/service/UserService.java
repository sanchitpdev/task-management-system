package com.sanchitp.dev.task.management.system.user.service;

import com.sanchitp.dev.task.management.system.common.enums.Role;
import com.sanchitp.dev.task.management.system.task.entity.Task;
import com.sanchitp.dev.task.management.system.task.repository.TaskRepository;
import com.sanchitp.dev.task.management.system.user.entity.User;
import com.sanchitp.dev.task.management.system.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final TaskRepository taskRepository;


    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public User createUser(String name, String email, Role role){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        return userRepository.save(user);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(Long userId) {
        User user = getUserById(userId);

        List<Task> tasks = taskRepository.findByAssignedTo(user);
        for (Task task : tasks) {
            task.setAssignedTo(null);
        }

        userRepository.delete(user);
    }

}
