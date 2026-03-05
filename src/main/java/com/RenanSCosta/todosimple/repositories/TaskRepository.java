package com.RenanSCosta.todosimple.repositories;

import com.RenanSCosta.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByUser_id(Long userId);

    // consulta personalizada usando JPQL para buscar tarefas por ID do usuário
//  @Query (value = "SELECT t FROM Task t WHERE t.user.id = :id")
//  List<Task> findByUser_id(@Param("id") Long userId);

    // consulta personalizada usando SQL puro para buscar tasks por id do usuário
//  @Query(value = "SELECT * FROM tasks t WHERE t.user_id = :id", nativeQuery = true)
//  List<Task> findByUser_id(@Param("id")Long userId);

}
