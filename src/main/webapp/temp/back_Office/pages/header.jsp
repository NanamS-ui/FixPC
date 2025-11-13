<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>FixPC</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="<%=request.getContextPath()%>/temp/back_Office/assets/images/logoAdmin.webp" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">

    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="<%=request.getContextPath()%>/temp/back_Office/assets/lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="<%=request.getContextPath()%>/temp/back_Office/assets/lib/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="<%=request.getContextPath()%>/temp/back_Office/assets/css/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="<%=request.getContextPath()%>/temp/back_Office/assets/css/style.css" rel="stylesheet">
</head>
    <%
        String activePage = (String) request.getAttribute("activePage");
    %>
<body>
<style>
    .dropdown-submenu {
        position: relative;
    }

    .dropdown-submenu > .dropdown-menu {
        top: 0;
        left: 100%;
        margin-top: -1px;
    }

</style>
<div class="container-xxl position-relative bg-white d-flex p-0">
    <!-- Spinner Start -->
    <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
        <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
            <span class="sr-only">Loading...</span>
        </div>
    </div>
    <!-- Spinner End -->

    <!-- Sidebar Start -->
    <div class="sidebar pe-4 pb-3">
        <nav class="navbar bg-light navbar-light">
            <a href="index.html" class="navbar-brand mx-4 mb-3">
                <h3 class="text-primary">
                    <i class="fa fa-hashtag me-2 fixed-icon"></i>FixPC
                </h3>
            </a>
            <div class="d-flex align-items-center ms-4 mb-4">
                <div class="position-relative">
                    <img class="rounded-circle" src="<%=request.getContextPath()%>/temp/back_Office/assets/images/logoAdmin.webp" alt="" style="width: 40px; height: 40px;">
                </div>
                <div class="ms-3">
                    <span></span>
                </div>
            </div>
            <div class="navbar-nav w-100">
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="far fa-file-alt me-2"></i>Filtre</a>
                    <div class="dropdown-menu bg-transparent border-0">
                        <a href="<%= request.getContextPath()%>/filtre" class="dropdown-item">Filtre Probleme</a>
                        <a href="<%= request.getContextPath()%>/CombinedServlet" class="dropdown-item">Filtre Retour</a>
                        <a href="<%= request.getContextPath()%>/RecommandationFiltreServlet" class="dropdown-item">Filtre Recommandation</a>
                        <a href="<%= request.getContextPath()%>/RecommandationListServlet" class="dropdown-item">Liste Recommandation</a>
                        <a href="<%= request.getContextPath()%>/RetourClientServlet" class="dropdown-item">Liste Clients</a>
                        <a href="<%= request.getContextPath() %>/CommissionServlet?dateDebut=2025-01-01&dateFin=2025-01-31" class="dropdown-item">Commission Technicien</a>
                        <a href="<%= request.getContextPath() %>/CommissionGenreServlet?dateDebut=2025-01-01&dateFin=2025-01-31&seuil=200000" class="dropdown-item">Commission Genre Technicien</a>
                        <a href="<%= request.getContextPath() %>/historiquePrix" class="dropdown-item">Historique Prix</a>

                    </div>
                </div>
                <div class="nav-item dropdown">
                    <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown"><i class="far fa-file-alt me-2"></i>Pages</a>
                    <div class="dropdown-menu bg-transparent border-0">
                        <a href="<%= request.getContextPath()%>/TypeOrdiServlet" class="dropdown-item">Type PC</a>
                        <a href="<%= request.getContextPath()%>/TypeProblemeServlet" class="dropdown-item">Type Probleme</a>
                        <a href="<%= request.getContextPath()%>/TypeReparationServlet" class="dropdown-item">Type Réparation</a>
                        <a href="<%= request.getContextPath()%>/ClientServlet" class="dropdown-item">Client</a>
                        <a href="<%= request.getContextPath()%>/SystemeServlet" class="dropdown-item">Systeme</a>
                        <a href="<%= request.getContextPath()%>/ComposantServlet" class="dropdown-item">Composant</a>
                        <a href="<%= request.getContextPath()%>/PrixComposantServlet" class="dropdown-item">Prix Composant</a>
                        <a href="<%= request.getContextPath()%>/FournisseurServlet" class="dropdown-item">Fournisseur</a>
                        <a href="<%= request.getContextPath()%>/PosteServlet" class="dropdown-item">Poste</a>
                        <a href="<%= request.getContextPath()%>/EmployeServlet" class="dropdown-item">Employe</a>
                        <a href="<%= request.getContextPath()%>/OrdinateurServlet" class="dropdown-item">Ordinateur</a>
                        <a href="<%= request.getContextPath()%>/ReparationServlet" class="dropdown-item">Reparation</a>
                        <a href="<%= request.getContextPath()%>/ProblemeServlet" class="dropdown-item">Probleme</a>
                        <a href="<%= request.getContextPath()%>/ReparationDetailsServlet" class="dropdown-item">Reparation Détails</a>
                        <a href="<%= request.getContextPath()%>/AchatServlet" class="dropdown-item">Achat</a>
                        <a href="<%= request.getContextPath()%>/MvtStockServlet" class="dropdown-item">Mouvement Stock</a>
                        <a href="<%= request.getContextPath()%>/StockServlet" class="dropdown-item">Stock</a>
                        <a href="<%= request.getContextPath()%>/TypeRetourServlet" class="dropdown-item">Type retour</a>
                        <a href="<%= request.getContextPath()%>/RetourServlet" class="dropdown-item">Retour</a>
                        <a href="<%= request.getContextPath()%>/RecommandationServlet" class="dropdown-item">Recommandation</a>
                    </div>
                </div>

            </div>
        </nav>
    </div>

    <!-- Sidebar End -->
    <div class="content">
        <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
            <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
            </a>
            <a href="#" class="sidebar-toggler flex-shrink-0">
                <i class="fa fa-bars"></i>
            </a>
            <form class="d-none d-md-flex ms-4">
                <input class="form-control border-0" type="search" placeholder="Search">
            </form>
        </nav>
        <!-- Navbar End -->