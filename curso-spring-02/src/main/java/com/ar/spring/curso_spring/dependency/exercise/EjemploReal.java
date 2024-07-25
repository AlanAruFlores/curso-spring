package com.ar.spring.curso_spring.dependency.exercise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/*Ejercicio
* Inyectar cualquier servicio de dato (mysql o mongo) en un BussinessService para luego su posterior uso.
* 1.Usar El contexto de spring
* 2.Hacer que Mongo sea la preferida @Primary
* */

@Configuration
@ComponentScan
public class EjemploReal {

    public interface DBService{
        int[] devolverDatos();
    }

    //@Component
    @Repository
    @Qualifier("mysql")
    public class MysqlServiceData implements DBService{
        @Override
        public int[] devolverDatos() {
            return new int[] {44,2,1,10};
        }
    }

    //@Component
    @Repository
    @Primary
    public class MongoDBServiceData implements DBService{
        @Override
        public int[] devolverDatos() {
            return new int[] {100,400,2,10};
        }
    }


    //@Component
    @Service
    public class BussinessService{

        private DBService dataService;

        @Autowired
        public BussinessService(@Qualifier("mysql") DBService dataService){
            this.dataService = dataService;
        }

        public int findMaxData(){
            return Arrays.stream(dataService.devolverDatos()).max().orElse(0);
        }
    }


    //Metodo de ejecucion
    public static void main(String[] args) {
        try(var context =  new AnnotationConfigApplicationContext(EjemploReal.class)){
            BussinessService service = context.getBean(BussinessService.class);
            int dataRecibirMaximo = service.findMaxData();
            System.out.println(dataRecibirMaximo);
        }
    }
}
