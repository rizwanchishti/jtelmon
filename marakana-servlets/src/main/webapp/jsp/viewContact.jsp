<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>

<head>
<title>${contact.name}</title>

</head>

<body>
<h1>${contact.name}</h1>


<ul>

	<li>${address.street}</li>
	<li>${address.city}, ${address.state}, ${address.zip}</li>
	
</ul>

<a href="contact?edit&id=${contact.id}">edit contact list</a> |
<a href="contacts">back to contact list</a>

</body></html>