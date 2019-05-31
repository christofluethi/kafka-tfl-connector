package ch.shaped.kafka.tfl;

import ch.shaped.kafka.tfl.model.CarPark;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/occupancy")
public interface TfLOccupancy {

    @GET
    @Path("/CarPark")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    List<CarPark> carPark();

}
