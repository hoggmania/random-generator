package com.temenos.security.servic;

import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.jboss.resteasy.annotations.cache.NoCache;

import io.agroal.api.AgroalDataSource;
import io.quarkus.agroal.DataSource;

@Path("/users")
public class UsersResource {



    @Inject
    @DataSource("user")
    AgroalDataSource usersDataSource;

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    @Counted(name = "performedChecks", description = "How many user have been requested")
    @Timed(name = "checksTimer", description = "A measure of how long it takes to perform request", unit = MetricUnits.MILLISECONDS)
    public User getAll() {
        //ResultSet rs = usersDataSource.createConnectionBuilder().build().prepareStatement("select * from public.persons").executeQuery();
        return new User();
    }


    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON)
    @NoCache
    @Counted(name = "performedChecks", description = "How many user have been requested")
    @Timed(name = "checksTimer", description = "A measure of how long it takes to perform request", unit = MetricUnits.MILLISECONDS)
    public User me() {
        return new User();
    }


    @Entity
    public class User {

        private final String userName;

        User() {
            this.userName = "hhhhh"+System.currentTimeMillis();
            try {
                String[] args = {"1", "2"};
                Class<?> clazz = Class.forName("com.temenos.security.servic.Test");
                //System.out.println("Class = " + clazz.getName());

                Method method = clazz.getMethod("main", String[].class);
                //System.out.println("method = " + method.getParameterTypes());
                
                method.invoke(null, new Object[] {args});
                //System.out.println("invoked != ");

             } catch(Exception ex) {
                 ex.printStackTrace();
             }
        }

        public String getUserName() {
            return userName;
        }
    }
}