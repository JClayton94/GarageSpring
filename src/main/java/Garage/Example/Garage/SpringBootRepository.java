package Garage.Example.Garage;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import Garage.Example.Garage.springBootModel.*;

public interface SpringBootRepository extends JpaRepository<SpringBootDataModel, Long>{

	 List<SpringBootDataModel> findByVehicleType(String vehicleType);
		
}
