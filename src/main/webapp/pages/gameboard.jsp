<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Gameboard</title>

    <script>
        function go(row, col) {
            var obj = document.gameboardForm;
            obj.row.value = row;
            obj.col.value = col;
            obj.gameover.value = false;
            obj.submit();
        }
    </script>

    <style type="text/css">
        body {
            text-align: center;
        }

        td {
            width: 100px;
            height: 100px;
            font-size: 80px;
            empty-cells: show;
            text-align: center;
        }

        #leftcol {
            float: left;
            width: 20%;
        }

        #centercol {
            float: left;
            width: 60%;
            align: center;
        }

        #rightcol {
            float: left;
            width: 20%;
        }
    </style>

</head>


<body>

<h1>Make your move</h1>
<h6>Version 3.2</h6>

<div id="leftcol">
    <h1>Your wins:</h1>
    <h2>${wins}</h2>
</div>

<div id="centercol">

    <form name="gameboardForm" action="gameboard" method="post">
        <table border="1" align="center"
               <c:if test="${gameover}">onmousedown="return false" onselectstart="return false"</c:if>>
            <tr>
                <td <c:if test="${gameboard.charAt(0) == 32 && !gameover}"> onclick="go(0, 0)"</c:if>>
                    ${gameboard.charAt(0)}
                </td>
                <td <c:if test="${gameboard.charAt(1) == 32 && !gameover}"> onclick="go(0, 1)"</c:if>>
                    ${gameboard.charAt(1)}
                </td>
                <td <c:if test="${gameboard.charAt(2) == 32 && !gameover}"> onclick="go(0, 2)"</c:if>>
                    ${gameboard.charAt(2)}
                </td>
            </tr>
            <tr>
                <td <c:if test="${gameboard.charAt(3) == 32 && !gameover}"> onclick="go(1, 0)"</c:if>>
                    ${gameboard.charAt(3)}
                </td>
                <td <c:if test="${gameboard.charAt(4) == 32 && !gameover}"> onclick="go(1, 1)"</c:if>>
                    ${gameboard.charAt(4)}
                </td>
                <td <c:if test="${gameboard.charAt(5) == 32 && !gameover}"> onclick="go(1, 2)"</c:if>>
                    ${gameboard.charAt(5)}
                </td>
            </tr>
            <tr>
                <td <c:if test="${gameboard.charAt(6) == 32 && !gameover}"> onclick="go(2, 0)"</c:if>>
                    ${gameboard.charAt(6)}
                </td>
                <td <c:if test="${gameboard.charAt(7) == 32 && !gameover}"> onclick="go(2, 1)"</c:if>>
                    ${gameboard.charAt(7)}
                </td>
                <td <c:if test="${gameboard.charAt(8) == 32 && !gameover}"> onclick="go(2, 2)"</c:if>>
                    ${gameboard.charAt(8)}
                </td>
            </tr>
        </table>

        <input type="hidden" name="gameboard" value="${gameboard}"/>
        <input type="hidden" name="wins" value="${wins}"/>
        <input type="hidden" name="losses" value="${losses}"/>
        <input type="hidden" name="row" value="-1"/>
        <input type="hidden" name="col" value="-1"/>

        <input type="hidden" name="gameover" value="true"/>
        <p><input type="submit" value="Restart game"/></p>
    </form>

</div>

<div id="rightcol">
    <h1>Your losses:</h1>
    <h2>${losses}</h2>
</div>

<c:if test="${alertMessage != null}">
    <script>
        alert("${alertMessage}");
    </script>
</c:if>


</body>
</html>

