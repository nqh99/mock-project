package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.entity.Budget;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.ClassAdminProfile;
import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.ClassStatus;
import com.example.demo.entity.DeliveryManager;
import com.example.demo.entity.DeliveryType;
import com.example.demo.entity.FAManager;
import com.example.demo.entity.FARec;
import com.example.demo.entity.FormatType;
import com.example.demo.entity.Location;
import com.example.demo.entity.Role;
import com.example.demo.entity.Scope;
import com.example.demo.entity.Status;
import com.example.demo.entity.SubSubjectType;
import com.example.demo.entity.SubjectType;
import com.example.demo.entity.Trainer;
import com.example.demo.entity.TrainerProfile;
import com.example.demo.entity.User;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.repository.ClassAdminProfileRepository;
import com.example.demo.repository.ClassAdminRepository;
import com.example.demo.repository.ClassBatchRepository;
import com.example.demo.repository.ClassStatusRepository;
import com.example.demo.repository.DeliveryTypeRepository;
import com.example.demo.repository.FormatTypeRepository;
import com.example.demo.repository.LocationRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.ScopeRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.SubSubjectTypeRepository;
import com.example.demo.repository.SubjectTypeRepository;
import com.example.demo.repository.TrainerProfileRepository;
import com.example.demo.repository.TrainerRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.HibernateValidator;
//nducmy 22/3/2022
@SpringBootApplication
@ComponentScan(value = "com.example.demo")
public class MockProjectApplication  implements CommandLineRunner{
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	TrainerRepository trainerRepository;
	
	@Autowired
	TrainerProfileRepository trainerProfileRepository;
	
	@Autowired
	ClassAdminRepository classAdminRepository;
	
	@Autowired
	ClassAdminProfileRepository classAdminProfileRepository;
	
	@Autowired
	BudgetRepository budgetRepository;
	
	@Autowired
	ClassStatusRepository classStatusRepository;
	
	@Autowired
	SubjectTypeRepository subjectTypeRepository;
	
	@Autowired
	SubSubjectTypeRepository subSubjectTypeRepository;
	
	@Autowired
	DeliveryTypeRepository deliveryTypeRepository;
	
	@Autowired
	FormatTypeRepository formatTypeRepository;
	
	@Autowired
	ScopeRepository scopeRepository;
	
	@Autowired
	StatusRepository statusRepository;
	
	@Autowired
	LocationRepository locationRepository;
	
	@Autowired
	ClassBatchRepository classBatchRepository;
	
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
		Trainer trainer = new Trainer();
		trainer.setUsername("trainer");
		
		

		List<Role> listOfRole2 = new ArrayList<Role>();
		listOfRole2.add(roleRepository.findByName("ROLE_USER"));
		listOfRole2.add(roleRepository.findByName("ROLE_TRAINER"));

		TrainerProfile trainerProfile = new TrainerProfile();
		trainerProfile.setTrainer(trainer);
		trainerProfile.setFullName("Nguyen Minh Khoa");
		trainerProfile.setDateOfBirth(DateUtils.parseDateFromString("1998-08-09"));
		trainerProfile.setGender("Male");
		trainerProfile.setUnit(1);
		trainerProfile.setMajor("Mechatronic");
		trainerProfile.setPhone("0836255955");
		trainerProfile.setEmail("khoa@gmail.com");
		trainerProfile.setExperience(3);
		trainerProfile.setRemarks("demo");
		
		trainer.setTrainerProfile(trainerProfile);
		HibernateValidator.validateEntityAndGetMessage(trainer);
		HibernateValidator.validateEntityAndGetMessage(trainerProfile);
		
		userService.save(trainer, "123", listOfRole2);
		System.out.println("created trainer: " + trainer.toString());
		
		trainerProfileRepository.save(trainerProfile);

		System.out.println("TRAINER_PROFILE_TRAINER: " + trainerProfileRepository.findByTrainer_IdIs(trainerRepository.findByUsername("trainer").getId()));
		//Tao admin
		ClassAdmin admin = new ClassAdmin();
		admin.setUsername("admin");

		List<Role> listOfRole = new ArrayList<Role>();
		listOfRole.add(roleRepository.findByName("ROLE_USER"));
		listOfRole.add(roleRepository.findByName("ROLE_ADMIN"));
		
		ClassAdminProfile classAdminProfile = new ClassAdminProfile();
		classAdminProfile.setClassAdmin(admin);
		classAdminProfile.setFullName("Le Thi A");
		classAdminProfile.setDateOfBirth(DateUtils.parseDateFromString("2000-12-13"));
		classAdminProfile.setGender("Female");
		classAdminProfile.setPhone("0123423732");
		classAdminProfile.setEmail("adminA@gmail.com");
		classAdminProfile.setRemarks("demo");
		
		admin.setClassAdminProfile(classAdminProfile);
		HibernateValidator.validateEntityAndGetMessage(admin);
		HibernateValidator.validateEntityAndGetMessage(classAdminProfile);

		userService.save(admin, "123", listOfRole);
		System.out.println("created admin: " + admin.toString());
		
		classAdminProfileRepository.save(classAdminProfile);
		
		System.out.println("CLASS_ADMIN_PROFILE_ADMIN:" + classAdminProfileRepository.findByClassAdmin_IdIs(classAdminRepository.findByUsername("admin").getId()));
		
		//Tao FA manager
		User faManager = new FAManager();
		faManager.setUsername("famanager");
		
		List<Role> listOfRole3 = new ArrayList<Role>();
		listOfRole3.add(roleRepository.findByName("ROLE_USER"));
		listOfRole3.add(roleRepository.findByName("ROLE_FA_MANAGER"));
		
		HibernateValidator.validateEntityAndGetMessage(faManager);
		
		userService.save(faManager, "123", listOfRole3);
		System.out.println("created Fa Manager: " + faManager.toString());
		
		//Tao FA Rec
		FARec faRec = new FARec();
		faRec.setUsername("farec");
		
		List<Role> listOfRole4 = new ArrayList<Role>();
		listOfRole4.add(roleRepository.findByName("ROLE_USER"));
		listOfRole4.add(roleRepository.findByName("ROLE_FA_REC"));
		
		HibernateValidator.validateEntityAndGetMessage(faRec);
		
		userService.save(faRec, "123", listOfRole4);
		System.out.println("created Fa Rec: " + faRec.toString());
		
		//Tao Delivery Manager
		DeliveryManager deliManager = new DeliveryManager();
		deliManager.setUsername("delimanager");
		
		List<Role> listOfRole5 = new ArrayList<Role>();
		listOfRole5.add(roleRepository.findByName("ROLE_USER"));
		listOfRole5.add(roleRepository.findByName("ROLE_DELIVERY_MANAGER"));
		
		HibernateValidator.validateEntityAndGetMessage(deliManager);
		
		userService.save(deliManager, "123", listOfRole5);
		System.out.println("created Delivery manager: " + deliManager.toString());
		
		
		//Tao cac entity lien quan den ClassBatch
		budgetRepository.save(new Budget("CTC_Project_ADP"));
		budgetRepository.save(new Budget("CTC_Fresher_Allowance"));
		budgetRepository.save(new Budget("CTC_Fresher_Training"));
		budgetRepository.save(new Budget("CTC_Specific_Fresher_Allowance"));
		budgetRepository.save(new Budget("CTC_Specific_Fresher_Training"));
		budgetRepository.save(new Budget("CTC_Specific_Fresher_Training_Award"));
		budgetRepository.save(new Budget("CTC_FU"));
		budgetRepository.save(new Budget("CTC_Uni")); 
		
		classStatusRepository.save(new ClassStatus("Planned"));
		classStatusRepository.save(new ClassStatus("In-progress"));
		classStatusRepository.save(new ClassStatus("Pending for review"));
		classStatusRepository.save(new ClassStatus("Draft"));
		classStatusRepository.save(new ClassStatus("Closed"));
		classStatusRepository.save(new ClassStatus("Declined"));
		classStatusRepository.save(new ClassStatus("Waiting for more information"));
		classStatusRepository.save(new ClassStatus("Rejected"));
		
		subjectTypeRepository.save(new SubjectType("Organization Overview & Culture"));
		subjectTypeRepository.save(new SubjectType("Company Process"));
		subjectTypeRepository.save(new SubjectType("Standard Process"));
		subjectTypeRepository.save(new SubjectType("IT Technical"));
		subjectTypeRepository.save(new SubjectType("Non-IT Technical"));
		subjectTypeRepository.save(new SubjectType("Foreign Language"));
		subjectTypeRepository.save(new SubjectType("Soft Skill"));
		subjectTypeRepository.save(new SubjectType("Management"));
		
		subSubjectTypeRepository.save(new SubSubjectType("Cloud"));
		subSubjectTypeRepository.save(new SubSubjectType("Big Data"));
		subSubjectTypeRepository.save(new SubSubjectType("CAD"));
		subSubjectTypeRepository.save(new SubSubjectType("CAE"));
		subSubjectTypeRepository.save(new SubSubjectType("SAP"));
		subSubjectTypeRepository.save(new SubSubjectType("IT General"));
		subSubjectTypeRepository.save(new SubSubjectType("Test"));
		subSubjectTypeRepository.save(new SubSubjectType("Others"));
		
		deliveryTypeRepository.save(new DeliveryType("Class"));
		deliveryTypeRepository.save(new DeliveryType("Seminar"));
		deliveryTypeRepository.save(new DeliveryType("Exam"));
		deliveryTypeRepository.save(new DeliveryType("Contest"));
		deliveryTypeRepository.save(new DeliveryType("Certificate"));
		deliveryTypeRepository.save(new DeliveryType("Club"));
		deliveryTypeRepository.save(new DeliveryType("OJT"));
		deliveryTypeRepository.save(new DeliveryType("Others"));
		
		formatTypeRepository.save(new FormatType("Online"));
		formatTypeRepository.save(new FormatType("Offline"));
		formatTypeRepository.save(new FormatType("Blended"));
		
		scopeRepository.save(new Scope("Company"));
		scopeRepository.save(new Scope("Unit"));
		scopeRepository.save(new Scope("Outside"));
		
		statusRepository.save(new Status("Waiting for Class"));
		statusRepository.save(new Status("Waiting for Allocation"));
		statusRepository.save(new Status("In-progress"));
		statusRepository.save(new Status("Allocated"));
		statusRepository.save(new Status("Enrolled"));
		statusRepository.save(new Status("Drop-out"));
		statusRepository.save(new Status("Deferred"));
		
		locationRepository.save(new Location("District 1"));
		locationRepository.save(new Location("District 2"));
		locationRepository.save(new Location("District 3"));
		locationRepository.save(new Location("District 4"));
		locationRepository.save(new Location("District 5"));
		locationRepository.save(new Location("District 6"));
		locationRepository.save(new Location("District 7"));
		locationRepository.save(new Location("District 8"));
		locationRepository.save(new Location("District 9"));
		locationRepository.save(new Location("District 10"));
		locationRepository.save(new Location("District 11"));
		locationRepository.save(new Location("District 12"));
		locationRepository.save(new Location("Thu Duc"));
		locationRepository.save(new Location("Cu Chi"));
		locationRepository.save(new Location("Can Gio"));
		
		ClassBatch c1 = new ClassBatch();
		c1.setClassName("Fresher Developer Java");
		c1.setClassCode("HCM_FR_Java_18_01");
		c1.setActualStartDate(DateUtils.parseDateFromString("2022-03-03"));
		c1.setActualEndDate(DateUtils.parseDateFromString("2023-04-5"));
		c1.setLocation(locationRepository.findByLocationName("District 1"));
		c1.setClassStatus(classStatusRepository.findByClassStatusName("Planned"));
		
		ClassBatch c2 = new ClassBatch();
		c2.setClassName("Fresher Developer C#");
		c2.setClassCode("HCM_FR_C#_04_03");
		c2.setActualStartDate(DateUtils.parseDateFromString("2022-04-03"));
		c2.setActualEndDate(DateUtils.parseDateFromString("2023-04-01"));
		c2.setLocation(locationRepository.findByLocationName("District 5"));
		c2.setClassStatus(classStatusRepository.findByClassStatusName("In-progress"));
		
		ClassBatch c3 = new ClassBatch();
		c3.setClassName("Fresher Developer HTML");
		c3.setClassCode("HCM_FR_HTML_09_03");
		c3.setActualStartDate(DateUtils.parseDateFromString("2022-09-03"));
		c3.setActualEndDate(DateUtils.parseDateFromString("2022-09-01"));
		c3.setLocation(locationRepository.findByLocationName("Thu Duc"));
		c3.setClassStatus(classStatusRepository.findByClassStatusName("Draft"));
		
		ClassBatch c4 = new ClassBatch();
		c4.setClassName("Fresher Developer C");
		c4.setClassCode("HCM_FR_C_11_03");
		c4.setActualStartDate(DateUtils.parseDateFromString("2022-11-03"));
		c4.setActualEndDate(DateUtils.parseDateFromString("2022-08-05"));
		c4.setLocation(locationRepository.findByLocationName("Cu Chi"));
		c4.setClassStatus(classStatusRepository.findByClassStatusName("Draft"));
		
		ClassBatch c5 = new ClassBatch();
		c5.setClassName("Fresher Developer SQL");
		c5.setClassCode("HCM_FR_SQL_15_04");
		c5.setActualStartDate(DateUtils.parseDateFromString("2022-11-04"));
		c5.setActualEndDate(DateUtils.parseDateFromString("2022-08-05"));
		c5.setLocation(locationRepository.findByLocationName("District 3"));
		c5.setClassStatus(classStatusRepository.findByClassStatusName("Rejected"));
		
		classBatchRepository.save(c1);
		classBatchRepository.save(c2);
		classBatchRepository.save(c3);
		classBatchRepository.save(c4);
		classBatchRepository.save(c5);
		for (ClassBatch clazz : classBatchRepository.findAll()) {
			System.out.println(clazz.getClassCode());
		}
		
		
		
	}
	

}
