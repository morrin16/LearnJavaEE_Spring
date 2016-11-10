<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Gameboard</title>

    <script>
        function go(row, col)
        {
            var obj = document.gameboardForm;
            obj.row.value = row;
            obj.col.value = col;
            obj.submit();
        }
    </script>

    <style type="text/css">
        <!-- table {border: 1px solid; align: center;} -->
        td {width: 100px; height: 100px; font-size: 80px; text-align: center; empty-cells: show;}
        #leftcol {float: left; width: 20%; text-align: center;}
        #centercol {float: left; width: 50%; text-align: center; align: center;}
        #rightcol {float: left; width: 20%; text-align: center;}
    </style>
</head>

<body>




<div id="leftcol">
    <h1>Your wins:</h1>
    <h2>${yourWins}</h2>
</div>

<div id="centercol">

    <h1>Make your move</h1>
    <h6>(test version 2.1, <c:if test="${gameover}">gameover = ${gameover}</c:if>)</h6>

    <form name="gameboardForm" action="gameboard.go" method="post">
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
        <input type="hidden" name="yourWins" value="${yourWins}"/>
        <input type="hidden" name="AIWins" value="${AIWins}"/>
        <input type="hidden" name="row"/>
        <input type="hidden" name="col"/>
    </form>

    <form name="restartForm" action="gameboard.go" method="get">
        <input type="submit" value="Restart game"/>
        <input type="hidden" name="yourWins" value="${yourWins}"/>
        <input type="hidden" name="AIWins" value="${AIWins}"/>
    </form>

</div>



<div id="rightcol">
    <h1>AI wins:</h1>
    <h2>${AIWins}</h2>
</div>

</body>
</html>

