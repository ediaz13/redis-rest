# Demo REST API para Santander Mobile Banking Individuos

## Endpoints:

Base URL: http://localhost:8080/

### Home

``
GET /
``

#### Request

(empty)

#### Response

``
Welcome!
``

### Login

``
POST /login
``

#### Headers

``
Content-Type: application/json
``

#### Request

``
{
	"username": "demo",
	"password": "1234"
}
``

#### Response

``
{
    "token": "valor.del.token"
}
``

### Dashboard

``
GET /dashboard
``

#### Header

``
Content-Type: application/json
``

``
Authorization: Bearer <valor.del.token>
``

#### Request

(empty)

#### Response

``
Protected endpoint demo response.
``
