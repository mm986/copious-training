# Assignment 1

This assignment covers following concepts and terminologies -
    
    1. Use of below collection apis and make use of streams -
        ArrayList, LinkedList
        HashSet, LinkedHashSet, and TreeSet
        HashMap, LinkedHashMap, and TreeMap
        Make use of comparable and comparator interface
        Properties class in java
    2. Create 5 custom business exceptions and throw them based on validations in your project.
    3. Make use of singleton design pattern and factory or prototype design pattern.
    4. Write unit test case for same using Mockito framework.
    5. Make use of java 8 features. No code should be written below java 8 version.
    6. Check in code into your repo on daily basis and send me the link to your repo.


## Use Case - Order Processing 

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
Do business validations on order processing and cover exception handling

    * Invalid Order Date
    * Invalid Arrival Date
    * Invalid Promo Code Applied
    * Unable to place your order

Create N number of custom exception and throw it via single Controller advice.
Pipe operation.
Try with resources.

### Applying Design Patterns 
1. Singleton - Define fixed promo codes and use them as a part of Singleton class
               Cover all loopholes - many ways to implemented
             
### Mockito