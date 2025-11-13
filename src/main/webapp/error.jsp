<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="temp/back_Office/pages/header.jsp"/>
<div class="container mt-4">
    <div class="container-fluid pt-4 px-4">
        <div class="row vh-100 bg-light rounded align-items-center justify-content-center mx-0">
            <div class="col-md-6 text-center p-4">
                <i class="bi bi-exclamation-triangle display-1 text-primary"></i>
                <h1 class="display-1 fw-bold">Error</h1>
                <p class="mb-4"><%=request.getAttribute("error")%></p>
                <a class="btn btn-primary rounded-pill py-3 px-5" href="">Go Back To Home</a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="temp/back_Office/pages/footer.jsp"/>
