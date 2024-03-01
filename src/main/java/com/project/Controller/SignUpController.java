package com.project.Controller;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.EmailService;
import com.project.Model.User;
import com.project.Repository.UserRepository;

@Controller
public class SignUpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        // Add any necessary logic for displaying the signup form
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignupForm(User user) {
        // Add validation logic if necessary

        // Generate and store OTP
        String otp = generateAndStoreOTP(user);

        emailService.sendOtpEmail(user.getMail(), otp);

        // Redirect to OTP verification page
        return "redirect:/verify-otp?userId=" + user.getMail();
    }

    @GetMapping("/verify-otp")
    public String showOtpVerificationPage(@RequestParam String userId, Model model) {
        // Display the OTP verification page
        model.addAttribute("userId", userId);
        return "otpver";
    }

    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam String userId, @RequestParam String otp, Model model) {
        // Retrieve the stored OTP for the user
        String storedOtp = getStoredOtp(userId);

        // Validate the entered OTP
        if (otp.equals(storedOtp)) {
            // OTP is valid, activate the user account
            activateUser(userId);
           
            return "redirect:/signup?message=Signup%20successful";
        } else {
            // Invalid OTP, redirect back to OTP verification page with an error message
            model.addAttribute("error", "Invalid OTP. Please try again.");
            model.addAttribute("userId", userId);
            return "otpver";
        }
    }

    private void activateUser(String userId) {	
        // Find the user by ID and activate them
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            user.setActivated(true);
            user.setBlocked("no");
            userRepository.save(user);
        }
    }

    private String generateAndStoreOTP(User user) {
        // Generate a random OTP (you can use a library or implement your own logic)
        String otp = generateRandomOTP();

        // Store the OTP securely, associating it with the user's account (e.g., in the database)
        user.setOtp(otp);
        userRepository.save(user);

        return otp;
    }

    private String getStoredOtp(String userId) {
        // Retrieve the stored OTP from the repository or database
        User user = userRepository.findById(userId).orElse(null);
        return (user != null) ? user.getOtp() : null;
    }


        private static final String OTP_CHARACTERS = "0123456789";
        private static final int OTP_LENGTH = 6;

        public static String generateRandomOTP() {
            StringBuilder otp = new StringBuilder(OTP_LENGTH);
            SecureRandom random = new SecureRandom();

            for (int i = 0; i < OTP_LENGTH; i++) {
                int index = random.nextInt(OTP_CHARACTERS.length());
                otp.append(OTP_CHARACTERS.charAt(index));
            }

            return otp.toString();
        }
    }
    // Other methods...

    

