# NUTRITION-APP
//BACKEND (Springboot)

For backened part. I have built five micro services.

Nutrition Server(port:8080)
Nutrition Favourite(port:8585)
Nutrition API(port:8090)
API GATEWAY(port:9000)
Nutrition Backened(port:8761)
-> Nutrition Server(port:8080) Nutrition Server is basically used for login and sign up part and also here we have implemented JWT Authentication too. It is also connected with mysql database for storing user's sign up and login data.

-> Nutrition Favourite(port:8585) Nutrition Favourite is used only for marking item favourite and deleting favourite item. All that logic is applied in this micro service. It is connected with mongoDB database to store user's favourite food items list.

-> Nutrition API(port:8090) Nutrition API is basically used to deal with calling the external api.

-> API GATEWAY(port:9000) As we can see above all three micro-services are running on different ports. So API Gateway will provide a single port to the client(user) i.e 9000. The user only have to hit on this port. After which our API Gateway automatically forward the request to the desriable ports with their micro-services.

-> Nutrition Backened(port:8761) Nutrition Backened is basically a Eureka netflix server which we use to deploy all are micro-services. It itself is also a micro-services but we have deploy all are 4 mico services including API gateway.

//FRONTEND (Angular)

Try npm install command first to install all the node module packages used in our project.
For frontend part. I have used 6 components and 6 services.

I) Components:

Home : For the home purpose page.
List-Food-Item : All the total food items will be shown in a format of list.
List-Favourite-Item : All the favourite item marked by the user will be shown in a list.
Login : For Login purpose page.
Sign up : For Signup purpose page.
Navbar : For having a navbar in our all pages.
II) Services:

Auth-Interceptor : We used this class for Angular Interceptor. Angular Interceptor will basically takes the token and put it inside every request.
Dummy-Guard : Angular guard is an interface which can be implemented to decide if a route can be activated.If all guards return true, navigation continues. If any guard returns false, navigation is cancelled.
Favourite : To get favourite item data marked by user and communicate it to our springboot(backened).
FoodItems : To communicate to our spring boot application (backened).
Login : For login purpose.
Sign up : For sign up purpose.
//DATABASE

For the database part. I have used two databases.

mySql or mariaDB : I have used mariaDB database. It is similar to mySql. This database is used to store user's all basic information like sign up information and also during jwt authentication part the login data(i.e username & password) is checked from the same database. The database is created by name fooddb. Under which the table name is users
mongoDB : MongoDB is an open source NoSQL database management program. NoSQL is used as an alternative to traditional relational databases. NoSQL databases are quite useful for working with large sets of distributed data. So here I used mongoDB to store user's favourite item list. The database is created by name fooddb. Under which the collection(table) name is favourite.
//EXTERNAL API'S

For generating API Key :

https://fdc.nal.usda.gov/api-key-signup.html
The rest end point details are available :

https://fdc.nal.usda.gov/api-guide.html
