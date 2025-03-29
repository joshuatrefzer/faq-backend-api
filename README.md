# FAQ Backend API  

This application is a FAQ management system designed for companies. Administrators can create solutions for frequently asked questions and problem-solving scenarios. They have the ability to add and select tags to optimize the searchability of FAQs. Only authenticated users can add tags and create FAQs. Additionally, only usernames listed in the `allowed_users.txt` file are permitted to sign up as users.  

For users seeking solutions to their problems, there is a public endpoint where they can search for existing FAQs.  
If no suitable FAQ is found, users have the option to submit a question via another public endpoint. Administrators can then review these questions, provide solutions, and add new FAQs accordingly.  

## Table of Contents  
- [Used Technologies](#used-technologies)  

## Used Technologies  
This repository contains a backend API built with **Spring Boot**.  
**Docker** is used for containerization, running both the Spring Boot application and a **PostgreSQL** database in separate containers.  
For data persistence, a **Docker volume** is utilized.  

To facilitate development and streamline common tasks, a **Makefile** is included for managing commands efficiently.  

