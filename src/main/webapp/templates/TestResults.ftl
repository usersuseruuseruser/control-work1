<html>
<#include "base.ftl">
<#macro title>Main page</#macro>
<#macro content>
    <@sf.authorize access="isAuthenticated()">
    <div class="container mt-5">
        <H2>Ваш результат: ${results}</H2>
    </div>
    </@sf.authorize>
</#macro>
</html>
