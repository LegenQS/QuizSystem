# QuizSystem

# Table of Content
- [Description](#desc)
- [Database Design](#db)
- [JSP Pages](#jsp)
- [Functionality](#func)
    - [General](#general)
    - [User](#user)
    - [Admin](#admin)
- [Backend Service](#back)

<a name="desc"/>  

## 1. Desctiption:
This is a simple quiz system development for local host usage. System is divided into `user` and `admin`, where `user` could take quizzes, provide feedback etc. and `admin` could manage quizzes, users and display. We use **Spring Boot**, **JDBC Template**, **MySQL** to develop this project, for design pattern, we use **MVC** architecture. For frontend design, we use JSP pages to show the interaction with users and admins, and Bootstrap is used to display the global navigation bar. 

<a name="db"/>  

## 2. Database Design
For this system, we use 8 tables to demonstrate the relationships among `User`, `Quiz`, `Question` and `Option` in different entities, which provides the isolation between `Quiz` and `Question` junction by `Quiz_Question` junction table. Detailed design diagram is shown as 

![](https://github.com/LegenQS/QuizSystem/blob/main/img/db%20design.png)

<a name="jsp"/>  

## 3. JSP Pages
You can go to [pages](https://github.com/LegenQS/QuizSystem/tree/main/src/main/webapp/WEB-INF/jsp) to see all the JSP pages, where for `admin` pages filename start with admin, for `user` page filename starts with user, and for general purpose filename start with nothing specific.


<a name="func"/>  

## 4. Functionality

<a name="general"/>  

### General
- Registration  
New users can click on the register link on the navigation bar to be directed to a register page to create an account.

- Login  
Default page when users enter the application, to be more specific (any endpoints starts with http://localhost:8080/***), a filter will be applied here to realize this feature. 

- Feedback  
In this page, user can submit their feedback to this application, they can also provide star rating ranging from 1-5. There is also a text area for user inputting their feedback.

<a name="user"/>  

### User
- Home Page  
In user's home page, there is only a navigation bar holding `home`, `quiz`, `result`, `contact` and `feedback` where `quiz` will display all the quiz categories that a user can choose from. `result` displayes all the quizzes that the user has taken, for each quiz, there will be the quiz name, date taken, and link to the quiz result page. `contact` acts [here](#contact) and `feedback` acts [here](#feedback).

- Quiz  
In the quiz homepage, users can select a category to start a quiz, they can also define a quiz name for their preference, after clicking the submit button they will be redirected to the quiz page where there are 5 multiple choices questions. 

- (Quiz) Result  
After clicking submit button in the quiz page, the user will be redirected to the result page where shows quiz name, user name, start time and end time and the result of each multiple choice questions including the question content, question options, and the user selected option.  
If the user answers more than 3 questions correctly, then it will display the message showing the user passes the quiz, otherwise, the system will display the user fails this quiz and a “Take Another Quiz” button will be provided for the user to take another quiz.

<a name="feedback"/> 

- Feedback  
In this page, user can submit their feedback to this application, they can also provide star rating ranging from 1-5. There is also a text area for user inputting their feedback [Noted: this feature is anonymous].

<a name="contact"/> 

- Contact  
Here the user can create a form to send a message to the admin, different from feedback, this message is user-specific and will be displayed in admin side.

<a name="admin"/> 

### Admin
- Home Page  
The home page shows a navigation bar to redirect the admin to `user`, `result`, `quiz`, `feedback` and `contact`.

- User Profile  
In user page, it shows the user's first name, last name, primary address, primary email, phone number, status (active or suspended), and the admin can toggle the status of the user between those two status. 
 
- Quiz Result

- Quiz Edition  
Admin is able to add/update/disable quiz questions in this page, details are shown as below.

- Feedback  
Admin can view all the feedbacks including their contents and time.
 
- Contact  
Admin can also view all the contact messages from user side, including contents, rating, time and the user associated with the message.

<a name="back"/>  

## 5. Backend
