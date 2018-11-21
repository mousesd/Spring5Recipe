package net.homenet.service;

import net.homenet.domain.Todo;
import net.homenet.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.GrantedAuthoritySid;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
    private final TodoRepository repository;
    private final MutableAclService aclService;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public TodoServiceImpl(TodoRepository repository, MutableAclService aclService) {
        this.repository = repository;
        this.aclService = aclService;
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    @PostFilter("hasAnyAuthority('ADMIN') or hasPermission(filterObject, 'read')")
    public List<Todo> listTodos() {
        return repository.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    public void save(Todo todo) {
        repository.save(todo);

        ObjectIdentity oid = new ObjectIdentityImpl(Todo.class, todo.getId());
        MutableAcl acl = aclService.createAcl(oid);
        acl.insertAce(0, BasePermission.READ, new PrincipalSid(todo.getOwner()), true);
        acl.insertAce(1, BasePermission.WRITE, new PrincipalSid(todo.getOwner()), true);
        acl.insertAce(2, BasePermission.DELETE, new PrincipalSid(todo.getOwner()), true);

        acl.insertAce(0, BasePermission.READ, new GrantedAuthoritySid("ADMIN"), true);
        acl.insertAce(1, BasePermission.WRITE, new GrantedAuthoritySid("ADMIN"), true);
        acl.insertAce(2, BasePermission.DELETE, new GrantedAuthoritySid("ADMIN"), true);
    }

    @Override
    @PreAuthorize("hasPermission(#id, 'net.homenet.domain.Todo', 'write')")
    public void complete(long id) {
        Todo todo = repository.findOne(id);
        todo.setCompleted(true);
        repository.save(todo);
    }

    @Override
    @PreAuthorize("hasPermission(#id, 'net.homenet.domain.Todo', 'delete')")
    public void remove(long id) {
        repository.remove(id);

        ObjectIdentity oid = new ObjectIdentityImpl(Todo.class, id);
        aclService.deleteAcl(oid, false);
    }

    @Override
    @PreAuthorize("hasAuthority('USER')")
    @PostAuthorize("hasPermission(returnObject, 'read')")
    public Todo findById(long id) {
        return repository.findOne(id);
    }
}
