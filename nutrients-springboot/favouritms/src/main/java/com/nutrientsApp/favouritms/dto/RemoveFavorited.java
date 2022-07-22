package com.nutrientsApp.favouritms.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class RemoveFavorited {
	@NotBlank
	@Length(min=2,max=15)
    private String username;
	@NotNull
    private long fdcId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getFdcId() {
        return fdcId;
    }

    public void setFdcId(long fdcId) {
        this.fdcId = fdcId;
    }
}
