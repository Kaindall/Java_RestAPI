package com.judapp.users.model.responses;

import lombok.*;

import java.util.List;
import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ErrorModel {
    @NonNull private Date timestamp;
    private List<String> errorDetails;
    private String developerMessage;
}
