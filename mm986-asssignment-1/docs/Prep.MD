#Interview Preparation
## Core Java 
### Exception handling

## Java 8 Features with example
Refer: https://www.journaldev.com/2389/java-8-features-with-examples
### forEach() method in Iterable interface
- replaces while(iterator.hasNext()) logic which may cause mismatch during iteration.
    
### default and static methods in Interfaces
- forEach() is example of this, but it may occur **Diamond Problem** 
- Here implementation class will throw compile time exception as - A default method cannot override a method from java.lang.Object
- Solution to this is we will have to provide implementation logic in the class implementing the interfaces.
- Since any class implementing an interface already has Object as a superclass, if we have equals(), hashCode() default methods in the interface, it will become irrelevant. That’s why for better clarity, interfaces are not allowed to have Object default methods.
    
### Functional Interfaces and Lambda Expressions
- java.util.function
- An interface with exactly one abstract method
- @FunctionalInterface annotation - This not only clearly communicates the purpose of this interface, but also allows a compiler to generate an error if the annotated interface does not satisfy the conditions.
- No runtime benefit of Lambda expression - only reduces boilerplate code.
        
            Runnable r = new Runnable(){
        			@Override
        			public void run() {
        				System.out.println("My Runnable");
        			}}; 
        	
        	This can be defined as in single line with the helpo of anonymous classes - 
        	    Runnable r1 = () -> System.out.println("My Runnable");
  
    - **Function**      
        - Represents a function that accepts one argument and produces a result.
        - Methods
            - **default <V> Function<T,V>	andThen(Function<? super R,? extends V> after)**
              Returns a composed function that first applies this function to its input, and then applies the after function to the result.
            - **R	apply(T t)**
              Applies this function to the given argument.
            - **default <V> Function<V,R>	compose(Function<? super V,? extends T> before)**
              Returns a composed function that first applies the before function to its input, and then applies this function to the result.
            - **static <T> Function<T,T>	identity()**
        
        - **Example**
            
                Function<Integer, Integer> times2 = e -> e * 2;
                Function<Integer, Integer> squared = e -> e * e;  
                
                times2.compose(squared).apply(4);  
                // Returns 32
                
                times2.andThen(squared).apply(4);  
                // Returns 64        

            - https://www.deadcoderising.com/2015-09-07-java-8-functional-composition-using-compose-and-andthen/

    - **Consumer**
        - Represents an operation that accepts a single input argument and returns no result.
        - Methods
            - void accept(T t)
            - default Consumer<T> andThen(Consumer<? super T> after)
    - **Supplier**
        - Represents a supplier of results.
        - Methods
            - T get()
            
    - **Predicate** It is an Functional Interface which allows only one Abstract method **test(T t)** within the Interface scope.
            - Methods
                - static  Predicate isEqual(Object targetRef)
                - default Predicate and(Predicate other)
                - default Predicate negate()
                - default Predicate or(Predicate other)
            - Example:
                
                       // Creating predicate 
                         Predicate<Integer> greaterThanTen = (i) -> i > 10; 
                         Predicate<Integer> lowerThanTwenty = (i) -> i < 20;  
                             
                       // Calling Predicate method 
                         boolean result = greaterThanTen.and(lowerThanTwenty).test(15); 
                         boolean result2 = greaterThanTen.and(lowerThanTwenty).negate().test(15); 
                 
                 - https://www.geeksforgeeks.org/java-8-predicate-with-examples/
        			
### Java Stream API
- To avoid external iteration over collections and object containers in Java.. Stream API introduce internal iteration 
facility to operate on data structures in Java 8. 
    Example:
        
        private static int sumIterator(List<Integer> list) {
        	Iterator<Integer> it = list.iterator();
        	int sum = 0;
        	while (it.hasNext()) {
        		int num = it.next();
        		if (num > 10) {
        			sum += num;
        		}
        	}
        	return sum;
        }
        
        Following is the optimized way to write above logic using streams
       
        private static int sumStream(List<Integer> list) {
        	return list.stream().filter(i -> i > 10).mapToInt(i -> i).sum();
        }
         
- It provides several features such as sequential and parallel execution, filtering based on the given criteria, mapping etc.
- We use Streams with following:
    - **Streams with Collections**
        - A collection is an in-memory data structure to hold values. Whereas a Java Stream doesn’t store data, it is computed on-demand.
        - Java Stream doesn’t store data, it operates on the source data structure (collection and array) and produce pipelined data that we can use and perform specific operations.                                                                     
        - lazy-seeking - filtering, mapping, or duplicate removal can be implemented lazily, allowing higher performance and scope for optimization.
        
    - **Streams with Functional Interfaces**
        - Function & Bi-Function - map(), mapToInt(), reduce(), toArray()
        - Predicate and BiPredicate - filter(), anyMatch(), allMatch()
        - Consumer and BiConsumer - peek(), forEach(), forEachOrdered()
        - Supplier - generate(), collect()
        
    - **Streams with Optional Class (Object container)**
        - Java Optional is a container object which may or may not contain a non-null value.
        - Methods - reduce(), min(), max(), findFirst(), findAny()
        
    - **Streams with Splitrator** 
        - used for traversing and partitioning sequences, used for parallel processing as well. 
        - tryAdvance() takes a Consumer that’s used to consume elements of the Spliterator one by one sequentially
        - trySplit() split it into two parts. Then the caller process elements, and finally, the returned instance will process the others, allowing the two to be processed in parallel.
        - estimatedSize() gives us an estimated number of elements
        - hasCharacteristics()
        
- Java Stream Intermediate and Terminal Operations
    - filter(), map() are the most commonly used intermediate operations
    - Terminal operations that returns a result or produce a side effect.
    - Once the terminal method is called on a stream, it consumes the stream and after that we can’t use stream.
    - forEach(), toArray(), min(), max(), findFirst(), anyMatch(), allMatch() are terminal 
     
    - reduce()
        - reduction stream operations allow us to produce one single result from a sequence of elements
        - The Key Concepts: Identity, Accumulator, and Combiner
        - Examples
            
                List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
                int result = numbers
                 .stream()
                 .reduce(0, (subtotal, element) -> subtotal + element);
              
                List<String> letters = Arrays.asList("a", "b", "c", "d", "e");
                String result = letters
                .stream()
                .reduce("", (partialString, element) -> partialString + element);
              
    - peek()
        - This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline
        - peek() can be useful in another scenario: when we want to alter the inner state of an element. 
        - For example:
                
                Stream.of("one", "two", "three", "four")
                  .filter(e -> e.length() > 3)
                  .peek(e -> System.out.println("Filtered value: " + e))
                  .map(String::toUpperCase)
                  .peek(e -> System.out.println("Mapped value: " + e))
                  .collect(Collectors.toList());
                  
                Stream<User> userStream = Stream.of(new User("Alice"), new User("Bob"), new User("Chuck"));
                userStream.peek(u -> u.setName(u.getName().toLowerCase()))
                  .forEach(System.out::println);
                
- Java Stream Short Circuiting Operations
    - An intermediate operation is called short circuiting, if it may produce finite stream for an infinite stream.
      Eg. limit() and skip()
    - A terminal operation is called short circuiting, if it may terminate in finite time for infinite stream.
      Eg.  anyMatch(), allMatch(), noneMatch(), findFirst() and findAny()

    
### Java Time API
- It has some sub-packages **java.time.format** that provides classes to print and parse dates and times and **java.time.zone** provides support for time-zones and their rules.
- DateTimeFormatter
    
### Collection API improvements**
- Iterator default method **forEachRemaining(Consumer action)** to perform the given action for each remaining element until all elements have been processed or the action throws an exception.
- Collection default method **removeIf(Predicate filter)** to remove all of the elements of this collection that satisfy the given predicate.
- Collection **spliterator()** method returning Spliterator instance that can be used to traverse elements sequentially or parallel.
- Map **replaceAll(), compute(), merge()** methods.
- Coolision Resolution in case of HashMap - from Java 8, the linked lists are dynamically replaced with balanced binary search trees in collision resolution after the number of collisions in a given bucket location exceed a certain threshold.This change offers a performance boost, since, in the case of a collision, storage and retrieval happen in O(log n).
    
### Concurrency API improvements
### Java IO improvements
### Miscellaneous Core API improvements

##Java Collections
- Collection Hierarchy
    - Root Interface & Purpose is to play with group of objects.
    - Contains multiple Sub-Interfaces to provide different features.
    - Factors based on they are segregated - Storing, Insertion order, Access, Thread Safety(Sync/Async)
    - All collection interface classes implement Collection interface indirectly through Sub-Interfaces.
    - Mostly Two Constructors
        - No Args - Empty Collection
        - Single arg of type Collection (They can be copied from other collections)
    - UnsupportedOperationException - methods that operate on these collections throws this exception if the operation is not supported by specific collection.
    - Some unchecked exception like NullPointerException or ClassCastException are marked as optional.
    - It is up to each collection to determine its own synchronization policy
    - returns true if and only if this collection contains at least one element e such that
      (o==null ? e==null : o.equals(e)) 
    - Implementations are free to implement optimizations whereby the equals invocation is avoided, for example, by first comparing the hash codes of the two elements. (The hashCode()} specification guarantees that two objects with unequal hash codes cannot be equal.
    - Methods: 
        - size()
        - isEmpty()
        - contains(Object o)
        - iterator()
        - toArray(): The returned array will be "safe" in that no references to it are maintained by this collection.
        - toArray(T[] a)
        
### List 
Follows insertion order. Allows multiple null elements.

**Implementation Classes -** 
- **ArrayList**
    - Maintains Insertion Order
    - Allows Index based access to element
    - Dynamically resizes by 50%
    - Non Synchronised
- **LinkedList**
    - Maintains Insertion Order
    - Implements List and Dequeue (FIFO approach to remove element)
    - Does not allow Index based access to element (Random access) - need to use ListIterator to iterate over elements.
    - Non Synchronised  
- **Vector**
    - Synchronised
    - Dynamically resizes by doubling.
    - Maintains Insertion Order
    - Extends Stack - LIFO
- **ArrayList vs LinkedList** 
    - https://beginnersbook.com/2013/12/difference-between-arraylist-and-linkedlist-in-java/
    - searching
    - Deletion
    - Insert Performance
    - Memory overhead
     
### Set
Only one null allowed.

**Sub Interfaces -**
- **SortedSet**
    - All elements must implements Comparable interface as it stores elements in ascending order.
    
**Implementation Classes -**    
- **HashSet**
    - Implements HashTable
    - Stores only unique elements
    - Unordered collection. 

- **LinkedHashSet**
    - Uses Doubly Linked List
    - Maintains Insertion Order
    
- **TreeSet**
    - Implements SortedSet
    - uses Tree at back end 
    - self balanced Binary Search Tree
    - Stores elements in ascending order.

- **Comparison**
    - https://www.geeksforgeeks.org/difference-and-similarities-between-hashset-linkedhashset-and-treeset-in-java/
    
### Queue

**Sub Interfaces -**
- **DeQue**
    - Double ended queue where elements can be inserted and deleted from both ends.

**Implementation Classes -**   
- **PriorityQueue**
    - Elements are stored and served as per the priority of the element irrespective of their insertion order.
    
- **ArrayDeQue**
    - This is an only implementation of DeQue.
    - In addition, it's a resizable array with no capacity restrictions.

### Map

**Sub Interfaces -**
- **SortedMap**
    - Entries are maintained by an ascending key order
    
**Implementation Classes -**    
- **HashMap**
    - Non Synchronised 
    - Allows only one null key but multiple null values (because keys are always unique)
    - uses hashcode() and equals() method for put and get the elements from map, Also applies collision resolution technique.
    
- **HashTable**
    - Synchronised
    - Does not allow any null key or values
    
- **TreeMap**
    - Implements SortedMap
    - Implicitly implements Red-Black tree implementation
    - Can't store any null key

### Questions
- Why map doesn't extend the Collection interface?
    Both are different in structural manner. Key-pair Vs Object. Add(obj) Vs Put(k,v)

- What is difference between fail-fast and fail-safe Iterators?
     - **fail-fast** It throws ConcurrentModificationException - In case of modification of non sync collections by multiple threads.
     - **fail-safe** It operates on a clone of Collection so doesn't throw an Exception.
     - Here basically iterators iterate over thread safe collections like Vectors, ConcurrentHashMap's etc. are fail-safe others are fail-fast which throws ConcurrentModificationException
     
- Map Vs IMap 

- What is the usage of BlockingQueue

- What is difference between Synchronous collection and concurrent collection?

### Synchronized Vs Concurrent Collections
- **synchronizedCollection()**
- **synchronizedList()**
- **synchronizedMap()**
- **synchronizedSortedMap()**
- **synchronizedSet()**
- **synchronizedSortedSet()**
- **ConcurrentHashMap**
    - Constructor args    
        - Concurrency-Level - It is the number of threads concurrently updating the map. The implementation performs internal sizing to try to accommodate this many threads.
        - Load-Factor - It’s a threshold, used to control resizing.
        - Initial Capacity - Accommodation of a certain number of elements initially provided by the implementation. if the capacity of this map is 10. It means that it can store 10 entries.
        - default initial capacity (16), load factor (0.75) and concurrencyLevel (16).
    - Methods
        - put()
        - putAll()
        - remove()
        - clear() 
        - get()
- **CopyOnWriteArrayList**
    - an enhanced version of ArrayList in which all modifications (add, set, remove, etc) are implemented by making a fresh copy
    - thread-safe version of ArrayList
    - the Iterator of CopyOnWriteArrayList can't perform remove operation otherwise we get Run-time exception saying UnsupportedOperationException. Also Iterator of CopyOnWriteArrayList will never throw ConcurrentModificationException.
    
### Iterators, Spliterators and Enumerations

## Optional in Java
- A container object which may or may not contain a non-null value. If a value is present, **isPresent()** will return **true** and **get()** will return the value.
- Provides following methods
    - of()
    - ofNullable()
    - map()
    - filter()
    - empty()
    - isPresent()
    - ifPresent()
    - orElse()
    - orElseThrow()
    
## Java Annotation
- Java annotations are typically used for the following purposes:
    - Compiler instructions
    - Build-time instructions
    - Runtime instructions
- how to access Java annotations via reflection?
- Accessing Java Annotations via Java Reflection
    - Annotation Elements - default value, use name value if it is single.
    - Annotations used to define annotations
        - @Retention
        - @Target this is related to annotation placement
        - @Inherited
        - @Documented
    - Built-In Java annotations
       - @Deprecated
       - @Override
       - @SuppressWarnings
    - Custom annotations
    
## Java Multithreading
- Deadlock
- Multithreading iterators
- synchronized
- Executor framework
    - task executor
    - executor service

## Generics

##SpringBoot Starter

@SpringBootApplication
- @EnableAutoConfiguration: enable Spring Boot’s auto-configuration mechanism
    - exclude:  @EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
    - We can also control the list of auto-configuration classes to exclude by using the spring.autoconfigure.exclude property.
- @ComponentScan: enable @Component scan on the package where the application is located (see the best practices)
- @Configuration: allow to register extra beans in the context or import additional configuration classes    
   - basePackages
   - basePackageClasses
   - nameGenerator
- What is On Condition Bean?
   - @Conditional annotation
   - https://reflectoring.io/spring-boot-conditionals/
- https://docs.spring.io/spring-boot/docs/2.0.x/reference/html/using-boot-using-springbootapplication-annotation.html

## Spring Security
- @EnableWebSecurity
- extends WebSecurityConfigurerAdapter
- Configure UserDetail Service - Authentiocation
    
    	@Override
    	public void configure(AuthenticationManagerBuilder auth) throws Exception {
    		auth
    				.userDetailsService(userDetailsService)
    				.passwordEncoder(passwordEncoder());
    	}

- Configure HttpSecurity - Authorization
    - Always go from most restrictive to least restrictive url's for authorization in HttpSecurity
    
        public void configure(HttpSecurity http) throws Exception {
    		http.csrf()
    				.disable()
    				.headers().frameOptions().deny()
    				.cacheControl().disable()
    				.httpStrictTransportSecurity().and().xssProtection().block(false);
    	}
    
    - headers()     
        - What are frameOptions ?
            - X-Frame-Options header help in avoiding clickjacking attacks
            - Possible values for this header:
                - DENY – The recommended value for X-Frame-Options and it prevents any domain to frame the content.
                - SAMEORIGIN – This allows only the current site to frame the content.
                - ALLOW-FROM URI – This allows the specified URI to frame the content.
            - clickjacking attack - iframe
        
        - What is cacheControl?
        - What is httpStrictTransportSecurity?
        - What is xssProtection?
    - sessionManagement()
    - authorizeRequests()
        - Note that the matchers are considered in order
    - portMapper()
    - What is csrf?
        - Cross-Site Request Forgery attack is an attack that forces the end user to make an unwanted calls to the web application servers where the end user is already authenticated
        - Adds CSRF support. This is activated by default when using WebSecurityConfigurerAdapter's default constructor. You can disable it using
    - What is cors?
           - We can enable cross-origin resource sharing (CORS) from either in individual methods, controllers or globally with @CrossOrigin annotation.
       
    - logout()
            
            http
                         .authorizeRequests()
                             .antMatchers("/**").hasRole("USER")
                             .and()
                         .formLogin()
                             .and()
                         // sample logout customization
                         .logout()
                             .logout()
                                .deleteCookies("remove")
                                .invalidateHttpSession(false)
                                .logoutUrl("/custom-logout")
                                .logoutSuccessUrl("/logout-success");
    - formLogin()
        - The most basic configuration defaults to automatically generating a login page at the URL "/login", redirecting to "/login?error" for authentication failure.  
    - httpBasic()
    - authenticationProvider()
    - userDetailsService()
    - addFilterAfter()
    - addFilterBefore()
    
- BCryptPasswordEncoder 
    - On the top of SHA-256 Bcrypt is used specially for password encoding 
    
## Spring Data JPA (Hibernate)
- What is Hibernate?
    - ORM - implemented on the to of JPA. 
    - Mappings - JPA Annotations, XML configuration
    - HQL
- Why Hibernate?
    - reduces boiler-plate code
    - provide internal exception handling
    - provides internal transaction management
    - provides multiple levels of caching
    
- Important Interfaces in hibernate
    - **SessionFactory** used to store session object for db connections.One SessionFactory per DB connection like mysql,oracle.
    - **Session** Factory for transaction, it's used to get a physical connection with db. CRUD operation methods.
    - **Transaction** single atomic unit of work

- Important Annotations
    - Entity
    - Table
    - Id
    - EmbeddedId
    - Column
    - 
## JMS

## JAVA AOP

## Maven/Pom.xml
- How to add dependencies 
- Exclude

## SOAP - Simple Object Access Protocol


## Questions
- Why String is Immutable or final in Java?
- What is the difference between poll() and remove() method of Queue interface?
- How do you remove an entry from a Collection? and subsequently what is the difference between the remove() method of Collection and remove() method of Iterator, which one you will use while removing elements during iteration?
- What is the difference between Synchronized Collection and Concurrent Collection?
- What is the difference between Iterator and Enumeration?
- What is transient keyword? When we use this?
Read more: https://javarevisited.blogspot.com/2011/11/collection-interview-questions-answers.html#ixzz6jjH6PNw2

## Discussion With Guru 
- Deadlock
- Multithreaded list iterator
- synchronized

 - Java8
- Collections all with examples
- Executor
- Treeset and treemap
- singleton ,factory patterns
-

- OOPS example
- Abstraction vs Interfaces scenarios
- Immutability with implementation
- Generics

Java8 with examples Journal dev
Consumers, Splitarators, Streams
Three types - Splitarators

## Interview discussion with Akhand (27 Jan 2021)

### Core Java
- What is the difference between Abstract class, Abstraction, Interface?
    These all deals with abstraction, It is a way to achieve abstraction in Java. We are hiding implementation details, and showing functionality. Focus on what instead of how.
    There are levels of abstraction-
    - Abstract class - 
        - abstract methods & other
        - concreate & non concreate methods
        - all non final variables
        - Extends
        - when to use this 
        - can i create instance of abstract class? No - what is the way if i want to create instance - adding main method inside 
        - for default functionality we can use (code reusability) 
        - faster than interfaces
    - Interface -
        - Only abstract methods
        - now in Java 8 it provides  
        - all are final variables 
        - Implements multiple interfaces(multiple inheritance)
        - when to use this - when u have to go with 100% abstraction.
        - loose coupling 
        - slower than abstract class

- Constructors in Java
    - return type void
    - types - 
    - default vs arg constrctor
    - Constructor chaining
        - process of calling one constructor from another constructor in the context of same object
        - In case of inheritance
        - this keyword should be the first line in calling constructor or else it will not work
        - they can be executed in any order
        - we are doing chanining because we want to initialize values
        - Is there any other way we can achieve chaining? Java provides **init block** features {} during class load this is called before constructor.
        
- What are Exception in Java? Exception Hierarchy in Java
    - Exception is nothing but handling the execution flow and handling errors so the flow should not terminate normally 
    - Checked vs Unchecked 
    - Throwable class is root of exception - split into Exception & Error
    - Error - OutOfMemory, StackOverflow
    - Exception - IO, ClassNotFound, RuntimeException - NullPointer, Arithmetic
    - Exception handling - try, catch, finally, throw(throw an exception), throws(declare in a method - so consumer will get informed)
    - Pipe feature in Exception catch
    - remove finally block - and use try with resources - feature of Java 7 - AutoClosable interface 
    - Exception Handling in spring - ControllerAdvice  - extends ResponseEntityExceptionHandler
    - Without exception handling there is no java exception
    - Custom exception handling - how we can include Internationalization in that 
    - ResourceBundleMessageSource - picks property file contains exception messages
    
-  What is HashMap?
    - Implementation of Map - deals with (k,v) pairs 
    - default size 0-15
    - it can be used in non multithreaded environment
    - hashmap keys should be immutable - String is mostly used as a key - Immutable
    - We can create Custom key - should be immutable object - should implement hashcode(), equals() (Not mandatory)
    - Hash-collision - Java7 - LinkedList, Java8 - Binary Search Tree
    - ConcurrentModificationException - solution is SynchronizedMap - but still has performance issue
    - ConcurrentHashMap(initlimit,loadfactor, concurrency) 
    - Rehashing - when hash map reaches to loadFactor then rehashing happens.
    - Resizing of hash map will lead you to race condition 
    
-  What is Immutable class? How will you create?
    - will make class as final so no one can instantiate
    - create variables as final- initialzied during instantiation.
    - expose only getter methods.
    - If want to update then instantiate new object.

- What is transient & volatile
    - transient 
        - variables doesn't take part in serialization & deserialization
        - we can't mark it as final
        - deserialization set's default value for that var's
        
    - volatile 
        - used in multithreaded env. 
        - When we have 2 threads r/w simultaneously. 
        - used where we need most recent value 
        - stored directly in main memory 

- I have an ArrayList of Employee - has 5 vars - I want to sort list by name, age, salary
   - Comparable 
        - provides me single sorting 
        - has drawback is we have to modify existing class by overriding compareTo() 
        - java.lang
        - Collectios.sort(list)
        - here we compare with current instance
        
   - Comparator
        - we need to create custom comparator 
        - implement comparator's compare()
        - java.util
        - Collectios.sort(list, Comparator)
        - here we pass two instances
   
   - all above is Java 7 - java 8 provides with streams           

- What is OOP's concept?
    - Abstraction
    - Encapsulation - coupling method and variables as asingle unit
    - Inheritance
        - Is-A relationship - parent-child - heavily used in OOP 
        - Code reusability, provides overriding
        - levels of inheritance - single, multilevel, hybrid, 
        - There will be compile time error in this issue - diamond problem - resolved using implementing multiple inheritance
    - Polymorphism
        - compile time
            - overloading - static binding - early binding - single class only     
        - runtime  
            - overriding - dynamic binding - late binding - inheritance multiple classes
    

- Collections        
     - Introdudction
     - Iterable root interface 
     - Collection
        - generic collection vs non generic collection
        - List
            - ArrayList - non synchronized
                - ways to remove element 
                    - remove() from List - remove(index), remove(object) 
                    - remove() from Iterator interface remove() - slow performance
                - CopyOnWriteArrayList 
                    - works on clone of object    
            - LinkedList
                - maintains order of insertion
                - non sync
                - faster in case of insertion, deletion
            - Vector
                - sync
                - slow as compare with 
                - LIFO - push, pop
            - where you will use List - insertion, deletion, access
        
        - Queue
            - FIFO
            - PriorityQueue - don't allowed
            
        - Set
            - insertion order followed
            - duplicate not allowed - override 
            - allows only one null - always null at 0th index of set
            - Set initialization - we should create implementation of interface 
            - HashSet
                - always unique
                - Iterator
            - LinkedHashSet
                - maintains insertion order
            - TreeSet
                - ascending order 
            - SortedSet
                - gives natural order of sorting
            - Can u explain me TreeSet, HashSet, 
            - Can u explain me collection with three
            - Can u remove from list? How?
            
     - Map
        - LinkedHashMap vs HashMap vs TreeMap
        - We can't traverse over map - need to convert keySet or entrySet.
        - generic 
            
     - Collections Vs Stream
         - Stream will not modify source it operates on source and 
         - Stoarage is fixed - not finalized in Stream
        
     - Generic comes from Java 5   
    
- Thread
    - States of thread - new , runnable, terminated, waiting, blocked, timed waiting. 
    - Thread Lifecycle
        
        - init()
        - runnable -> Thread.start() - executes run()
            - thread schedular -> time slice given to execute specific thread otherwise thrread will get blocked
        - blocked state - whenever thread is waiting for other source, and thread then it goes to blocked state. from here thread can only go to runnable
        - waiting state - wait() then it goes to runnable
        - timed waiting - 
        - terminated - after task completion
        - Thread Constants
    
    - What is difference between wait, sleep, yeild ?     
        - wait always called on Object class - for the purpose of Interthread Communication
        - sleep, yeild are from Thread class
        - yeild - it releases CPU hold and gives to other - resume from this state only depends on scheduler.
        - sleep resumes after specific time, wait needs 

- String
    - this is immutable, final class provided by Java, lang package
    - It is not an primitive type
    - It is thread safe
    - JVM has special arrangement - String pool in heap
    - How to create String Object?
        - use new String()
        - String litral - all those will go to String pool
    - String litral pool - when we create litral it looks in string pool for specific litral
    - intern() method - used to store objects to string pool
    - Methods
        - loweCase, upperCase, conversion toString
    - Initially switch case not allowed to use String - now from Java 7 8 allowed
    - Can we use Enum in switch case? Yes we can directly use as object instead of values of Enum, For multiple Enum we can have single code logic
    
    - String vs StringBuffer vs StringBuilder
        - all are final classes
        - String is immutable
        - StringBuffer and StringBuilder are mutable
        - StringBuffer thread Safe & StringBuilder is not thread safe
    - String also supports RegEx         
    - String is used as key in HashMap     

- What is boxing & unboxing?     
 
- Singleton Design Patterns
    - When we need single instance of class in JVM then we implement this
    - How to create? 
        - Lazy Initialization 
            - create class
            - mark constructor private
            - create private static member variable of a class
            - create static getInstance method. 
                    static Object getInstance(){
                        if(obj!=null){
                            obj = new ObjClass();
                        }
                    } 
            - add synchronized keyword for thread safety
                - synchronized It is keyword provided by Java. It should be executed single thread at a time. Thread lock is there (helps to avoid race condition)
                
       - Eager Initialization 
            - create private static member variable of a class and initialize it with new Object()
       
       - How to break Singleton Design Pattern?
            - If object is there - we can clone object - this will break Singleton design pattern
            - Object class clone() method 
            - Solution - override clone() and throw the exception CloneNotSupportedException
            - Reflection API can also break - class.forName()  
            - Deserialization will also create new instance  
       
       - Enumeration - these are checked at compile time - we can implement singleton design pattern.
       
       
- Deep Copy & Shallow Copy
              
- JDBC - Core Java    
    - It is an API to provide common platfrom to connect with Relational database.
    - Driver classes - 
        - Jdbc/Odbc, 
        - thin 
            - completely written in Java. 
        - native
    - Classes
        - DriverManager class we need to register driver, provides connection 
            - getConnection()
    - Key Interfaces 
        - Connection - It is an Factory which contains all instances of other objects
            - Database Meta Data
        - ResultSet - stores all the result
            - It's scroll sensitive - single direction - we can't update
            - What is Exhasted Result Set - exception thrown by this intrface
            - Result Set meta data - column count, column name
        - Statement 
            - Used to execute static query. 
            - vulnerable for SQL Injection
            - Used only for DDL commands
            - Gets compiled every time
        - CallableStatement 
            - Used for stored processor, 
            - It has it's own return type, Register out
        - PreparedStatement 
            - Here we can pass parameterized query 
            - Dynamic query building
            - Gets compiled once(pre compiled)
    - It provides batch processing
    - How we do TransactionManagement - programetic transaction managenment commit(), rollback()
    - ACID 
        - Atomic
        - Consistency - Table level lock used to achieve this 
        - Isolation 
        - Durability - we can't change once committed.
         
- Generics    

- Garbage Collections

- Unit Testing    
    
- Struts

- Inner classes

- Deadlock
    
### Spring
- Which Design pattern Spring follows
    - Template
    - Singleton
    - Factory
    
- Scope of Bean in Spring
    - default scope is singleton for spring IOC container level
    - prototype
    - request - always deals with HttpRequest valid in web context
    - session - available at the session
    - global

- SpringMVC flow
    - There are key parties involved - DispatcherServlet, HandlerMapping, Controller, ModelAndView, ViewResolver, View
    - Dispatcher servlet interacts with ->  UrlHandler Mapper to get mapping and then go to -> Controller - > service,repo processing -> returns ModelAndView -> DispatcherServlet takes view -
    - HandlerMapperInterceptor
        - three life cycle operations - afterCompletion(), prehandle(), postHandle()
    - DispacherServlet -> web.xml
    - ViewResolver -> need to see -> servlet.xml -> we configure which type ka view we need to render
        
- Exception Handling in Spring 
    - Explain What approach we told initially - need exception handling in each class.
    - Spring 4 provided Global level exception handler -> @ControllerAdvice, @ExceptionHandler
        
- Have you heard about SpringBoot what is it?
    - Autoconfiguration
    - All hectic configuration
    - Microservice based apps we can easily build
    - Spring Vs Spring Boot
        - Spring is used to develop Enterprise Apps , Spring boot used to develop quick apps with rapid Action development
    - Springboot provides in memory db
    - Here we can create our own pom
    - Dependency Injection is mannual in Spring
    - Drawback depends on scenario - uses unused dependencies - we can exclude
    - specially designed for microservices based application
    
- Can you please do integration of spring with JPA hibernate
    - Springboot autoconfig
    - Spring need to explicitly Configure
    - SpringBoot has it's inbuilt DB server/ for development purpose          
    - We can exclude the artifacts that are unused
    
- Dev tools

- Profiles
    - we can change properties at runtime to override properties at runtime with the help of commands

    
### Web Services


### PL SQL


### JMS


### Database Connection