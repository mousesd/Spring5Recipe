package net.homenet.service;

import net.homenet.domain.Todo;
import net.homenet.repository.TodoRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public List<Todo> listTodos() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public void save(Todo todo) {
        repository.save(todo);
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public void complete(long id) {
        Todo todo = repository.findOne(id);
        todo.setCompleted(true);
        repository.save(todo);
    }

    @Override
    @PreAuthorize("hasAuthority('ADMIN')")
    public void remove(long id) {
        repository.remove(id);
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    @PostAuthorize("returnObject.owner == authentication.name")
    public Todo findById(long id) {
        return repository.findOne(id);
    }
}
