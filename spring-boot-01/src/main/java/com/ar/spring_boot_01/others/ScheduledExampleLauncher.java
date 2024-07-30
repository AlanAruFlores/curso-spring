package com.ar.spring_boot_01.others;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/*
    @SCHEDULED : Nos sirve para ejecutar un determinado proceso en un tiempo especifico.
   A este mismo se le puede agregar la annotation @Async para hacerla como una tarea asincrona en spring

    Tener en cuenta que si queremos que el mismo funcione, debemos anotar como componente la clase "ScheduledExampleLauncher" para que el contexto de spring
    la pueda gestionar. A su vez, usar @ComponentScan en el archivo inicial de spring boot "SpringBootApplication" con el fin de mapearla en el contexto.


    #FIXED RATE VS FIXED DELAY

    FIXED DELAY es una propiedad que permite que una tarea se ejecute en un lapso de tiempo, y que la siguiente tarea espere hasta que la "tarea anterior" termine.
    FIXED RATE es una propiedad que permite que la siguiente tarea se ejecute, incluso si la anterior no termina

    Si queremos soportar la tareas en paralelo usamos @Async

    #InitialDelay
    Esta propiedad ofrece que pase un lapso de tiempo determinado, y luego se empieze a ejecutarse la tarea en el tiempo definido
    de "fixedDelay"


    #Cron Expression
    Son procesos Daemon en java que se ejecutan en segundo plano , los cuales una vez ejecutados van a seguir persistiendo.


 */

@Component
public class ScheduledExampleLauncher {
    /*
    @Scheduled(fixedDelay = 1000)
    public void scheduledExample() {
        System.out.println("Hello World using ScheduledExample , se ejecuto en "+System.currentTimeMillis()/1000);
    }*/

    /*Delay vs Rate*/
    /*
    @Scheduled(fixedDelay = 1000)
    public void scheduledExample1() {
        for(int i = 0 ; i< Integer.MAX_VALUE ; i++) {}
        System.out.println("Tarea con delay 1");
    }
    */

    /*
    @Scheduled(fixedRate = 1000)
    public void scheduledExample1() {
        for(int i = 0 ; i< Integer.MAX_VALUE ; i++) {}
        System.out.println("Tarea con rate 1");
    }*/

    /*
    @Scheduled(fixedRate = 1000)
    @Async
    public void scheduledExample1() {
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
        }
        System.out.println("Tarea con rate y asincrono 1");
    }
    */


    @Scheduled(fixedDelay = 1000, initialDelay = 4000)
    public void scheduleFixedRateWithInitialDelayTask() {

        long now = System.currentTimeMillis() / 1000;
        System.out.println(
                "Fixed rate task with one second initial delay - " + now);
    }
   }
