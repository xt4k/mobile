<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Creation of Subscriber</title>
</head>
<body>
    <h3>Редактирование абонента</h3>
    <form action="/subscriber/edit" method="post">
        <div>
            <div>
                <label>ID: </label>
                <input id="id" type="text" name="id" value="${subscriber.id}" readonly />
            </div>
            <div>
                <label>Имя: </label>
                <input id="fname" type="text" name="fname"  value="${subscriber.firstName}"/>
            </div>

            <div>
                <label>Фамилия: </label>
                <input id="lname" type="text" name="lname"  value="${subscriber.lastName}"/>
            </div>

            <div>
             <c:set var="gender" value="${subscriber.gender}"/>
                <input type="radio" id="male" name="gender" value="m"
                    <c:if test="${gender eq 'MALE'}">checked</c:if> />
                <label for="gender">м</label>

                <input type="radio" id="female" name="gender" value="f"
                    <c:if test="${gender=='FEMALE'}">checked</c:if> />
                <label for="gender">ж</label>
            </div>

            <div>
                <label>Возраст: </label>
                <input id="age" type="text" name="age" pattern="[0-9.]+"  value="${subscriber.age}">
            </div>
        </div>
        <div>
            <button type="submit">Сохранить</button>
        </div>
    </form>

</body>
</html>