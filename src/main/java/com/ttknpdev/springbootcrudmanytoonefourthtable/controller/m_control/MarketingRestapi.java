package com.ttknpdev.springbootcrudmanytoonefourthtable.controller.m_control;

import com.ttknpdev.mycommonsresponse.common.ConstantsResponse;
import com.ttknpdev.mycommonsresponse.response.ResponseObject;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.Project;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.s_entity.Sale;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.MarketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/marketing")
public class MarketingRestapi {
    private MarketingService<Marketing> service;
    @Autowired
    public MarketingRestapi(MarketingService service) {
        this.service = service;
    }
    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody Marketing marketing){
        return ResponseEntity.status(ConstantsResponse.STATUS_CREATED).body(ResponseObject.<Marketing>builder()
                .status(ConstantsResponse.STATUS_CREATED)
                .code(ConstantsResponse.STRING_CREATED)
                .object(service.create(marketing))
                .build());
    }
    @GetMapping(value = "/reads")
    private ResponseEntity reads(){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<List<Marketing>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.reads())
                .build());
    }
    @GetMapping(value = "/read/{id}")
    private ResponseEntity read(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Marketing>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.read(id))
                .build());
    }

    @PutMapping(value = "/update/{id}")
    private ResponseEntity read(@PathVariable Long id,@RequestBody Marketing marketing){
        return ResponseEntity.status(ConstantsResponse.STATUS_OK).body(ResponseObject.<Marketing>builder()
                .status(ConstantsResponse.STATUS_OK)
                .code(ConstantsResponse.STRING_OK)
                .object(service.update(marketing,id))
                .build());
    }


    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Map<String, Marketing>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.delete(id))
                .build());
    }
}
