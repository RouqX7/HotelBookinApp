package com.example.hotelbookingapp.Models;

public class Customer {
    private String firstName,lastName,DOB,gender, profilePic,phoneNumber,email,securityQuestions,
            newsletterOpt,termsAndConditions,captcha,emailVerification,confirmSms,OauthToken,
            referralSource,userPref,userId,userName,password,age,phoneNo;

    public Customer(){

    }

    public Customer(String firstName, String lastName, String DOB, String gender, String profilePic,
                    String phoneNumber, String email, String securityQuestions, String newsletterOpt,
                    String termsAndConditions, String captcha, String emailVerification,
                    String confirmSms, String oauthToken, String referralSource, String userPref,
                    String userId,String userName,String password,String age,String phoneNo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.gender = gender;
        this.profilePic = profilePic;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.email = email;
        this.securityQuestions = securityQuestions;
        this.newsletterOpt = newsletterOpt;
        this.termsAndConditions = termsAndConditions;
        this.captcha = captcha;
        this.emailVerification = emailVerification;
        this.confirmSms = confirmSms;
        OauthToken = oauthToken;
        this.referralSource = referralSource;
        this.userPref = userPref;
        this.userId = userId;
        this.userName = userName;
        this.age = age;
        this.phoneNo = phoneNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecurityQuestions() {
        return securityQuestions;
    }

    public void setSecurityQuestions(String securityQuestions) {
        this.securityQuestions = securityQuestions;
    }

    public String getNewsletterOpt() {
        return newsletterOpt;
    }

    public void setNewsletterOpt(String newsletterOpt) {
        this.newsletterOpt = newsletterOpt;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getEmailVerification() {
        return emailVerification;
    }

    public void setEmailVerification(String emailVerification) {
        this.emailVerification = emailVerification;
    }

    public String getConfirmSms() {
        return confirmSms;
    }

    public void setConfirmSms(String confirmSms) {
        this.confirmSms = confirmSms;
    }

    public String getOauthToken() {
        return OauthToken;
    }

    public void setOauthToken(String oauthToken) {
        OauthToken = oauthToken;
    }

    public String getReferralSource() {
        return referralSource;
    }

    public void setReferralSource(String referralSource) {
        this.referralSource = referralSource;
    }

    public String getUserPref() {
        return userPref;
    }

    public void setUserPref(String userPref) {
        this.userPref = userPref;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DOB='" + DOB + '\'' +
                ", gender='" + gender + '\'' +
                ", profilePic='" + profilePic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", securityQuestions='" + securityQuestions + '\'' +
                ", newsletterOpt='" + newsletterOpt + '\'' +
                ", termsAndConditions='" + termsAndConditions + '\'' +
                ", captcha='" + captcha + '\'' +
                ", emailVerification='" + emailVerification + '\'' +
                ", confirmSms='" + confirmSms + '\'' +
                ", OauthToken='" + OauthToken + '\'' +
                ", referralSource='" + referralSource + '\'' +
                ", userPref='" + userPref + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
