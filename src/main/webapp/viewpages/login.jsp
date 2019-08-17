<%@ include file="header.jsp" %>
<h1>Login</h1>

<c:if test="${not empty error}">
	<div class="error">${error}</div>
</c:if>
<c:if test="${not empty msg}">
	<div class="msg">${msg}</div>
</c:if>

<form name='login' action="login" method='POST'>
    <table>
       <tr>
          <td>Username:</td>
          <td><input type='text' id='username' name='username' value=''></td>
       </tr>
       <tr>
          <td>Password:</td>
          <td><input type='password' id='password' name='password' /></td>
       </tr>
       <tr>
          <td><input name="submit" type="submit" value="submit" /></td>
       </tr>
    </table>
    <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>
</form>
<%@ include file="footer.jsp" %>