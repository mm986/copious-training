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
    		
## Your Orders - Play with Collections 
    * Provide filters based on order status arrival date
    * Sort based on price, arrival date, order date
    * Use ArrayList to perform above operations
    * Use Linked List to perform some of the above operations
    * Use Map to store and operate on above operations
    * Refer following link for HashSet, LinkedHashSet and TreeSet
    https://www.geeksforgeeks.org/difference-and-similarities-between-hashset-linkedhashset-and-treeset-in-java/ 
##Business Validations & Exception Handling

###Custom Exception Creation 
Do business validations on order processing and cover exception handling

    Order -
    * Invalid Order - Order Date
    * Invalid Order - Shipping Date
    
    Product -
    * Invalid Product - Invalid Sku name.
    * Invalid Product - Invalid Sku price.

Create N number of custom exception and throw it via single Controller advice.
Pipe operation.

###Try with resources.
An implemented try with resources while reading mock data from JSON files.

## Applying Design Patterns 

###Singleton
    Define fixed promo codes and use them as a part of Singleton class. Cover all loopholes - many ways to implemented. Threadsafe Singleton pattern implemented to create a global instance of ObjectMapper.

###Factory Pattern
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

## Properties class in Java
Properties class implementation to read System properties and provide it to rest api.

## LocalDate operations
LocalDate operations includes ObjectMapper config to serialize LocalDate type in Java. Also the operations like before, after, minusDays are implemented as a part of this assignment.

## JUnit/Mockito
Included JUnit test cases to cover all Service class and repository logic. Following are the major test cases:
    
    - Getting Order
    - Validating Order (All +ve and -ve test cases)
    
    - Getting Products 
    - Validating Products (All +ve and -ve test cases)
    - Getting Product by Category (For all categories)

## Swagger Annotations and Authorization

BCrypt Paswsowrds
    hydrosigma $2y$12$vsqTdSd6riwSk5YPdxa1bOr57hW4Ilznq0.QHlRN/r7AZM1Y1hj66
    mm986   $2y$12$tW8sqhdq9oWPuHZATqfvQe7IOXDTrLjwCNdeCaekeJhW0Gto3bIx6  

# MOM's
## Tue 8th Discussion

- Use and purpose of Swagger
- Authorization for Swagger
- Controller level mapping for API
- Annotations for Swagger
    http://localhost:8080/assignment1/swagger-ui.html

Tomorrow To Discuss-
- Security


## Wed 9th Dec Discussion (With Akhand)

- Swagger config for sending authorization token.
- How to use properties in pom.xml
- What is a difference between provided and compile scope of dependency?
    - Compile dependencies are available in all classpaths of a project.
    - Indicates you expect the JDK or a container to provide the dependency at runtime.(basically not packaged inside application jar or war, expected at runtime)

- What is OnePerRequestFilter for security filter?
    - Example- Logging, Validation, Alarm for rest call
    - some of the security/access filter actions should only be performed once for a request.
    
- Usage and importance of Order annotation for filters?
    - @Order Annotations (as well as the Ordered interface) implies a specific order, in which the beans will be loaded or prioritized by Spring.
    
- Filter is a lifecycle class
- HTTP Headers (All standard)
- JWT Token generation logic
- JWT Token Validation logic
- Static and Dynamic tokens/ Pre login and post login call
- Request dispatcher sending error

Discussions pending (TODO) -

- Concurrent collection
- Exception Handling  - Need to handle All exceptions to common responses.
- Multi-module project - Parent-child relationship for pom
- MDC logs (Park it for later)

## Internal Team Discussion 9th Dec 

- Solved issues with Swagger-UI
- Swagger authorization configuration
- Steps to implement JWT-AUTH
    1. Implement User Login service and generate and return JWT to client/user
    2. Implement JWT validation flow to unmarshal and validate the JWT
    3. Web security config to allow and restrict api's 
    4. Testing of above flows from Swagger-UI
 
## Thur 10th Dec Discussion (With Akhand)
- Swagger implementation demo from team.
- class level mapping for RestController
- private for @Autowiring
- How to skip the tests
- How to connect two different db's from single service (Primary & Secondary DB's)
- How to add landing page url to app to automatically open chrome.
- Server port customization.
- Exception handling beginning
    How to write Exception Enum?
    How to write GenericResponse?
    How to write ExceptionResolver?
    
    
## Friday 11th Dec Discussion (With Akhand)    

- Add JWT token in response header
- JWT Token is not session specific
- Java Predicates - Combining more than one filter conditions and use if once
- Atomic Variables in Lambdas
- JSON Schema to POJO (Maven configurations)
- Exception Handling Implementation.


## Friday 11th Dec Discussion - Internal Discussion

JWT Auth code walk through & Flow -
    Token Generation Flow -
        For first time during user login
        1. login API
        2. UserDetailsService - Hardcoded user/pass
        3. Token Generation Logic
         
   Token Validation Flow -
        When user will hit any API -
        1. Check for WebSecurity config
        2. Validate in the filter
        3. Then execute actual business Login 


## Thursday 17th Dec Discussion (With Akhand)
- transient 
- Order of calling constructor - Final var with no arg constructor
- WebRequest R&D
- String Formatting
- ResponseEntityExceptionHandler methods
- All controller should return ResponseEntity<GenericResponse>


## Saturday 19th Dec Discussion (Guru Code Walk-through)
### Session 1
Spring Security -Web Security Configuration

- WebSecurity vs HttpSecurity
- Cors - To allow api access from Cross Origins / We can restrict calls from specific origin  
- Csrf - To detect and filter any alteration in request in between
- Xss - Enable/Disable
- Framing Options - Enable/Disable
- Cache Control - Enable/Disable
- Session Creation Policy - Stateless
- OncePerRequestFilter - why we are using this?
- OPTIONS method - pre flight check - Angular

- Master data loading during start-up (New Use case). There is solution that is provided by spring-boot - via Static Data Config
     ImplementApplicationRunner Interface - tasks to do on start-up.
 
- Scope of Bean in spring? Singleton default - One instance per spring container, Prototype, session
- Use of @Component? Register bean

### Session 2
- LocalDate operations.
- Using Joda-Time utility
- Executor framework - Completable Future.
    
## Tuesday 22th Dec Discussion (Utkarsh & Meghdoot code walkthrough) 
- Use of Enum
- BaseComponentScan
- Loading external property file - PropertySource annotation
- Jacoco plugin for code coverage

## Wednesday 23rd Dec (Session with Akhand)
- Mock vs Inject Mock
- @RunWith annotation
- Interface and implementation for Service classes.
- @InjectMock annotation
- when & thenReturn methods of Mockito
- Any matcher classes.
- IMap - Identical Map
- Concurrent hashMap
- AWS - CloudFront Invalidate cache
- Static Imports
- verify and assert methods of Mockito
- Optional Interface in Java.
- Multimodule Project 
- Use of powermock - Advancement in Mockito## Wednesday 23rd Dec (Session with Akhand)
- Use of Dependency Management in Parent Pom.xml

## Tuesday 5th Jan (Session with Akhand)
- Spring boot profiling
- Identity HashMap Vs Hash Map - HashMap uses equals method, Identity HashMap implements == method
- What is fail fast & Fail safe (In case of concurrent modification exception - when u try to alter structure of collection then it fails because it is not synchronized)
- Which collection is fail safe? Vector, ConcurrentHashMap, CopyOnWriteArrayList
- How we will synchronize hash map? Collections.synchronize() 

## Wednesday 6th Jan (Session with Akhand) - JSON Schema
- JPA validators
- json-schema.org - Currently they are on 7th version
- JSON to POJO converter profile configuration in maven
- JSON Schema Definitions

## Code Review Comments
1. <java.version> in pom is not being used its just a variable
    Yes this property is a Spring Boot Specification, and we can use it to override default version of parent.
      
2. what is the use of this org.jetbrains, spring-boot-maven-plugin dependency?
    org.jetbrains I'm using for Nullable annotation to mark class variable as not required to construct an immutable object. 
    
    spring-boot-maven-plugin used to package executable jar or war archives, run Spring Boot applications
    
3. try (InputStream mockProduct = new ClassPathResource("mock/products.json").getInputStream())  does not have catch block
    Here it's a demonstration of Try with resources. Is this valid way to use try with resources?
    
4. @ControllerAdvice does not require @Component annotation. remove it.
    Done Removed.
    
5. @CrossOrigin can also be added in main class so that it will apply for all controllers.
    
6. import org.springframework.web.bind.annotation.*; avoid * imports

## Wednesday 7th Jan (Session with Akhand)
- JSON Schema - Properties, Definitions, Nested Objects and references, Arrays. 
- What is BCryptEncoder is implementation of PasswordEncoder. It uses salt mechanism converts into hash. 
  PKD password encoder to encodes strings in banking systems.
  SCrypt
- Testing Controller with MockMvc - Get, Post

## Wednesday 8th Jan (Session with Akhand)
Ways to connect to DB
- Hibernate (JPA) based database connection - 
    - Drawbacks - difficulties while connecting to multiple data sources, Difficulties while migrating one db to other.
    - Hibernate dialect - Conversion to native SQL.

- Repository Config based JPA
    - @Configuration 
    - @EnableTransactionManagement
    - @EnableJpaRepositories
    - JNDI - specific to env so this property always comes from property file.
    - Setting Entity Manager Factory - Provide Persistence Unit - Provide JPA properties
        
- Multiple DataSource Connection.
    
- Connection Pooling
- Spring Package scan for repositories
- @NoRepositoryBean Annotation - why it is in CrudRepository.
- Lazy Loading
- WebServer Vs Application server

## Saturday 9th Jan (Session with Akhand)
- Add Context Param to Web.xml - spring.active.profile
- Swagger Profiles for local only 

- Add user and role to tomcat-user.properties
- Add JNDI properties in application.properties
- Setup JNDI props in context.xml - what is the drawback to setup all data source details here? - it will not provide connection pooling
- Setup JNDI props in server.xml

- MySql max connecction property - 1024 we can change with Query
- Query to get active connections

- CURL commands
- Change maven packaging with war.

MySql setup question
context.xml question
how we mention scan for repo and entity

## Tuesday 12th Jan (Session with Akhand) DB & Hibernate
- Connection with Multiple DBs with JNDI
- How to map Entities and Repos to multiple datasource 
    - Package based segregation primary, secondary for Entities and Repos
    - Create two separate classes for Primary & Secondary DB
- Where we use @Qualifier annotation - Why?
- First level 
    - session specific cache 
    - We can't change this.
- Lazy Loading 
    - fetchType = lazy 
    - (First level Vs Lazy Loading) 
    - (Mappings fetch property - LAZY, EAGAR)
- Second Level 
    - hibernate-ehcache.
        hibernate.cache.use_second_level_cache=true
        hibernate.cache.region.factory_class=org.hibernate.cache.ehcache.EhCacheRegionFactory
    - Frequently checks table updation timestamp
    - Where to store all data - disk-stoarage path
    - https://www.baeldung.com/hibernate-second-level-cache
    
## JMS Session with Ashish
- Bridging between Topics and Queues
- Blocking Queues - thread safe, Blocks thread in case if Queue is empty or full it blocks thread before proceed. 
- Backout queue - In case of exception while processing messages from Queue

## R&D 14th Jan

- Spring Static Data initialization in MYSQL 
https://docs.spring.io/spring-boot/docs/1.2.0.M1/reference/html/howto-database-initialization.html 

## Tuesday 16th Jan (Session with Akhand) Spring Transactions
- Meaning of Transaction
- Two types of Transaction Management
    - Programmatic - We know the exact scope of transaction
           
            try{
                tx.begin();   
                dologic();  
                tx.commit(); 
            } cathc(Exc e){ 
                tx.rollback()
            }
            
    - Declarative - This is annotation based provided by Spring
        - @EnableTransactionManagement 
        - @Transactional(isolation= Isolation.)
            - If we annotate at class level then applied for all, mostly used at  
            - Transaction has its own Isolation level and rollback strategy
            - Transaction annotations are only used at public methods not at private methods
            - What is transaction Isolation level?
            - Dirty reads, phantom reads, non repeatable reads for DB
            - What is transaction propagation
            - Params 
                - isolation - 
                    - @Transactional(isolation = Isolation.REPEATABLE_READ)
                    - @Transactional(isolation = Isolation.READ_COMMITTED)
                    - @Transactional(isolation = Isolation.READ_UNCOMMITTED)
                - timeout -
                    - @Transactional(timeout =10 )
                - propagation -    
                    - @Transactional(propagation = Propagation.REQUIRED)
                    - @Transactional(propagation = Propagation.MANDATORY)
                    - @Transactional(propagation = Propagation.NEVER)
                    - @Transactional(propagation = Propagation.SUPPORTS)
                    - @Transactional(propagation = Propagation.NOT_SUPPORTED)
                    - Spring will throw runtime exception if transaction is not able to link with others.      
                - rollbackFor
                    - @Transactional(rollbackFor = RewardOrchestrationException.class)
                - noRollbackFor
            - Where we should use @Transactional in application layer?   
                - At orchestration layer or service layer. Controller layer designed only to redirect rest requests to service.     
            - What is real time advantage of using @Transactional at service layer?
                - here we communicate with multiple service and DAO layers.
- When IllegalStateException comes?
- Async service
- CURL scripts
- Coding standards
- Dynatrace

## Friday 22nd Jan (Session with Akhand) Coding Standards
### IntelliJ Coding Standards
- toString()
- serializeVersionUID
    - Serializable - are hibernate entities serializable ?
    - Marker Interfaces
    - why we use this id - unique identification purpose

- Live Templates
    - This is kind of autocomplete for coding 
        - eg. SOP -> System.out.println()
        - eg. linf -> logger

- Maven Build Keyboard Shortcuts

### Java Coding Standards
- Consistency
- Formatting
- SOLID Principals 
    - Immutable classes
- Utility Classes
    - class  must be public final
    - private no arg constructor
    - public static methods
    
- Switch case over if else
- this
- Exception and errors


## Saturday 23rd Jan (Session with Akhand) Curl
- Curl command
- How we can create script for thousands of curl command
    - Request processing table
    - SQL file
        
        DECLARE
          V_SERVER_NAME VARCHAR2(50) := '#########';
        
          V_SYSDATE                DATE := TRUNC(SYSDATE);
          V_EXERCISE_MODULE    VARCHAR2(50) := '##########';
          V_HEALTHY_FOOD_MODULE    VARCHAR2(50) := '##########';
          V_VITALITY_PRODUCT_HOUSE VARCHAR2(50) := '########';
       
          F_FILE_DIR      VARCHAR2(50) := '/######/######/######'; -- Location: 
          F_FILE_HANDLE_W UTL_FILE.FILE_TYPE;
          F_FILE_NAME_W   VARCHAR2(100);
          V_SYSDATE_CR    VARCHAR2(10) := TO_CHAR(V_SYSDATE, 'YYYY-MM-DD');
          V_COUNT         NUMBER := 0;
        
          F_BASE_FILE_NAME_W VARCHAR2(100) := 'IRP_' || V_VITALITY_PRODUCT_HOUSE || '_' || V_HEALTHY_FOOD_MODULE || '_Eligiblity_PROD_' ||
                                              V_SYSDATE_CR || '_';
       
          V_FILE_NUMBER            NUMBER := 1;
          V_NUMBER_PER_FILE        NUMBER := 5000;
          V_NUMBER_IN_CURRENT_FILE NUMBER := 0;
          V_SERVER_NUMBER          NUMBER := 1;
          V_MAX_SERVERS            NUMBER := 12;
          V_SERVER_URL             VARCHAR2(800);
        
          -- TODO: update dates + goals
          V_BASE_SERVER_URL VARCHAR2(800) := ':####/integrated-rewards-platform-service/process/eligibility?overrideSystemDate=2019-12-01" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"eligibilityFlowStateDto\": [ { \"eligibilityStatePeriodDtos\": [{ \"effectiveFrom\": \"2019-12-01\", \"effectiveTo\": \"9999-12-31\", \"metaData\": { }, \"state\": \"ELIGIBLE\" } ], \"goalIdentifier\": \"' ||
                                             V_HEALTHY_FOOD_MODULE ||
                                             '\", \"productHouseIdentifier\": \"' ||
                                             V_VITALITY_PRODUCT_HOUSE ||
                                             '\" }], \"entityNumber\": ';
        
          V_MIN_ENTITY   NUMBER := 0;
          V_MAX_ENTITY   NUMBER := 0;
          V_COUNT_TO_RUN NUMBER;
          V_RUN          NUMBER := 0;
          V_ROWS_PER_RUN NUMBER := 10000;
          
          cursor v_cursor is 
            SELECT E.ENTITY_NO FROM (SELECT distinct GE.ENTITY_NO, GE.EFF_FROM, PH.IDENTIFIER
            FROM VIT_RSA.RP_GOAL_ELG         GE,
                 VIT_RSA.RP_GOAL             G,
                 VIT_RSA.RP_ELIGIBILITY_STAT ES, -- TODO: ELigiblity table + add overall clause + add eligible state
                 RP_GOAL_ELIGIBI_PH          GEP,
                 RP_PRODUCT_HOUSE            PH
           WHERE G.GOAL_ID = GE.GOAL_ID
             AND GE.ELG_STATE_ID = ES.ELIGIBILITY_STATE_ID
             AND ES.IDENTIFIER = 'ELIGIBLE'
             AND GE.ELG_LEVEL = 'OVERALL'
             AND G.IDENTIFIER = V_EXERCISE_MODULE
             AND V_SYSDATE BETWEEN TRUNC(GE.EFF_FROM) AND TRUNC(GE.EFF_TO)
             AND V_SYSDATE BETWEEN TRUNC(GEP.EFF_FROM) AND TRUNC(GEP.EFF_TO)
             AND ES.ELIGIBILITY_STATE_ID = GEP.ELIGIBILITY_STATE_ID
             AND GEP.GOAL_ID = GE.GOAL_ID
             AND GEP.ENTITY_NO = GE.ENTITY_NO
             AND GEP.PRODUCT_HOUSE_ID = PH.PRODUCT_HOUSE_ID
             AND PH.IDENTIFIER = V_VITALITY_PRODUCT_HOUSE
             AND NOT EXISTS
           (SELECT *
                    FROM VIT_RSA.RP_GOAL_ELG         GEE,
                         VIT_RSA.RP_GOAL             GG,
                         VIT_RSA.RP_ELIGIBILITY_STAT ESS -- TODO: ELigiblity table + add overall clause + add eligible state
                   WHERE GEE.GOAL_ID = GG.GOAL_ID
                     AND GG.IDENTIFIER = V_HEALTHY_FOOD_MODULE
                     AND V_SYSDATE BETWEEN GEE.EFF_FROM AND GEE.EFF_TO
                     AND GEE.ENTITY_NO = GE.ENTITY_NO)
           ORDER BY GE.EFF_FROM DESC) E;
       
        BEGIN
          F_FILE_NAME_W   := F_BASE_FILE_NAME_W || V_FILE_NUMBER || '.sh';
          F_FILE_HANDLE_W := UTL_FILE.FOPEN(F_FILE_DIR, F_FILE_NAME_W, 'W');
          V_SERVER_URL := 'curl -X POST "http://' || V_SERVER_NAME || '0' ||
                          V_SERVER_NUMBER || V_BASE_SERVER_URL;
                          
          FOR R IN v_cursor LOOP
            IF (V_NUMBER_IN_CURRENT_FILE >= V_NUMBER_PER_FILE) THEN
              V_NUMBER_IN_CURRENT_FILE := 0;
              UTL_FILE.FCLOSE(F_FILE_HANDLE_W);
            
              V_FILE_NUMBER   := V_FILE_NUMBER + 1;
              V_SERVER_NUMBER := V_SERVER_NUMBER + 1;
              IF (V_SERVER_NUMBER > V_MAX_SERVERS) THEN
                V_SERVER_NUMBER := 1;
              END IF;
              IF (V_SERVER_NUMBER >= 10) THEN
                V_SERVER_URL := 'curl -X POST "http://' || V_SERVER_NAME ||
                                V_SERVER_NUMBER || V_BASE_SERVER_URL;
              ELSE
                V_SERVER_URL := 'curl -X POST "http://' || V_SERVER_NAME || '0' ||
                                V_SERVER_NUMBER || V_BASE_SERVER_URL;
              END IF;
            
              F_FILE_NAME_W   := F_BASE_FILE_NAME_W || V_FILE_NUMBER || '.sh';
              F_FILE_HANDLE_W := UTL_FILE.FOPEN(F_FILE_DIR, F_FILE_NAME_W, 'W');
              DBMS_OUTPUT.PUT_LINE(F_FILE_NAME_W); -- output
            END IF;
            UTL_FILE.PUT_LINE(F_FILE_HANDLE_W,
                              V_SERVER_URL || R.ENTITY_NO ||
                              ', \"productHouseIdentifier\": \"' ||
                              V_VITALITY_PRODUCT_HOUSE || '\"}"'); -- TODO productHouseIdentifier: Vitality
          
            V_NUMBER_IN_CURRENT_FILE := V_NUMBER_IN_CURRENT_FILE + 1;
          
            V_COUNT := V_COUNT + 1;
          END LOOP;
          UTL_FILE.FCLOSE(F_FILE_HANDLE_W);
          DBMS_OUTPUT.PUT_LINE('Done: ' || V_COUNT);
        EXCEPTION
          WHEN NO_DATA_FOUND THEN
            DBMS_OUTPUT.PUT_LINE('Error:' || SQLERRM);
            UTL_FILE.FCLOSE(F_FILE_HANDLE_W);
          WHEN OTHERS THEN
            DBMS_OUTPUT.PUT_LINE('Error:' || SQLERRM);
            UTL_FILE.FCLOSE(F_FILE_HANDLE_W);
        END;
         
- **Async Services**
    - Standard timeout of rest call is 30 sec.
    - Basically we need to create new executor thread and
    - @EnableAsync
    - Async Service 
        -we should use Async method as public
        - we cannot use it via same class
    - java.util.concurrent.Future used to get the state of AsyncProcess.
        
        while (true) {
        if (future.isDone()) {
        System.out.println("Result " + future.get());
        break;
        }
    - Db Query to check 

        SHOW VARIABLES LIKE "max_connections";
        show status where variable_name = 'threads_connected';
        show processlist;
        SET GLOBAL max_connections = 500;
    - Three ways to disable springboot auto-config
        - Disable via exclude over main class @SpringBootApplication
        - Disable with application.properties
        - Disable from pom.xml
        

## Session with Guru(27th Jan 2021)
- 3 ways of DB connection
- mappings -lazy loading
- caching - first, second

Collection
Spring boot
Security configuration - spring Security
Integration JMS,
DB connection
Swager,
Json,
JIRA,
GIT, 
SOAP, 
API JAVA

# AWS Session 1 (17 Feb - with Ripul)
- VPC, Subnet- Public, Private
- Route table
- CIDR - 0.0 and 127.0. 0.0 are two such addresses. The first is called the default route, and the latter is the loopback address. 
- Internet Gateway - without this none of the VPC can communicate with internet
    This is attached to VPC

    
- Handson 
    - Create VPC
    - Create Subnet
    - Create Internet Gateway
    - Route tables
        - if we don't have 0.0.0.0 routing then it is considered as private subnet
        - VPC base route to allow inter subnet communication
    - Elastic IP - once assigned then no one can change it (Static and fix) - Create and delete it
    
    - NAT Gateway
    - VPC peering
    - Transient Gateway
        

        