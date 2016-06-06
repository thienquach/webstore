<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>WebStore</title>
</head>
<body>
	<section>
		<div class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-4 col-md-offset-4">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h3 class="panel-title">Please sign in</h3>
							</div>
							<div class="panel-body">
								<c:if test="${not empty error }">
									<div class="alert alert-danger">
										Login failed!!!!
									</div>
								</c:if>
								<!-- start login form, using Spring security -->
								<form action="<c:url value="/j_spring_security_check"></c:url>" method="post">
									<fieldset>
										<div class="form-group">
											<input class="form-control" placeholder="Username" name="j_username" type="text"/>
										</div>
										<div class="form-group">
											<input class="form-control" placeholder="Password" name="j_password" type="password" value=""/>
										</div>
										
										<input class="btn btn-lg btn-success btn-block" type="submit" value="Login"/>
										
									</fieldset>
								</form>
								
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>
</body>
</html>