package com.ttknpdev.springbootcrudmanytoonefourthtable.controller.s_control;

import com.ttknpdev.mycommonsresponse.common.ConstantsResponse;
import com.ttknpdev.mycommonsresponse.response.ResponseObject;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.SaleService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/sale")
public class SaleRestapi {
    private final SaleService<Sale> service;
    private final ServiceCommon<Sale> serviceCommon;

    @Autowired
    public SaleRestapi(SaleService service, ServiceCommon<Sale> serviceCommon) {
        this.service = service;
        this.serviceCommon = serviceCommon;
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody Sale sale){
        return ResponseEntity.status(ConstantsResponse.STATUS_CREATED).body(ResponseObject.<Sale>builder()
                .status(ConstantsResponse.STATUS_CREATED)
                .code(ConstantsResponse.STRING_CREATED)
                .object(service.create(sale))
                .build());
    }
    @GetMapping(value = "/reads")
    private ResponseEntity reads(){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<List<Sale>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.reads())
                .build());
    }
    @GetMapping(value = "/read/{id}")
    private ResponseEntity read(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Sale>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.read(id))
                .build());
    }
    @PutMapping(value = "/update/{id}")
    private ResponseEntity read(@PathVariable Long id,@RequestBody Sale sale){
        return ResponseEntity.status(ConstantsResponse.STATUS_OK).body(ResponseObject.<Sale>builder()
                .status(ConstantsResponse.STATUS_OK)
                .code(ConstantsResponse.STRING_OK)
                .object(service.update(sale,id))
                .build());
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Map<String, Sale>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.delete(id))
                .build());
    }

    //
    @PostMapping(value = "/common/create")
    private ResponseEntity commonCreate(@RequestBody Sale sale){
        return ResponseEntity.status(ConstantsResponse.STATUS_CREATED).body(ResponseObject.<Sale>builder()
                .status(ConstantsResponse.STATUS_CREATED)
                .code(ConstantsResponse.STRING_CREATED)
                .object(serviceCommon.create(sale))
                .build());
    }
    @GetMapping(value = "/common/reads")
    private ResponseEntity commonReads(){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<List<Sale>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(serviceCommon.reads())
                .build());
    }
    @GetMapping(value = "/common/read/{id}")
    private ResponseEntity commonRead(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Sale>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(serviceCommon.read(id))
                .build());
    }
    @PutMapping(value = "/common/update/{id}")
    private ResponseEntity commonUpdate(@PathVariable Long id,@RequestBody Sale sale){
        return ResponseEntity.status(ConstantsResponse.STATUS_OK).body(ResponseObject.<Sale>builder()
                .status(ConstantsResponse.STATUS_OK)
                .code(ConstantsResponse.STRING_OK)
                .object(serviceCommon.update(sale,id))
                .build());
    }

    @DeleteMapping(value = "/common/delete/{id}")
    private ResponseEntity commonDelete(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Map<String, ?>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(serviceCommon.delete(id))
                .build());
    }
}
