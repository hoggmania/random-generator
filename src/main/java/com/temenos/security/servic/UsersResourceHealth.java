package com.temenos.security.servic;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.health.Readiness;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;


import java.time.Instant;
import java.util.Arrays;


@ApplicationScoped
public class UsersResourceHealth {

    static String[][] data_to_report =  {{"quarkus.application.name","application.name"}, {"quarkus.application.version", "application.version"}, {"java.vm.version"}, {"java.class.path"}};  
    static final Instant instant = Instant.ofEpochMilli(System.currentTimeMillis());

    @Produces
    @Liveness
    HealthCheck liveCheck() {
        HealthCheckResponseBuilder health = HealthCheckResponse.named("service:live");
        reportConfig(health, data_to_report);
        return () -> health.up().build();
    }

    @Produces
    @Readiness
    HealthCheck readyCheck() {
        HealthCheckResponseBuilder health = HealthCheckResponse.named("service:ready");
        boolean isReady = true;
        return () -> (isReady ? health.up() : health.down()).build();
    }


    static void reportConfig(HealthCheckResponseBuilder health, String[][] propKeys) {
        health.withData("time:up", instant.toString());

        for (String[] prop : propKeys) {
            String display = null;
            switch (prop.length) {
                case 1:
                    display = prop[0];
                    break;
                case 2:
                    display = prop[1];          
                    break;
                default:
                    //Error as we should only have array of 1/2 in length
                    System.out.println("Array wrong size "+ Arrays.toString(prop));
                    continue;
            }
            try {

                health.withData(display, org.eclipse.microprofile.config.ConfigProvider.getConfig().getValue(prop[0], String.class));                    
            } catch (Exception e) {
                /* Catches the case where the property is not set within the application.properties file*/
                health.withData(display, "<not set>");
            }
        }
    }


}