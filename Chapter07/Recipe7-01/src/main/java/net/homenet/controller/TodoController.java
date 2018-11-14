package net.homenet.controller;

import net.homenet.domain.Todo;
import net.homenet.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public String list(Model model) {
        List<Todo> todos = todoService.listTodos();
        model.addAttribute("todos", todos);
        return "todos";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("todo", new Todo());
        return "todo-create";
    }

    @PostMapping
    public String newMessage(@ModelAttribute @Validated Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            return "todo-create";
        }

        String owner = "mousesd@gmail.com";
        todo.setOwner(owner);
        todoService.save(todo);
        return "redirect:/todos";
    }

    @PostMapping("/{todoId}/completed")
    public String complete(@PathVariable("todoId") long todoId) {
        todoService.complete(todoId);
        return "redirect:/todos";
    }

    @PostMapping("/{todoId}")
    public String delete(@PathVariable("todoId") long todoId) {
        todoService.remove(todoId);
        return "redirect:/todos";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("id", "owner");
    }
}
