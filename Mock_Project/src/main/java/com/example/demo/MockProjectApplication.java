package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.DeliveryManager;
import com.example.demo.entity.FAManager;
import com.example.demo.entity.FARec;
import com.example.demo.entity.Role;
import com.example.demo.entity.Trainer;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.HibernateValidator;

@SpringBootApplication
@ComponentScan(value = "com.example.demo")
public class MockProjectApplication  implements CommandLineRunner{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TrainerRepository trainerRepository;
	

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		//Tao role
		Role userRole = new Role();
		userRole.setName("ROLE_USER");
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		Role trainerRole = new Role();
		trainerRole.setName("ROLE_TRAINER");
		Role faManagerRole = new Role();
		faManagerRole.setName("ROLE_FA_MANAGER");
		Role faRecRole = new Role();
		faRecRole.setName("ROLE_FA_REC");
		Role deliveryManagerRole = new Role();
		deliveryManagerRole.setName("ROLE_DELIVERY_MANAGER");
		
		//Dua role vao database
		System.out.println("Saved role: " + roleRepository.save(userRole).getName());
		System.out.println("Saved role: " + roleRepository.save(adminRole).getName());
		System.out.println("Saved role: " + roleRepository.save(trainerRole).getName());
		System.out.println("Saved role: " + roleRepository.save(faManagerRole).getName());
		System.out.println("Saved role: " + roleRepository.save(faRecRole).getName());
		System.out.println("Saved role: " + roleRepository.save(deliveryManagerRole).getName());
		
		//Tao trainer
		User trainer = new Trainer();
		trainer.setUsername("trainer");
		
		System.out.println(HibernateValidator.validateEntityAndGetMessage(trainer));

		List<Role> listOfRole2 = new ArrayList<Role>();
		listOfRole2.add(roleRepository.findByName("ROLE_USER"));
		listOfRole2.add(roleRepository.findByName("ROLE_TRAINER"));

		userService.save(trainer, "123", listOfRole2);
		System.out.println("created trainer: " + trainer.toString());
		
		//Tao admin
		User admin = new ClassAdmin();
		admin.setUsername("admin");

		List<Role> listOfRole = new ArrayList<Role>();
		listOfRole.add(roleRepository.findByName("ROLE_USER"));
		listOfRole.add(roleRepository.findByName("ROLE_ADMIN"));
		
		System.out.println(HibernateValidator.validateEntityAndGetMessage(admin));

		userService.save(admin, "123", listOfRole);
		System.out.println("created admin: " + admin.toString());
		
		//Tao FA manager
		User faManager = new FAManager();
		faManager.setUsername("famanager");
		
		List<Role> listOfRole3 = new ArrayList<Role>();
		listOfRole3.add(roleRepository.findByName("ROLE_USER"));
		listOfRole3.add(roleRepository.findByName("ROLE_FA_MANAGER"));
		
		System.out.println(HibernateValidator.validateEntityAndGetMessage(faManager));
		
		userService.save(faManager, "123", listOfRole3);
		System.out.println("created Fa Manager: " + faManager.toString());
		
		//Tao FA Rec
		User faRec = new FARec();
		faRec.setUsername("farec");
		
		List<Role> listOfRole4 = new ArrayList<Role>();
		listOfRole4.add(roleRepository.findByName("ROLE_USER"));
		listOfRole4.add(roleRepository.findByName("ROLE_FA_REC"));
		
		System.out.println(HibernateValidator.validateEntityAndGetMessage(faRec));
		
		userService.save(faRec, "123", listOfRole4);
		System.out.println("created Fa Rec: " + faRec.toString());
		
		//Tao Delivery Manager
		User deliManager = new DeliveryManager();
		deliManager.setUsername("delimanager");
		
		List<Role> listOfRole5 = new ArrayList<Role>();
		listOfRole5.add(roleRepository.findByName("ROLE_USER"));
		listOfRole5.add(roleRepository.findByName("ROLE_DELIVERY_MANAGER"));
		
		System.out.println(HibernateValidator.validateEntityAndGetMessage(deliManager));
		
		userService.save(deliManager, "123", listOfRole5);
		System.out.println("created Delivery manager: " + deliManager.toString());
	}

}
