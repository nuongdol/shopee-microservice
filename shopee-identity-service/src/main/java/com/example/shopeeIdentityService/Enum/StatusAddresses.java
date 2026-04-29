package com.example.shopeeIdentityService.Enum;


import lombok.Getter;

@Getter
public enum StatusAddresses {
    // -- 0=home, 1=office, 2=other
    HOME(0),
    OFFICE(1),
    OTHER(2);
    private final Integer statusAddresses;

    StatusAddresses(Integer statusAddresses) {
        this.statusAddresses = statusAddresses;
    }

    public static StatusAddresses fromValue(Integer value) {
        for (StatusAddresses status : StatusAddresses.values()) {
            if (status.getStatusAddresses().equals(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status: " + value);
    }

    public Integer getStatusAddresses() {
        return statusAddresses;
    }
}
