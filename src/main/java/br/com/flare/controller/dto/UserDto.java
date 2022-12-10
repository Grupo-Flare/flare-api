package br.com.flare.controller.dto;

import br.com.flare.model.Device;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the {@link br.com.flare.model.User} entity
 */
public class UserDto implements Serializable {
    private final String name;
    private final String email;

    private final List<Device> devices;

    public UserDto(String name, String email, List<Device> devices) {
        this.name = name;
        this.email = email;
        this.devices = devices;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public List<Device> getDevices() {
        return devices;
    }
}