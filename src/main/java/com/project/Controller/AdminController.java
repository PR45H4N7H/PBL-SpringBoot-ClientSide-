package com.project.Controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.Model.PostProjectsModel;
import com.project.Model.User;
import com.project.Model.AdminCred;
import com.project.Repository.AdminCredRepo;
import com.project.Repository.PostProjectsRepository;
import com.project.Repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class AdminController {

    @Autowired
    private PostProjectsRepository projectRepo;

    @GetMapping("/Projects")
    public List<PostProjectsModel> getAllProjects() {
        return projectRepo.findAll();
    }

    @GetMapping("/PendingProjects")
    public List<PostProjectsModel> getPendingProjects() {
        return projectRepo.findByApprovedIsEmptyString();
    }

    @GetMapping("/ApprovedProjects")
    public List<PostProjectsModel> getApprovedProjects() {
        return projectRepo.findByApprovedIsM();
    }

    @GetMapping("/RejectedProjects")
    public List<PostProjectsModel> getRejectedProjects() {
        return projectRepo.findByApprovedIsR();
    }

    @GetMapping("/MostFlagged")
    public List<PostProjectsModel> getFlaggedProjects() {
        return projectRepo.findByFlagIsMore();
    }

    @Autowired
    private AdminCredRepo adminCredRepo;

    @PostMapping("/createAdmin")
    public @ResponseBody AdminCred createAdmin(@RequestBody AdminCred adminCred) {
        return adminCredRepo.save(adminCred);
    }

    @GetMapping("/showAdmins")
    public List<AdminCred> getAdmins() {
        return adminCredRepo.findAll();
    }

    @PostMapping("/approveProject/{projectId}")
    public @ResponseBody ResponseEntity<String> approveProject(@PathVariable String projectId) {
        Optional<PostProjectsModel> optionalAdmin = projectRepo.findById(projectId);
        if (optionalAdmin.isPresent()) {
            PostProjectsModel admin = optionalAdmin.get();
            admin.setApproved('a');
            projectRepo.save(admin);
            return ResponseEntity.ok("Project approved successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
    }

    @PostMapping("/rejectProject/{projectId}")
    public @ResponseBody ResponseEntity<String> rejectProject(@PathVariable String projectId) {
        Optional<PostProjectsModel> optionalProject = projectRepo.findById(projectId);
        if (optionalProject.isPresent()) {
            PostProjectsModel project = optionalProject.get();
            project.setApproved('r');
            projectRepo.save(project);
            return ResponseEntity.ok("Project rejected successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project not found");
        }
    }

    @Autowired
    private UserRepository usersRepo;

    @GetMapping("/Users")
    public List<User> getAllUsers() {
        return usersRepo.findAll();
    }

    @PutMapping("/Users/{userId}/block")
    public ResponseEntity<String> blockUser(@PathVariable String userId) {
        return updateBlockedStatus(userId, "yes");
    }

    @PutMapping("/Users/{userId}/unblock")
    public ResponseEntity<String> unblockUser(@PathVariable String userId) {
        return updateBlockedStatus(userId, "no");
    }

    private ResponseEntity<String> updateBlockedStatus(String userId, String blocked) {
        System.out.println("Received request for user ID: " + userId + ", blocked: " + blocked);
        Optional<User> optionalUser = usersRepo.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setBlocked(blocked);
            usersRepo.save(user);
            System.out.println("Blocked status updated successfully");
            return ResponseEntity.ok("Blocked status updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
