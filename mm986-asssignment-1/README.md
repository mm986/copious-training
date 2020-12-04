# Assignment 1

This assignment covers following concepts and terminologies -
    
   1. Use of below collection apis and make use of streams -

        `ArrayList, LinkedList`
              
        `HashSet, LinkedHashSet, and TreeSet`
        
        `HashMap, LinkedHashMap, and TreeMap`
        
        `Make use of comparable and comparator interface`
        
        `Properties class in java`
   
   2. Create 5 custom business exceptions and throw them based on validations in your project.
   
   3. Make use of singleton design pattern and factory or prototype design pattern.
   
   4. Write unit test case for same using Mockito framework.
   
   5. Make use of java 8 features. No code should be written below java 8 versions.
   
   6. Check in code into your repo on daily basis and send me the link to your repo.
   
       Git Repo:  _https://github.com/mm986/copious-training/_

## Tech Stack
   This is a Spring-boot application which is using spring-web to implement Rest api's along with that following are the additional libraries we are using in this app.
       
       Java 8 (Stream API, Lambdas, Functional Interfaces)
       spring-boot-starter-web
       Java Immutables
       Jackson API 
       Log4J
       JUnit Testing libraries.
         
## Order Processing - Business Use Case 
_We are developing order processing software (An ECom platform) for a demo as a part of Assignment1 in copious training. 
This app contains implementation of above Java 8 features as a part of Order and Product (SKU) modules in this system along with web/rest api based implementation. 
We don't have database at the backend all data is mock and serialized from JSON._ 

### Order data Structure
    Order ={
           "orderPhase": "ORDER_COMPLETE",
           "orderId": "123ABC",
           "emailAddress": "mahesh@copious.cloud",
           "currency": "USD",
           "subtotal": 35.98,
           "discountAmount": 0,
           "promoApplied": "NONE"
           "taxAmount": 0,
           "grandTotal": 35.98,
           "cartUrl": "http://copious.com/",
           "shippingAmount": 7.99,
           "shippingDate": "11-25-2020",
           "shippingDetails": "BlueDart",
           "shippingTrackingUrl": "http://bluedart.com/tracking/NIeX3KYLcPhgRzKy",
           "lineItems": [{
                   "sku": "576879",
                   "name": "Shirt",
                   "description": "A super great description of the product",
                   "category": "Shirts > T-Shirts > Blue",
                   "other": "Copious assignment test data",
                   "unitPrice": 11.99,
                   "salePrice": 11.99,
                   "quantity": 2,
                   "totalPrice": 23.98,
                   "imageUrl": "http://copious.com/a/p/shirt.jpeg",
                   "productUrl": "http://copious.com/index.php/shirt.html"
                 },
                {
                    "sku": "1112296",
                    "name": "Fleece Jacket",
                    "description": "A well appointed Fleece jacket",
                    "category": "Jackets > Winter > Fleece",
                    "other": "Copious assignment test data",
                    "unitPrice": 65.99,
                    "salePrice": 55.50,
                    "quantity": 1,
                    "totalPrice": 55.50,
                    "imageUrl": "http://copious.com/a/p/fleece.jpeg",
                    "productUrl": "http://copious.com/index.php/fleece.html"     
               }
           ]
       }
    		
### Your Orders - Play with Collections 
    * Provide filters based on order status arrival date
    * Sort based on price, arrival date, order date
    * Use ArrayList to perform above operations
    * Use Linked List to perform some of the above operations
    * Use Map to store and operate on above operations
    * Refer following link for HashSet, LinkedHashSet and TreeSet
    https://www.geeksforgeeks.org/difference-and-similarities-between-hashset-linkedhashset-and-treeset-in-java/ 
### Business Validations & Exception Handling
####Custom Exception Creation 
Do business validations on order processing and cover exception handling

    Order -
    * Invalid Order - Order Date
    * Invalid Order - Shipping Date
    
    Product -
    * Invalid Product - Invalid Sku name.
    * Invalid Product - Invalid Sku price.

Create N number of custom exception and throw it via single Controller advice.
Pipe operation.

####Try with resources.
An implemented try with resources while reading mock data from JSON files.

### Applying Design Patterns 
####Singleton
    Define fixed promo codes and use them as a part of Singleton class. Cover all loopholes - many ways to implemented. Threadsafe Singleton pattern implemented to create a global instance of ObjectMapper.
####Factory Pattern
    Implemented ECom Product factory to segregate and get differnet types of Products.
    - APPAREL_AND_ACCESSORIES,
    - STYLE_AND_FASHION,
    - HOME_AND_GARDEN,
    - SPORTING_GOODS,
    - HEALTH_AND_WELLNESS,
    - MEDICAL_HEALTH,
    - CHILDREN_AND_INFANTS,
    - PETS_AND_PET_SUPPLIES,
    - CONSUMER_ELECTRONIC_GOODS,
    - HOME_IMPROVEMENT   

### Properties class in Java
Properties class implementation to read System properties and provide it to rest api.

### LocalDate operations
LocalDate operations includes ObjectMapper config to serialize LocalDate type in Java. Also the operations like before, after, minusDays are implemented as a part of this assignment.

### JUnit/Mockito
Included JUnit test cases to cover all Service class and repository logic. Following are the major test cases:
    
    - Getting Order
    - Validating Order (All +ve and -ve test cases)
    
    - Getting Products 
    - Validating Products (All +ve and -ve test cases)
    - Getting Product by Category (For all categories)