$(document).ready(function(){

    const guardarId = "guardar";
    const limpiarId = "limpiar";
    const mensajeError = "error-global";
    const mensajeExito = "exito-global";
    const HEADER_MENSAJE_ERROR = "X-ERROR";
    const nombreId = "nombre";
    const montoId = "monto";
    const descuentoId = "descuento";

    function elemento(id){
        return $("#"+id);
    }

    function mostrarMensajesError(mensajeDiv){
        mensajeDiv.show();
        return new Promise((resolve,reject) => {setTimeout(function(){
            mensajeDiv.hide();
            resolve(1);
        },2000)});
    }

    function validarRequerido(id){
        var valor = elemento(id).val();
        if(valor == null || valor === "" || valor.trim() === ""){
            mostrarMensajesError(elemento(id+"-requerido"));
            return false;
        }
        return true;
    }
    function validarLargoTexto(id,cantidad){
        var valor = elemento(id).val();
        if(valor != null && valor.trim().length > 100){
            mostrarMensajesError(elemento(id+"-largo"));
            return false;
        }
        return true;
    }
    function validarEntero(id){
        var valor = elemento(id).val();
        if(valor != null && valor.trim() !== "" && (isNaN(valor) || !Number.isInteger(parseFloat(valor)))){
            mostrarMensajesError(elemento(id+"-entero"));
            return false;
        }
        return true;
    }

    function validarForm(){
        var validacion = true;
        validacion = validacion && validarRequerido(nombreId);
        validacion = validarLargoTexto(nombreId,100) && validacion;
        validacion = validarRequerido(montoId) && validacion;
        validacion = validarEntero(montoId) && validacion;
        validacion = validarEntero(descuentoId) && validacion;
        return validacion;
    }

    function mostrarMensajeGlobal(idMensaje, mensaje){
        var divMensaje = elemento(idMensaje);
        var htmlDefault = divMensaje.html();
        divMensaje.html(mensaje);
        mostrarMensajesError(divMensaje).then((success) => {divMensaje.html(htmlDefault);});
    }

    function vanillaAjaxRequest(datos,url){
        var request = new XMLHttpRequest();
        request.open('POST', url, true);
        request.setRequestHeader('Content-Type', 'application/json');
        request.onload = function() {
            if (request.status >= 200 && request.status < 400) {
                mostrarMensajeGlobal(mensajeExito,"Se ha guardado el pedido.");
                limpiar();
            }else {
                mostrarMensajeGlobal(mensajeError,"Error al guardar el pedido: "+request.getResponseHeader(HEADER_MENSAJE_ERROR));
            }
        };

        request.onerror = function() {
            if(request.status === 404){
                mostrarMensajeGlobal(mensajeError,"Fuera de servicio o servidor inalcanzable.");
            }else{
                mostrarMensajeGlobal(mensajeError,"Error al guardar el pedido: "+request.getResponseHeader(HEADER_MENSAJE_ERROR));
            }
        };
        request.send(datos);
    }

    function guardarForm(){
        if(validarForm()){
            var datos = '{"name":"'+elemento(nombreId).val()+'","amount":"'+elemento(montoId).val()+'","discount":"'+elemento(descuentoId).val()+'"}';
            vanillaAjaxRequest(datos,"http://localhost:8080/pedido/guardar");
        }else{
            return false;
        }
        return true;
    }

    function limpiarElemento(id){
        elemento(id).val("");
    }

    function limpiar(){
        limpiarElemento(nombreId);
        limpiarElemento(montoId);
        limpiarElemento(descuentoId);
    }

    elemento(guardarId).click(guardarForm);
    elemento(limpiarId).click(limpiar);
});