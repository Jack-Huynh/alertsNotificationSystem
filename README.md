# alertsNotificationSystem

SafetyNet Alerts is an application written in Java using SpringBoot framework, designed to send emergency information to emergency responders. It provides various RestfulAPI endpoints to retrieve information about people, households, and fire stations, allowing emergency responders to access critical data quickly and efficiently.

## Endpoints

### 1. Firestation Information

- **URL:** `/firestation?stationNumber=<station_number>`
- **Description:** Returns a list of people serviced by the corresponding fire station. Includes first name, last name, address, and phone number of each person, along with a summary of the number of adults and children.
  
### 2. Child Alert

- **URL:** `/childAlert?address=<address>`
- **Description:** Returns a list of children (under 18 years old) at the specified address. Includes first and last name, age, and a list of other persons living at that address.

### 3. Phone Alert

- **URL:** `/phoneAlert?firestation=<firestation_number>`
- **Description:** Returns a list of phone numbers of each person within the fire station's jurisdiction.

### 4. Fire Information

- **URL:** `/fire?address=<address>`
- **Description:** Returns the fire station number servicing the provided address, along with a list of all people living at the address. Includes name, phone number, age, medications with dosage, and allergies for each person.

### 5. Flood Stations

- **URL:** `/flood/stations?stations=<list_of_station_numbers>`
- **Description:** Returns a list of households in each fire station's jurisdiction. Groups people by household address and includes name, phone number, age, medications (with dosages), and allergies for each person.

### 6. Person Information

- **URL:** `/personInfo?firstName=<firstName>&lastName=<lastName>`
- **Description:** Returns information about a person based on their first name and last name. Includes name, address, age, email, list of medications with dosages, and allergies. If there are multiple people with the same name, returns information for all of them.

### 7. Community Email

- **URL:** `/communityEmail?city=<city>`
- **Description:** Returns the email addresses of all people in the specified city.

### Note
- All endpoints return results in JSON format.
- If an address or fire station number is not found within the data file, an empty JSON object is returned.
- The application logs every request and response.

## Architecture

SafetyNet Alerts follows the Model-View-Controller (MVC) design pattern. It separates the model (data), view (presentation), and controller (business logic) components, following the SOLID principles to ensure maintainability and scalability. Also, a DTO(Data Transfer Object) design patterns is used to transfer data between different layers of an application.

## Unit Tests

The application includes a set of unit tests to validate each of the requirements in Postman or any test applications of your choice.

