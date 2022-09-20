$(async function () {
    await allUsers();
});
const table = $('#fill-users');

async function allUsers() {
    table.empty()
    fetch("http://localhost:8080/admin/users")
        .then(res => res.json())
        .then(data => {
            data.forEach(user => {
                let tableWithUsers = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.surname}</td>
                            <td>${user.username}</td>
                            <td>${user.age}</td>
                            <td>${user.roles.map(role => " " + role.name.substring(5))}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonEdit"
                                data-action="edit" data-id="${user.id}" data-target="#editModal">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${user.id}" data-target="#deleteModal">Delete</button>
                            </td>
                        </tr>)`;
                table.append(tableWithUsers);
            })
        })
}

