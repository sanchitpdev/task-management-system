package com.sanchitp.dev.task.management.system.task.service;

import com.sanchitp.dev.task.management.system.common.enums.TaskStatus;
import com.sanchitp.dev.task.management.system.common.enums.exception.TaskNotFoundException;
import com.sanchitp.dev.task.management.system.common.enums.exception.UserNotFoundException;
import com.sanchitp.dev.task.management.system.security.service.CustomUserDetails;
import com.sanchitp.dev.task.management.system.security.util.SecurityUtils;
import com.sanchitp.dev.task.management.system.task.dto.TaskResponse;
import com.sanchitp.dev.task.management.system.task.entity.Task;
import com.sanchitp.dev.task.management.system.task.repository.TaskRepository;
import com.sanchitp.dev.task.management.system.user.entity.User;
import com.sanchitp.dev.task.management.system.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    /* ===================== HELPERS ===================== */

    private CustomUserDetails getCurrentUserOrThrow(){
        CustomUserDetails user  = SecurityUtils.getCurrentUser();
        if (user == null){
            throw new AccessDeniedException("Unauthorized");
        }
        return user;
    }

    private boolean isAdmin(CustomUserDetails user){
        return user.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private Task getTaskEntityById(Long id){
        return taskRepository.findById(id).orElseThrow(()->new TaskNotFoundException(id));
    }

    private TaskResponse toResponse(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getTaskStatus(),
                task.getAssignedTo() != null ? task.getAssignedTo().getId() : null
        );
    }

    /* ===================== CRUD ===================== */

    public TaskResponse createTask(String title ,String description){
        getCurrentUserOrThrow();

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);
        task.setTaskStatus(TaskStatus.CREATED);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse assignTaskToUser(Long taskId, Long userId){
        CustomUserDetails currentUser= getCurrentUserOrThrow();

        if(!isAdmin(currentUser)){
            throw new AccessDeniedException("Only admin can assign tasks");
        }

        Task task = getTaskEntityById(taskId);
        User user = userRepository.findById(userId).orElseThrow(()-> new UserNotFoundException(userId));

        task.setAssignedTo(user);
        task.setTaskStatus(TaskStatus.ASSIGNED);

        return toResponse(taskRepository.save(task)
        );
    }

    public TaskResponse getTaskById(Long id){
        return toResponse(getTaskEntityById(id));
    }

    public List<TaskResponse> findAllTasks(){
        return taskRepository.findAll().stream().map(this::toResponse).toList();
    }

    public List<TaskResponse> getTaskByUser(Long userId){
        User user =  userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));

        return taskRepository.findByAssignedTo(user).stream().map(this::toResponse).toList();
    }

    public TaskResponse updateTaskStatus(Long taskId,TaskStatus status)     {
        CustomUserDetails currentUser = getCurrentUserOrThrow();
        Task task = getTaskEntityById(taskId);

        boolean isAdmin = isAdmin(currentUser);
        boolean isAssignUser =
                task.getAssignedTo() != null &&
                task.getAssignedTo().getId().equals(currentUser.getUserId());

        if (!isAdmin && !isAssignUser){
            throw new AccessDeniedException(
                    "You are not allowed to update this task"
            );
        }

        task.setTaskStatus(status);
        return toResponse(taskRepository.save(task));
    }
}
