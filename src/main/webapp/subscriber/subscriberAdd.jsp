<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Creation of Subscriber</title>
        <link rel="stylesheet" href="../js/lib/bootstrap-4.0.0/css/bootstrap.min.css" />
        <link rel="stylesheet" href="../js/lib/bootstrap-4.0.0/css/bootstrap-theme.min.css" />
        <script type="text/javascript" src="../js/lib/jquery-3.3.1.js" ></script>
        <script type="text/javascript" src="../js/lib/bootstrap-4.0.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col">
                    <h3>Заполните все поля для создания абонента</h3>
                </div>
            </div>
            <div class="row">
                <div class="col">
                    <a class="btn btn-link" href="/">Home</a>
                </div>
            </div>
            
            <form action="/subscriber/add" method="post">
                <div class="row">
                    <div class="col"> 
                        <table class="table table-borderless">
                            <tbody>
                                <tr>
                                    <td>
                                        <label>Имя:</label>
                                    </td>
                                    <td>
                                        <input id="fname" type="text" name="fname"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Фамилия:</label>
                                    </td>
                                    <td>
                                         <input id="lname" type="text" name="lname"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Пол:</label>
                                    </td>
                                    <td>
                                        <input type="radio" id="male" name="gender" value="m" checked>
                                        <label for="gender">м</label>

                                        <input type="radio" id="female" name="gender" value="f">
                                        <label for="gender">ж</label>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <label>Возраст:</label>
                                    </td>
                                    <td>
                                        <input id="age" type="text" name="age" pattern="[0-9.]+" >
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="col"></div>
                </div>
                <button type="submit" class="btn btn-success">Создать</button>             
            </form>
        </div>
    </body>
</html>