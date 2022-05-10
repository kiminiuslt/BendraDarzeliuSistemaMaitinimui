package eu.kiminiuslt.bdsm.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    @GetMapping
    public String warehouseStart(){
        return "/warehouse/warehouse-all";
    }
}
