<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<html>

<head>
<title>add contact</title>

</head>

<body>
<h1>add contact</h1>

<form action="contact?add" method="post">

<input type="hidden" name="add">

<ul>

	<li>name: <input type="text" name="name"> </li>
	<li>street: <input type="text" name="street"> </li>
	<li>city: <input type="text" name="city"> </li>
	<li>state: <input type="text" name="state"> </li>	
	<li>zip: <input type="text" name="zip"> </li>		
</ul>

<input type="submit" value="add">

</form>

<a href="contacts">back to contact list</a>

</body></html>