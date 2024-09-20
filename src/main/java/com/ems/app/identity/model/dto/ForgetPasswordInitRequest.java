package com.ems.app.identity.model.dto;

import com.ems.app.identity.model.enums.NotificationMethod;
import lombok.Data;
import lombok.NonNull;

@Data
public class ForgetPasswordInitRequest {

    @NonNull
    private NotificationMethod notificationMethod;
    @NonNull
    private String username;

}
