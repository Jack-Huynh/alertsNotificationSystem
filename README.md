# SafetyNet Alerts System

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://openjdk.java.net/)
[![MapStruct](https://img.shields.io/badge/MapStruct-1.5.5-blue.svg)](https://mapstruct.org/)
[![H2 Database](https://img.shields.io/badge/Database-H2-blue.svg)](http://www.h2database.com/)

SafetyNet Alerts is a modern Spring Boot application designed to provide emergency information to first responders and emergency services. The system offers a comprehensive REST API with an interactive web interface for accessing critical data about people, households, and fire stations during emergency situations.

## ✨ Features

- **🌐 Interactive Web Interface**: Modern, responsive HTML5 interface with tabbed output display
- **📱 Mobile-Friendly Design**: Responsive grid layout that works on all devices
- **🔄 Real-time API Testing**: Built-in API explorer with copy-to-clipboard functionality
- **📊 Dual Output Formats**: Toggle between JSON and human-readable data presentation
- **🗃️ In-Memory Database**: Fast H2 database with automatic schema generation
- **🔧 Modern Architecture**: Clean separation of concerns with MapStruct for object mapping
- **📋 API Documentation**: Built-in endpoint documentation with live URL generation

## 🚀 Quick Start

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Running the Application

1. **Clone the repository**
   ```bash
   git clone https://github.com/Jack-Huynh/alertsNotificationSystem.git
   cd alertsNotificationSystem
   ```

2. **Build and run**
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the application**
   - Web Interface: [http://localhost:8080](http://localhost:8080)
   - H2 Database Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - Health Check: [http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

## 📚 API Endpoints

### 🔥 Fire Station Services

| Endpoint | Description | Example URL |
|----------|-------------|-------------|
| `/firestation` | Get people serviced by fire station | `http://localhost:8080/firestation?stationNumber=1` |
| `/phoneAlert` | Get phone numbers for station jurisdiction | `http://localhost:8080/phoneAlert?firestation=2` |
| `/fire` | Get fire station and residents for address | `http://localhost:8080/fire?address=123%20Main%20St` |
| `/flood/stations` | Get households by station numbers | `http://localhost:8080/flood/stations?stations=1,2` |

### 👨‍👩‍👧‍👦 Person & Community Services

| Endpoint | Description | Example URL |
|----------|-------------|-------------|
| `/childAlert` | Get children at specific address | `http://localhost:8080/childAlert?address=123%20Main%20St` |
| `/personInfo` | Get detailed person information | `http://localhost:8080/personInfo?firstName=Alice&lastName=Smith` |
| `/communityEmail` | Get all emails for city residents | `http://localhost:8080/communityEmail?city=HCM` |

### 📊 Response Formats

All endpoints support:
- **JSON Format**: Standard REST API responses
- **Human-Readable Format**: Formatted for easy reading (via web interface)
- **Error Handling**: Appropriate HTTP status codes and error messages

## 🏗️ Architecture

### Technology Stack
- **Framework**: Spring Boot 3.2.0
- **Database**: H2 In-Memory Database
- **ORM**: Spring Data JPA
- **Mapping**: MapStruct 1.5.5 for automatic object mapping
- **Build Tool**: Maven
- **Frontend**: Vanilla HTML5, CSS3, JavaScript

### Design Patterns & SOLID Principles

#### 🏗️ **Design Patterns Used**
- **MVC Pattern**: Clear separation of Model, View, and Controller layers
- **DTO Pattern**: Data Transfer Objects for clean API contracts
- **Repository Pattern**: Data access abstraction
- **Service Layer Pattern**: Business logic encapsulation
- **Mapper Pattern**: Automatic entity-DTO conversion with MapStruct
- **Dependency Injection**: Loose coupling through Spring's IoC container

#### 🎯 **SOLID Principles Applied**

##### **S - Single Responsibility Principle (SRP)**
```java
// ✅ Each class has one responsibility
@Controller - Handle HTTP requests only
@Service - Business logic only  
@Repository - Data access only
@Entity - Data representation only
```

##### **O - Open/Closed Principle (OCP)**
```java
// ✅ MapStruct interfaces are open for extension, closed for modification
@Mapper
public interface PersonMapper {
    PersonDTO toDTO(Person person);
    // Can add new mapping methods without changing existing ones
}
```

##### **L - Liskov Substitution Principle (LSP)**
```java
// ✅ Service implementations can be substituted without breaking functionality
@Autowired
private PersonService personService; // Any PersonService implementation works
```

##### **I - Interface Segregation Principle (ISP)**
```java
// ✅ Small, focused repositories instead of one large interface
public interface PersonRepository { /* Person-specific methods only */ }
public interface FireStationRepository { /* FireStation-specific methods only */ }
```

##### **D - Dependency Inversion Principle (DIP)**
```java
// ✅ Controllers depend on Service abstractions, not concrete implementations
@Autowired
private PersonService personService; // Depends on abstraction
@Autowired 
private FireStationService fireStationService; // Not concrete classes
```

### Project Structure
```
src/
├── main/
│   ├── java/com/example/alert/
│   │   ├── controller/          # REST API Controllers
│   │   ├── service/             # Business Logic Services
│   │   ├── repository/          # Data Access Layer
│   │   ├── model/               # JPA Entities
│   │   │   └── DTO/             # Data Transfer Objects
│   │   ├── mapper/              # MapStruct Mappers
│   │   └── util/                # Utility Classes
│   └── resources/
│       ├── alerts-data.json     # Sample Data
│       ├── application.properties
│       └── static/
│           └── index.html       # Interactive Web Interface
└── test/                        # Unit Tests
```

## 🔧 Configuration

## 📋 Sample Data

The application loads sample data from `alerts-data.json` containing:
- **Persons**: Alice Smith, Bob Johnson, Charlie Brown, David Williams, Eva Taylor, Frank Miller, Grace Miller, Henry Clark, Oliver Johnson, Sophie Smith
- **Cities**: HCM, Halifax
- **Fire Stations**: Station 1 (123 Main St, 789 Pine St), Station 2 (456 Oak St, 101 Elm St)
- **Age Distribution**: Adults and children for testing child alert functionality

### 📊 Test Data Examples
- **Adults**: Alice Smith (32), Bob Johnson (27), David Williams (54), Eva Taylor (36), Frank Miller (22), Henry Clark (32)
- **Children**: Charlie Brown (10), Grace Miller (10), Oliver Johnson (17), Sophie Smith (12)
- **Addresses**: 123 Main St, 456 Oak St, 789 Pine St, 101 Elm St