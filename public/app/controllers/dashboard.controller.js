app.controller('dashboardController', ['$scope', 'userFactory', DashboardController]);

function DashboardController ($scope, userFactory) {
    var dashboard = this;

    dashboard.register = function () {
    var patt = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{8,}$");
    if (patt.test(dashboard.password)) {
    console.log(dashboard.username);
        userFactory.register(
                    dashboard.username,
                    dashboard.password,
                    function (response) {
                        console.log("Success: " + JSON.stringify(response));
                    },
                    function (response) {
                        console.log("Error: " + JSON.stringify(response));
                    }
                )
    } else {
        toastr.error('Your password should be 8 characters long and contain at least 1 uppercase, 1 lowercase and 1 number.', 'Invalid password');
    }
    }
}