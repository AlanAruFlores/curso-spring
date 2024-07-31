package com.ar.spring_boot_01.presentacion;

import com.ar.spring_boot_01.ServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceConfigurationController {
    @Autowired
    ServiceConfiguration serviceConfiguration;

    @RequestMapping("/service-config")
    public ServiceConfiguration getServiceConfiguration(){
        return this.serviceConfiguration;
    }
}
