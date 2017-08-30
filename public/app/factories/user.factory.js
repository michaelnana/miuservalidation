app.factory('userFactory', ['$http', UserFactory]);

function UserFactory ($http) {
    var register = function (username, password, success, failure) {
        $http({
            method: 'POST',
            url: '/users',
            data: {username: username, password: password}
        }).then(success, failure);
    }

    return {
        register: register
    }
}