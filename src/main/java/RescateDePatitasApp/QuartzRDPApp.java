package RescateDePatitasApp;

import RescateDePatitasApp.RescateDePatitasApp;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.SimpleTriggerImpl;

public class QuartzRDPApp {
/*
   public static void main(String[] args) {
    String[] argumentos;

    try {
// Creacion de una instancia de Scheduler
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      System.out.println("Iniciando Scheduler...");
      scheduler.start();
// Creacion una instancia de JobDetail
      JobDetail jobDetail = new JobDetailImpl(
          "RescateDePatitasJob",
          Scheduler.DEFAULT_GROUP,
          RescateDePatitasApp.class);

// Creacion de un Trigger donde indicamos
//que el Job se
// ejecutara de inmediato y a partir de ahi en lapsos
// de 5 segundos por 10 veces mas.
      Trigger trigger = new SimpleTriggerImpl(
          "RescateDePatitasTrigger",
          Scheduler.DEFAULT_GROUP,
          10, 5000);
      // SI FUERA CADA UNA SEMANA EN UN LAPSO DE UN AÑO, EL REPEAT COUNT DEPENDE DE LA
      // CANT DE SEMANAS QUE TENGA EL AÑO.
      // SI FUERA UNA SEMANA, EL REPEAT INTERVAL SERÍA DE 604800000 MILISEGUNDOS.

// Registro dentro del Scheduler
      scheduler.scheduleJob(jobDetail, trigger);

// Damos tiempo a que el Trigger registrado
//termine su periodo
// de vida dentro del scheduler
      Thread.sleep(60000);

// Detenemos la ejecución de la
// instancia de Scheduler
      scheduler.shutdown();

    } catch (Exception e) {
      System.out.println("Ocurrió una excepción");
    }
  }
*/
}