<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'agency.label', default: 'Agency')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <g:if test="${flash.message}">
            <div class="errors" role="status">${flash.message}</div>
        </g:if>
        <div id="list-agency" class="container" role="main">
            <g:form action="agencias">
                <div class="row">

                <div class="col-md-6 col-md-offset-3" >
                    <div class="form-group">
                        <label>Dirección:</label>
                        <input class="form-control" required placeholder="Dirección" id="address" name="address" type="text">
                    </div>
                    <div class="form-group">
                        <label for="paymentMethod">Medio de pago:</label>
                        <g:select class="form-control" id="paymentMethod"  name="paymentSelect" from="${paymentMethodList}" optionKey="${{it.paymentId}}" />
                    </div>

                    <div class="form-group">
                        <label for="distance">Distancia en metros:</label>
                        <input id="distance" class="form-control" required placeholder="Distancia" name="distance" type="number">
                    </div>

                    <g:submitButton name="buscarAgencias" class="btn-primary"
                                        value="${message(code: 'default.button.search.label', default: 'Buscar')}" />

                </div>
                </div>
            </g:form>
        </div>
    </body>
</html>