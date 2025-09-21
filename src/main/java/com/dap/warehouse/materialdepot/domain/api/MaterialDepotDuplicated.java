package com.dap.warehouse.materialdepot.domain.api;

import com.dap.warehouse.materialdepot.domain.model.MaterialDepot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialDepotDuplicated {

    private MaterialDepot materialDepot;

    private Boolean isDuplicated;

}
