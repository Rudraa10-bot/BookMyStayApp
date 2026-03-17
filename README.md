# BookMyStayApp

## Welcome

Welcome to **BookMyStay App**, a simple Java console application that demonstrates basic hotel room management and centralized room inventory handling.

This project simulates a minimal hotel booking environment where different room types and their availability are managed and displayed. The purpose of the application is to illustrate how room data can be organized and managed using simple object-oriented concepts and Java collections.

The application includes two main components:

* Basic Room Types with Static Availability
* Centralized Room Inventory Management

---

# 📌 Basic Room Types with Static Availability

This feature demonstrates a simple way to represent hotel room types and their availability using objects and basic variables.

## Flow

1. The user runs the application.
2. Room objects representing different room types are created.
3. Availability for each room type is stored using simple variables.
4. Room details and availability information are printed to the console.
5. The application terminates.

## Example Room Types

The application may include the following room types:

* Standard Room
* Deluxe Room
* Suite Room

Each room type contains a predefined availability value that represents how many rooms are currently available.

---

# 📌 Centralized Room Inventory Management

This feature demonstrates a structured way of managing room availability using a centralized inventory system.

The system stores and manages room availability using a **HashMap**, allowing all room types and their counts to be handled in a single location.

## Flow

1. The system initializes the inventory component.
2. Room types are registered with their available counts.
3. Availability is stored and retrieved from a centralized **HashMap**.
4. Updates to availability are performed through controlled methods.
5. The current inventory state is displayed when requested.

This centralized approach ensures consistent tracking and easier updates of room availability across the system.

---

# 🛠 Technologies Used

* Java
* Object-Oriented Programming (OOP)
* HashMap for centralized data storage
* Console-based application

---

# 📂 How to Clone and Run the Application

## 1️⃣ Clone the Repository

Open your terminal or command prompt and run:

```
git clone https://github.com/your-username/BookMyStay-App.git
```

---

## 2️⃣ Navigate to the Project Directory

```
cd BookMyStay-App
```

---

## 3️⃣ Compile the Java Program

```
javac BookMyStayApp.java
```

---

## 4️⃣ Run the Application

```
java BookMyStayApp
```

---

# 📖 Learning Purpose

This project demonstrates:

* Creating and managing Java objects
* Console-based application workflow
* Handling room availability data
* Using **HashMap** for centralized inventory storage
* Basic structure of a hotel booking system