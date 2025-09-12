package com.dap.warehouse.log.domain.api;

import com.dap.warehouse.user.domain.model.User;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class LogModel {

    private Integer idLog;

    private LocalDate date;

    private String operation;
    
    private Integer entity;
    
    private User user;
}
