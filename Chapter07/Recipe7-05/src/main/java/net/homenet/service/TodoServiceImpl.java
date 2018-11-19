package net.homenet.service;

import net.homenet.domain.Todo;
import net.homenet.repository.TodoRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Secured("USER")
    @Override
    public List<Todo> listTodos() {
        return repository.findAll();
    }

    @Secured("USER")
    @Override
    public void save(Todo todo) {
        repository.save(todo);
    }

    @Secured("USER")
    @Override
    public void complete(long id) {
        Todo todo = repository.findOne(id);
        todo.setCompleted(true);
        repository.save(todo);
    }

    @Secured({ "USER", "ADMIN" })
    @Override
    public void remove(long id) {
        repository.remove(id);
    }

    @Secured("USER")
    @Override
    public Todo findById(long id) {
        return repository.findOne(id);
    }
}
