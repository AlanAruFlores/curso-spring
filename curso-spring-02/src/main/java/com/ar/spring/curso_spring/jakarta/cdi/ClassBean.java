package com.ar.spring.curso_spring.jakarta.cdi;

import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
public class ClassBean {
    private ClassSubBean classSubBean;

    @Inject
    public void setClassSubBean(ClassSubBean classSubBean) {
        this.classSubBean = classSubBean;
        System.out.println("Inyectando objeto subbean: " + this.classSubBean);
    }
}
