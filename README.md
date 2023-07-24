# volatile-wilderness-1112
# project_name : **RentoMania**

RentoMania (House Renting System) is a software solution designed to simplify the process of renting houses for both owners and tenants. The system provides a command-line interface for owners to manage their properties and for tenants to search for available houses, make rental offers, and track the status of their offers. The system aims to streamline the house renting process and provide a convenient platform for both owners and tenants.


Types of Users :
* **Owner/Landlord**
* **Tenant/Renter**

* Role of Owner/Landlord
Register for an owner account by providing necessary information.
Log in to the owner account using registered credentials.
Create and manage property listings, including specifying rent amount, location, and property details.
Update rental details and availability status of properties.
Receive and review tenant offers for the listed properties.
Accept or reject tenant offers based on owner preferences and criteria.
Log out from the owner account.


* Role of Tenant/Renter
Register for a tenant account by providing necessary information.
Log in to the tenant account using registered credentials.
Search for available houses based on criteria such as location, rental amount, and property features.
View detailed information about the listed houses, including photos and descriptions.
Apply filters and sorting options to refine the search results.
Submit rental offers to the owners for desired properties.
Track the status of their offers, whether they are accepted or rejected.
View updates on the progress of the rental process.
Log out from the tenant account


# Architecture and Tech Stack of the software

## Database:
This app has been created by using JPA and not any specific ORM framework. Hence only a relational database can be used to store the data.
<br>
ER Diagram:
<img src="https://i.imgur.com/MLtNO2G.png">


## Authentication:
Every service that this app provides, requires password for authentication.

## Abstruction Layers:
There are three abstruction layers:
* **Persistence Layer**: This layer provides access to the database through various interfaces.
* **Service Layer**: This layer provides the core functionality for the app, especially the authentication and authorization.
* **UI Layer**: This layer provides the console based UI.

## Tech Stack:
* Java Standard Edition, version 17
* [Maven](https://maven.apache.org/) as the build tool
* [Jakarta Persistence APIs](https://jakarta.ee/specifications/persistence/3.0/)
* [MySql](https://jakarta.ee/specifications/persistence/3.0/)
* [Hibernate](https://hibernate.org/) as the ORM framework (but can be changed to any other ORM framework, complient with JPA)

# Authors:
* [Hitesh Chandra](https://github.com/HiteshChandra001)
