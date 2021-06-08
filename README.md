# Spring challenge - bootcamp

# **API Social MeLi**

A social network to users and sellers.

## **Description**

This project was developed in Java with Spring Framework and H2 Database, to create a social network.

To use this API you must clone this repository. When started, the database will be automatically populated with some 
users, sellers, follows and products. There is a postman collection in the resources path the can be used to test the endpoints. You can 
access the database on http://localhost:8080/h2-ui/login.do?jsessionid=22a5cdf54200923a90a21f22058999db, where the access 
is the default H2 user. The JDBC URL is jdbc:h2:mem:testdb

## **Endpoints**

### User follow seller **(POST)**

- http://localhost:8080/users/{userId}/follow/{sellerIdToFollow}

A user can follow a seller. Returns a message:

"The user {userId} has followed {sellerId}" with 200 Status Code

If you pass a user, seller or both that doesn't exists will receive a error message with 400 Status Code

### Seller follow seller **(POST)**

- http://localhost:8080/users/sellers/{sellerId}/follow/{sellerIdToFollow}

A seller can follow another seller. Returns a message:

"The seller {userId} has followed {sellerId}" with 200 Status Code

If you pass a user, seller or both that doesn't exists will receive a error message with 400 Status Code

### Followers count **(GET)**

- http://localhost:8080/users/{sellerId}/followers/count

Followers number of a given seller. Returns a message:

```json
{

"sellerId": 4,

"sellerName": "example",

"followers_count": 2

}
```

with 200 Status Code

### Followers list **(GET)**

- http://localhost:8080/users/{sellerId}/followers/list

Followers list of a given seller. This will include the followers users and followers sellers separately. Returns a message:

```json
{

"sellerId": 1,

"sellerName": "seller1",

"followers": [

{ 
	"userId": 1,
	
	"userName": "user1"
	
	},
	
	{
	
	"userId": 2,
	
	"userName": "user2"
	
	}
],

"followersSeller": [

	{
	
	"sellerId": 2,
	
	"sellerName": "seller2"
	},
	{
	
	"sellerId": 3,
	
	"sellerName": "seller3"
	
	}
]}
```

with 200 Status Code

### Followed list (GET**)**

- http://localhost:8080/users/{sellerId}/followed/list

Followed seller list of a given user. Returns a message:

```json

 {
    "userId": 1,
    "userName": "user1",
    "followed": [
        {
            "sellerId": 4,
            "sellerName": "seller4"
        },
        {
            "sellerId": 1,
            "sellerName": "seller1"
        },
        {
            "sellerId": 2,
            "sellerName": "seller2"
        },
        {
            "sellerId": 3,
            "sellerName": "seller3"
        }
    ]
}
```

with 200 Status Code

### Followed list - sellers (GET**)**

- http://localhost:8080/users/sellers/{sellerId}/followed/list

Followed seller list of a given seller. Returns a message:

```json
{
    "sellerId": 2,
    "sellerName": "seller2",
    "followed": [
        {
            "sellerId": 1,
            "sellerName": "seller1"
        },
        {
            "sellerId": 3,
            "sellerName": "seller3"
        }
    ]
}
```

with 200 Status Code

### New post **(POST)**

- [http://localhost:8080/users/products/newpost](http://localhost:8080/users/products/newpost)

A seller can post a new product. You must send a body as follow:

```json
{
    "sellerId": 2,
    "id_post": 12,
    "date": "29-05-2021",
    "detail": [{
        "productId":1,
        "productName":"mouse",
        "productType": "gamer",
        "productBrand": "race",
        "productColor": "red",
        "productNotes": "special"
    }],
    "category": 100,
    "price": 1500.50
}
```

The id_post will be generate automatically according the data base, so you can omit this field.

Returns a message:

"The product was posted successfully!" with 200 Status Code.

### Followed post list - users (GET**)**

- http://localhost:8080/users/products/followed/{sellerId}/list

Followed seller post list of a given  user. This endpoint filter the last two week posts, showing the most recently first. Returns a message:

```json
{
    "userId": 1,
    "userName": "user1",
    "posts": [
        {
            "sellerId": 1,
            "id_post": 52,
            "date": "29-05-2021",
            "detail": [
                {
                    "productId": 1,
                    "productName": "mouse",
                    "productType": "gamer",
                    "productBrand": "race",
                    "productColor": "red",
                    "productNotes": "special"
                }
            ],
            "category": 100,
            "price": 100.5
        },
        {
            "sellerId": 2,
            "id_post": 50,
            "date": "28-05-2021",
            "detail": [
                {
                    "productId": 1,
                    "productName": "monitor",
                    "productType": "gamer",
                    "productBrand": "race",
                    "productColor": "red",
                    "productNotes": "special"
                }
            ],
            "category": 100,
            "price": 1500.5
        }
    ]
}
```

with 200 status code

### Followed post list - sellers (GET**)**

- http://localhost:8080/users/sellers/products/followed/{sellerId}/list

Followed seller post list of a given seller. This endpoint filter the last two week posts, showing the most recently first. Returns a message:

```json
{
    "sellerId": 3,
    "sellerName": "seller3",
    "posts": [
        {
            "sellerId": 1,
            "id_post": 52,
            "date": "29-05-2021",
            "detail": [
                {
                    "productId": 1,
                    "productName": "mouse",
                    "productType": "gamer",
                    "productBrand": "race",
                    "productColor": "red",
                    "productNotes": "special"
                }
            ],
            "category": 100,
            "price": 100.5
        },
        {
            "sellerId": 2,
            "id_post": 50,
            "date": "28-05-2021",
            "detail": [
                {
                    "productId": 1,
                    "productName": "monitor",
                    "productType": "gamer",
                    "productBrand": "race",
                    "productColor": "red",
                    "productNotes": "special"
                }
            ],
            "category": 100,
            "price": 1500.5
        }
    ]
}
```

with 200 status code

### User unfollow seller **(POST)**

- http://localhost:8080/users/{userId}/unfollow/{sellerIdToUnfollow}

A user can follow a seller. Returns a message:

"The user {userId} has unfollowed {sellerId}" with 200 Status Code

If you pass a user, seller or both that doesn't exists will receive a error message with 400 Status Code

### Seller unfollow seller **(POST)**

- http://localhost:8080/users/sellers/{sellerId}/unfollow/{sellerIdToUnfollow}

A seller can unfollow another seller. Returns a message:

"The seller {userId} has unfollowed {sellerId}" with 200 Status Code

If you pass a follower seller, followed seller or both that doesn't exists will receive a error message with 400 Status Code

### Alphabetical order - ascending and descending (GET**)**

- [http://localhost:8080/users/{sellerId}/followers/list?order=name_asc](http://localhost:8080/users/3/followers/list?order=name_asc)
- [http://localhost:8080/users/{sellerId}/followers/list?order=name_desc](http://localhost:8080/users/3/followers/list?order=name_desc)
- [http://localhost:8080/users/{userId}/followed/list?order=name_asc](http://localhost:8080/users/2/followed/list?order=name_asc)
- [http://localhost:8080/users/{userId}/followed/list?order=name_desc](http://localhost:8080/users/2/followed/list?order=name_desc)
- [http://localhost:8080/users/sellers/{sellerId}/followed/list?order=name_asc](http://localhost:8080/users/sellers/1/followed/list?order=name_asc)
- [http://localhost:8080/users/sellers/{sellerId}/followed/list?order=name_desc](http://localhost:8080/users/sellers/1/followed/list?order=name_desc)

Ascending or descending alphabetical order of followed sellers from a given user. Ascending or descending alphabetical order of followers users/sellers or followed sellers from a given seller. Returns a message ordering the list accordingly to query param.

### Date order - ascending and descending (GET**)**

- [http://localhost:8080/users/sellers/products/followed/{sellerId}/list?order=date_asc](http://localhost:8080/users/sellers/products/followed/1/list?order=date_asc)
- [http://localhost:8080/users/sellers/products/followed/{sellerId}/list?order=date_desc](http://localhost:8080/users/sellers/products/followed/1/list?order=date_desc)
- [http://localhost:8080/users/products/followed/{userId}/list?order=date_asc](http://localhost:8080/users/products/followed/1/list?order=date_asc)
- [http://localhost:8080/users/products/followed/{userId}/list?order=date_desc](http://localhost:8080/users/products/followed/1/list?order=date_desc)

Ascending or descending order by post date of followed sellers posts from a given user. Ascending or descending order by post date of followed sellers posts from a given seller. Returns a message ordering posts accordingly to query param considering the last two weeks from the request date.

### New promo post **(POST)**

- [http://localhost:8080/users/products/newp](http://localhost:8080/users/products/newpost)romopost

A seller can post a new promo product. You must send a body as follow:

```json
{
    "sellerId": 2,
    "id_post": 12,
    "date": "08-10-2021",
    "detail": [{
        "productId":1,
        "productName":"mouse",
        "productType": "gamer",
        "productBrand": "race",
        "productColor": "red",
        "productNotes": "special"
    }],
    "category": 80,
    "price": 1500.50,
    "hasPromo":true,
    "discount":0.25
}
```

The id_post will be generate automatically according the data base, so you can omit this field.

Returns a message:

"The product was posted successfully! " with 200 Status Code.

### Promo posts count **(GET)**

- [http://localhost:8080/users/products/{sellerId}/countpromo](http://localhost:8080/users/products/1/countpromo)

Promo posts number of a given seller. Returns a message:

```json
{
    "sellerId": 1,
    "sellerName": "seller1",
    "promoproducts_count": 3
}
```

with 200 Status Code

### Promo posts list **(GET)**

- [http://localhost:8080/users/products/{sellerId}/](http://localhost:8080/users/products/1/promo/list)promo/list

Promo posts list of a given seller. Returns a message:

```json
{
    "sellerId": 1,
    "sellerName": "seller1",
    "posts": [
        {
            "sellerId": 1,
            "id_post": 52,
            "date": "08-10-2021",
            "detail": [
                {
                    "productId": 1,
                    "productName": "mouse",
                    "productType": "gamer",
                    "productBrand": "race",
                    "productColor": "red",
                    "productNotes": "special"
                }
            ],
            "category": 80,
            "price": 100.5,
            "discount": 0.25,
            "hasPromo": true
        },
        {
            "sellerId": 1,
            "id_post": 53,
            "date": "08-10-2021",
            "detail": [
                {
                    "productId": 2,
                    "productName": "chair",
                    "productType": "gamer",
                    "productBrand": "race",
                    "productColor": "red",
                    "productNotes": "special"
                }
            ],
            "category": 80,
            "price": 2000.5,
            "discount": 0.25,
            "hasPromo": true
        }
    ]
}
```

with 200 Status Code

### Add a new user **(POST)**

- http://localhost:8080/users/

Add a new user. You must send a body as follow:

```json
{
    "userId": 7,
    "userName": "user7"
}
```

Returns a message with the created user.

### Add a new seller **(POST)**

- http://localhost:8080/users/seller

Add a new seller. You must send a body as follow:

```json
{
    "sellerId": 7,
    "ssllerName": "seller7"
}
```

Returns a message with the created user.

### Get all users **(GET)**

- [http://localhost:8080/users](http://localhost:8080/users/products/1/countpromo)/

Get all users with theirs followed list names. Returns a message:

```json
[
    {
        "userId": 1,
        "userName": "user1",
        "followed": [
            "amanda",
            "carlos"
        ]
    },
    {
        "userId": 2,
        "userName": "user2",
        "followed": [
            "roberta",
            "fernando"
        ]
    }
]
```

with 200 Status Code

### Get all sellers **(GET)**

- [http://localhost:8080/users](http://localhost:8080/users/products/1/countpromo)/sellers

Get all sellers with theirs followers list. Returns a message:

```json
[
    {
        "sellerId": 1,
        "sellerName": "seller1",
        "followers": [
            {
                "userId": 1,
                "userName": "user1"
            }
        ],
        "followersSeller": [
            {
                "sellerId": 2,
                "sellerName": "seller2"
            },
            {
                "sellerId": 3,
                "sellerName": "seller3"
            }
        ]
    },
    {
        "sellerId": 2,
        "sellerName": "seller2",
        "followers": [
            {
                "userId": 1,
                "userName": "user1"
            },
            {
                "userId": 2,
                "userName": "user2"
            }
        ],
        "followersSeller": []
    }
]
```

with 200 Status Code