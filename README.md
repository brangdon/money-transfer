# Money Transfer Task #   

Simple Http Rest Api for transferring money between accounts.


### 1. How to start? ###
Type `mvn clean install` to compile application and run tests
 
Run `MainClass` to launch application. Server is started on 4567 port.

### 2. API ###
All calls to API must be started with `http://localhost:4567/`
                                                        
<table>
<thead>
<tr>
<th>Endpoint</th>
<th>Description</th>
<th>Parameters</th>
<th>Success Response (Example)</th>
</tr>
</thead>
<tbody>
<tr>
	<td><code>POST /account</code></td>
	<td>Creates new account</td>
	<td>      <pre>
{
"email": "mail@mail.com",
"balance": 100.2
}
	  </pre></td>
	<td>
200
    </td>
</tr>

<tr>
	<td><code>GET /account/{id}</code></td>
	<td>Gets account by ID</td>
    <td>Path:<br/><code>id</code> - account ID</td>
	<td>
    200
      <pre>
{
"id":1,
"balance":1000.00,
"email":"email1"
}
	  </pre>
    </td>
</tr>

<tr>
	<td><code>GET /account/all</code></td>
	<td>Gets all accounts</td>
    <td></td>
	<td>
    200
      <pre>
[
{"id":1,"balance":1000.00,
"email":"email1"},
{"id":2,"balance":1000.00,
"email":"email2"}
]
	  </pre>
    </td>
</tr>

<tr>
	<td><code>GET /account/transfer/{id}</code></td>
	<td>Gets all account transfers</td>
    <td><code>id</code> - account ID</td>
	<td>
    200
      <pre>
[
{"id":1,"accountFrom":1,
"accountTo":2,"amount":100.00}
]
	  </pre>
    </td>
</tr>


<tr>
	<td><code>GET /transfer/all</code></td>
	<td>Get all transfers</td>
    <td>    </td>
	<td>
      200 
      <pre> 
      [
      {"id":1,
      "accountFrom":1,
      "accountTo":2,
      "amount":100.00}
      ]
      </pre>
    </td>
</tr>
<tr>
	<td><code>GET /transfer/{id}</code></td>
	<td>Get transfer by id</td>
    <td>    </td>
	<td>
      200 
      <pre> 
{
"id":1,
"accountFrom":1,
"accountTo":2,
"amount":100.00
}
      </pre>
    </td>
</tr>
<tr>
	<td><code>POST /transfer</code></td>
    <td>Tranfers money from account id <code>accountFrom</code> to account <code>accountTo</code> </td>
    <td>        Body:<br/>        <pre>
{
"accountFrom": 1,
"accountTo": 2,
"amount": 1212.00
}
        </pre>    </td>
	<td>
      200
    </td>
</tr>

</tbody></table>

#### Errors ####

<table>
<thead>
<tr>
<th>Status code</th>
<th>Response</th>
</tr>
</thead>
<tbody>
<tr>
  <td>404</td>
  <td><code>{    "message": "No user with id 12 found"}</td>
</tr>
<tr>
  <td>404</td>
  <td><code>{    "message": "No transfer with id 12 found"}</td>
</tr>
<tr>
  <td>500</td>
  <td><code>{"message":"Internal server error"}</td>
</tr>

</tbody>
</table>

