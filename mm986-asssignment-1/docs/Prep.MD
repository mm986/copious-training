#Interview Preparation
## Java 8
- Refer: https://www.journaldev.com/2389/java-8-features-with-examples
### forEach() method in Iterable interface
- Iterator whose whole purpose is to iterate over and then we have business logic in a loop for each of the elements in the Collection
- Java 8 has introduced forEach method in **java.lang.Iterable** interface so that while writing code we focus on business logic only. 
- forEach method takes **java.util.function.Consumer** object as argument, so it helps in having our business logic at a separate location that we can reuse.
- replaces while(iterator.hasNext()) logic which may cause mismatch during iteration.
        
        //traversing using Iterator
        Iterator<Integer> it = myList.iterator();
        while(it.hasNext()){
        	Integer i = it.next();
        	System.out.println("Iterator Value::"+i);
        }
        		
        //traversing through forEach method of Iterable
        myList.forEach(element -> {
        	System.out.println("forEach element Value::"+element);
        });
        		
### default and static methods in Interfaces
- forEach() is example of this, but it may occur **Diamond Problem** 
- Here implementation class will throw compile time exception as - A default method cannot override a method from java.lang.Object
- Solution to this is we will have to provide implementation logic in the class implementing the interfaces.
- Since any class implementing an interface already has Object as a superclass, if we have equals(), hashCode() default methods in the interface, it will become irrelevant. That’s why for better clarity, interfaces are not allowed to have Object default methods.
- Examples:
    - Iterable -> forEach()
    - Comparable -> comparing(), thenComparing()
    
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
        	
        	This can be defined as in single line with the help of anonymous classes - 
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
    **Example:**
        
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
        - **tryAdvance()** takes a Consumer that’s used to consume elements of the Spliterator one by one sequentially
            
                spliterator.tryAdvance(a -> a.setName(article.getName().concat("- published by @mm")))
                            
        - **trySplit()** split it into two parts. Then the caller process elements, and finally, the returned instance will process the others, allowing the two to be processed in parallel.
                
                public static List<Article> generateElements() {
                    return Stream.generate(() -> new Article("Java"))
                      .limit(35000)
                      .collect(Collectors.toList());
                }
                
                @Test
                public void givenSpliterator_whenAppliedToAListOfArticle_thenSplittedInHalf() {
                    Spliterator<Article> split1 = Executor.generateElements().spliterator(); 
                    Spliterator<Article> split2 = split1.trySplit(); 
                    
                    assertThat(new Task(split1).call()).containsSequence(Executor.generateElements().size() / 2 + ""); 
                    assertThat(new Task(split2).call()).containsSequence(Executor.generateElements().size() / 2 + ""); 
                }
                
        - estimatedSize() gives us an estimated number of elements
        - hasCharacteristics()
           - SIZED – if it's capable of returning an exact number of elements with the estimateSize() method
           - SORTED – if it's iterating through a sorted source
           - SUBSIZED – if we split the instance using a trySplit() method and obtain Spliterators that are SIZED as well
           - CONCURRENT – if source can be safely modified concurrently
           - DISTINCT – if for each pair of encountered elements x, y, !x.equals(y)
           - IMMUTABLE – if elements held by source can't be structurally modified
           - NONNULL – if source holds nulls or not
           - ORDERED – if iterating over an ordered sequence    
- Java Stream Intermediate and Terminal Operations
    - filter(), map() are the most commonly used intermediate operations
    - Terminal operations that returns a result or produce a side effect.
    - Once the terminal method is called on a stream, it consumes the stream and after that we can’t use stream.
    - forEach(), toArray(), min(), max(), findFirst(), anyMatch(), allMatch() are terminal 
     
    - reduce()
        - A reduction is a terminal operation that aggregates a stream into a type or a primitive.
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
    - A terminal operation is called short circuiting, if it may terminate in finite stream for infinite stream.
      Eg.  anyMatch(), allMatch(), noneMatch(), findFirst() and findAny()

    
### Java Time API
- It has some sub-packages **java.time.format** that provides classes to print and parse dates and times and **java.time.zone** provides support for time-zones and their rules.
- DateTimeFormatter
    
### Collection API improvements
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
    - Root Interface is Iterable & Purpose is to play with group of objects.
    - Contains multiple Sub-Interfaces to provide different features.
    - Factors based on they are segregated - Storing, Insertion order, Access, Thread Safety(Sync/Async)
    - All collection interface classes implement Collection interface indirectly through Sub-Interfaces.
    - Mostly Two Constructors
        - No Args - Empty Collection
        - Single arg of type Collection (They can be copied from other collections)
    - UnsupportedOperationException - methods that operate on these collections throws this exception if the operation is not supported by specific collection.
    - Some unchecked exception like NullPointerException or ClassCastException are marked as optional.
    - It is up to each collection to determine its own synchronization polic
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
    - **When to use**
        - If you don’t want to maintain insertion order but want store unique objects	
        - If you want to maintain insertion order of elements then you can use LinkedHashSet	
        - If you want to sort the elements according to some Comparator then use TreeSet
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

# Interview discussion with Akhand (27 Jan 2021)

### Core Java
- What is the difference between Abstract class, Abstraction, Interface?
    - These all deals with abstraction, It is a way to achieve abstraction in Java. We are hiding implementation details, and showing functionality. Focus on what instead of how.
    There are levels of abstraction-
    - Abstract class Vs Interface 
        - **Type of methods**: Interface can have only abstract methods. Abstract class can have abstract and non-abstract methods.
        - **Final Variables**: Variables declared in a Java interface are by default final. An abstract class may contain non-final variables.
        - **Type of variables**: Abstract class can have final, non-final, static and non-static variables. Interface has only static and final variables.
        - **Inheritance vs Abstraction**: A Java interface can be implemented using keyword “implements” and abstract class can be extended using keyword “extends”.
        - **Accessibility of Data Members**: Members of a Java interface are public by default. A Java abstract class can have class members like private, protected, etc.
        - **When to use** Interfaces are used to achieve 100%  abstraction(loose coupling) while Abstract classes are used where we have to implement default functionality(code reusability)
        - **Speed** Abstract classes are faster than interfaces.
        - Can i create instance of abstract class? No -> What is the way if i want to create instance? adding main method inside 
       
- Constructors in Java
    - **Def**: It is a special method used to initialize the objects at the time of instantiation.
    - **Return type**: void
    - **Types**: 
        - Default - 
        - Parameterized 
        - Copy
    - **Constructor chaining**
        - process of calling one constructor from another constructor in the context of same object
        - In case of inheritance
        - this keyword should be the first line in calling constructor or else it will not work
        - they can be executed in any order
        - we are doing chanining because we want to initialize values
        - Is there any other way we can achieve chaining? Java provides **IIB** features {} during class load this is called before constructor.
        
- What is Exception in Java? Exception Hierarchy in Java
    - **Defin** - Exception is nothing but handling the execution flow and handling error's so the flow should not terminate normally 
    - **Hierarchy** - subclasses of java.lang.Throwable class - split into Exception & Error
    - **Error** - Errors are abnormal conditions, Errors belong to unchecked type and mostly occur at runtime. Eg. OutOfMemory, StackOverflow
    - **Exception** - IO, ClassNotFound, RuntimeException - NullPointer, Arithmetic
    - **Checked** -  known to the compiler at compile time. Eg. SQLException,IOException
    - **Unchecked** exceptions like ArrayIndexOutOfBoundException known to the compiler at runtime. Eg. NullPointerException, ArithmeticException. 
    - **Exception Handling** - try, catch, finally, throw(throw an exception), throws(declare in a method - so consumer will get informed)
    - Pipe feature in Exception catch
    - **try with resources** here we can remove finally block - and use this feature of Java 7 - where resources that implements AutoClosable interface are enclosed in that block.  
    - Exception Handling in spring - **ControllerAdvice**  - extends ResponseEntityExceptionHandler
    - Without exception handling there is no java exception
    - **I18n** - Custom exception handling - how we can include Internationalization in that 
        - ResourceBundleMessageSource - picks property file contains exception messages
    
-  What is HashMap?
    - Implementation of Map - deals with (k,v) pairs 
    - default size 0-15
    - it can be used in non multithreaded environment
    - hashmap keys should be immutable - String is mostly used as a key.
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
        
- Serialization & Deserialization -
    - Serializable is a marker interface
    - It is a process of converting java object into byte stream so that we can easily transfer it over network and store in files.
    - SUID - serialVersionUID - here class name, sorted member names, modifiers, interfaces to SHA to compute hash value. This is further used to match with existing obj during deserialization, if not match then it throws InvalidClassException
    - Compatible changes vs Incompatible changes in serializable class 
                  
                  private static final Long serialVersionId = 123456L;
                  Advantage of this statement - If we make any compatible change in serializable class then we don't need to recompile and deploy - it will work
                  If we don't provide this statement then we must have to recompile and redepoly class
                  For any non compatible change we must have to recompile and redeploy the code.      
    - Other ways to make object Serializable
        - use Externalizable -> writeExternal(), readExternal()
        - XML serialization
        - roll-your-own - write content directly via ObjectOutputStream & DataOutputStream 
                  
- I have an ArrayList of Employee - has 5 vars - I want to sort list by name, age, salary
   - Comparable 
        - A comparable object is capable of comparing itself with another object. 
        - The class itself must implements the java.lang.Comparable interface to compare its instances. 
        - provides me single sorting 
        - has drawback is we have to modify existing class by overriding compareTo() 
        - Collectios.sort(list)
        - here we compare with current instance
        
   - Comparator
        - we need to create custom comparator 
        - implement comparator's compare()
        - java.util
        - Collectios.sort(list, Comparator)
        - here we pass two instances
   
   - all above is Java 7 - java 8 provides with streams           

- OOP's in Java                                                                                                                                                                                                                                                                                                                                                                                                        - What is OOP's concept?
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
   - Shallow copy copies the existing references of object - we can do with default clone()  method  
   - Deep copy we create new instance and then copy the values.
            
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

- How will you map your Query result's in spring? RowMappers in JDBC
    - this is with help of RowMapper
    - RowMapper is an Interface which allows us to map the rows to user defined entities. (fetches the records from DB and contains Query())
    - It iterates ResultSet and adds it to collecction
    - What is the advantage over ResultSet?
        - Select * from Students
            ResultSet needs to iterate and add to collection manually
            We can create RowMapper and override **mapRows()** and will map with entity
            RowMapper used with JDBCTemplate         
- Generics    

- Garbage Collections
    -  
    Three sections 
- Unit Testing    
    
- Inner classes

- Deadlock
    
### Spring
- What is Spring Application Container?
    - Web container responsible for managing the lifecycle of servlets.
    - Spring Application Container is IOC container that manages bean life cycle and perform dependency injection.  
    - IoC container (Inversion of Control) which receives metadata from either an XML file, Java annotations, or Java code and works accordingly
    - BeanFactory and ApplicationContext are two different IOC containers provided by Spring. 
    - **BeanFactory** 
        - Basic Interface defined in org.springframework.beans.factory.BeanFactory which provides the basic support for Dependency Injection.
        - Lazy Loading: Initially only the BeanFactory is initialized, The beans will be loaded only when we explicitly call the getBean() method.(Lightweight container)
        - Required Manual registration of processors: BeanFactoryPostProcessor, BeanPostProcessor 
        - Methods: getBean(), containsBean(), isSingleton(), isPrototype() 
        - BeanFactory only supports two scopes — Singleton and Prototype.
        - XML Configuration: XmlBeanFactory is the implementation class for the BeanFactory interface.
        
                <bean id="employee" class="com.baeldung.beanfactory.Employee">
                    <constructor-arg name="name" value="Hello! My name is Java"/>
                    <constructor-arg name="age" value="18"/>
                </bean>
                
                 BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("beans.xml"));
                 Employee emp = (Employee) factory.getBean("employee");
                
    - **ApplicationContext**
        - It is the advanced Spring container and is built on top of the BeanFactory interface.
        - Eager-initialization hence loads all beans at startup. 
        - It provides: 
            - It extends the MessageSource interface, therefore provides internationalization functionality.
                
                    public void testInternationalization() {
                        MessageSource resources = new ClassPathXmlApplicationContext("classpathxmlapplicationcontext-internationalization.xml");
                    
                        String enHello = resources.getMessage("hello", null, "Default", Locale.ENGLISH);
                        String enYou = resources.getMessage("you", null, Locale.ENGLISH);
                        String enThanks = resources.getMessage("thanks", new Object[] { enYou }, Locale.ENGLISH);
                     
                        assertThat(enHello, equalTo("hello"));
                        assertThat(enThanks, equalTo("thank you"));
                    
                        String chHello = resources.getMessage("hello", null, "Default", Locale.SIMPLIFIED_CHINESE);
                        String chYou = resources.getMessage("you", null, Locale.SIMPLIFIED_CHINESE);
                        String chThanks = resources.getMessage("thanks", new Object[] { chYou }, Locale.SIMPLIFIED_CHINESE);
                     
                        assertThat(chHello, equalTo("你好"));
                        assertThat(chThanks, equalTo("谢谢你"));
                    }
                
            - event publication functionality
            - annotation-based dependency injection
            - easy integration with Spring AOP features
            - Automatic Registration of BeanFactoryPostProcessor and BeanPostProcessor
        - supports almost all types of bean scope    
        - The ClassPathXmlApplicationContext class is the implementation class of ApplicationContext interface.
        - Shut down the container gracefully when the application is shut down,
        
    - @Configuration - Annotation Config
    - @Component is in spring ?
        - It takes part in Dependency Injection
        - There is not difference in @Component @Service @Repository all are same but just streotypes to mark class as component
    
    - What is Component Scan?
       - Spring boot - @SpringBootApplication
            - no need to explicitly scan - scans all sub packages
            - used in case of external package scan 
        
       - Spring 
            - Where we create application context - provided there as component scan package.
                
- What is IOC and Dependency Injection in Spring?
    - IOC is mechanism by which the control of objects or portions of a program is transfered to a container or framework.
    - It helps in decoupling the execution of a task from its implementation
    - Inversion of Control can be achieved through various mechanisms such as: 
        - Strategy design pattern 
        - Service Locator pattern 
        - Factory pattern
        - Dependency Injection (DI).        
            - Constructor 
                
                    @Configuration
                    public class AppConfig {
                    
                        @Bean
                        public Item item1() {
                            return new ItemImpl1();
                        }
                    
                        @Bean
                        public Store store() {
                            return new Store(item1());
                        }
                    }
                    
                   XML Based:
                    <bean id="item1" class="org.baeldung.store.ItemImpl1" /> 
                    <bean id="store" class="org.baeldung.store.Store"> 
                        <constructor-arg type="ItemImpl1" index="0" name="item" ref="item1" /> 
                    </bean>
            - Setter - pass object in setter
                    
                    @Bean
                    public Store store() {
                        Store store = new Store();
                        store.setItem(item1());
                        return store;
                    }
                    
                    <bean id="store" class="org.baeldung.store.Store">
                        <property name="item" ref="item1" />
                    </bean>
                    
            - Field - @Autowiring - byName, byType - it will throw exception in case of duplicate (@Qualifier) , constructor, default
                   
                   Example:
                     @Bean(autowire = Autowire.BY_TYPE)
                   
                   byType:                
                    public class Store {
                        @Autowired
                        private Item item; 
                    }
                   
                   byName: 
                    public class Store {
                        
                        @Autowired
                        @Qualifier("item1")
                        private Item item;
                    }
            
            - Lazy Initialized Beans
                - By default, the container creates and configures all singleton beans during initialization. To avoid this, you can use the lazy-init attribute with value true on the bean configuration:
                    
                        <bean id="item1" class="org.baeldung.store.ItemImpl1" lazy-init="true" />       
                                
    - Why we get NoQualifiedBeanPresent?
        - If dependency bean is not picked by container then it shows
         
- Which Design pattern Spring follows?
    - **Singleton**
        - one instance of an object exists per application.
        - useful when managing shared resources or providing cross-cutting services, such as logging.
        - Singleton Beans - Spring restricts a singleton to one object per Spring IoC container
        - an application can have more than one Spring container
        - By default, Spring creates all beans as singletons.
        - We can create separate instances of the bean by changing the bean scope from singleton to prototype using the 
                
              @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) annotation.
              @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON) annotation.
        
    - **Factory**
        - It provides a factory class with an abstract method for creating the desired object. 
        - Spring treats a IOC bean container as a factory that produces beans.
        - Each of the getBean methods is considered a factory method, which returns a bean matching the criteria supplied to the method, like the bean's type and name.
            
                 getBean(Class<T> requiredType);
                 getBean(Class<T> requiredType, Object... args);
                 getBean(String name);
                    
        - BeanFactory and the ApplicationContext interface are the (IOC containers) examples of Factory Design Pattern
        
    - **Proxy**
        - the proxy pattern is a technique that allows one object — the proxy — to control access to another object — the subject or service
        - Spring APO follows proxy pattern to proxy/wrap an existing class and get control of execution.
        - We see this approach when using transactions:
            
                @Transactional
                public Book create(String author) {
                    System.out.println(repository.getClass().getName());
                    return repository.create(author);
                }
        - Generally, Spring uses two types of proxies:
           - CGLib Proxies – Used when proxying classes
           - JDK Dynamic Proxies – Used when proxying interfaces
           
    - **Template**
        - The template method pattern is a technique that defines the steps required for some action, implementing the boilerplate steps, and leaving the customizable steps as abstract.
        - Following is the examples:
                   
                  Example:    
                    public abstract DatabaseQuery {
                    
                        public void execute() {
                            Connection connection = createConnection();
                            executeQuery(connection);
                            closeConnection(connection);
                        } 
                    
                        protected Connection createConnection() {
                            // Connect to database...
                        }
                    
                        protected void closeConnection(Connection connection) {
                            // Close connection...
                        }
                    
                        protected abstract void executeQuery(Connection connection);
                    }
                                
            - JdbcTemplate
                    
                    public class JdbcTemplate {
                    
                        public <T> T query(final String sql, final ResultSetExtractor<T> rse) throws DataAccessException {
                            // Execute query...
                        }
                    
                        // Other methods...
                    }
             
                - Template provided a **callback method** is a method that allows the subject to signal to the client that some desired action has completed.
                - Spring further reduces boilerplate code by creating more specific callback interfaces.
                    
                        @FunctionalInterface
                        public interface RowMapper<T> {
                            T mapRow(ResultSet rs, int rowNum) throws SQLException;
                        }
                        
                        // Implementation on RowMapper
                        public class BookRowMapper implements RowMapper<Book> {
                        
                            @Override
                            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                        
                                Book book = new Book();
                                
                                book.setId(rs.getLong("id"));
                                book.setTitle(rs.getString("title"));
                                book.setAuthor(rs.getString("author"));
                                
                                return book;
                            }
                        }
                        
            - JmsTemplate
            - TransactionTemplate
        
- Spring Bean Scope 
    - Spring framework defines 6 types of scopes:
        - default scope is singleton for spring IOC container level
            
                @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
                <bean id="personSingleton" class="org.baeldung.scopes.Person" scope="singleton"/>
                
        - prototype - one per call to container 
                
                @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
                
        -  web-aware scopes
            - request - always deals with HttpRequest valid in web context
                
                    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
                    or @RequestScope instead of above
                
            - session - available at the session
                
                    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS
                    @SessionScope
                    
            - application
                    
                    @Scope(value = WebApplicationContext.SCOPE_APPLICATION, proxyMode = ScopedProxyMode.TARGET_CLASS)
                    @ApplicationScope
                    
            - websocket - WebSocket-scoped beans when first accessed are stored in the WebSocket session attributes.
            
                    @Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
                    
- Spring MVC
    - Flow & Components
        - There are key parties involved - DispatcherServlet, HandlerMapping, Controller, ModelAndView, ViewResolver, View
        - Dispatcher servlet interacts with ->  UrlHandlerMapper to get mapping and then go to -> Controller - > service,repo processing -> returns ModelAndView -> DispatcherServlet takes view -
        - HandlerMapperInterceptor
            - three life cycle operations - afterCompletion(), prehandle(), postHandle()
        - DispacherServlet -> web.xml
        - ViewResolver -> need to see -> servlet.xml -> we configure which type of view we need to render
    - Configuration
        - **@EnableWebMvc** annotation - This will set up the basic support we need for an MVC project, such as:
            - registering controllers and mappings 
            - type converters
            - validation support 
            - message converters and exception handling.
       - **WebMvcConfigurer**: If we want to customize this configuration, we need to implement this interface.
       
                @EnableWebMvc
                @Configuration
                public class WebConfig implements WebMvcConfigurer {
                
                   @Override
                   public void addViewControllers(ViewControllerRegistry registry) {
                      registry.addViewController("/").setViewName("index");
                   }
                
                   @Bean
                   public ViewResolver viewResolver() {
                      InternalResourceViewResolver bean = new InternalResourceViewResolver();
                
                      bean.setViewClass(JstlView.class);
                      bean.setPrefix("/WEB-INF/view/");
                      bean.setSuffix(".jsp");
                
                      return bean;
                   }
                }
       - **ViewControllerRegistry** : we can register view controllers that create a direct mapping between the URL and the view name
       
       - **XML Config** 
            
                <context:component-scan base-package="com.baeldung.web.controller" />
                <mvc:annotation-driven />    
                
                <bean id="viewResolver" 
                      class="org.springframework.web.servlet.view.InternalResourceViewResolver">
                        <property name="prefix" value="/WEB-INF/view/" />
                        <property name="suffix" value=".jsp" />
                    </bean>
                
                    <mvc:view-controller path="/" view-name="index" />
                
                </beans>   
       - With SpringBoot no additional config is required if we add **spring-boot-starter-parent** dependency and entry point for spring boot application.
       
                @SpringBootApplication
                public class Application {
                    public static void main(String[] args) {
                        SpringApplication.run(Application.class, args);
                    }
                }        
       
- Exception Handling in Spring 
    - Explain What approach we told initially - need exception handling in each class.
            
            try{
                //Logic
            }catch(Exception ex){
                //Handle
            } finally{
                //Finally  close resources.
            }
    - Before Spring 3.2 two main approaches to handling exceptions in a Spring MVC application were:
    - **@ExceptionHandler** : 
         - @ExceptionHandler annotated method is only active for that particular Controller, not globally for the entire application.    
    
    - **HandlerExceptionResolver** uniform exception handling mechanism
       - ExceptionHandlerExceptionResolver, DefaultHandlerExceptionResolver, ResponseStatusExceptionResolver, SimpleMappingExceptionResolver 
       - It's used to resolve standard Spring exceptions to their corresponding HTTP Status Codes, namely Client error 4xx and Server error 5xx status codes
       - it's enabled by default in the DispatcherServlet.
       - limitation is that it doesn't set anything to the body of the Response
    
    - **@ControllerAdvice**
        - Since 3.2, we've had the @ControllerAdvice annotation
            
              @ControllerAdvice
              public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
                 
                   @ExceptionHandler(value = {InvalidOrderException.class})
                   private ResponseEntity<Object> handle(InvalidOrderException exception, WebRequest webRequest) {
                       return handleExceptionInternal(exception,
                                 new GenericResponse<>(false,
                                         ExceptionCodeEnum.INVALID_ORDER.getHttpStatus().name(),
                                         new ErrorResponse(ExceptionCodeEnum.INVALID_ORDER.getMessage(),exception.getErrorDetails())
                                 ),
                                 new HttpHeaders(),
                                 ExceptionCodeEnum.INVALID_ORDER.getHttpStatus(),
                                 webRequest
                       );
                   }
              }
    
        - The actual mechanism is extremely simple but also very flexible:
            - It gives us full control over the body of the response as well as the status code.
            - It provides mapping of several exceptions to the same method, to be handled together.
            - It makes good use of the newer RESTful ResposeEntity response.
    
    - **ResponseStatusException**    
        - Spring 5 introduces the ResponseStatusException class.
        - We can create an instance of it providing an HttpStatus and optionally a reason and a cause:
            
                @GetMapping(value = "/{id}")
                public Foo findById(@PathVariable("id") Long id, HttpServletResponse response) {
                    try{
                        //logic
                     }
                    catch (MyResourceNotFoundException exc) {
                         throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Foo Not Found", exc);
                    }
                }
        - **Benefits**
            - Excellent for prototyping: We can implement a basic solution quite fast.
            - One type, multiple status codes: One exception type can lead to multiple different responses. This reduces tight coupling compared to the @ExceptionHandler.
            - We won't have to create as many custom exception classes.
            - We have more control over exception handling since the exceptions can be created programmatically.
    
    - **Handle the Access Denied in Spring Security**
        - The XML configuration:
            
                <http>
                    <intercept-url pattern="/admin/*" access="hasAnyRole('ROLE_ADMIN')"/>   
                    ... 
                    <access-denied-handler error-page="/my-error-page" />
                </http>
                
        - Java configuration:
            
                @Override
                protected void configure(HttpSecurity http) throws Exception {
                    http.authorizeRequests()
                        .antMatchers("/admin/*").hasAnyRole("ROLE_ADMIN")
                        ...
                        .and()
                        .exceptionHandling().accessDeniedPage("/my-error-page");
                }
        
        - Custom AccessDeniedHandler:  
        
                @Component
                public class CustomAccessDeniedHandler implements AccessDeniedHandler {
                
                    @Override
                    public void handle
                      (HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) 
                      throws IOException, ServletException {
                        response.sendRedirect("/my-error-page");
                    }
                }      
                
                @Autowired
                private CustomAccessDeniedHandler accessDeniedHandler;
                
                @Override
                protected void configure(HttpSecurity http) throws Exception {
                    http.authorizeRequests()
                        .antMatchers("/admin/*").hasAnyRole("ROLE_ADMIN")
                        ...
                        .and()
                        .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                }     
                
    - **Spring Boot Support**
        - Spring boot provides **Whitelabel Error Page** in response and a JSON response for RESTful
            
                {
                    "timestamp": "2019-01-17T16:12:45.977+0000",
                    "status": 500,
                    "error": "Internal Server Error",
                    "message": "Error processing the request!",
                    "path": "/my-endpoint-with-exceptions"
                }
                
        - Spring Boot allows configuring these features with properties:
            - server.error.whitelabel.enabled:
            - server.error.include-stacktrace:

- Spring Profiles
    - allowing us to map our beans to different profiles — for example, dev, test, and prod.
    - we can change properties at runtime to override properties at runtime with the help of commands
    - **@Profile** annotation — we are mapping the bean to that particular profile
        
           @Component
           @Profile("dev")
           public class DevDatasourceConfig
    
    - profile names can also be prefixed with a NOT operator, e.g. !dev to exclude them from a profile.
            
           @Component
           @Profile("!dev")
           public class DevDatasourceConfig
    
    - **Set Profiles**
        - Programmatically via WebApplicationInitializer Interface
            
                @Configuration
                public class MyWebApplicationInitializer implements WebApplicationInitializer {
                    @Override
                    public void onStartup(ServletContext servletContext) throws ServletException {
                        servletContext.setInitParameter("spring.profiles.active", "dev");
                    }
                }
        - Programmatically via ConfigurableEnvironment
                
                @Autowired
                private ConfigurableEnvironment env;
                ...
                env.setActiveProfiles("someProfile");
        
        - Context Parameter in web.xml
        
                <context-param>
                    <param-name>spring.profiles.active</param-name>
                    <param-value>dev</param-value>
                </context-param>
                
        - JVM System Parameter
                
                -Dspring.profiles.active=dev
                
        - Environment Variable
                
                export spring_profiles_active=dev
        
        - Maven Profile
                
               <properties>
                   <spring.profiles.active>dev</spring.profiles.active>
               </properties>
        
        - application.properties               
                      
                spring.profiles.active=local
                
        - @ActiveProfile in Tests
        
    - **Get Profiles**
        
             @Value("${spring.profiles.active:}")
             private String activeProfiles;
                                                                                                              
- SpringBoot what is it?
    - All hectic configuration
    - Microservice based apps we can easily build
    - Spring Vs Spring Boot
            - Spring is used to develop Enterprise Apps, Spring boot used to develop quick apps with rapid Action development
    - Springboot provides in memory db
    - Here we can create our own pom
    - Dependency Injection is manual in Spring
    - Drawback depends on scenario - uses unused dependencies - we can exclude
    - specially designed for microservices based application

    - Enable Autoconfiguration @SpringBootApplication - We use this annotation to mark the main class of a Spring Boot application
            
            @SpringBootApplication
            public class Application {
                public static void main(String[] args) {
                    SpringApplication.run(Application.class, args);
                }
            }
            
       - @SpringBootApplication encapsulates @Configuration, @EnableAutoConfiguration, and @ComponentScan annotations with their default attributes.
            - **@Configuration** – which marks the class as a source of bean definitions
            - **@EnableAutoConfiguration** – which tells the framework to add beans based on the dependencies on the classpath automatically
            - **@ComponentScan** – which scans for other configurations and beans in the same package as the Application class or below     
       
       - Conditional Configuration
            - @ConditionalOnClass and @ConditionalOnMissingClass
            
                    @Configuration
                    @ConditionalOnClass(DataSource.class)
                    class MySQLAutoconfiguration {
                        //...
                    }
       
            - @ConditionalOnBean and @ConditionalOnMissingBean - define conditions based on the presence or absence of a specific bean:
                
                    @Bean
                    @ConditionalOnBean(name = "dataSource")
                    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
                        // ...
                    }
                
            -  @ConditionalOnProperty
                
                    @Bean
                    @ConditionalOnProperty(name = "usemysql", havingValue = "local")
                    DataSource dataSource() {
                        // ...
                    }
       
            - @ConditionalOnResource
                
                    @ConditionalOnResource(resources = "classpath:mysql.properties")
                    Properties additionalProperties() {
                        // ...
                    }
       
    - Disable Autoconfiguration - exclude the auto-configuration from being loaded
        - Disable from annotation
        
                @Configuration
                @SpringBootApplication(exclude={MySQLAutoconfiguration.class})
                public class AutoconfigurationApplication {
                    //...
                }
        
        - Disable using application.properties
        
                spring.autoconfigure.exclude=com.baeldung.autoconfiguration.MySQLAutoconfiguration
           
        - Disable by excluding dependencies from pom.xml
                
                Example:
                        <dependency>
                            <groupId>org.hibernate</groupId>
                            <artifactId>hibernate-ehcache</artifactId>
                            <version>${hibernate.ehcache.version}</version>
                            <exclusions>
                                <exclusion>
                                    <groupId>org.jboss.logging</groupId>
                                    <artifactId>jboss-logging</artifactId>
                                </exclusion>
                            </exclusions>
                        </dependency>

- Spring Security
    - Which Security framework you have worked upon? JWT
        - Introduction of JWT
            - It is an open standard for securely transmitting information between parties as a JSON object.
            - information can be verified and trusted because it is digitally signed.
            - JWTs can be signed using a secret (with the HMAC algorithm) or a public/private key pair using RSA or ECDSA.
        
        - Why we are using JWT? 
            token - set of values - encrypted based on HMAC
            
        - When to use?
            - Authorization: This is the most common scenario for using JWT
            - Information Exchange: JSON Web Tokens are a good way of securely transmitting information between parties.      
        - Add dependencies
            spring-boot-starter-security
      
        - Major components in Configuration
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
            
    - How you will maintain roles in your controller?
        - Using spring security @PreAutorized
        - This is provided by spring security which we can use it at method level or controller level
        - To enable this we need to @EnableGlobalSpringSecurity
        - This accepts SPEL expression - hasRoles - all should be appended by Role_
            
        - **SecurityContext**
                - Extract SecurityContextHolder -> Authentication
                
        - **UserDetailsServie**
                - Extract from UserDetailsService 
                
        - **HttpServletRequest**
                by extracting isUserInRole(), Principle   

- Spring JPA/Hibernate
    - Can you please do integration of spring with JPA hibernate?
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
        
    - Dependencies
 
            spring-boot-starter
            spring-boot-starter-data-jpa
          
    - **Springboot**
        - **entityManagerFactory** - Spring Boot configures Hibernate as the default JPA provider, so it's no longer necessary to define the entityManagerFactory bean unless we want to customize it.
        - **dataSource** 
            - Spring Boot can also auto-configure the dataSource bean, depending on the database we're using
                - Refer Spring section for DataSource configuration with program and xml both 
            - To configure the data source using a properties file, we have to set properties prefixed with spring.datasource:
            
                    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
                    spring.datasource.username=mysqluser
                    spring.datasource.password=mysqlpass
                    spring.datasource.url=jdbc:mysql://localhost:3306/myDb?createDatabaseIfNotExist=true
            
            - SpringBoot has it's inbuilt DB server/ for development purpose
                              
    - **Spring**
        - if we have a standard Spring project, then we need more explicit configuration, using either Java or XML
        - Programetic Configuration
                
                @Configuration
                @EnableTransactionManagement
                public class PersistenceJPAConfig{
                
                   @Bean
                   public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
                      LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
                      JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
                      
                      em.setDataSource(dataSource());
                      em.setPackagesToScan(new String[] { "com.mm.model" });
                      em.setJpaVendorAdapter(vendorAdapter);
                      em.setJpaProperties(additionalProperties());
                
                      return em;
                   }
                   
                   @Bean
                   public DataSource dataSource(){
                       DriverManagerDataSource dataSource = new DriverManagerDataSource();
                       dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                       dataSource.setUrl("jdbc:mysql://localhost:3306/spring_jpa");
                       dataSource.setUsername( "user" );
                       dataSource.setPassword( "hydrosigma" );
                       return dataSource;
                   }
                    
                   @Bean
                   public PlatformTransactionManager transactionManager() {
                       JpaTransactionManager transactionManager = new JpaTransactionManager();
                       transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
                   
                       return transactionManager;
                   }
                   
                   @Bean
                   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
                       return new PersistenceExceptionTranslationPostProcessor();
                   }
                   
                   Properties additionalProperties() {
                       Properties properties = new Properties();
                       properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
                       properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
                          
                       return properties;
                   }
                    
                }
        - Configuration With XML
                
                <bean id="myEmf" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
                    <property name="dataSource" ref="dataSource" />
                    <property name="packagesToScan" value="com.mm.persistence.model" />
                    <property name="jpaVendorAdapter">
                        <bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter" />
                    </property>
                    <property name="jpaProperties">
                        <props>
                            <prop key="hibernate.hbm2ddl.auto">create-drop</prop>
                            <prop key="hibernate.dialect">org.hibernate.dialect.MySQL5Dialect</prop>
                        </props>
                    </property>
                </bean>
                
                <bean id="dataSource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
                    <property name="driverClassName" value="com.mysql.cj.jdbc.Driver" />
                    <property name="url" value="jdbc:mysql://localhost:3306/spring_jpa" />
                    <property name="username" value="tutorialuser" />
                    <property name="password" value="tutorialmy5ql" />
                </bean>
                
                <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
                    <property name="entityManagerFactory" ref="myEmf" />
                </bean>
                <tx:annotation-driven />
                
                <bean id="persistenceExceptionTranslationPostProcessor" class="org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor" />
    - Hibernate Mappings
        - OneToOne
            - **Example: User <--> Address**
                - **User Entity**    
                       
                        @Entity
                        @Table(name = "users")
                        public class User {
                            
                            @Id
                            @GeneratedValue(strategy = GenerationType.AUTO)
                            @Column(name = "id")
                            private Long id;
                            //... 
                        
                            @OneToOne(cascade = CascadeType.ALL)
                            @JoinColumn(name = "address_id", referencedColumnName = "id")
                            private Address address;
                        
                            // ... getters and setters
                        }               
                - **@OneToOne** we place the  annotation on the related entity field
                - **@JoinColumn** 
                    - we need to place this annotation to configure the name of the column in the users table that maps to the primary key in the address table                     
                    - note in the next entity that we won't use the @JoinColumn annotation there. This is because we only need it on the owning side of the foreign key relationship. 
                    - Simply put, whoever owns the foreign key column gets the @JoinColumn annotation.
            - **Address entity**
                - We also need to place the @OneToOne annotation here too. That's because this is a bidirectional relationship. 
                - The address side of the relationship is called the **non-owning** side. 
                
                        @Entity
                        @Table(name = "address")
                        public class Address {
                        
                            @Id
                            @GeneratedValue(strategy = GenerationType.AUTO)
                            @Column(name = "id")
                            private Long id;
                            //...
                        
                            @OneToOne(mappedBy = "address")
                            private User user;
                        
                            //... getters and setters
                        }
        - OneToMany
            - one-to-many mapping means that one row in a table is mapped to multiple rows in another table.
            - **Example: Cart <--> Items**
                - One cart can have many items, so here we have a one-to-many mapping.
                - The way this works at the database level is we have a cart_id as a primary key in the cart table and also a cart_id as a foreign key in items.
                - **Cart Entity**
                        
                       @Entity
                       @Table(name="CART")
                       public class Cart {
                       
                           //...
                       
                           @OneToMany(mappedBy="cart")
                           private Set<Items> items;
                       	
                           // getters and setters
                       }
                - **Item Entity**
                        
                        @Entity
                        @Table(name="ITEMS")
                        public class Items {
                            
                            //...
                            @ManyToOne
                            @JoinColumn(name="cart_id", nullable=false)
                            private Cart cart;
                        
                            public Items() {}
                            
                            // getters and setters
                        }
                               
                - We can also add a reference to Cart in Items using @ManyToOne, making this a bidirectional relationship. Bidirectional means that we are able to access items from carts, and also carts from items.             
                - The mappedBy property is what we use to tell Hibernate which variable we are using to represent the parent class in our child class.
                - As stated in the JPA specification under section 2.9, it's a good practice to mark many-to-one side as the owning side.
                - By including the mappedBy attribute in the Cart class, we mark it as the inverse side.
                - At the same time, we also annotate the Items.cart field with @ManyToOne, making Items the owning side.
        
        - ManyToMany
            - **Example: Employee <--> Project**
                - both the Employee class and Project classes refer to one another, which means that the association between them is bidirectional.    
                    @Entity
                    @Table(name = "Employee")
                    public class Employee { 
                        // ...
                     
                        @ManyToMany(cascade = { CascadeType.ALL })
                        @JoinTable(
                            name = "Employee_Project", 
                            joinColumns = { @JoinColumn(name = "employee_id") }, 
                            inverseJoinColumns = { @JoinColumn(name = "project_id") }
                        )
                        Set<Project> projects = new HashSet<>();
                       
                        // standard constructor/getters/setters
                    }
                    
                    @Entity
                    @Table(name = "Project")
                    public class Project {    
                        // ...  
                     
                        @ManyToMany(mappedBy = "projects")
                        private Set<Employee> employees = new HashSet<>();
                        
                        // standard constructors/getters/setters   
                    }
                - In our example, the owning side is Employee so the join table is specified on the owning side by using the @JoinTable annotatio
        - Fetch Type
            - **Eager Loading**
                - In eager loading strategy, if we load the Table data, it will also load up all tables associated with it and will store it in a memory.
                    
                        @OneToMany(fetch = FetchType.EAGER)
                        
            - **Lazy Loading**
                - Hibernate applies lazy loading approach on entities and associations by providing a proxy implementation of classes.
                
                        @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")            
                - Initial load time much smaller
                - Less memory consumption        
                
        - Cascade Type   
            - **Cascade.ALL** propagates all operations — including Hibernate-specific ones — from a parent to a child entity.
            - **CascadeType.PERSIST** propagates the persist operation from a parent to a child entity. 
            - **CascadeType.MERGE** propagates the merge operation from a parent to a child entity    
            - **CascadeType.REMOVE** propagates the remove operation from parent to child entity. Similar to JPA's CascadeType.REMOVE, we have CascadeType.DELETE, which is specific to Hibernate.
            - **CascaseType.DETACH** the child entity will also get removed from the persistent context.
            - **CascadeType.LOCK** re-attaches the entity and its associated child entity with the persistent context again.
            - **CascadeType.REFRESH** the child entity also gets reloaded from the database whenever the parent entity is refreshed.
            - **CascadeType.REPLICATE** a sync operation also propagates to child entities whenever performed on the parent entity. The replicate operation is used when we have more than one data source, and we want the data in sync
            - **CascadeType.SAVE_UPDATE** propagates the same operation to the associated child entity.
    - Hibernate Named Queries
        - **Named Query** 
            - @NamedQuery annotation is attached to exactly one entity class or mapped superclass.
            - As the scope of named queries is the entire persistence unit, we should select the query name carefully to avoid a collision
                
                    @org.hibernate.annotations.NamedQuery(name = "DeptEmployee_findByEmployeeNumber", 
                      query = "from DeptEmployee where employeeNumber = :employeeNo")
                  
            - @NamedQueries annotation to group
                    
                    @org.hibernate.annotations.NamedQueries({
                        @org.hibernate.annotations.NamedQuery(name = "DeptEmployee_FindByEmployeeNumber", 
                          query = "from DeptEmployee where employeeNumber = :employeeNo"),
                        @org.hibernate.annotations.NamedQuery(name = "DeptEmployee_FindAllByDesgination", 
                          query = "from DeptEmployee where designation = :designation"),
                        @org.hibernate.annotations.NamedQuery(name = "DeptEmployee_UpdateEmployeeDepartment", 
                          query = "Update DeptEmployee set department = :newDepartment where employeeNumber = :employeeNo"),
                    ...
                    })
            - HQL query can be a DML-style operation. So, it doesn't need to be a select statement only.
            - Query Features:
               - timeout - query timeout
               - fetchSize - fetch size limit
               - cacheable – whether the query (results) is cacheable or not
               - cacheMode – the cache mode used for this query; this can be one of GET, IGNORE, NORMAL, PUT, or REFRESH
               - cacheRegion – if the query results are cacheable, name the query cache region to use
               - comment – a comment added to the generated SQL query; targetted for DBAs
               - flushMode – the flush mode for this query, one of ALWAYS, AUTO, COMMIT, MANUAL, or PERSISTENCE_CONTEXT
            - Using the Named Query
                    
                    Query<DeptEmployee> query = session.createNamedQuery("DeptEmployee_FindByEmployeeNumber", 
                      DeptEmployee.class);
                    query.setParameter("employeeNo", "001");
                    DeptEmployee result = query.getSingleResult();
                               
        - **Named Native Query**
            - Same like HQL queries, we can also define native SQL as a named query with @NamedNativeQueries annotation
                
                @org.hibernate.annotations.NamedNativeQueries(
                    @org.hibernate.annotations.NamedNativeQuery(name = "DeptEmployee_GetEmployeeByName", 
                      query = "select * from deptemployee emp where name=:name",
                      resultClass = DeptEmployee.class)
                )
            - Since this is a native query, we'll have to tell Hibernate what entity class to map the results to. Consequently, we've used the **resultClass** property for doing this.
            - Another way to map the results is to use the **resultSetMapping** property. Here, we can specify the name of a pre-defined SQLResultSetMapping.
            - Use:
                
                    NativeQuery query = session.getNamedNativeQuery("DeptEmployee_FindByEmployeeName");
                    query.setParameter("name", "John Wayne");
                    DeptEmployee result = (DeptEmployee) query.getSingleResult();
                
        - **Stored Procedures and Functions**
            - We can use the @NamedNativeQuery annotation to define calls to stored procedures and functions as well
                
                    @org.hibernate.annotations.NamedNativeQuery(
                      name = "DeptEmployee_UpdateEmployeeDesignation", 
                      query = "call UPDATE_EMPLOYEE_DESIGNATION(:employeeNumber, :newDesignation)", 
                      resultClass = DeptEmployee.class)
                                          
- Transaction Management in Spring
    - Two types of Transaction Management
         - Declarative - This is annotation based provided by Spring
            - @EnableTransactionManagement 
            - If we annotate at class level then applied for all, mostly used at  
            - Transaction has its own Isolation level and rollback strategy
            - Transaction annotations are only used at public methods not at private methods
            - Dirty reads, phantom reads, non repeatable reads for DB
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
            
        - Programmatic - We know the exact scope of transaction
               
                try{
                    tx.begin();   
                    dologic();  
                    tx.commit(); 
                } cathc(Exc e){ 
                    tx.rollback()
                }
            - Declarative transaction may not suitable in following scenario
               - Let's suppose we're mixing two different types of I/O in a simple service:
                    
                        @Transactional
                        public void initialPayment(PaymentRequest request) {
                            savePaymentRequest(request); // DB
                            callThePaymentProviderApi(request); // API
                            updatePaymentState(request); // DB
                            saveHistoryForAuditing(request); // DB
                        }
                        
                        If the API call responds very slowly for a while, this method would hog the borrowed Connection while waiting for the response. That's why we may run out of database connections — because of a slow back-end service!
                        Mixing the database I/O with other types of I/O in a transactional context is a bad smell. So, the first solution for these sorts of problems is to separate these types of I/O altogether.
               
               - This can be achieved in following ways:
                  - **TransactionTemplate**
                    - It provides callback-based APIs to manage transactions manually
                            
                            // test annotations
                            class ManualTransactionIntegrationTest {
                            
                                @Autowired
                                private PlatformTransactionManager transactionManager;
                            
                                private TransactionTemplate transactionTemplate;
                            
                                @BeforeEach
                                void setUp() {
                                    transactionTemplate = new TransactionTemplate(transactionManager);
                                }
                                
                                @Test
                                void givenAPayment_WhenNotDuplicate_ThenShouldCommit() {
                                    Long id = transactionTemplate.execute(status -> {
                                        Payment payment = new Payment();
                                        payment.setAmount(1000L);
                                        payment.setReferenceNumber("Ref-1");
                                        payment.setState(Payment.State.SUCCESSFUL);
                                
                                        entityManager.persist(payment);
                                
                                        return payment.getId();
                                    });
                                
                                    Payment payment = entityManager.find(Payment.class, id);
                                    assertThat(payment).isNotNull();
                                }
                                
                            }
                    - Similar to the declarative approach, the template can guarantee atomicity for us. That is, if one of the operations inside a transaction fails to complete, it rolls back all of them
                    - We can do following configurations for TransactionTemplate
                            
                            transactionTemplate = new TransactionTemplate(transactionManager);
                            transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
                            transactionTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
                            transactionTemplate.setTimeout(1000);
                            transactionTemplate.setReadOnly(true);
                    - if we need multiple configurations, we should create multiple template instances.
                    
                  - **PlatformTransactionManager**
                    - This is basic lower-level api for transaction management - both @Transactional and TransactionTemplate use this API to manage their transactions internally.
                    - Transaction definitions are similar to TransactionTemplate configurations. However, we can use multiple definitions with just one PlatformTransactionManager.
                            
                          @Test
                          void givenAPayment_WhenUsingTxManager_ThenShouldCommit() {
                           
                              // transaction definition
                              DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
                              definition.setIsolationLevel(TransactionDefinition.ISOLATION_REPEATABLE_READ);
                              definition.setTimeout(3);
                          
                              TransactionStatus status = transactionManager.getTransaction(definition);
                              try {
                                  Payment payment = new Payment();
                                  payment.setReferenceNumber("Ref-1");
                                  payment.setState(Payment.State.SUCCESSFUL);
                          
                                  entityManager.persist(payment);
                                  transactionManager.commit(status);
                              } catch (Exception ex) {
                                  transactionManager.rollback(status);
                              }
                          
                              assertThat(entityManager.createQuery("select p from Payment p").getResultList()).hasSize(1);
                          }  

- Spring JMS   
   - What is JMS
   - Topics and queues
   - Difference between topic and queue in JMS
   - What its purpose
   - sync and async messaging
   - Producer and consumer
   - jms handles which type of calls.
   - How queue get active.
   - How modules communicates.                         

- Spring Async Services
    - Spring provides asynchronous execution support in Spring – with the @Async annotation
    - annotating a method of a bean with @Async will make it execute in a separate thread i.e. the caller will not wait for the completion of the called method.
    - enabling asynchronous processing with Java configuration 
        
            @Configuration
            @EnableAsync
            public class SpringAsyncConfig { ... }
            
    - XML configuration – by using the task namespace:
        
            <task:executor id="myexecutor" pool-size="5"  />
            <task:annotation-driven executor="myexecutor"/>
            
    - @Async has two limitations:
        - it must be applied to public methods only – the method needs to be public so that it can be proxied.
        - self-invocation – calling the async method from within the same class – won't work - And self-invocation doesn't work because it bypasses the proxy and calls the underlying method directly.
    - @Async Methods    
        - **Methods with void return type**
            
                @Async
                public void asyncMethodWithVoidReturnType() {
                    System.out.println("Execute method asynchronously. " + Thread.currentThread().getName());
                }
        - **Method with return type** – by wrapping the actual return in the Future:
        
                @Async
                public Future<String> asyncMethodWithReturnType() {
                    System.out.println("Execute method asynchronously - " 
                      + Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                        return new AsyncResult<String>("hello world !!!!");
                    } catch (InterruptedException e) {
                        //
                    }
                
                    return null;
                }
                
                // Calling Async method
                public void testAsyncAnnotationForMethodsWithReturnType()
                  throws InterruptedException, ExecutionException {
                    System.out.println("Invoking an asynchronous method. " 
                      + Thread.currentThread().getName());
                    Future<String> future = asyncAnnotationExample.asyncMethodWithReturnType();
                
                    while (true) {
                        if (future.isDone()) {
                            System.out.println("Result from asynchronous process - " + future.get());
                            break;
                        }
                        System.out.println("Continue doing something else. ");
                        Thread.sleep(1000);
                    }
                }
        - Spring also provides an AsyncResult class which implements Future.
    
    - **The Executor** 
        - By default, Spring uses a SimpleAsyncTaskExecutor to actually run these methods asynchronously.
        - We can cutomize this in two ways:
            - Override the Executor at the Method Level
                - The required executor needs to be declared in a configuration class:
                  
                      @Configuration
                      @EnableAsync
                      public class SpringAsyncConfig {
                          
                          @Bean(name = "threadPoolTaskExecutor")
                          public Executor threadPoolTaskExecutor() {
                              ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
                                executor.setCorePoolSize(10);
                                executor.setMaxPoolSize(10);
                                executor.setQueueCapacity(100);
                                executor.setThreadNamePrefix("Test-Executor-");
                                executor.initialize();
                              return executor;
                           
                          }
                      }
                - Then the executor name should be provided as an attribute in @Async:
                  
                      @Async("threadPoolTaskExecutor")
                      public void asyncMethodWithConfiguredExecutor() {
                          System.out.println("Execute method with configured executor - " + Thread.currentThread().getName());
                      }
            - Override the Executor at the Application Level 
                - The configuration class should implement the AsyncConfigurer interface – which will mean that it has the implement the getAsyncExecutor() method.
                    
                        @Configuration
                        @EnableAsync
                        public class SpringAsyncConfig implements AsyncConfigurer {
                            
                            @Override
                            public Executor getAsyncExecutor() {
                                return new ThreadPoolTaskExecutor();
                            }
                            
                        }
                - here that we will return the executor for the entire application – this now becomes the default executor to run methods annotated with @Async
                                          
- What are interceptors in a spring?
    - HandlerIntercceptor is the only interceptor provided by spring
    - It is a way to control request response flow
    - HandlerIntercceptor has three lifecycle methods
        Prehandle
        Posthandle
        AfterCompletion
    - We must have to register interceptor in interceptor registory - inside SpringSecurityConfig -> addInterceptor() 
    
    - Filter is similer to interceptor but different
    
- Spring AOP
    - APO is a framework that is extended by Spring
    - Spring Global Exception Handling - with @ControllerAdvice is example 
    -  Implementation Example of Spring AOP
        - Define Marker Interface - Custom Annotation for Aspect
            
                /** 
                 * <code>LoggableMethod</code> is a Marker interface defined to enable method logging. <br>
                 * @author Mahesh More
                 */
                public @interface LoggableMethod {
                }     
        
        - Define aspect to wrap the business logic
            
                /** 
                 * <code>MethodLogAspect</code> is an Aspect that provides logging service on START, SUCCESS and FAILURE of method <br>
                 * @author Mahesh More
                 */
                @Aspect
                @Component
                public class MethodLogAspect {
                    static final Logger log = LoggerFactory.getLogger(MethodLogAspect.class.getName());
                
                    /**
                     * 
                     *  Provides logging on START, SUCCESS and FAILURE of all LoggableMethod.
                     * @param proceedingJoinPoint
                     * @return Object
                     * @throws Exception
                     */
                    @Around("@annotation(com.mm.logging.aspects.markers.LoggableMethod) && execution(public * *(..))")
                    public Object methodLogHandler(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
                        Object value;
                        String methodLocation = "";
                        String methodClass = "";
                        String methodName = "";
                        try {
                            Map<String, Object> methodParams = new HashMap<>();
                            String[] methodArgs = null;
                            if (proceedingJoinPoint != null && proceedingJoinPoint.getSignature() != null) {
                                methodLocation = proceedingJoinPoint.getSignature().getDeclaringType().toString();
                                methodName = proceedingJoinPoint.getSignature().getName();
                                methodClass = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
                                CodeSignature codeSignature = (CodeSignature) proceedingJoinPoint.getSignature();
                                methodArgs = codeSignature.getParameterNames();
                                for (int i = 0; i < methodArgs.length; i++) {
                                    methodParams.put(methodArgs[i], proceedingJoinPoint.getArgs()[i]);
                                }
                            }
                            log.info("{}.{}() :: {} :: {} :: START :: PARAMS: {}", methodLocation, methodName, methodClass.toUpperCase(), methodName.toUpperCase(),
                                    MsvcJSONUtil.toJSON(methodParams));
                            value = proceedingJoinPoint.proceed();
                            log.info("{}.{}() :: {} :: {} :: SUCCESS", methodLocation, methodName, methodClass.toUpperCase(), methodName.toUpperCase());
                            log.debug("{}.{}() :: RETURN_VALUE: {}", methodLocation, methodName, MsvcJSONUtil.toJSON(value));
                        } catch (Throwable ex) {
                            log.error("{}.{}() :: {} :: {} :: EXCEPTION :: MESSAGE : {} :: STACK_TRACE : {}", methodLocation, methodName, methodClass.toUpperCase(),
                                    methodName.toUpperCase(), ex.getMessage(), ex.getStackTrace());
                            throw ex;
                        }
                        return value;
                    }
                }
 
    - Joint Points
        Lifecycle @Before, @After

        
### Web Services

- Rest Mapping should be noun. 

### PL SQL
