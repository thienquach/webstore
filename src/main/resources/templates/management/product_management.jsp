<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:url var="firstUrl" value="/management/products?page=1" />
<c:url var="lastUrl"
	value="/management/products?page=${productPage.totalPages }" />
<c:url var="prevUrl"
	value="/management/products?page=${currentIndex - 1 }" />
<c:url var="nextUrl"
	value="/management/products?page=${currentIndex + 1 }" />


<section class="container">
	<nav>
		<ul class="pagination">
			<c:choose>
				<c:when test="${currentIndex == 1 }">
					<li class="disabled"><a href="#">&laquo;</a>
					<li class="disabled"><a href="#">&lt;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${firstUrl}">&laquo;</a></li>
					<li><a href="${prevUrl}">&lt;</a></li>
				</c:otherwise>
			</c:choose>
			<c:forEach var="i" begin="${beginIndex }" end="${endIndex }">
				<c:url var="pageUrl" value="/management/products?page=${i }" />
				<c:choose>
					<c:when test="${i == currentIndex }">
						<li class="active"><a href="${pageUrl }"><c:out
									value="${i }" /> </a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${pageUrl }"><c:out value="${i }" /></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentIndex == productPage.totalPages }">
					<li class="disabled"><a href="#">&gt;</a></li>
					<li class="disabled"><a href="#">&raquo;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="${nextUrl }">&gt;</a></li>
					<li><a href="${lastUrl }">&raquo;</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</nav>
	<button type="button" class="btn btn-primary pull-right"
		data-toggle="modal" data-target=".addEditModal">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> New
	</button>
	<table class="table table-condensed table-hover table-border">
		<thead>
			<tr>
				<th>#</th>
				<th>Product Code</th>
				<th>Product Name</th>
				<th>Category</th>
				<th>Unit Price</th>
				<th>Description</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productPage.content}" var="product"
				varStatus="status">
				<tr>
					<td>${product.id }</td>
					<td>${product.code }</td>
					<td>${product.name }</td>
					<td>${product.category.name }</td>
					<td>${product.unitPrice }</td>
					<td>${product.description }</td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
</section>

<!-- start AddEditModal -->
<div class="modal fade addEditModal" tabindex="-1" role="dialog"
	aria-labelledby="modalLabel">
	<div class="modal-dialog" role="addEditModalForm">
		<div class="modal-content">
			<div class="modal-header">
				<button class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="modalLabel">Add</h4>

			</div>
			<form:form modelAttribute="newProduct" action="products/add"
				method="post" class="form-horizontal">
				<div class="modal-body">
					<!-- Product Code -->
					<div class="form-group">
						<label class="control-label col-lg-3 col-md-4 col-sm-4 "
							for="code"> Product Code </label>
						<div class="col-lg-9 col-md-8 col-sm-8 ">
							<form:input id="code" path="code" type="text"
								class="form-control" />
						</div>
					</div>
					<!-- Product Category -->
					<div class="form-group">
						<label class="control-label col-lg-3 col-md-4 col-sm-4 "
							for="category"> Category </label>
						<div class="col-lg-9 col-md-8 col-sm-8 ">

							<form:select id="category" path="category" class="form-control">
								<form:options items="${categories }" itemLabel="name"
									itemValue="id" />

							</form:select>
						</div>
					</div>
					<!-- Product Name -->
					<div class="form-group">
						<label class="control-label col-lg-3 col-md-4 col-sm-4 "
							for="name"> Product Name </label>
						<div class="col-lg-9 col-md-8 col-sm-8 ">
							<form:input id="name" path="name" type="text"
								class="form-control" />
						</div>
					</div>
					<!-- Unit Price -->
					<div class="form-group">
						<label class="control-label col-lg-3 col-md-4 col-sm-4 "
							for="unitPrice"> Unit Price </label>
						<div class="col-lg-9 col-md-8 col-sm-8 ">
							<form:input id="unitPrice" path="unitPrice" type="text"
								class="form-control" />
						</div>
					</div>
					<!-- Description -->
					<div class="form-group">
						<label class="control-label col-lg-3 col-md-4 col-sm-4 "
							for="description"> Description </label>
						<div class="col-lg-9 col-md-8 col-sm-8 ">
							<form:textarea id="description" path="description" rows="2"
								class="form-control" />
						</div>
					</div>
					<!-- Condition -->
					<div class="form-group">
						<label class="control-label col-lg-3 col-md-4 col-sm-4 ">
							Status </label>
						<div class="col-lg-9 col-md-8 col-sm-8 ">
							<label class="radio-inline"> <form:radiobutton
									path="status" value="New" />New
							</label> <label class="radio-inline"> <form:radiobutton
									path="status" value="Old" />Old
							</label> <label class="radio-inline"> <form:radiobutton
									path="status" value="Refurbished" />Refurbished
							</label>
						</div>
					</div>
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" id="btnAdd" class="btn btn-primary"
						value="Add">
				</div>
			</form:form>
		</div>
	</div>
</div>
<!-- end AddEditModal -->

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.5/angular.min.js">
<!--
	
//-->
</script>

