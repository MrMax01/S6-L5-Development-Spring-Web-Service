package massimomauro.S6L5DevelopmentSpringWebService.services;

import massimomauro.S6L5DevelopmentSpringWebService.entities.Device;
import massimomauro.S6L5DevelopmentSpringWebService.entities.User;
import massimomauro.S6L5DevelopmentSpringWebService.enums.DeviceStatus;
import massimomauro.S6L5DevelopmentSpringWebService.exceptions.BadRequestException;
import massimomauro.S6L5DevelopmentSpringWebService.exceptions.NotFoundException;
import massimomauro.S6L5DevelopmentSpringWebService.payloads.NewDevicePayload;
import massimomauro.S6L5DevelopmentSpringWebService.repositories.DevicesRepository;
import massimomauro.S6L5DevelopmentSpringWebService.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DevicesService {
    @Autowired
    private DevicesRepository devicesRepository;
    @Autowired
    private UsersService usersService;

    public Device save() {
        Device newDevice = new Device();
        newDevice.setDeviceStatus(DeviceStatus.DISPONIBILE);
        return devicesRepository.save(newDevice);
    }

    public Page<Device> getDevices(int page, int size, String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return devicesRepository.findAll(pageable);
    }

    public Device findById(int id) {
        return devicesRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Device found = this.findById(id);
        devicesRepository.delete(found);
    }

    public Device findByIdAndSetStatus(int id, NewDevicePayload body) {

        Device found = this.findById(id);
        User userFound = usersService.findById(body.getUserId());
        found.setDeviceStatus(body.getDeviceStatus());
        found.setUser(userFound);
        return devicesRepository.save(found);
    }
    /*
    public Device giveDeviceToUser(Device body) {
        User user = usersService.findById(body.getUser());

    }
    */

}
