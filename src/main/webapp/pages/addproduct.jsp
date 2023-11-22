<%--
  Created by IntelliJ IDEA.
  User: Franjo
  Date: 11/22/2023
  Time: 9:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Product</title>
</head>
<body>
<h2>Add Product</h2>
<form action="${pageContext.request.contextPath}/addproduct" method="POST" enctype="multipart/form-data">
    <label for="name">Ime modela:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="description">Deskripcija:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label for="image">Skica/slika modela:</label>
    <input type="file" id="image" name="image" accept="image/*" required><br>

    <input type="submit" value="Dodaj model">
</form>
</body>
</html>

