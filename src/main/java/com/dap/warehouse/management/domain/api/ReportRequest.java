package com.dap.warehouse.management.domain.api;

import com.dap.warehouse.area.domain.model.Area;
import com.dap.warehouse.depot.domain.model.Depot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReportRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private Depot depot;
    private Area area;

}
