<%@ page import="java.util.List" %>
<%@ page import="org.projets3s5.atelier.model.Composant" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FixPC</title>
    <link rel="stylesheet" href="<%= request.getContextPath()%>/temp/front_Office/style.css">
    <link rel="icon" type="image/png" href="<%= request.getContextPath()%>/temp/front_Office/images/logo.webp">
    <script src="https://kit.fontawesome.com/3010b1eaf1.js" crossorigin="anonymous"></script>
</head>
<body>

<!-- header  -->
<header>
    <!-- menu responsive -->

    <div class="menu_toggle">
        <span></span>
    </div>

    <div class="logo">
        <p><span><img src="<%= request.getContextPath() %>/temp/front_Office/images/logo.png" alt="PcFix Logo" style="height: 80px; vertical-align: middle;"></span></p>

    </div>
    <ul class="menu">
        <li><a href="#home">Accueil</a></li>
        <li><a href="#cars">Composants</a></li>
        <li><a href="#services">Services</a></li>
        <li><a href="#contact">Contact</a></li>
    </ul>
    <a href="<%= request.getContextPath()%>/temp/back_Office/pages/accueil.jsp"><button class="login_btn">ADMIN</button></a>
</header>
<!-- section Acceuil -->

<section id="home">
    <div class="left">
        <h1>Fix<span>Your PC</span> With us</h1>
        <p>
            Nous sommes votre partenaire de confiance pour tous vos besoins en réparation et informatique. Que votre ordinateur soit lent, rencontre des erreurs système ou nécessite une mise à niveau matérielle, notre équipe d'experts est là pour vous aider.
        </p>
        <a href="#">VISITEZ-NOUS</a>
    </div>
    <div class="right">
        <img src="<%= request.getContextPath()%>/temp/front_Office/images/black.jpg">
    </div>
</section>


<!-- section vehicule -->
<%
    List<Composant> composantList = (List<Composant>) request.getAttribute("composants");
%>
<section id="cars">
    <h1 class="section_title">Nos composants</h1>
    <div class="images">
        <ul>
            <% for (Composant composant : composantList) {%>
            <li class="car">
                <div>
                    <img src="<%= request.getContextPath()%>/temp/front_Office/images/composants/<%= composant.getImg()%>" alt="">
                </div>
                <span><%= composant.getNom()%></span>
            </li>
            <% } %>
        </ul>
    </div>
</section>

<!-- section services -->

<section id="services">
    <h1 class="section_title">Nos Services</h1>
    <div class="list_services">
        <div class="service">
            <i class="fa-solid fa-screwdriver-wrench"></i>
            <h3>Réparation et maintenance</h3>
            <p>Ecrans cassés, claviers endommagés, batteries usées, nettoyage de composants, réparation des cartes mères</p>
        </div>
        <div class="service">
            <i class="fa-solid fa-screwdriver-wrench"></i>
            <h3>Diagnostic et dépannage</h3>
            <p>Problème de démarrage, détection de pannes, récupération de données...</p>
        </div>
        <div class="service">
            <i class="fa-solid fa-screwdriver-wrench"></i>
            <h3>Optimisation et sécurité</h3>
            <p>Optimisation thermique, sécurisation matérielle, support pour périphériques...</p>
        </div>
    </div>
</section>


<!-- section contact -->

<section id="contact">
    <h1 class="section_title">Nos contacts</h1>
    <div class="localisation_contact_div">
        <div class="localisation">
            <h3>Our address</h3>
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10499.966567606692!2d2.285747998068967!3d48.85836977022069!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47e66e2964e34e2d%3A0x8ddca9ee380ef7e0!2sTour%20Eiffel!5e0!3m2!1sfr!2sfr!4v1644955637071!5m2!1sfr!2sfr" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
        </div>

        <div class="form_contact">
            <h3>Send messages</h3>
            <form action="#">
                <input type="text"placeholder="Name">
                <input type="email"placeholder="Email address">
                <input type="text"placeholder="Object">
                <textarea name="" id="" cols="30" rows="10" placeholder="Message"></textarea>
                <input type="submit" value="Send">
            </form>
        </div>
    </div>
</section>


<footer>
    <p> Project S3-S5 mode</p>
</footer>

<script>
    //menu responsive code JS

    var menu_toggle = document.querySelector('.menu_toggle');
    var menu = document.querySelector('.menu');
    var menu_toggle_span = document.querySelector('.menu_toggle span');

    menu_toggle.onclick = function(){
        menu_toggle.classList.toggle('active');
        menu_toggle_span.classList.toggle('active');
        menu.classList.toggle('responsive') ;
    }

</script>
</body>
</html>