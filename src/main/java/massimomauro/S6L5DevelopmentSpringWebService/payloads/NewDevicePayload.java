package massimomauro.S6L5DevelopmentSpringWebService.payloads;

import lombok.Getter;
import massimomauro.S6L5DevelopmentSpringWebService.enums.DeviceStatus;

@Getter
public class NewDevicePayload {
    private int authorId;
    private DeviceStatus deviceStatus;
}
