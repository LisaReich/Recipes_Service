# Recipes_Service
## Description
What if you want to cook something special, but lost the recipe book?  
Recipes Service is a multi-user web service that allows storing, retrieving, updating, and deleting recipes.    
## Features
- The service supports registration process
- Recipes are stored in H2 database
- Users can update or delete only their recipes
- Users may search recipes by name or category
## Running the project
The Source files for this project can be found by navigating to:  
Recipes/task/src/recipes

As far as layered architecture is implemented for this project, the directory contains several packages:
- businesslayer - contains entities' models, services for working with repositories
- persistancelayer - contains repositories' interfaces
- presentationlayer - contains REST controllers and controller exception handler
- securitylevel - contains WebSecurityConfigurer class, UserDetails and UserDetailsService implementations

RecipesApplication.java - This file contains the method that run the program.
## Additional info
To find more about this project, please visit https://hyperskill.org/projects/180.
