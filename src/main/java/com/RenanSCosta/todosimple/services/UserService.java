package com.RenanSCosta.todosimple.services;


import com.RenanSCosta.todosimple.models.User;
import com.RenanSCosta.todosimple.repositories.TaskRepository;
import com.RenanSCosta.todosimple.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    public User findById(Long id) {
        Optional<User> user = this.userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found with id: " + id + " Type: " + User.class.getName()));
    }

    @Transactional
    public User create(User obj) {
        obj.setId(null);
        obj = this.userRepository.save(obj);
        this.taskRepository.saveAll(obj.getTasks());
        return obj;

    }

    @Transactional
    public User update(User obj) {
        User newObj = this.findById(obj.getId());
        newObj.setPassaword(obj.getPassword());
        return this.userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possivel excluir pois há entidade relacionada!");
        }
    }
}
