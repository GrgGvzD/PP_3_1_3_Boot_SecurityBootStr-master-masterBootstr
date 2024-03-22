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
});
