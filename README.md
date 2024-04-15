# Spring Security OAuth 2.0 using Google

#### Required Set up
- Spring Boot 3.2.4+
- Spring Security 6+
- Java 8, 11, 17+ (⭐better to use 17)
- MySQL 5.7+

### Steps to Completed 
1. Developing Single Sign in application using Google Cloud Platform 
2. Use the code

## 1. Developing Single Sign in application using Google Cloud Platform 

**Step 1:** To register client app with Google Developer, go to the  [Google Cloud Platform](https://console.cloud.google.com/)

**Step 2:** If you go for the first time then choose your **Country** and check **Terms of Service** then click on **AGREE AND CONTINUE**.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/b8fec38a-84ef-4ee1-98f3-e5d661876ad8)

**Step 3:** To create a new project, you can get to **Select a project** option 

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/54e70f35-373c-4037-b226-a4fc904e62ca)

Then click on **NEW PROJECT**

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/09fc7736-75a8-4f63-96e7-018f0ad77e4c)

**Step 4:** Give the **Project name** the click on **CREATE** button.
You can see a warning that we can create 12 projects in your quota.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/71995357-6c44-4745-bf97-55ba511ffc87)

**Step 5:** After the project created, this will select as recent project then click on the **Credentials** tab.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/6ebc5b9e-fc56-46e1-8ec5-36a32ebb561f)

**Step 6:** Click on **CREATE CREDENTIALS** and then **OAuth client ID**.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/fe0fb77d-21ae-411a-8423-3d5267b92d2e)

**Step 7:** Then click on **CONFIGURE CONSENT SCREEN** button.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/f2d27d81-2351-4aa8-96b7-5ddec1a822af)

**Step 8:** Choose **User Type** as **External** then click on **CREATE** button.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/466b3d4e-99c6-4490-820b-2bc903f3af29)

**Step 9:** Give **App information** like **App name** and **User support email**,

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/859fea52-870e-4998-a1af-4fdcf3a074d9)
 
Then scroll down and give **Developer contact information** like **Email address** after that click on **SAVE AND CONTINUE** button.

**Step 10:** After that click **SAVE AND CONTINUE** button **two times** then click on the **BACK TO DASHBOARD** button.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/28c739e3-1024-4bb0-9a14-011dec68d378)

**Step 11:** Follow **Step 5 & 6** and now choose the **Application type** as **Web application**.

**NOTE:** Then a lot of options will enable, according your need you can choose and bydefault Email and Profile is enables.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/fb032838-6e7b-40ee-b11f-969bd66c8cd3)

**Step 12:** Go to **Authorized JavaScript origins** section and click on **ADD URL** button and give URL like **http://localhost:<port_number> or https://localhost:<port_number>** and after that go to **Authorized redirect URLs** and click on **ADD URL** button then give the URL like **http://localhost:<port_number>/login/oauth2/code/google or https://localhost:<port_number>/login/oauth2/code/google** then click on **CREATE** button

**Note:** After port number **“login/oauth2/code/google”** is fixed for redirect URL.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/c053618b-d1b8-4f1a-88d4-613da637493e)
![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/4e08443b-3960-41fe-8097-36a6bde78e2e)

**Step 13:** Now you will get a popup like below from there you can copy **Your Client ID** and **Your Client Secret** and as well as you can download as JSON file by click on **DOWNLOAD JSON** button.

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/5c513380-2e4a-4e90-b463-4bdec3c2a141)

**Step 14:** Otherwise, you can collect later by going inside of your **OAuth 2.0 Client IDs (Web client 1)**

![image](https://github.com/nirmalakumarsahu/spring-boot-oauth2.0-google/assets/62144558/5d1269c4-e7ba-470a-9bee-de86761ba91f)

## 2. Use the code

- Now use the above code and change the **Client ID** and **Client Secret** and if you want to add **SSL** add and run the application.
