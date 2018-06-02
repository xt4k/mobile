<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Creation of Subscriber</title>
</head>
<body>
    <h3>Заполните все поля для создания абонента</h3>
    <form action="/subscriber/add" method="post">
        <div>
            <div>
                <label>Имя: </label>
                <input id="fname" type="text" name="fname"/>
            </div>

            <div>
                <label>Фамилия: </label>
                <input id="lname" type="text" name="lname"/>
            </div>

            <div>
                <input type="radio" id="male" name="gender" value="m" checked>
                <label for="gender">м</label>

                <input type="radio" id="female" name="gender" value="f">
                <label for="gender">ж</label>
            </div>

            <div>
                <label>Возраст: </label>
                <input id="age" type="text" name="age" pattern="[0-9.]+" >
            </div>
        </div>
        <div>
            <button type="submit">Создать</button>
        </div>
    </form>

</body>
</html>