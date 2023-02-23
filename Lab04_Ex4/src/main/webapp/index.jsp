<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>File Upload Example</title>
</head>
<body>
	<h1>File Upload Example</h1>
	<% if (request.getAttribute("message") != null) { %>
		<p><%= request.getAttribute("message") %></p>
		<% if (request.getAttribute("fileUrl") != null) { %>
			<p><a href="<%= request.getAttribute("fileUrl") %>">File uploaded. Click here to visit file.</a></p>
		<% } %>
	<% } %>
	<form method="post" action="upload" enctype="multipart/form-data">
		<div>
			<label for="name">File Name</label>
			<input placeholder="This name will be used to save the file after uploading to server" id="name" name="name" type="text">
		</div>
		<div>
			<label for="document">Choose file</label>
			<input type="file" id="document" name="document">
		</div>
		<div>
			<label for="override">Override if exists</label>
			<input type="checkbox" id="override" name="override">
		</div>
		<button type="submit">Upload</button>
	</form>
</body>
</html>
