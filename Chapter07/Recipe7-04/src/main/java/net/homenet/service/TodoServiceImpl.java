package net.homenet.service;

import net.homenet.domain.Todo;
import net.homenet.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;

    public TodoServiceImpl(TodoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Todo> listTodos() {
        return repository.findAll();
    }

    @Override
    public void save(Todo todo) {
        repository.save(todo);
    }

    @Override
    public void complete(long id) {
        Todo todo = repository.findOne(id);
        todo.setCompleted(true);
        repository.save(todo);
    }

    @Override
    public void remove(long id) {
        repository.remove(id);
    }

    @Override
    public Todo findById(long id) {
        return repository.findOne(id);
    }
}
