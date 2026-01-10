    package com.sanchitp.dev.task.management.system.user.controller;

    import com.sanchitp.dev.task.management.system.user.dto.CreateUserRequest;
    import com.sanchitp.dev.task.management.system.user.entity.User;
    import com.sanchitp.dev.task.management.system.user.service.UserService;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/users")
    public class UserController{

        private final UserService userService;

        public UserController(UserService userService) {
            this.userService = userService;
        }

        //Create User
        @PostMapping
        public User createUser(@RequestBody CreateUserRequest request){
            return userService.createUser(
                    request.getName(),
                    request.getEmail(),
                    request.getRole()
            );
        }

        //Get User By Id
        @GetMapping("/{id}")
        public User getUserById(@PathVariable Long id){
            return userService.getUserById(id);
        }

        //Get All Users
        @GetMapping
        public List<User> getAllUsers(){
            return userService.getAllUsers();
        }


        @DeleteMapping("/{id}")
        public void deleteUser(@PathVariable Long id){
            userService.deleteUser(id);
        }
    }
