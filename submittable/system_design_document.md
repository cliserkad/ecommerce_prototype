# Introduction
## Purpose
This document outlines the system requirements for developing a basic ecommerce website. The website’s core functionality is to support online retail operations, including posting product listings, shopping cart functionality, user accounts, and order processing.

## Scope
The ecommerce webservice will include the following key components:
Product catalog and inventory management
User registration and authentication
Shopping cart functionality
Order placement and processing
Basic search and filtering capabilities



# Design-Level Class Diagrams

```mermaid
classDiagram
    direction TD
    
    Review "1" o-- "1" Listing
    Order "1" o-- "1" Listing
    CreditCard "1" o-- "1" PhysicalAddress
    User "1" o-- "0..*" CreditCard
    User "1" o-- "0..*" PhysicalAddress
    Review "1" o-- "1" User
    Order "1" o-- "1" User
    Order "1" o-- "1" PhysicalAddress
    
    class User {
        <<entity>>
        +id: long
        +name: String = "none"
        +phone: String
        +email: string
        +creditCards: List&ltCreditCard&gt = empty list
        +shippingAddresses: List&ltPhysicalAddress&gt = empty list
        +searchForProduct(name: String) List&ltProduct&gt
        +submitListing(listing: Listing)
        +submitOrder(order: Order)
        +submitReview(review: Review)
    }
    
    class Listing {
        <<entity>>
        +id: long
        +name: String
        +description: String
        +weightInGrams: int
        +stampsOfApproval: List&ltString&gt
        +imageURLS: List&ltString&gt
        +reviews: List&ltReview&gt
        +priceInCents: int
        +quantity: int
    }
    
    class Order {
        <<entity>>
        +id: long
        +purchaser: User
        +items: List&ltListing&gt
        +shippingAddress: PhysicalAddress
        +paymentMethod: CreditCard
        +orderDate: Timestamp = now
        +totalPiceInCents() int
    }
    
    class Review {
        <<entity>>
        +id: long
        +reviewer: User
        +itemReviewed: Listing
        +stars: int
        +comment: String
    }
    
    class CreditCard {
        <<entity>>
        +billingAddress: PhysicalAddress
        +cardNumber: String
        +securityCode: String
        +pin: string
        +expiry: Timestamp
    }
    
    class PhysicalAddress {
        <<entity>>
        +street: String
        +city: String
        +province: String
        +country: String
        +zip: String
    }
    
```

## Psuedocode
```
searchForProduct(name: String) List<Product>
	GET Listings from Database
	FOR listing in Listings
		IF listing.name contains name
			RETURN Product
		END IF
	RETURN null
	END FOR
	
submitListing(listing: Listing)
	POST listing to Database
	RETURN
	
submitOrder(order: Order)
	POST order to Database
	GET orders from Database
	BOOLEAN allInStock = TRUE
	FOR order in orders
		FOR listing in order.items
			IF !(listing.quantity > 0)
				allInStock = FALSE
			END IF
		END FOR
	END FOR
	IF allInStock
		fufillOrder(order)
	RETURN
	
submitReview(review: Review)
	POST review to Database
	RETURN
	
totalPriceInCents() int
	INT total = 0
	FOR item in items
		total += item.priceInCents
	END FOR
	RETURN total
```

# Object Statechart Diagrams

1. Order


![Order State Chart](order_state_chart.jpg)

2. Review


![Review State Chart](review_state_chart.jpg)

3. Listing


![Listing State Chart](listing_state_chart.jpg)

4. Credit Card


![Credit Card State Chart](credit_card_state_chart.jpg)

5. User

   
![User State Chart](user_state_chart.jpg)




# First Cut Domain Sequence Diagram

```mermaid
---
title: Post Product
---
sequenceDiagram
    autonumber
	participant u as Vendor
    participant c as Client
    participant s as Server
    u -> c: Selects Photos from Local Storage
    c -> u: Display Selected Photos
    u -> c: Select Upload Option
    c -> s: Submit Product Photos
    s -->> c: Photo Upload Acknowledgement, with URLs
    c -> u: Display Product Listing Form
    u -> c: Fills Out Product Listing Form
    c ->> s: Submit Product Listing
    s -->> c: Product Listing Acknowledgement
    c -> u: Display Product Listing
```

```mermaid
---
title: Post Review
---
sequenceDiagram
    autonumber
    participant u as Purchaser
    participant s as System
    u ->> s: Submit Review
    s -->> u: Review Creation Acknowledgement / Denial
```

```mermaid
---
title: Create Account
---
sequenceDiagram
    autonumber
    participant u as User
    participant s as System
    u ->> s: Create Account
    s -->> u: Account Creation Acknowledgement
    s -->> u: User Account Linked with Current Session
```

```mermaid
---
title: Purchase Product
---
sequenceDiagram
    autonumber
    participant u as User
    participant s as System
    u ->> s: Add Listing to Order
    s -->> u: Acknowledge Order Addition
    u ->> s: Submit Order
    s -->> u: Order Creation Acknowledgement
    s -->> u: Order Fulfillment Time Estimate (Immediate or Delayed)
```

```mermaid
---
title: Product Search
---
sequenceDiagram
    autonumber
    participant u as User
    participant s as System
    u ->> s: Search for Product with Name
    s -->> u: List of Products Matching Search
```
       
