package com.ar.spring.curso_spring.bean.alcances;

/*Los beans en spring tienen 2 tipos de alcances o scopes
*
* SINGLETON: Patron de diseño hace referencia a la idea de la creacion de un unica instancia y reutilizar la misma (Por defecto en todos los beans)
* PROTOTYPE: Patron de diseño que tiene como fin la clonacion de un objeto
*
* Por default, los beans y components de spring esta regidos por el patron de diseño SINGLETON, pero si queremos hacerlo PROTOTYPE
* se hace usando la annotation @Scope()
* */

import org.springframework.aop.scope.ScopedProxyFactoryBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan
public class BeanScopeLauncherApplication {

    //SINGLETON SCOPE POR DEFECTO
    @Component
    public class ClassASingleton{
    }

    @Component
    @Scope(value= ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public class ClassBPrototype{

    }

    public static void main(String[] args) {
        try(var context= new AnnotationConfigApplicationContext(BeanScopeLauncherApplication.class)){
            System.out.println("\nPATRON SINGLETON:\n");

            System.out.println(context.getBean(ClassASingleton.class));
            System.out.println(context.getBean(ClassASingleton.class));
            System.out.println(context.getBean(ClassASingleton.class));
            System.out.println(context.getBean(ClassASingleton.class));

            System.out.println("\nPATRON PROTOTYPE: \n");

            System.out.println(context.getBean(ClassBPrototype.class));
            System.out.println(context.getBean(ClassBPrototype.class));
            System.out.println(context.getBean(ClassBPrototype.class));
            System.out.println(context.getBean(ClassBPrototype.class));


        }
    }
}
