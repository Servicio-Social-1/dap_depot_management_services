package com.dap.warehouse.materialdepot.domain.api;

import com.dap.warehouse.depot.domain.model.Depot;
import com.dap.warehouse.material.domain.model.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DuplicatedRequest {

    Material material;

    List<Depot> depotList;

}
