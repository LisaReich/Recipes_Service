# Recipes_Service
## Description
What if you want to cook something special, but lost the recipe book?  
Recipes Service is a multi-user web service that allows storing, retrieving, updating, and deleting recipes.    
## Features
- The service supports registration process
- Recipes are stored in H2 database
- Users can update or delete only their recipes
- Users may search recipes by name or category
## Dependencies
- Spring Data JPA  
- Spring Security  
- Spring MVC  
## Running the project
The Source files for this project can be found by navigating to:  
Recipes/task/src/recipes

As far as layered architecture is implemented for this project, the directory contains several packages:
- ```businesslayer``` - contains entities' models, services for working with repositories
- ```persistancelayer``` - contains repositories' interfaces
- ```presentationlayer``` - contains REST controllers and controller exception handler
- ```securitylevel``` - contains WebSecurityConfigurer class, UserDetails and UserDetailsService implementations

```RecipesApplication.java``` - This file contains the method that run the program.
## API 
- ```POST /api/recipe/new``` receives a recipe as a JSON object and returns a JSON object with one id field;  

  The service accept only valid recipes – all fields are obligatory, name and description shouldn't be blank, and JSON arrays should contain at least one item.
- ```GET /api/recipe/{id}``` returns a recipe with a specified id as a JSON object;
- ```DELETE /api/recipe/{id}``` deletes a recipe with a specified id
- ```PUT /api/recipe/{id}``` receives a recipe as a JSON object and updates a recipe with a specified id
- ```GET /api/recipe/search``` takes one of the two mutually exclusive query parameters:  
  1. ```category``` – if this parameter is specified, it returns a JSON array of all recipes of the specified category;  
  2. ```name``` – if this parameter is specified, it returns a JSON array of all recipes with the names that contain the specified parameter.  
  
  Search is case-insensitive, recipes are sorted by date (newer first).  
- ```POST /api/register``` receives a JSON object with two fields: email (string), and password (string).  

  Both fields are required and must be valid: email should contain @ and . symbols, password should contain at least 8 characters and shouldn't be blank.  
## Usage
**Example 1**    
- ```POST /api/register```  
```
{
   "email": "Cook_Programmer@somewhere.com",
   "password": "RecipeInBinary"
}
```
Status code: ```200 (Ok)```  
  
- ```POST /api/recipe/new```
```
{  
    "name": "Mint Tea",  
    "category": "beverage",  
    "description": "Light, aromatic and refreshing beverage, ...",  
    "ingredients": ["boiled water", "honey", "fresh mint leaves"],  
    "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix   again"]  
}  
```
Response:
```
{
   "id": 1
}
```
- ```PUT /api/recipe/1``` request with basic authentication; email (login): Cook_Programmer@somewhere.com, password: RecipeInBinary
```
{  
    "name": "Mint Tea",  
    "category": "beverage",  
    "description": "Light, aromatic and refreshing beverage, ...",  
    "ingredients": ["boiled water", "honey", "fresh mint leaves"],  
    "directions": "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]    
}  
```
Status code: ```204 (No Content)```  

- ```GET /api/recipe/1``` request with basic authentication; email (login): Cook_Programmer@somewhere.com, password: RecipeInBinary  

Response:  
```
{  
    "name": "Mint Tea",  
    "category": "beverage",  
    "date": "2022-10-27T12:11:25.034734",  
    "description": "Light, aromatic and refreshing beverage, ...",  
    "ingredients": ["boiled water", "honey", "fresh mint leaves"],  
    "directions": "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]    
}  
```
- ```DELETE /api/recipe/1``` request with basic authentication; email (login): Cook_Programmer@somewhere.com, password: RecipeInBinary  
  
Status code: ```204 (No Content)```  
  
**Example 2**    
A database with several recipes    
```
{  
   "name": "Iced Tea Without Sugar",  
   "category": "beverage",  
   "date": "2019-07-06T17:12:32.546987",  
   ....  
},  
{  
   "name": "vegan avocado ice cream",  
   "category": "DESSERT",  
   "date": "2020-01-06T13:10:53.011342",  
   ....  
},  
{  
   "name": "Fresh Mint Tea",  
   "category": "beverage",  
   "date": "2021-09-06T14:11:51.006787",  
   ....  
},  
{  
   "name": "Vegan Chocolate Ice Cream",  
   "category": "dessert",  
   "date": "2021-04-06T14:10:54.009345",  
   ....  
},  
{  
   "name": "warming ginger tea",  
   "category": "beverage",  
   "date": "2020-08-06T14:11:42.456321",  
   ....  
}  
```
  
Response for the ```GET /api/recipe/search/?category=dessert``` request:  
```
[  
   {  
      "name": "Vegan Chocolate Ice Cream",  
      "category": "dessert",  
      "date": "2021-04-06T14:10:54.009345",  
      ....  
   },  
   {  
      "name": "vegan avocado ice cream",  
      "category": "DESSERT",  
      "date": "2020-01-06T13:10:53.011342",  
      ....  
   },  
]  
```
Response for the ```GET /api/recipe/search/?name=tea``` request:  
```
[  
   {  
      "name": "Fresh Mint Tea",  
      "category": "beverage",  
      "date": "2021-09-06T14:11:51.006787",  
      ....  
   },  
   {  
      "name": "warming ginger tea",  
      "category": "beverage",  
      "date": "2020-08-06T14:11:42.456321",  
      ....  
   },  
   {  
      "name": "Iced Tea Without Sugar",  
      "category": "beverage",  
      "date": "2019-07-06T17:12:32.546987",  
      ....  
   },  
]  
```
## Additional info
To find more about this project, please visit https://hyperskill.org/projects/180.
