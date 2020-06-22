<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c"
          uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Bac à Sable</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
              integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
              crossorigin="anonymous">
    </head>
    
    <body>
        <br/>
        <p class="display-1 text-center">Bac à Sable.</p><br/>
        <br/>
        <p class="h2 text-center">Agents</p><br/>
        <table class="table table-striped"
               style="width: 50%; margin: auto;">
            <thead class="thead-dark">
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>Prénom</th>
                </tr>
            </thead>
            <tbody>
                <%--@elvariable id="agents" type="java.util.List<fr.gouv.rie.e2.application.bacasable.metier.Agent>"--%>
                <c:forEach var="agent"
                           items="${agents}">
                    <tr>
                        <td><c:out value="${agent.id}"/></td>
                        <td><c:out value="${agent.nom}"/></td>
                        <td><c:out value="${agent.prenom}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <br/>
        <p class="h2 text-center">Projets</p><br/>
        <table class="table table-striped"
               style="width: 50%; margin: auto;">
            <thead class="thead-dark">
                <tr>
                    <th>Id</th>
                    <th>Nom</th>
                    <th>MOA</th>
                    <th>AMOA</th>
                </tr>
            </thead>
            <tbody>
                <%--@elvariable id="projets" type="java.util.List<fr.gouv.rie.e2.application.bacasable.metier.Projet>"--%>
                <c:forEach var="projet"
                           items="${projets}">
                    <tr>
                        <td><c:out value="${projet.id}"/></td>
                        <td><c:out value="${projet.nom}"/></td>
                        <td><c:out value="${projet.moa.nom}"/> <c:out value="${projet.moa.prenom}"/></td>
                        <td><c:out value="${projet.amoa.nom}"/> <c:out value="${projet.amoa.prenom}"/></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>