$(document).ready(function () {
    // Handle form submission for login
    $('#loginForm').submit(function (event) {
        event.preventDefault();
        var username = $('#username').val();
        var password = $('#password').val();

        // Base64 encode the username and password
        var base64Credentials = btoa(username + ':' + password);
        localStorage.setItem('token', base64Credentials);

        // Make AJAX request to login endpoint
        $.ajax({
            type: 'GET',
            url: '/api/login',
            headers: {
                'Authorization': 'Basic ' + base64Credentials
            },
            success: function (data) {
                console.log('Login successful');
                console.log(localStorage.getItem('token'));

                console.log(data.roles.find((role) => 'USER' === role));
                let isUser = data.roles.find((role) => 'USER' === role);
                let isAdmin = data.roles.find((role) => 'ADMIN' === role);

                if(isUser) {
                    console.log('redirect to user');
                    window.location.href = "/user";
                } else {
                    console.log('redirect to admin');
                    window.location.href = "/admin";
                }


                // Fetch user details after successful login
                // fetchUserDetails(base64Credentials);
            },
            error: function (xhr, textStatus, errorThrown) {
                console.error('Login failed:', textStatus);
            }
        });
    });

    // Function to fetch user details
    function fetchUserDetails(credentials) {
        // Make AJAX request to fetch user details
        $.ajax({
            type: 'GET',
            url: '/api/login',
            headers: {
                'Authorization': 'Basic ' + credentials
            },
            success: function (data) {
                console.log('User details:', data);
                // Display user details on the page
                $('#userDetails').html('<p>Username: ' + data.username + '</p>');
            },
            error: function (xhr, textStatus, errorThrown) {
                console.error('Failed to fetch user details:', textStatus);
            }
        });
    }
    $("#logoutButton").click(function(){
        localStorage.clear(); // Удаляем все данные из localStorage
        window.location.href = "/"; // Перенаправляем на главную страницу
    });

    // Показать модальное окно при нажатии на кнопку "Create"
    $("#createButton").click(function(){
        $("#createUserModal").modal("show");
    });

    // Обработчик отправки формы
    $("#createUserForm").submit(function(event){
        event.preventDefault(); // Предотвратить отправку формы по умолчанию

        // Получить данные из формы
        var firstName = $("#firstName").val();
        var lastName = $("#lastName").val();
        var userName = $("#userName").val();
        var password = $("#password").val();
        var roles = $("#roles").val();


        // Создать объект пользователя
        var userData = {
            firstName: firstName,
            lastName: lastName,
            userName: userName,
            password: password,
            roles: roles
        };

        // Отправить POST-запрос
        $.ajax({
            type: "POST",
            url: "/api/admin/users",
            contentType: "application/json",
            data: JSON.stringify(userData),
            success: function(response) {
                $("#createUserModal").modal("hide"); // Закрыть модальное окно после успешного создания пользователя
                location.reload(); // Обновить страницу после создания пользователя
            },
            error: function(xhr, status, error) {
                alert("Error creating user: " + xhr.responseText);
            }
        });
    });

    $('#close_btn').click(function() {
        $('#editUserModal').modal('hide');
    });
    $('#X_btn').click(function() {
        $('#editUserModal').modal('hide');
    });

    // Обработчик нажатия на кнопку сохранения изменений
    $('#saveChangesBtn').click(function() {
        // Формируем объект с данными пользователя из полей формы
        var userData = {
            id: $('#editId').val(),
            firstName: $('#editFirstName').val(),
            lastName: $('#editLastName').val(),
            userName: $('#editUsername').val(),
            password: $('#editPassword').val(),
            roles: $('#editRoles').val() // Преобразуем строку ролей в массив
        };

        // Отправляем PUT запрос на сервер для обновления данных пользователя
        $.ajax({
            url: '/api/admin/users/' + $('#editId').val(),
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(userData),
            success: function(response) {
                // Здесь можно добавить код для обновления данных в таблице или выполнить другие действия
                // Например, можно обновить строки в таблице или закрыть модальное окно
                $('#editUserModal').modal('hide');
                location.reload(); // Обновить страницу после создания пользователя
            },
            error: function(xhr, status, error) {
                // Здесь можно добавить код для обработки ошибок при отправке запроса
                console.error(error);
            }
        });
    });

    $('#deleteBtn').click(function() {
        $.ajax({
            url: '/api/admin/users/' + $('#editId').val(),
            type: 'DELETE',
            contentType: 'application/json',
            success: function(response) {
                location.reload(); // Обновить страницу после создания пользователя
            },
            error: function(xhr, status, error) {
                // Здесь можно добавить код для обработки ошибок при отправке запроса
                console.error(error);
            }
        });
    });


});



