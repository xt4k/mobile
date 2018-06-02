<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Abonents</title>
    <script type="text/javascript" src="../js/lib/jquery-3.3.1.js" ></script>
    <script type="text/javascript" src="../js/subscriberList.js" ></script>
</head>
<body>
<h3>Subscribers</h3>
<div class="row">
    <a href="./">Home</a>
    <p>
          <input id="add"  type="button" value="add"/>
          <input id="del" type="button" value="delete"/>
          <form id="del-form" action="subscriber/del" method="Post">
                <input id="ids" name="ids" type="hidden" />
          </form>
    </p>
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
</body>
</html>