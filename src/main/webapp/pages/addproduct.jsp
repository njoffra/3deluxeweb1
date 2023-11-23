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
    <style>
        body {
            background-color: #f2f2f2;
            font-family: Arial, sans-serif;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-top: 50px;
        }
        form {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            color: #333;
            font-weight: bold;
        }
        input[type="text"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        input[type="file"] {
            margin-bottom: 20px;
        }
        input[type="submit"] {
            padding: 10px 20px;
            background-color: #5791ff;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
    </style>
</head>
<body>
<h2>Add Product</h2>
<form action="${pageContext.request.contextPath}/addproduct" method="POST" enctype="multipart/form-data">
    <label for="name">Ime modela:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="description">Deskripcija:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label for="image">Slika/skica modela:</label>
    <input type="file" id="image" name="image" accept="image/*" required><br>

    <input type="submit" value="Add Product">
</form>
</body>
</html>

