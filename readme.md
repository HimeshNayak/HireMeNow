# Hire Me Now
### Java 11 Spring Boot Application using Microsoft Azure Services to help students learn and apply for internships

#### Application hosted at https://hire-me-now.azurewebsites.net/
#### Working Video Demo is available [here](https://youtu.be/x6Xu4G6gATE)

## Problem Statement 
We live in a country where the major population is young and want to learn skills and work in different companies to gain experience. But some people do not get the correct resources and the steps to learn the cutting-edge technologies that are being used in the industry. Also, they do not find the right platform to find internships where they can apply easily and gain experience which would help them when they get hired for a job. 
There are many students and mentors who really want to help and guide the students in need but don't get the right platform to share their knowledge. There is always one to one interaction with a junior which doesn't benefit everyone. Also, there might be some language barriers due to which they are not able to communicate well. There is a need of such a platform where everything is concised at one place, where you can find the right resources and get hired at your dream company.

![Screenshot (205)](https://user-images.githubusercontent.com/30944790/150837314-8796e57f-8e49-49f0-a986-17e6f163d51a.png)

## Solution
Hire Me Now is a portal for the students who are looking for guidance and resources so that they can apply to internships and get hired. A portal where the seniors who are really willing to guide the juniors can come forward and tell their experiences of what exactly is to be done and what the students should refrain from. Its a platform to build a community which helps out each other and learn from each other.
- The application uses **Cosmos DB API for Mongo DB** to store all the information of the students and mentors, and the posts done by mentors or queries by students as documents in their respective collections. 
- It uses **App Service** to host the application on azurewebsites.com domain so that it is available to everyone. 
- We live in a country where there are many languages and thus we need to have a system to translate the posts / queries so that there is a better understanding among people. That is where **Microsoft-Translator** comes into action.

These three main features of Microsoft Azure build the backend of the application. 
The internships and courses are fetched using apis available which can be called from spring-boot backend and are shown on the templates using the thymeleaf dependency.
