package massimomauro.S6L5DevelopmentSpringWebService.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import massimomauro.S6L5DevelopmentSpringWebService.entities.User;
import massimomauro.S6L5DevelopmentSpringWebService.exceptions.BadRequestException;
import massimomauro.S6L5DevelopmentSpringWebService.exceptions.NotFoundException;
import massimomauro.S6L5DevelopmentSpringWebService.payloads.NewUserDTO;
import massimomauro.S6L5DevelopmentSpringWebService.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Cloudinary cloudinary;

    public User save(NewUserDTO body) throws IOException {

        usersRepository.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + body.email() + " è già stata utilizzata");
        });
        User newUser = new User();

        newUser.setName(body.name());
        newUser.setSurname(body.surname());
        newUser.setEmail(body.email());
        User savedUser = usersRepository.save(newUser);
        return savedUser;

    }

    public Page<User> getUsers(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return usersRepository.findAll(pageable);
    }

    public User findById(int id)  throws NotFoundException{
        return usersRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) throws NotFoundException {
        User found = this.findById(id);
        usersRepository.delete(found);
    }

    public User findByIdAndUpdate(int id, User body) throws NotFoundException {

        User found = this.findById(id);
        found.setEmail(body.getEmail());
        found.setName(body.getName());
        found.setSurname(body.getSurname());

        found.setAvatarURL(body.getAvatarURL());
        return usersRepository.save(found);
    }

    public String uploadPicture(MultipartFile file) throws IOException {
        return (String) cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap()).get("url");
    }



}
