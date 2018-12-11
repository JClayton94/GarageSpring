package Garage.Example.Garage.appController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Garage.Example.Garage.SpringBootRepository;
import Garage.Example.Garage.springBootException.ResourceNotFoundException;
import Garage.Example.Garage.springBootModel.SpringBootDataModel;

@RestController
@RequestMapping("/api")
public class AppController {
	
	@Autowired 
	SpringBootRepository myRepository;
	
	@PostMapping("/SpringBootDataModel")
	public SpringBootDataModel createVehicle(@Valid @RequestBody SpringBootDataModel sdm) {
		
		return myRepository.save(sdm);
		
	}
	
	@GetMapping("/vehicle/{id}")
	public SpringBootDataModel getVehiclebyID(@PathVariable(value = "id")Long vehicleID){
		return myRepository.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("SpringBootDataModel", "id", vehicleID));
	}

	@GetMapping("/vehicle_type/{vehicle_type}")
	public List<SpringBootDataModel> getVehiclebyType(@PathVariable(value = "vehicle_type")String vehicleType){
		
		return myRepository.findByVehicleType(vehicleType);
	}
	
	
	@GetMapping("/vehicle")
	public List<SpringBootDataModel> getAllVehicles(){
		return myRepository.findAll();
	}
	
	@PutMapping("/vehicle/{id}")
	public SpringBootDataModel updateVehicle(@PathVariable(value = "id")Long vehicleID, @Valid @RequestBody SpringBootDataModel vehicleDetails) {
		
		SpringBootDataModel sBDM  = myRepository.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", vehicleID));
		
		sBDM.setVehicleType(vehicleDetails.getVehicleType());
		sBDM.setNumberOfWheels(vehicleDetails.getNumberOfWheels());
		sBDM.setNumberOfDoors(vehicleDetails.getNumberOfDoors());
		sBDM.setNumberOfSeats(vehicleDetails.getNumberOfSeats());
		sBDM.setEngine(vehicleDetails.getEngine());
		sBDM.setTopSpeed(vehicleDetails.getTopSpeed());
		
		return myRepository.save(sBDM);
	}
	
	@DeleteMapping("/vehicle/{id}")
	public ResponseEntity<?> deleteVehicle(@PathVariable(value = "id")Long vehicleID){
		
		SpringBootDataModel sBDM  = myRepository.findById(vehicleID).orElseThrow(() -> new ResourceNotFoundException("Person", "id", vehicleID));
		
		myRepository.delete(sBDM);
		
		return ResponseEntity.ok().build();
		
	}
	
	

}
