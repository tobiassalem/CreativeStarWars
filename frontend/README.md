## StarWars frontend

A simple frontend application to illustrate how to build a backend API. It is built using React, Node and npm. 
NVM = Node Version Manager is recommended to smoothly install and switch between different Node versions.
This simple frontend currently supports the following features:
* Listing Star Wars characters.
* Viewing details of any given character.
* Listing planets of the Star Wars universe.
* Viewing details of any given plane.
* Listing Star Wars spaceships.
* Viewing details of any given spaceship.


### Requirements
* Node 20+
* Dependencies per the `package.json` file

### Building
* Run `npm install` which installs the needed dependencies.
* Build with mvn install.


### Running
* Run `npm start`
* Access the frontend appliation on http://localhost:3000/


### Known issues
* NB! This is very much a work in progress. For example, test data is used for the listing features. 
* Not all ids in the test data correspond to real backend data. This affects some of the detail lookups.
* The Spring Boot backend is not yet called by the React frontend. This will naturally solve the id issue above.

### References

* Node - @See https://nodejs.org/en
* NVM - @See https://github.com/nvm-sh/nvm
* Ract - @See https://react.dev/
* Postman REST client - @See https://www.postman.com/
* Clean Code by Robert C. Martin - @See https://www.amazon.com/Clean-Code-Handbook-Software-Craftsmanship/dp/0132350882