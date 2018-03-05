
<!-- Modal -->
<div class="modal fade" id="myModal${id}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">${agency.description}</h4>
            </div>
            <div class="modal-body">
                <h4>Dirección</h4>
                ${agency.address.addressLine}
                <h4>Ciudad</h4>
                <p>${agency.address.city}</p>
                <h4>Estado</h4>
                <p>${agency.address.state}</p>
                <h4>País</h4>
                <p>${agency.address.country}</p>
                <h4>Código postal</h4>
                <p>${agency.address.zipCode}</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
            </div>
        </div>
    </div>
</div>