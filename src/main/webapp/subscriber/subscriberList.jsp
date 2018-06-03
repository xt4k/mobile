<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Abonents</title>
    <link rel="stylesheet" href="../js/lib/bootstrap-4.0.0/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../js/lib/bootstrap-4.0.0/css/bootstrap-theme.min.css" />
    <script type="text/javascript" src="../js/lib/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="../js/lib/bootstrap-4.0.0/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../js/subscriberList.js" ></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col">
            <h3>Subscribers</h3>
        </div>
    </div>
    <div class="row">
        <a class="btn btn-link" href="/">Home</a>
    </div>
    <div class="row">
        <div class="btn-group">
            <div>
                <input id="add" class="btn btn-success" type="button" value="add"/>
                <input id="del" class="btn btn-danger" type="button" value="delete"/>
                <form id="del-form" action="subscriber/del" method="Post">
                    <input id="ids" name="ids" type="hidden" />
                </form>
            </div>
        </div>
    </div>
    <div class="row">
        <div>
                <!--<c:if test="${not empty subscribers}">-->
                                <table class="table table-hover table-bordered table-striped"  border="1" cellpadding="8" cellspacing="0">
                                    <thead style="background-color: #dff0d8;">
                                    <%--<thead>--%>
                                        <tr>
                                            <th></th>
                                            <th>id</th>
                                            <th>Имя</th>
                                            <th>Фамилия</th>
                                            <th>Возраст</th>
                                            <th>Пол</th>
                                        </tr>
                                    </thead>
                                    <c:forEach items="${subscribers}" var="subscriber">
                                        <tr>
                                            <td>
                                                <input type="checkbox" id=${subscriber.id} name="selCheckBox"/>
                                            </td>
                                            <td>
                                                <a class="link" href="subscriber/edit?id=${subscriber.id}" name="contact-edit-id" id="contact-edit-id" title="Редактировать контакт">${subscriber.id}</a>
                                                                                         </td>
                                            <td>${subscriber.firstName}</td>
                                            <td>${subscriber.lastName}</td>
                                            <td>${subscriber.age}</td>
                                            <td>${subscriber.gender.ru}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                           <!-- </c:if> -->
        </div>
    </div>
</div>
</body>
</html>