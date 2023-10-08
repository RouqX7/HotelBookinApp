package com.example.hotelbookingapp.Controllers;

import static android.content.ContentValues.TAG;

import static com.example.hotelbookingapp.Controllers.ValidationController.validateEmail;
import static com.example.hotelbookingapp.Controllers.ValidationController.validatePassword;

import android.util.Log;

import com.example.hotelbookingapp.Models.Customer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class firebaseDatabase {
    FirebaseAuth fAuth;
    FirebaseFirestore db;
    private CollectionReference userRef;

    public firebaseDatabase(){
        db = FirebaseFirestore.getInstance();
        userRef = db.collection("Customers");
    }

    public void createCustomer(Customer customer){
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            String userId = firebaseUser.getUid();
            if (!userId.isEmpty()) {
                DocumentReference userDocumentRef = userRef.document(userId);
                userDocumentRef.set(customer)
                        .addOnSuccessListener(aVoid -> Log.d(TAG, "User added with ID: " + userId))
                        .addOnFailureListener(e -> Log.e(TAG, "Error adding user: " + e.getMessage()));
            } else {
                Log.e(TAG, "User ID is null or empty.");
            }
        } else {
            Log.e(TAG, "FirebaseUser is null.");
        }
    }
    public void loginWithUsernameAndPassword(String username, String password) {
        getUserIDFromUsername(username, new UserIDCallback() {
            @Override
            public void onUserIDReceived(String userID) {
                if (userID != null) {
                    String fakeEmail = userID + "@example.com"; // Fake email for authentication
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(fakeEmail, password)
                            .addOnCompleteListener(task -> {
                                if (task.isSuccessful()) {
                                    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                                    if (firebaseUser != null) {
                                        // User logged in successfully
                                        // Do something, e.g., navigate to the next activity or display a success message
                                    }
                                } else {
                                    // Handle login failure
                                    Log.e(TAG, "Login failed: " + task.getException());
                                    // Show an error message or handle the error appropriately
                                }
                            });
                } else {
                    // Handle the case where the username is not found
                    // Show an error message or handle the error appropriately
                    Log.e(TAG, "Username not found.");
                }
            }
        });
    }
    private void getUserIDFromUsername(String username, UserIDCallback callback) {
        db.collection("Usernames")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        String userID = task.getResult().getDocuments().get(0).getString("userId");
                        callback.onUserIDReceived(userID);
                    } else {
                        callback.onUserIDReceived(null);
                    }
                });
    }

    public interface UserIDCallback {
        void onUserIDReceived(String userID);
    }
    public void deleteUser() {
        DocumentReference userDocRef = userRef.document("document_id_here");
        userDocRef.delete();
    }
    public void updateUser() {
        DocumentReference userDocRef = userRef.document("document_id_here");
        userDocRef.update("age", 30);
    }
    public void createAccountWithEmailAndPassword(String email, String password, String userName) {
        String emailValidationMessage = validateEmail(email);
        String emailError = validateEmail(email);
        if (emailError != null) {
            // Handle email validation error (e.g., show an error message)
            return;
        }
        // Perform password validation
        String passwordError = validatePassword(password);
        //String confirmPasswordError = validateConfirmPassword(password, confirmPassword);
        //if (passwordError != null || confirmPasswordError != null) {
        if (passwordError != null) {

            // Handle password validation errors (e.g., show error messages)
            return;
        }
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            // Create a User object with the user ID and other information
                            Customer customer = new Customer();
                            customer.setUserId(firebaseUser.getUid());
                            customer.setEmail(email);

                            // Add the user data to Firestore
                            createCustomer(customer);
                        }
                    } else {
                        // Handle account creation failure
                        Log.e(TAG, "Account creation failed: " + task.getException());
                    }
                });
    }
    public void loginWithEmailAndPassword(String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            // User logged in successfully
                            // Do something, e.g., navigate to the next activity or display a success message
                        }
                    } else {
                        // Handle login failure
                        Log.e(TAG, "Login failed: " + task.getException());
                        // Show an error message or handle the error appropriately
                    }
                });
    }

    public void createAccountWithPhoneNumberAndPassword(String phoneNumber, String password) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(phoneNumber + "@example.com", password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            // Create a User object with the user ID and other information
                            Customer customer = new Customer();
                            customer.setUserId(firebaseUser.getUid());

                            // Add the user data to Firestore
                            createCustomer(customer);
                        }
                    } else {
                        // Handle account creation failure
                        Log.e(TAG, "Account creation failed: " + task.getException());
                    }
                });
    }
    public void loginWithPhoneNumberAndPassword(String phoneNumber, String password) {

        // Still need to implement the phone number verification logic

        // After verifying the phone number, sign in with the phone number and password
        String fakeEmail = phoneNumber + "@example.com"; // Fake email for authentication
        FirebaseAuth.getInstance().signInWithEmailAndPassword(fakeEmail, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            // User logged in successfully
                            // Do something, e.g., navigate to the next activity or display a success message
                        }
                    } else {
                        // Handle login failure
                        Log.e(TAG, "Login failed: " + task.getException());
                        // Show an error message or handle the error appropriately
                    }
                });
    }

}

