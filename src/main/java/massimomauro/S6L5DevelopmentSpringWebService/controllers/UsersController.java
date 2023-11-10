package massimomauro.S6L5DevelopmentSpringWebService.controllers;

import massimomauro.S6L5DevelopmentSpringWebService.entities.User;
import massimomauro.S6L5DevelopmentSpringWebService.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;

    // 1. - POST http://localhost:3001/users (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public User saveUser(@RequestBody User body) throws Exception {
        return usersService.save(body);
    }

    // 2. - GET http://localhost:3001/users
    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.getUsers(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/users/{id}
    @GetMapping("/{authorId}")
    public User findById(@PathVariable int authorId){
        return usersService.findById(authorId);
    }

    // 4. - PUT http://localhost:3001/users/{id} (+ req.body)
    @PutMapping("/{authorId}")
    public User findAndUpdate(@PathVariable int authorId, @RequestBody User body){
        return usersService.findByIdAndUpdate(authorId, body);
    }

    // 5. - DELETE http://localhost:3001/users/{id}
    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int authorId) {
        usersService.findByIdAndDelete(authorId);
    }
}
