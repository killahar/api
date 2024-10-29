package com.killahar.api;

import com.killahar.api.service.FreelancerService;
import com.killahar.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

@Component
public class ConsoleApp implements CommandLineRunner {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private FreelancerService freelancerService;

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Welcome to the Project Management Console!");

        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Create Project");
            System.out.println("2. Add Freelancer to Project");
            System.out.println("3. Check Project Coverage");
            System.out.println("4. Exit");

            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1 -> createProject();
                case 2 -> addFreelancerToProject();
                case 3 -> checkProjectCoverage();
                case 4 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void createProject() {
        System.out.println("Enter Project Name: ");
        String name = scanner.nextLine();
        System.out.println("Enter Project Description: ");
        String description = scanner.nextLine();

        System.out.println("Project created!");
    }

    private void addFreelancerToProject() {
        System.out.println("Enter Project ID: ");
        UUID projectId = UUID.fromString(scanner.nextLine());

        System.out.println("Enter Freelancer ID: ");
        UUID freelancerId = UUID.fromString(scanner.nextLine());

        System.out.println("Freelancer added to project.");
    }

    private void checkProjectCoverage() {
        System.out.println("Enter Project ID: ");
        UUID projectId = UUID.fromString(scanner.nextLine());

        System.out.println("Coverage check complete.");
    }
}
