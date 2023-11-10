package massimomauro.S6L5DevelopmentSpringWebService.services;

import massimomauro.S6L5DevelopmentSpringWebService.entities.User;
import massimomauro.S6L5DevelopmentSpringWebService.exceptions.BadRequestException;
import massimomauro.S6L5DevelopmentSpringWebService.exceptions.NotFoundException;
import massimomauro.S6L5DevelopmentSpringWebService.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public User save(User body) {
        usersRepository.findByEmail(body.getEmail()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.getEmail() + " è già stata utilizzata");
        });

        return usersRepository.save(body);
    }

    public Page<User> getUsers(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return usersRepository.findAll(pageable);
    }

    public User findById(int id) {
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        User found = this.findById(id);
        usersRepository.delete(found);
    }

    public User findByIdAndUpdate(int id, User body) {

        User found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());

        found.setAvatarURL(body.getAvatarURL());
        return usersRepository.save(found);
    }




}
