package com.ttknpdev.springbootcrudmanytoonefourthtable.controller.p_control;

import com.ttknpdev.mycommonsresponse.common.ConstantsResponse;
import com.ttknpdev.mycommonsresponse.response.ResponseObject;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.m_entity.Marketing;
import com.ttknpdev.springbootcrudmanytoonefourthtable.entities.p_entity.Programmer;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProgrammerService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.ProjectService;
import com.ttknpdev.springbootcrudmanytoonefourthtable.services.common.ServiceCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/programmer")
public class ProgrammerRestapi {

    private final ProgrammerService<Programmer> service;
    private final ServiceCommon<Programmer> serviceCommon;
    @Autowired
    public ProgrammerRestapi(ProgrammerService service, ServiceCommon<Programmer> serviceCommon) {
        this.service = service;
        this.serviceCommon = serviceCommon;
    }

    @PostMapping(value = "/create")
    private ResponseEntity create(@RequestBody Programmer programmer){
        return ResponseEntity.status(ConstantsResponse.STATUS_CREATED).body(ResponseObject.<Programmer>builder()
                .status(ConstantsResponse.STATUS_CREATED)
                .code(ConstantsResponse.STRING_CREATED)
                .object(service.create(programmer))
                .build());
    }
    @GetMapping(value = "/reads")
    private ResponseEntity reads(){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<List<Programmer>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.reads())
                .build());
    }
    @GetMapping(value = "/read/{id}")
    private ResponseEntity read(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Programmer>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.read(id))
                .build());
    }
    @PutMapping(value = "/update/{id}")
    private ResponseEntity read(@PathVariable Long id,@RequestBody Programmer programmer){
        return ResponseEntity.status(ConstantsResponse.STATUS_OK).body(ResponseObject.<Programmer>builder()
                .status(ConstantsResponse.STATUS_OK)
                .code(ConstantsResponse.STRING_OK)
                .object(service.update(programmer,id))
                .build());
    }

    @DeleteMapping(value = "/delete/{id}")
    private ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Map<String, Programmer>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(service.delete(id))
                .build());
    }

    //
    @PostMapping(value = "/common/create")
    private ResponseEntity commonCreate(@RequestBody Programmer programmer){
        return ResponseEntity.status(ConstantsResponse.STATUS_CREATED).body(ResponseObject.<Programmer>builder()
                .status(ConstantsResponse.STATUS_CREATED)
                .code(ConstantsResponse.STRING_CREATED)
                .object(serviceCommon.create(programmer))
                .build());
    }
    @GetMapping(value = "/common/reads")
    private ResponseEntity commonReads(){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<List<Programmer>>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(serviceCommon.reads())
                .build());
    }
    @GetMapping(value = "/common/read/{id}")
    private ResponseEntity commonRead(@PathVariable Long id){
        return ResponseEntity.status(ConstantsResponse.STATUS_ACCEPTED).body(ResponseObject.<Programmer>builder()
                .status(ConstantsResponse.STATUS_ACCEPTED)
                .code(ConstantsResponse.STRING_ACCEPTED)
                .object(serviceCommon.read(id))
                .build());
    }
    @PutMapping(value = "/common/update/{id}")
    private ResponseEntity commonUpdate(@PathVariable Long id,@RequestBody Programmer programmer){
        return ResponseEntity.status(ConstantsResponse.STATUS_OK).body(ResponseObject.<Programmer>builder()
                .status(ConstantsResponse.STATUS_OK)
                .code(ConstantsResponse.STRING_OK)
                .object(serviceCommon.update(programmer,id))
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
