package massimomauro.S6L5DevelopmentSpringWebService.controllers;

import massimomauro.S6L5DevelopmentSpringWebService.entities.Device;
import massimomauro.S6L5DevelopmentSpringWebService.entities.User;
import massimomauro.S6L5DevelopmentSpringWebService.payloads.NewDevicePayload;
import massimomauro.S6L5DevelopmentSpringWebService.services.DevicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DevicesController {
    @Autowired
    DevicesService devicesService;

    // 1. - POST http://localhost:3001/blogs (+ req.body)
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED) // <-- 201
    public Device saveDevice(@RequestBody NewDevicePayload body) {
        return devicesService.save(body);
    }

    // 2. - GET http://localhost:3001/devices
    @GetMapping("")
    public Page<Device> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return devicesService.getDevices(page, size, sortBy);
    }

    // 3. - GET http://localhost:3001/blogs/{id}
    @GetMapping("/{deviceId}")
    public Device findById(@PathVariable int deviceId) {
        return devicesService.findById(deviceId);
    }

    // 4. - PUT http://localhost:3001/blogs/{id} (+ req.body)
    @PutMapping("/{deviceId}")
    public Device findAndUpdate(@PathVariable int deviceId, @RequestBody NewDevicePayload body) {
        return devicesService.findByIdAndSetStatus(deviceId, body);
    }

    // 5. - DELETE http://localhost:3001/blogs/{id
    @DeleteMapping("/{deviceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void findAndDelete(@PathVariable int deviceId) {
        devicesService.findByIdAndDelete(deviceId);
    }
}
