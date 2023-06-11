// Example starter JavaScript for disabling form submissions if there are invalid fields
(function () {
    'use strict'

    // Fetch all the forms we want to apply custom Bootstrap validation styles to
    var forms = document.querySelectorAll('.needs-validation')

    // Loop over them and prevent submission
    Array.prototype.slice.call(forms)
        .forEach(function (form) {
            form.addEventListener('submit', function (event) {
                if (!form.checkValidity()) {
                    event.preventDefault()
                    event.stopPropagation()
                }

                form.classList.add('was-validated')
            }, false)
        })
})()

let a = 1;
function addContacto(){
    a++;

    var div = document.createElement('div');
    div.setAttribute('class', 'form-inline');
    div.innerHTML = `
            <div class="row g-3 mt-2 mb-2">
                <div class="col-md-3">
                   <label for="apellidoContacto${a}" class="col-form-label">Apellido</label>
                   <input type="text" class="form-control" placeholder="Apellido" aria-label="Apellido" name="apellidoContacto${a}" required>
                </div>
                <div class="col-md-3">
                   <label for="nombreContacto${a}" class="col-form-label">Nombre</label>
                   <input type="text" class="form-control" placeholder="Nombre" aria-label="Nombre" name="nombreContacto${a}" required>
                </div>
                <div class="col-md-3">
                   <label for="emailContacto${a}" class="col-form-label">Email</label>
                   <input type="email" class="form-control" placeholder="mail@ejemplo.com" aria-label="Email" name="emailContacto${a}" required>
                </div>
                <div class="col-md-3">
                   <label for="telefonoContacto${a}" class="col-form-label">Telefono</label>
                   <input type="phone" class="form-control" placeholder="1166889977" aria-label="Phone" name="telefonoContacto${a}" maxlength="10" required>
                </div>
            </div>`
    document.getElementById('contactos').appendChild(div);document.getElementById('contactos').appendChild(div);
}
